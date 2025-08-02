package src.Factory.Design.Pattern;

interface Notification {
	void notifyUser();
}

class EmailNotification implements Notification {
	@Override
	public void notifyUser() {
		System.out.println("Sending an Email Notification");
	}
}

class SMSNotification implements Notification {
	@Override
	public void notifyUser() {
		System.out.println("Sending an SMS Notification");
	}
}

class PushNotification implements Notification {
	@Override
	public void notifyUser() {
		System.out.println("Sending a Push Notification");
	}
}

class NotificationFactory {
	public Notification createNotification(String type) {
		if (type == null || type.isEmpty())
			return null;

		switch (type.toLowerCase()) {
			case "sms":
				return new SMSNotification();
			case "email":
				return new EmailNotification();
			case "push":
				return new PushNotification();
			default:
				throw new IllegalArgumentException("Unknown notification type " + type);
		}
	}
}

public class NotificationService {

	public static void main(String[] args) {
		NotificationFactory factory = new NotificationFactory();

		Notification notification1 = factory.createNotification("email");
		notification1.notifyUser(); // Output: Sending an Email Notification

		Notification notification2 = factory.createNotification("sms");
		notification2.notifyUser(); // Output: Sending an SMS Notification

		Notification notification3 = factory.createNotification("push");
		notification3.notifyUser(); // Output: Sending a Push Notification

		try {
			// This will invoke default case because "fax" is not handled
			Notification notification4 = factory.createNotification("fax");
			notification4.notifyUser(); // This won't be reached
		} catch (IllegalArgumentException e) {
			System.out.println("Caught exception: " + e.getMessage());
		}
	}

}
