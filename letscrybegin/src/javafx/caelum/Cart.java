package javafx.caelum;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Product> products;

	public Cart() {
		this.products = new ArrayList<>();
	}

	public void add(Product product) {
		products.add(product);
	}

	public void addAll(Product... products) {
		for (Product product : products) {
			this.products.add(product);
		}
	}

}
