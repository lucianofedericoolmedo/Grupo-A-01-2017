package edu.unq.desapp.groupA.backend.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import edu.unq.desapp.groupA.backend.utils.JSONDateDeserialize;
import edu.unq.desapp.groupA.backend.utils.JSONDateSerialize;

@Entity
@Table(name = "carts")
public class Cart extends ItemGroup<ItemCart> {

	private static final long serialVersionUID = -6570435320228105087L;

	// Instance Variables
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ItemCart> items;
	
	@ManyToOne
	private ShoppingList usedShoppingList;
	
	@JsonSerialize(using = JSONDateSerialize.class)
	@JsonDeserialize(using = JSONDateDeserialize.class)
	private Date reservationTime;
	
	// Constructors
	public Cart() {
		this.setItems(new LinkedList<ItemCart>());
	}
	
	public boolean includesProduct(Product p){
		return this.getItems().stream().anyMatch(itemC -> itemC.getProduct().equals(p));
	}
	
	// Getters and Setters
	public ShoppingList getUsedShoppingList() {
		return usedShoppingList;
	}

	public void setUsedShoppingList(ShoppingList usedShoppingList) {
		this.usedShoppingList = usedShoppingList;
	}

	public List<ItemCart> getItems() {
		return items;
	}

	public void setItems(List<ItemCart> items) {
		this.items = items;
	}

	public void setReservationTime(Date date) {
		this.reservationTime = date;
	}
	
	public Date getReservationTime() {
		return reservationTime;
	}

	// Logic
	public void addItems(ItemCart item) {
		item.setParent(this);
		super.addItems(item);
	}
	
	public List<ItemCart> checkedItems() {
		return getItems().stream().filter(item -> item.getChecked()).collect(Collectors.toList());
	}

	public Double totalValueOfCheckedItemsWithProductCategory(ProductCategory categoryForDiscount) {
		return totalValueOfItems(itemsCartWithCategory(categoryForDiscount, checkedItems()));
	}
	
	public Double totalValueOfCheckedItems() {
		return totalValueOfItems(checkedItems());
	}

	public Integer quantityOfItems() {
		return this.getItems().size();
	}

}
