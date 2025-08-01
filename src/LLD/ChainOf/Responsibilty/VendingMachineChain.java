package LLD.ChainOf.Responsibilty;

//Request class holding item name and payment
class VendingRequest {
 private String item;
 private double payment;

 public VendingRequest(String item, double payment) {
     this.item = item;
     this.payment = payment;
 }

 public String getItem() {
     return item;
 }

 public double getPayment() {
     return payment;
 }
}

//Abstract handler interface
interface VendingHandler {
 void setNextHandler(VendingHandler nextHandler);
 void handleRequest(VendingRequest request);
}

//Concrete handler: validates if item exists in the vending machine
class ValidationHandler implements VendingHandler {
 private VendingHandler nextHandler;
 private static final java.util.Set<String> availableItems = new java.util.HashSet<>(
         java.util.Arrays.asList("Soda", "Chips", "Candy"));

 public void setNextHandler(VendingHandler nextHandler) {
     this.nextHandler = nextHandler;
 }

 public void handleRequest(VendingRequest request) {
     if (!availableItems.contains(request.getItem())) {
         System.out.println("ValidationHandler: Item '" + request.getItem() + "' is not available.");
         return;
     }
     System.out.println("ValidationHandler: Item '" + request.getItem() + "' is available.");
     if (nextHandler != null) {
         nextHandler.handleRequest(request);
     }
 }
}

//Concrete handler: checks stock for the requested item
class StockCheckHandler implements VendingHandler {
 private VendingHandler nextHandler;
 private java.util.Map<String, Integer> stock = new java.util.HashMap<>();

 public StockCheckHandler() {
     stock.put("Soda", 5);
     stock.put("Chips", 0); // Out of stock example
     stock.put("Candy", 10);
 }

 public void setNextHandler(VendingHandler nextHandler) {
     this.nextHandler = nextHandler;
 }

 public void handleRequest(VendingRequest request) {
     int currentStock = stock.getOrDefault(request.getItem(), 0);
     if (currentStock <= 0) {
         System.out.println("StockCheckHandler: '" + request.getItem() + "' is out of stock.");
         return;
     }
     System.out.println("StockCheckHandler: '" + request.getItem() + "' is in stock (" + currentStock + " left).");
     if (nextHandler != null) {
         nextHandler.handleRequest(request);
     }
 }
}

//Concrete handler: processes payment
class PaymentHandler implements VendingHandler {
 private VendingHandler nextHandler;
 private static final java.util.Map<String, Double> prices = new java.util.HashMap<>();

 static {
     prices.put("Soda", 1.50);
     prices.put("Chips", 1.00);
     prices.put("Candy", 0.75);
 }

 public void setNextHandler(VendingHandler nextHandler) {
     this.nextHandler = nextHandler;
 }

 public void handleRequest(VendingRequest request) {
     double price = prices.getOrDefault(request.getItem(), Double.MAX_VALUE);
     if (request.getPayment() < price) {
         System.out.println("PaymentHandler: Insufficient payment. Item price is $" + price);
         return;
     }
     double change = request.getPayment() - price;
     System.out.println("PaymentHandler: Payment accepted. Change to return: $" + String.format("%.2f", change));
     if (nextHandler != null) {
         nextHandler.handleRequest(request);
     }
 }
}

//Concrete handler: dispenses the item
class DispenseHandler implements VendingHandler {
 public void setNextHandler(VendingHandler nextHandler) {
     // This is the last handler in the chain
 }

 public void handleRequest(VendingRequest request) {
     System.out.println("DispenseHandler: Dispensing your " + request.getItem() + ". Enjoy!");
 }
}

//Main class to set up the vending machine chain and test requests
public class VendingMachineChain {
 private VendingHandler chain;

 public VendingMachineChain() {
     // Set up chain: Validation -> Stock Check -> Payment -> Dispense
     VendingHandler validation = new ValidationHandler();
     VendingHandler stockCheck = new StockCheckHandler();
     VendingHandler payment = new PaymentHandler();
     VendingHandler dispense = new DispenseHandler();

     validation.setNextHandler(stockCheck);
     stockCheck.setNextHandler(payment);
     payment.setNextHandler(dispense);

     this.chain = validation;
 }

 public void purchaseItem(String item, double payment) {
     System.out.println("\nRequest to buy: " + item + " with payment $" + payment);
     VendingRequest request = new VendingRequest(item, payment);
     chain.handleRequest(request);
 }

 public static void main(String[] args) {
     VendingMachineChain vendingMachine = new VendingMachineChain();

     vendingMachine.purchaseItem("Soda", 2.00);
     vendingMachine.purchaseItem("Chips", 1.00);
     vendingMachine.purchaseItem("Candy", 0.50);
     vendingMachine.purchaseItem("Juice", 1.50);
 }
}
