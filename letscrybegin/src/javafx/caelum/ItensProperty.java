package javafx.caelum;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItensProperty {
	private SimpleStringProperty name;
	private SimpleDoubleProperty price;

	public ItensProperty(String name, Double cost) {
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(cost);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public Double getPrice() {
		return this.price.get();
	}

	public void setPrice(Double price) {
		this.price.set(price);
	}

}
