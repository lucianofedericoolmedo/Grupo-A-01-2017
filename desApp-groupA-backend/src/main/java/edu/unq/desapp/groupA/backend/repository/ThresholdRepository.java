package edu.unq.desapp.groupA.backend.repository;

import java.util.List;
import java.util.stream.Collectors;

import edu.unq.desapp.groupA.backend.model.Threshold;
import edu.unq.desapp.groupA.backend.model.User;

public class ThresholdRepository {

	private List<Threshold> thresholds;
	
	public List<Threshold> findEnabledThresholdOfUser(User user) {
		return this.thresholds.stream().filter(t -> t.getUser().equals(user)).collect(Collectors.toList());
	}

	public List<Threshold> getThresholds() {
		return thresholds;
	}

	public void setThresholds(List<Threshold> thresholds) {
		this.thresholds = thresholds;
	}

}