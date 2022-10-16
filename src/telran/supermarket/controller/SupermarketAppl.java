package telran.supermarket.controller;

import java.time.LocalDate;
import java.util.Collection;

import telran.supermarket.dao.SupermarketImpl;
import telran.supermarket.model.Product;

public class SupermarketAppl {

	public static void main(String[] args) {
		SupermarketImpl superMarket = new SupermarketImpl();
		superMarket.addProduct(new Product(111, "bread", "food", "brand1", 10, LocalDate.now().minusDays(5)));
		superMarket.addProduct(new Product(222, "milk", "food", "brand1", 15, LocalDate.now().minusDays(1)));
		superMarket.addProduct(new Product(333, "fan", "electricity", "brand2", 99, LocalDate.now().plusDays(150)));
		superMarket.addProduct(new Product(444, "orange", "food", "brand3", 9, LocalDate.now().minusDays(7)));
		superMarket.addProduct(new Product(555, "lemon", "food", "brand3", 7, LocalDate.now().plusDays(10)));

		System.out.println("Quantity/ - "+superMarket.skuQuantity());
		printList(superMarket);
		System.out.println("Removed - "+superMarket.removeProduct(111));
		printList(superMarket);
		System.out.println("FindByCategory - "+superMarket.findByCategory("food"));
		System.out.println("findByBrand - "+superMarket.findByBrand("brand1"));
		System.out.println("findProductWithExpDate - "+superMarket.findProductWithExpDate());
	}

	private static void printList(SupermarketImpl superMarket) {
		for (Product product : superMarket) {
			System.out.println(product + "\t");
		}
		System.out.println();
	}

}
