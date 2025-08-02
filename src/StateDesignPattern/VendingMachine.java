package src.StateDesignPattern;

// Common behavior declared here
interface VendingMachineState {
    void selectProduct();

    void insertMoney();

    void dispenseProduct();
}

// Ready State: waiting for product selection
class ReadyState implements VendingMachineState {
    private VendingMachineContext context;

    public ReadyState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected.");
        context.setState(context.getProductSelectedState());
    }

    @Override
    public void insertMoney() {
        System.out.println("Select product before inserting money.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Select product first.");
    }
}

// Product Selected State: waiting for money insertion
class ProductSelectedState implements VendingMachineState {
    private VendingMachineContext context;

    public ProductSelectedState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void selectProduct() {
        System.out.println("Product already selected.");
    }

    @Override
    public void insertMoney() {
        System.out.println("Money inserted.");
        context.setState(context.getMoneyInsertedState());
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Insert money first.");
    }
}

// Money Inserted State: ready to dispense
class MoneyInsertedState implements VendingMachineState {
    private VendingMachineContext context;

    public MoneyInsertedState(VendingMachineContext context) {
        this.context = context;
    }

    @Override
    public void selectProduct() {
        System.out.println("Already selected a product.");
    }

    @Override
    public void insertMoney() {
        System.out.println("Money already inserted.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product dispensed.");
        context.setState(context.getReadyState());
    }
}

class VendingMachineContext {
    private VendingMachineState readyState;
    private VendingMachineState productSelectedState;
    private VendingMachineState moneyInsertedState;

    private VendingMachineState currentState;

    public VendingMachineContext() {
        readyState = new ReadyState(this);
        productSelectedState = new ProductSelectedState(this);
        moneyInsertedState = new MoneyInsertedState(this);

        currentState = readyState; // Initial state
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    public VendingMachineState getReadyState() {
        return readyState;
    }

    public VendingMachineState getProductSelectedState() {
        return productSelectedState;
    }

    public VendingMachineState getMoneyInsertedState() {
        return moneyInsertedState;
    }

    // Actions delegated to current state
    public void selectProduct() {
        currentState.selectProduct();
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }
}

public class VendingMachine {
    public static void main(String[] args) {
        VendingMachineContext machine = new VendingMachineContext();

        machine.selectProduct(); // Output: Product selected.
        machine.selectProduct(); // Output: Product already selected.

        machine.insertMoney(); // Output: Money inserted.
        machine.dispenseProduct(); // Output: Product dispensed.

        machine.dispenseProduct(); // Output: select product first

        // After dispensing, machine is ready for next transaction
        machine.insertMoney(); // Output: Select product before inserting money.

        machine.selectProduct(); // Output:Product selected.

    }

}
