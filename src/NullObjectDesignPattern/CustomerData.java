package src.NullObjectDesignPattern;

abstract class AbstractCustomer {
    protected String name;

    public abstract String getName();

    public abstract boolean isNil();
}

class RealCustomer extends AbstractCustomer {
    public RealCustomer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isNil() {
        return false;
    }
}

class NullCustomer extends AbstractCustomer {
    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }

    @Override
    public boolean isNil() {
        return true;
    }
}

class CustomerFactory {
    private static final String[] names = { "John", "Jane", "Alice" };

    public static AbstractCustomer getCustomer(String name) {
        for (String n : names) {
            if (n.equalsIgnoreCase(name)) {
                return new RealCustomer(name);
            }
        }
        return new NullCustomer();
    }
}

public class CustomerData {
    public static void main(String[] args) {
        AbstractCustomer customer1 = CustomerFactory.getCustomer("Alice");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");

        System.out.println(customer1.getName()); // Output: Alice
        System.out.println(customer2.getName()); // Output: Not Available in Customer Database
    }

}
