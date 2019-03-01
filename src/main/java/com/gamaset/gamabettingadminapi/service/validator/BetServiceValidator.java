package com.gamaset.gamabettingadminapi.service.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.gamaset.gamabettingadminapi.endpoint.schema.BetRequest;

@Component
public class BetServiceValidator {

	public void validateInsert(BetRequest request) {
		Objects.requireNonNull(request);
		Objects.requireNonNull(request.getBetValue(), "betValue must not be null");
		Objects.requireNonNull(request.getEvents(), "events must not be null");
		
	}

}
