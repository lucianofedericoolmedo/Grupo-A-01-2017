package edu.unq.desapp.groupA.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.model.Cart;
import edu.unq.desapp.groupA.backend.model.CashRegister;
import edu.unq.desapp.groupA.backend.model.PaymentType;
import edu.unq.desapp.groupA.backend.model.Purchase;
import edu.unq.desapp.groupA.backend.model.ShippingAddress;
import edu.unq.desapp.groupA.backend.model.UserCredential;
import edu.unq.desapp.groupA.backend.repository.GenericRepository;
import edu.unq.desapp.groupA.backend.repository.PurchaseRepository;
import edu.unq.desapp.groupA.backend.repository.pagination.PageResponse;


@Service
@Transactional
public class PurchaseService extends GenericService<Purchase> {

	@Autowired
	private PurchaseRepository repository;	

	@Autowired
	private TimeResponseService timeResponseService;

	public Purchase createPurchase(Cart cart, PaymentType paymentType){
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		this.repository.save(purchase);
		return purchase;
	}
	
	public Purchase createPurchase(Cart cart, PaymentType paymentType, Date creationDate){
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(creationDate);
		this.repository.save(purchase);
		return purchase;
	}

	public PurchaseService() { }

	public PurchaseService(PurchaseRepository repository) {
		this.repository = repository;
	}

	public List<Purchase> getPurchasesByUser(UserCredential user) {
		return this.repository.getPurchasesByUser(user);
	}

	public List<Cart> getAllCarts() {
		return this.repository.getAllCarts();
	}

	public Purchase createPurchase(Cart cart, PaymentType paymentType, ShippingAddress shippingAddress) {
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		purchase.setShippingAddress(shippingAddress);
		this.repository.save(purchase);
		return purchase;
		
	}

	public List<Purchase> getPurchases() {
		return this.repository.getPurchases();
	}

	public List<Purchase> fetchLastPurchases(Integer quantityToFetch, Long userId) {
		return repository.findLastPurchases(quantityToFetch, userId);
	}

	public List<Purchase> fetchPurchasesFrom(Date dateFromToFetch, Long userId) {
		return repository.findPurchasesFrom(dateFromToFetch, userId);
	}

	public Purchase createPurchase(Cart cart,PaymentType paymentType, CashRegister cashRegister) {
		cashRegister.removeItems(cart.totalValueOfCheckedItems().intValue());
		Purchase purchase = this.createPurchase(cart, paymentType);
		timeResponseService.registerResponseTime(cart.getReservationTime(),purchase.getCreationDate());
		return purchase;
	}

	public List<Purchase> getShippings() {
		return repository.getShippings();
	}

	@Override
	public GenericRepository<Purchase> getRepository() {
		return repository;
	}

	public PageResponse<Purchase> findPageByUserId(Integer pageNumber, Integer pageSize, Long userId) {
		return repository.findPageByUserId(pageNumber, pageSize, userId);
	}

}
