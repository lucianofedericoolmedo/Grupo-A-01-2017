package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payments_types")
public class PaymentType extends PersistenceEntity {
	
	private static final long serialVersionUID = 6224752371785504407L;

	// Instance Variables
	private String name;
	
	private String description;

	// Instance Variables
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
