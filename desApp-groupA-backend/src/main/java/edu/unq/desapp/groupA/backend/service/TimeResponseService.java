package edu.unq.desapp.groupA.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.TimeResponse;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.TimeResponseRepository;

@Service
public class TimeResponseService extends GenericService<TimeResponse>{

	@Autowired
	private TimeResponseRepository repository;
	
	public TimeResponseService(){
	}

	@Transactional
	public void registerResponseTime(Date reservationTime, Date responseTime) {
		TimeResponse timeResponse = new TimeResponse(reservationTime, responseTime);
		this.repository.save(timeResponse);
	}
	
	//TODO: Refac using Joda-Time
	@Transactional
	public Date averageResponseTime(){
		return new Date();
	}

	@Override
	public GenericRepository<TimeResponse> getRepository() {
		return repository;
	}

}
