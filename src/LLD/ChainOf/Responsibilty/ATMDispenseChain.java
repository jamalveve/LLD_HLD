package LLD.ChainOf.Responsibilty;

//Currency class holding the amount to dispense
class Currency {
 private int amount;

 public Currency(int amount) {
     this.amount = amount;
 }

 public int getAmount() {
     return amount;
 }
}

//Interface for handlers in the chain
interface DispenseChain {
 void setNextChain(DispenseChain nextChain);
 void dispense(Currency cur);
}

//Handler to dispense $50 notes
class Dollar50Dispenser implements DispenseChain {
 private DispenseChain nextChain;

 public void setNextChain(DispenseChain nextChain) {
     this.nextChain = nextChain;
 }

 public void dispense(Currency cur) {
     if (cur.getAmount() >= 50) {
         int num = cur.getAmount() / 50;
         int remainder = cur.getAmount() % 50;
         System.out.println("Dispensing " + num + " 50$ note(s)");
         if (remainder != 0) {
             nextChain.dispense(new Currency(remainder));
         }
     } else {
         nextChain.dispense(cur);
     }
 }
}

//Handler to dispense $20 notes
class Dollar20Dispenser implements DispenseChain {
 private DispenseChain nextChain;

 public void setNextChain(DispenseChain nextChain) {
     this.nextChain = nextChain;
 }

 public void dispense(Currency cur) {
     if (cur.getAmount() >= 20) {
         int num = cur.getAmount() / 20;
         int remainder = cur.getAmount() % 20;
         System.out.println("Dispensing " + num + " 20$ note(s)");
         if (remainder != 0) {
             nextChain.dispense(new Currency(remainder));
         }
     } else {
         nextChain.dispense(cur);
     }
 }
}

//Handler to dispense $10 notes
class Dollar10Dispenser implements DispenseChain {
 private DispenseChain nextChain;

 public void setNextChain(DispenseChain nextChain) {
     this.nextChain = nextChain;
 }

 public void dispense(Currency cur) {
     if (cur.getAmount() >= 10) {
         int num = cur.getAmount() / 10;
         int remainder = cur.getAmount() % 10;
         System.out.println("Dispensing " + num + " 10$ note(s)");
         if (remainder != 0) {
             System.out.println("Amount should be in multiples of 10.");
         }
     } else {
         System.out.println("Amount should be in multiples of 10.");
     }
 }
}

//Main class to set up and test the chain
public class ATMDispenseChain {

 private DispenseChain chain;

 public ATMDispenseChain() {
     // initialize the chain
     this.chain = new Dollar50Dispenser();
     DispenseChain c2 = new Dollar20Dispenser();
     DispenseChain c3 = new Dollar10Dispenser();

     // set the chain of responsibility
     chain.setNextChain(c2);
     c2.setNextChain(c3);
 }

 public void dispenseAmount(int amount) {
     if (amount % 10 != 0) {
         System.out.println("Amount should be in multiples of 10.");
         return;
     }
     chain.dispense(new Currency(amount));
 }

 public static void main(String[] args) {
     ATMDispenseChain atmDispenser = new ATMDispenseChain();

     System.out.println("Dispensing 130$:");
     atmDispenser.dispenseAmount(130);

     System.out.println("\nDispensing 280$:");
     atmDispenser.dispenseAmount(280);

     System.out.println("\nTrying to dispense 125$:");
     atmDispenser.dispenseAmount(125);
 }
}

