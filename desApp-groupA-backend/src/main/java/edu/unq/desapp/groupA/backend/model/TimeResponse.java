package edu.unq.desapp.groupA.backend.model;

import org.joda.time.DateTime;

public class TimeResponse {

	private DateTime reservationTime;
	private DateTime responseTime;

	public TimeResponse(DateTime reservationTime, DateTime responseTime) {
		this.setReservationTime(reservationTime);
		this.setResponseTime(responseTime);
	}

	public DateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(DateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public DateTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(DateTime responseTime) {
		this.responseTime = responseTime;
	}
	
	//TODO: Refac using Joda-Time
	public DateTime attentionTime(){
		return new DateTime();
	}

}
