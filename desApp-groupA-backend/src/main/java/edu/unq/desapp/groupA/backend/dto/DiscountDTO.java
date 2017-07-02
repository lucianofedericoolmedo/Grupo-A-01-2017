package edu.unq.desapp.groupA.backend.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import edu.unq.desapp.groupA.backend.model.Priority;
import edu.unq.desapp.groupA.backend.model.Product;
import edu.unq.desapp.groupA.backend.model.ProductCategory;
import edu.unq.desapp.groupA.backend.utils.JSONDateDeserialize;
import edu.unq.desapp.groupA.backend.utils.JSONSimpleDateSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DiscountDTO {

	// Instance Variables
	private Product product;
	
	private ProductCategory productCategory;
	
	private Integer quantity;
	
	@JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONSimpleDateSerialize.class)
	private Date startingDate;
	
	@JsonDeserialize(using = JSONDateDeserialize.class)
    @JsonSerialize(using = JSONSimpleDateSerialize.class)
	private Date finishingDate;
	
	private Double percentagePerProductToDiscount;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;

	// Getters and Setters
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getFinishingDate() {
		return finishingDate;
	}

	public void setFinishingDate(Date finishingDate) {
		this.finishingDate = finishingDate;
	}

	public Double getPercentagePerProductToDiscount() {
		return percentagePerProductToDiscount;
	}

	public void setPercentagePerProductToDiscount(Double percentagePerProductToDiscount) {
		this.percentagePerProductToDiscount = percentagePerProductToDiscount;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
}
