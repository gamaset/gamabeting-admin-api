package com.gamaset.gamabettingadminapi.service;

import static com.gamaset.gamabettingadminapi.infra.log.LogEvent.create;
import static com.gamaset.gamabettingadminapi.utils.UserProfileUtils.isAdmin;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamaset.gamabettingadminapi.endpoint.schema.AgentRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.SignUpRequest;
import com.gamaset.gamabettingadminapi.endpoint.schema.UserPrinciple;
import com.gamaset.gamabettingadminapi.infra.exception.BusinessException;
import com.gamaset.gamabettingadminapi.infra.log.LogEvent;
import com.gamaset.gamabettingadminapi.model.AgentBudgetHistoryModel;
import com.gamaset.gamabettingadminapi.model.AgentModel;
import com.gamaset.gamabettingadminapi.model.ManagerModel;
import com.gamaset.gamabettingadminapi.model.UserModel;
import com.gamaset.gamabettingadminapi.repository.AgentBudgetHistoryRepository;
import com.gamaset.gamabettingadminapi.repository.AgentRepository;
import com.gamaset.gamabettingadminapi.repository.ManagerRepository;
import com.gamaset.gamabettingadminapi.security.AuthService;
import com.gamaset.gamabettingadminapi.utils.UserProfileUtils;

@Service
public class AgentService {

	private static final Logger LOG_ACTION = LogEvent.logger("ACTION");
	private static final Logger LOG_ERROR = LogEvent.logger("ERROR");

	private AuthService authService;
	private ManagerRepository managerRepository;
	private AgentRepository agentRepository;
	private AgentBudgetHistoryRepository agentBudgetHistoryRepository;

	@Autowired
	public AgentService(AuthService authService, ManagerRepository managerRepository, AgentRepository agentRepository, AgentBudgetHistoryRepository agentBudgetHistoryRepository) {
		this.authService = authService;
		this.managerRepository = managerRepository;
		this.agentRepository = agentRepository;
		this.agentBudgetHistoryRepository = agentBudgetHistoryRepository;
	}

	public List<AgentModel> list() {
		List<AgentModel> response = null;
		
		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(isAdmin(principle)) {
			response = (List<AgentModel>) agentRepository.findAll();
		}else {
			response = agentRepository.findByManagerUserId(principle.getId());
		}
		
		return response;
		
	}

	@Transactional
	public AgentModel insert(AgentRequest request) {
		// TODO: add validation component
		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserModel userCreated = authService.signUp(buildUser(request));

		ManagerModel manager = managerRepository.findByUserId(principle.getId());

		AgentModel agentModel = new AgentModel(userCreated, manager);
		agentModel.setBudget(request.getBudget());
		AgentModel agentCreated = agentRepository.save(agentModel);
		
		agentBudgetHistoryRepository.save(new AgentBudgetHistoryModel(agentCreated, request.getBudget()));
		
		return agentCreated;
	}
	
	public void addBudget(Long agentId, BigDecimal amount) {
		
	}
	
	public void subtractBudget(BigDecimal amount) {
		
		UserPrinciple principle = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		LOG_ACTION.info(create("Atualizando Budget do Agente").add("amount", amount).add("userId", principle.getId()).build());
		
		Optional<AgentModel> agentOpt = agentRepository.findByUserId(principle.getId());
		
		if(agentOpt.isPresent()) {
			AgentModel agentModel = agentOpt.get();
			if(agentModel.getBudget().compareTo(amount) >= 0) {
				agentModel.setBudget(agentModel.getBudget().subtract(amount));
				AgentModel agentUpdated = agentRepository.save(agentModel);
				agentBudgetHistoryRepository.save(new AgentBudgetHistoryModel(agentUpdated, agentUpdated.getBudget()));
			}else {
				throw new BusinessException(String.format("Orçamento insuficiente - Atual [%s] Esperado [%s]", agentModel.getBudget(), amount));
			}
		}else {
			LOG_ERROR.error(create("Agente não encontrado ").add("userId", principle.getId()).build());
			throw new BusinessException(String.format("Agente não encontrado ID %s", principle.getId()));
		}
		
	}
	
	private SignUpRequest buildUser(AgentRequest request) {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail(request.getEmail());
		signUpRequest.setName(request.getName());
		signUpRequest.setPassword(request.getPassword());
		signUpRequest.setTaxId(request.getTaxId());
		signUpRequest.setUsername(request.getUsername());
		Set<String> roles = new HashSet<>();
		roles.add("AGENT");
		signUpRequest.setRole(roles);
		return signUpRequest;
	}

}
