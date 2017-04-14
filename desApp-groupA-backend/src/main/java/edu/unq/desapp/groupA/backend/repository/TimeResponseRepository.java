package edu.unq.desapp.groupA.backend.repository;

import java.util.ArrayList;
import java.util.List;

import edu.unq.desapp.groupA.backend.model.TimeResponse;

public class TimeResponseRepository {

	private List<TimeResponse> timeResponses;

	public List<TimeResponse> getTimeResponses() {
		return timeResponses;
	}

	public void setTimeResponses(List<TimeResponse> timeResponses) {
		this.timeResponses = timeResponses;
	}

	public TimeResponseRepository(){
		this.timeResponses = new ArrayList<TimeResponse>();
	}
	
	public void save(TimeResponse timeResponse) {
		this.timeResponses.add(timeResponse);		
	}	
}
