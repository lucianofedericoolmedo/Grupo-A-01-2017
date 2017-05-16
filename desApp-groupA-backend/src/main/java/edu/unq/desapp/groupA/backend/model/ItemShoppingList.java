package edu.unq.desapp.groupA.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "item_shopping_list")
public class ItemShoppingList extends Item {

	private static final long serialVersionUID = -3644991682254866936L;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JsonBackReference
	private ShoppingList parent;

	public ShoppingList getParent() {
		return parent;
	}

	public void setParent(ShoppingList parent) {
		this.parent = parent;
	}
	
}
