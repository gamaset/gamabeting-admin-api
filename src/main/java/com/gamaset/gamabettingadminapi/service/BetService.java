package com.gamaset.gamabettingadminapi.service;

import static com.gamaset.gamabettingadminapi.model.BetStatusEnum.valueOf;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gamaset.gamabettingadminapi.endpoint.schema.BetPatchRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetResponse;
import com.gamaset.gamabettingadminapi.infra.exception.BusinessException;
import com.gamaset.gamabettingadminapi.model.BetHistoryModel;
import com.gamaset.gamabettingadminapi.model.BetModel;
import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.repository.BetHistoryRepository;
import com.gamaset.gamabettingadminapi.repository.BetRepository;
import com.gamaset.gamabettingadminapi.repository.EventRepository;
import com.gamaset.gamabettingadminapi.service.validator.BetServiceValidator;

@Service
public class BetService {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private BetServiceValidator betValidator;
	@Autowired
	private BetRepository repository;
	@Autowired
	private BetHistoryRepository betHistoryRepository;
	@Autowired
	private EventRepository eRepository;

	@Transactional
	public BetResponse createBet(BetRequest request) {
		betValidator.validateInsert(request);

		CustomerModel customer = customerService.getCustomer(request.getCustomerId());

		BetModel bet = new BetModel(request, customer);

		BetModel betCreated = repository.save(bet);
		request.getEvents().forEach(e -> {
			e.setBet(betCreated);
			eRepository.save(e);
		});

		betHistoryRepository.save(new BetHistoryModel(betCreated, betCreated.getStatus()));

		return new BetResponse(betCreated, request.getEvents());
	}

	/**
	 * Listagem de Apostas
	 * 
	 * @return
	 */
	public List<BetResponse> list() {
		List<BetResponse> response = new ArrayList<>();
		List<BetModel> betsModel = (List<BetModel>) repository.findAll();

		if (!CollectionUtils.isEmpty(betsModel)) {
			betsModel.stream().forEach(b -> {
				response.add(new BetResponse(b, null));
			});
		}

		return response;
	}

	/**
	 * Atualiza Status da aposta
	 * 
	 * @param betId
	 * @param request
	 */
	public void updateStatusBet(Long betId, BetPatchRequest request) {
		Optional<BetModel> optBet = repository.findById(betId);
		if (optBet.isPresent()) {
			optBet.get().setStatus(valueOf(request.getStatus()).get());
			BetModel betModel = repository.save(optBet.get());
			betHistoryRepository.save(new BetHistoryModel(betModel, betModel.getStatus()));
		} else {
			throw new BusinessException(String.format("Aposta não encontrada ID %s", betId));
		}
	}

	/**
	 * Busca detalhe da aposta por ID
	 * 
	 * @param betId
	 * @return
	 */
	public BetResponse getBet(Long betId) {
		BetResponse bet = null;
		Optional<BetModel> optBet = repository.findById(betId);
		if (optBet.isPresent()) {
			bet = new BetResponse(optBet.get(), eRepository.findByBetId(betId));
		} else {
			throw new BusinessException(String.format("Aposta não encontrada ID %s", betId));
		}
		return bet;
	}
}
