package com.gamaset.gamabettingadminapi.endpoint.schema;

import com.gamaset.gamabettingadminapi.model.EventModel;

public class EventBet {

	private String competition;
	private String eventDescription;
	private String eventDate;
	private String marketDescription;
	private Double odd;

	public EventBet(EventModel model) {
		this.eventDescription = model.getDescription();
		this.competition = model.getCompetition().getDescription();
		this.marketDescription = model.getMarket().getDescription();
		this.odd = model.getMarket().getOdd();
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public String getMarketDescription() {
		return marketDescription;
	}

	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}

	public Double getOdd() {
		return odd;
	}

	public void setOdd(Double odd) {
		this.odd = odd;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

}
