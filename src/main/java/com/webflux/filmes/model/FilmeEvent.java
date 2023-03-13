package com.webflux.filmes.model;

import java.util.Objects;

public class FilmeEvent {
	
	private Long eventId;
	private String eventType;
	
	
	
	public FilmeEvent(Long eventId, String eventType) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
	}
	
	public FilmeEvent() {
		super();
	}



	@Override
	public int hashCode() {
		return Objects.hash(eventId, eventType);
	}

	public Long getEventId() {
		return eventId;
	}



	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}



	public String getEventType() {
		return eventType;
	}



	public void setEventType(String eventType) {
		this.eventType = eventType;
	}




	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmeEvent other = (FilmeEvent) obj;
		return Objects.equals(eventId, other.eventId) && Objects.equals(eventType, other.eventType);
	}





	@Override
	public String toString() {
		return "FilmeEvent [eventId=" + eventId + ", eventType=" + eventType + "]";
	}
	
	
	
	

}
