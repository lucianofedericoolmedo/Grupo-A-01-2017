package edu.unq.desapp.groupA.backend.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unq.desapp.groupA.backend.dto.PurchaseDTO;
import edu.unq.desapp.groupA.backend.dto.PurchaseWithItemsDTO;
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

	@Transactional
	public Purchase createPurchase(Cart cart, PaymentType paymentType){
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		this.repository.save(purchase);
		return purchase;
	}
	
	@Transactional
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

	@Transactional
	public List<Purchase> getPurchasesByUser(UserCredential user) {
		return this.repository.getPurchasesByUser(user);
	}

	@Transactional
	public List<Cart> getAllCarts() {
		return this.repository.getAllCarts();
	}

	@Transactional
	public Purchase createPurchase(Cart cart, PaymentType paymentType, ShippingAddress shippingAddress) {
		Purchase purchase = new Purchase();
		purchase.setCart(cart);
		purchase.setPayment(paymentType);
		purchase.setCreationDate(new Date());
		purchase.setShippingAddress(shippingAddress);
		this.repository.save(purchase);
		return purchase;
		
	}

	@Transactional
	public List<Purchase> getPurchases() {
		return this.repository.getPurchases();
	}

	@Transactional
	public List<Purchase> fetchLastPurchases(Integer quantityToFetch, Long userId) {
		return repository.findLastPurchases(quantityToFetch, userId);
	}

	@Transactional
	public List<Purchase> fetchPurchasesFrom(Date dateFromToFetch, Long userId) {
		return repository.findPurchasesFrom(dateFromToFetch, userId);
	}

	@Transactional
	public Purchase createPurchase(Cart cart,PaymentType paymentType, CashRegister cashRegister) {
		cashRegister.removeItems(cart.totalValueOfCheckedItems().intValue());
		Purchase purchase = this.createPurchase(cart, paymentType);
		timeResponseService.registerResponseTime(cart.getReservationTime(),purchase.getCreationDate());
		return purchase;
	}

	@Transactional
	public List<Purchase> getShippings() {
		return repository.getShippings();
	}

	@Override
	public GenericRepository<Purchase> getRepository() {
		return repository;
	}

	@Transactional
	public PageResponse<PurchaseDTO> findPageByUserId(Integer pageNumber, Integer pageSize, Long userId) {
		PageResponse<Purchase> purchasePageResponse = repository.findPageByUserId(pageNumber, pageSize, userId);
		return new PageResponse<PurchaseDTO>(toPurchaseDto(purchasePageResponse.getResult()), purchasePageResponse.getTotalSize());
	}

	@Transactional
	public PurchaseWithItemsDTO findDtoById(Long id) {
		Purchase fetchedPurchase = super.find(id);
		return new PurchaseWithItemsDTO(fetchedPurchase);
	}
	
	@Transactional
	private List<PurchaseDTO> toPurchaseDto(List<Purchase> purchases) {
		return purchases.stream().map(p -> new PurchaseDTO(p)).collect(Collectors.toList());
	}

}
