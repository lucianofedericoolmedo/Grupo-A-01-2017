package edu.unq.desapp.groupA.backend.model;

import java.util.Date;

public class TimeResponse {

	private Date reservationTime;
	private Date responseTime;

	public TimeResponse(Date reservationTime, Date responseTime) {
		this.setReservationTime(reservationTime);
		this.setResponseTime(responseTime);
	}

	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	//TODO: Refac using Joda-Time
	public Date attentionTime(){
		return new Date();
	}

}
