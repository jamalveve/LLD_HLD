package src.Observer.Design.Pattern;

import java.util.ArrayList;
import java.util.List;

interface ObserverNew {
	void update(String availability);
}

class ProductNew {
	private List<ObserverNew> observers = new ArrayList<>();
	private String name;
	private String type;
	private String availability;

	public ProductNew(String name, String type, String availability) {
		this.name = name;
		this.type = type;
		this.availability = availability;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
		notifyObservers();
	}

	public void registerObserver(ObserverNew observer) {
		observers.add(observer);
	}

	public void removeObserver(ObserverNew observer) {
		observers.remove(observer);
	}

	private void notifyObservers() {
		for (ObserverNew observer : observers) {
			observer.update(this.availability);
		}
	}
}

class CustomerNew implements ObserverNew {
	private String customerName;

	public CustomerNew(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public void update(String availability) {
		System.out.println("Hello " + customerName + ", Product is now " + availability + "!");
	}
}

public class ProductAvailabiltyNotify {

	public static void main(String[] args) {
		ProductNew laptop = new ProductNew("Laptop", "Electronics", "Out of Stock");

		ObserverNew alice = new CustomerNew("Alice");
		ObserverNew bob = new CustomerNew("Bob");

		// Customers subscribe for availability updates
		laptop.registerObserver(alice);
		laptop.registerObserver(bob);

		// Update product availability; all customers are notified automatically
		laptop.setAvailability("Available");
		// Output:
		// Hello Alice, Product is now Available!
		// Hello Bob, Product is now Available!
	}

}
