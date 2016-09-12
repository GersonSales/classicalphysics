package javafx.caelum;

public class Product {
	private String name;
	private Double price;

	public Product() {
		this("NoNamedProduct", 0d);
	}

	public Product(String name, Double price) {
		setName(name);
		setPrice(price);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
