package edu.unq.desapp.groupA.backend.service;

import java.util.Date;

import org.joda.time.DateTime;

import edu.unq.desapp.groupA.backend.model.TimeResponse;
import edu.unq.desapp.groupA.backend.repository.TimeResponseRepository;

public class TimeResponseService {
	
	private TimeResponseRepository repository;
	
	public TimeResponseService(){
		this.repository = new TimeResponseRepository();
	}

	public void registerResponseTime(DateTime reservationTime, DateTime responseTime) {
		TimeResponse timeResponse = new TimeResponse(reservationTime, responseTime);
		this.repository.save(timeResponse);
	}
	
	//TODO: Refac using Joda-Time
	public Date averageResponseTime(){
		return new Date();
	}
	
	

}
