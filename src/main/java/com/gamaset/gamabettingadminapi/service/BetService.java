package com.gamaset.gamabettingadminapi.service;

import static com.gamaset.gamabettingadminapi.infra.log.LogEvent.create;
import static com.gamaset.gamabettingadminapi.model.BetStatusEnum.valueOf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.gamaset.gamabettingadminapi.endpoint.schema.BetPatchRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.BetResponse;
import com.gamaset.gamabettingadminapi.endpoint.schema.UserPrinciple;
import com.gamaset.gamabettingadminapi.infra.exception.BusinessException;
import com.gamaset.gamabettingadminapi.infra.log.LogEvent;
import com.gamaset.gamabettingadminapi.model.BetHistoryModel;
import com.gamaset.gamabettingadminapi.model.BetModel;
import com.gamaset.gamabettingadminapi.model.BetStatusEnum;
import com.gamaset.gamabettingadminapi.model.CustomerModel;
import com.gamaset.gamabettingadminapi.model.RoleName;
import com.gamaset.gamabettingadminapi.repository.BetHistoryRepository;
import com.gamaset.gamabettingadminapi.repository.BetRepository;
import com.gamaset.gamabettingadminapi.repository.CustomerRepository;
import com.gamaset.gamabettingadminapi.repository.EventRepository;
import com.gamaset.gamabettingadminapi.service.validator.BetServiceValidator;

@Service
public class BetService {

	private static final Logger LOG_ACTION = LogEvent.logger("ACTION");
	private static final Logger LOG_ERROR = LogEvent.logger("ERROR");

	@Autowired
	private AgentService agentService;
	@Autowired
	private BetServiceValidator betValidator;
	@Autowired
	private BetRepository repository;
	@Autowired
	private BetHistoryRepository betHistoryRepository;
	@Autowired
	private EventRepository eRepository;
	@Autowired
	private CustomerRepository cRepository;

	@Value("${betwin.config.commissionPercent}")
	private BigDecimal commissionPercentConfig;

	@Transactional
	public BetResponse createBet(BetRequest request) {
		betValidator.validateInsert(request);

		UserPrinciple priciple = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CustomerModel customer = cRepository.findByUserId(priciple.getId());

		BetModel bet = new BetModel(request, customer, commissionPercentConfig);

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

		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		LOG_ACTION.info(create("Listando Apostas").add("userId", principle.getId()).add("roles", principle.getAuthorities()).build());

		List<BetModel> betsModel = null;
		for (GrantedAuthority authority : principle.getAuthorities()) {
			if(authority.getAuthority().equals(RoleName.ROLE_ADMIN.name()) || authority.getAuthority().equals(RoleName.ROLE_MANAGER.name())) {
				betsModel = (List<BetModel>) repository.findAll();
				break;
			}
		}
		
		
		if (CollectionUtils.isEmpty(betsModel)) {
			betsModel = repository.findByCustomerAgentUserId(principle.getId());
		}
		
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
	@Transactional
	public void updateStatusBet(Long betId, BetPatchRequest request) {

		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		LOG_ACTION.info(create("Atualizando Status da Aposta").add("betId", betId).add("userId", principle.getId()).build());

		Optional<BetModel> betOpt = repository.findByIdAndCustomerAgentUserId(betId, principle.getId());

		if (betOpt.isPresent()) {
			BetModel betEntity = betOpt.get();
			betEntity.setStatus(valueOf(request.getStatus()).get());
			BetModel betModel = repository.save(betEntity);
			betHistoryRepository.save(new BetHistoryModel(betModel, betModel.getStatus()));

			if (betModel.getStatus().equals(BetStatusEnum.WON) || betModel.getStatus().equals(BetStatusEnum.LOSE)) {
				agentService.subtractBudget(betModel.getBetValue());
			}

		} else {
			LOG_ERROR.error(
					create("Aposta não encontrada").add("betId", betId).add("userId", principle.getId()).build());
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
		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BetResponse bet = null;
		Optional<BetModel> optBet = repository.findById(betId);
		if (optBet.isPresent()) {
			bet = new BetResponse(optBet.get(), eRepository.findByBetId(betId));
		} else {
			LOG_ERROR.error(
					create("Aposta não encontrada").add("betId", betId).add("userId", principle.getId()).build());
			throw new BusinessException(String.format("Aposta não encontrada ID %s", betId));
		}
		return bet;
	}
}
