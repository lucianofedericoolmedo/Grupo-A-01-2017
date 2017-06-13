package edu.unq.desapp.groupA.backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "products_categories")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCategory extends PersistenceEntity {

	private static final long serialVersionUID = 8142353411183447982L;
	
	// Instance Variables
	private String name;

	public ProductCategory() {}
	
	public ProductCategory(String name) {
		this.name = name;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
