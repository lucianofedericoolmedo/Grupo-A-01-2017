package edu.unq.desapp.groupA.backend.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.Threshold;
import edu.unq.desapp.groupA.backend.model.User;


@Repository
public class ThresholdRepository extends HibernateGenericDAO<Threshold> {

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

	@Override
	protected Class<Threshold> getDomainClass() {
		return Threshold.class;
	}

}
