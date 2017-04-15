package edu.unq.desapp.groupA.backend.service.provider;

import edu.unq.desapp.groupA.backend.repository.CartRepository;
import edu.unq.desapp.groupA.backend.repository.CashRegisterRepository;
import edu.unq.desapp.groupA.backend.repository.ItemCartRepository;
import edu.unq.desapp.groupA.backend.repository.PaymentTypeRepository;
import edu.unq.desapp.groupA.backend.repository.ProductRepository;
import edu.unq.desapp.groupA.backend.repository.ProductThresoldRepository;
import edu.unq.desapp.groupA.backend.repository.PurchaseRepository;
import edu.unq.desapp.groupA.backend.repository.UserProfileRepository;
import edu.unq.desapp.groupA.backend.repository.UserRepository;
import edu.unq.desapp.groupA.backend.service.BalancerService;
import edu.unq.desapp.groupA.backend.service.CartService;
import edu.unq.desapp.groupA.backend.service.CashRegisterService;
import edu.unq.desapp.groupA.backend.service.ComprandoALoLocoService;
import edu.unq.desapp.groupA.backend.service.ItemCartService;
import edu.unq.desapp.groupA.backend.service.ItemShoppingListService;
import edu.unq.desapp.groupA.backend.service.PaymentTypeService;
import edu.unq.desapp.groupA.backend.service.ProductService;
import edu.unq.desapp.groupA.backend.service.ProductThresoldService;
import edu.unq.desapp.groupA.backend.service.PurchaseService;
import edu.unq.desapp.groupA.backend.service.ShoppingListService;
import edu.unq.desapp.groupA.backend.service.TimeResponseService;
import edu.unq.desapp.groupA.backend.service.UserProfileService;
import edu.unq.desapp.groupA.backend.service.UserService;

public class ComprandoALoLocoProvider {

	public static ComprandoALoLocoService settingComprandoALoLocoService(){
		ComprandoALoLocoService comprandoALoLocoService = new ComprandoALoLocoService();
		comprandoALoLocoService.setBalancerService(new BalancerService());
		
		comprandoALoLocoService.setCashRegisterService(new CashRegisterService(new CashRegisterRepository()));
		
		comprandoALoLocoService.setUserService(new UserService(new UserRepository()));
		comprandoALoLocoService.setCartService(new CartService(new CartRepository(), (long) 0));
		comprandoALoLocoService.setItemCartService(new ItemCartService(new ItemCartRepository()));
		comprandoALoLocoService.setProductService(new ProductService(new ProductRepository()));
		comprandoALoLocoService.setPaymentTypeService(new PaymentTypeService( new PaymentTypeRepository()));
		comprandoALoLocoService.setPurchaseService(new PurchaseService( new PurchaseRepository()));
		comprandoALoLocoService.setProductThresoldService(new ProductThresoldService(new ProductThresoldRepository()));
		comprandoALoLocoService.setUserProfileService(new UserProfileService( new UserProfileRepository()));
		
		//CAMBIAR ESTOS SETTERS
		comprandoALoLocoService.setShoppingListService(new ShoppingListService());
		comprandoALoLocoService.setItemShoppingListService(new ItemShoppingListService());
		comprandoALoLocoService.setTimeResponseService(new TimeResponseService());
		comprandoALoLocoService.createCashRegisters(2);
		return comprandoALoLocoService;
	}
	
}
