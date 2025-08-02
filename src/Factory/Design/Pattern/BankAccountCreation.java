package src.Factory.Design.Pattern;

import java.util.Scanner;

// Product Interface
interface BankAccount {
	void registerAccount();
}

// Concrete Products
class PersonalAccount implements BankAccount {
	@Override
	public void registerAccount() {
		System.out.println("Creating a personal account");
	}
}

class BusinessAccount implements BankAccount {
	@Override
	public void registerAccount() {
		System.out.println("Creating a business account");
	}
}

class CheckingAccount implements BankAccount {
	@Override
	public void registerAccount() {
		System.out.println("Creating a checking account");
	}
}

// Factory Class
class BankAccountFactory {
	public BankAccount createAccount(String accountType) {
		if (accountType == null || accountType.isEmpty())
			return null;

		switch (accountType.toUpperCase()) {
			case "P":
				return new PersonalAccount();
			case "B":
				return new BusinessAccount();
			case "C":
				return new CheckingAccount();
			default:
				throw new IllegalArgumentException("Unknown account type " + accountType);
		}
	}
}

// Client Code
public class BankAccountCreation {

	public static void main(String[] args) {
		BankAccountFactory factory = new BankAccountFactory();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter account type:\nP for Personal\nB for Business\nC for Checking");
		String input = scanner.nextLine();

		try {
			BankAccount account = factory.createAccount(input);
			account.registerAccount();
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}

		scanner.close();
	}
}
