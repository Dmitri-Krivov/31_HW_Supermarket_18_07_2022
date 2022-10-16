package telran.supermarket.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import telran.supermarket.model.Product;

public class SupermarketImpl implements SuperMarket {
	Collection<Product> products;
	int skuQuantity;

	public SupermarketImpl() {

		products = new ArrayList<>();
	}

	@Override
	public Iterator<Product> iterator() {
		return products.iterator();
	}

	@Override
	public boolean addProduct(Product product) {
		if (products == null || findByBarCode(product.getBarCode()) != null) {
			return false;
		}
		products.add(product);
		skuQuantity++;
		return true;
	}

	@Override
	public Product removeProduct(long barCode) {
		Product victim = this.findByBarCode(barCode);
		products.removeIf(bar -> bar == this.findByBarCode(barCode));
		skuQuantity--;
		return victim;
	}

	@Override
	public Product findByBarCode(long barCode) {
//		for (int i = 0; i < products.size(); i++) {
//			if (barCode == products.get(i).getBarCode()) {
//				return products.get(i);
//			}
//		}
//		return null;

			
		return  products.stream()
				.filter((n->n.getBarCode()==barCode))
				.findFirst().orElse(null);
		
		
	}

	@Override
	public Iterable<Product> findByCategory(String category) {
//		ArrayList<Product> newArrayList = new ArrayList<>();
//		for (int i = 0; i < products.size(); i++) {
//			if (category.equals(products.get(i).getCategory())) {
//				newArrayList.add(products.get(i));
//			}
//		}
//		return newArrayList;
		
		return  products.stream()
			.filter(n->n.getCategory().equals(category))
			.collect(Collectors.toList());

		
		
		
//		return findByPredicate(n->n.getCategory().equals(category));

	}
	
	
	
	
	
	

	@Override
	public Iterable<Product> findByBrand(String brand) {
//		ArrayList<Product> newArrayList = new ArrayList<>();
//		for (int i = 0; i < products.size(); i++) {
//			if (brand.equals(products.get(i).getBrand())) {
//				newArrayList.add(products.get(i));
//			}
//		}
//		return newArrayList;
		
		return products.stream()
				.filter(n->brand.equals(n.getBrand()))
				.collect(Collectors.toList());
//		
		
		
//	return findByPredicate(n->n.getBrand().equals(brand));
		
	}

	@Override
	public Iterable<Product> findProductWithExpDate() {
//		ArrayList<Product> newArrayList = new ArrayList<>();
//		for (int i = 0; i < products.size(); i++) {
//			if (LocalDate.now().isAfter(products.get(i).getExpDate())) {
//				newArrayList.add(products.get(i));
//			}
//		}
//		return newArrayList;
//		
		
		
	return	findByPredicate(n->n.getExpDate().isBefore(LocalDate.now()));
		
		
		
	}
	
	private  Iterable<Product> findByPredicate(Predicate <Product> predicate){
				List<Product> res = new ArrayList<>();
//				for (int i = 0; i < products.size(); i++) {
//					Product product = products.get(i);
//					if(predicate.test(product)) {
//						res.add(product);
//					}
//				}
//				return res;
				
			return	products.stream()
					.filter(n->predicate.test(n))
//					.filter(predicate)
					.collect(Collectors.toList());
		
	}

	@Override
	public int skuQuantity() {
		return skuQuantity;
	}

}
