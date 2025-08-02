package src.Observer.Design.Pattern;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface ProductObserver {
    void update(float newPrice);
}

// Subject class (Product)
class Product {
    private List<ProductObserver> observers = new ArrayList<>();
    private float price;

    public void addObserver(ProductObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }

    public void setPrice(float price) {
        this.price = price;
        notifyObservers(); // Notify all observers about the price change
    }

    private void notifyObservers() {
        for (ProductObserver observer : observers) {
            observer.update(price);
        }
    }
}

// Sample observer
class Customer implements ProductObserver {
    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(float newPrice) {
        System.out.println(name + ": Product price updated to " + newPrice);
    }
}

// Usage

public class ProductPriceUpdate {
    public static void main(String[] args) {
        Product laptop = new Product();
        ProductObserver alice = new Customer("Alice");
        ProductObserver bob = new Customer("Bob");
        ProductObserver chris = new Customer("Chris");

        laptop.addObserver(alice);
        laptop.addObserver(bob);
        laptop.addObserver(chris);

        laptop.setPrice(999.99f);
        // Output:
        // Alice: Product price updated to 999.99
        // Bob: Product price updated to 999.99
    }
}
