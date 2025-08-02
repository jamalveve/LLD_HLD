package src.AbstractFactory.Design.Pattern;

//Chair interface
interface Chair {
	void sitOn();
}

// Sofa interface
interface Sofa {
	void lieOn();
}

class ModernChair implements Chair {
	@Override
	public void sitOn() {
		System.out.println("Sitting on a modern chair");
	}
}

class ModernSofa implements Sofa {
	@Override
	public void lieOn() {
		System.out.println("Lying on a modern sofa");
	}
}

class VictorianChair implements Chair {
	@Override
	public void sitOn() {
		System.out.println("Sitting on an antique Victorian chair");
	}
}

class VictorianSofa implements Sofa {
	@Override
	public void lieOn() {
		System.out.println("Lying on a plush Victorian sofa");
	}
}

interface FurnitureFactory {
	Chair createChair();

	Sofa createSofa();
}

class ModernFurnitureFactory implements FurnitureFactory {
	@Override
	public Chair createChair() {
		return new ModernChair();
	}

	@Override
	public Sofa createSofa() {
		return new ModernSofa();
	}
}

class VictorianFurnitureFactory implements FurnitureFactory {
	@Override
	public Chair createChair() {
		return new VictorianChair();
	}

	@Override
	public Sofa createSofa() {
		return new VictorianSofa();
	}
}

public class FurnitureClient {

	private Chair chair;
	private Sofa sofa;

	public FurnitureClient(FurnitureFactory factory) {
		chair = factory.createChair();
		sofa = factory.createSofa();
	}

	public void useFurniture() {
		chair.sitOn();
		sofa.lieOn();
	}

	public static void main(String[] args) {

		FurnitureFactory modernFactory = new ModernFurnitureFactory();
		FurnitureClient modernClient = new FurnitureClient(modernFactory);
		modernClient.useFurniture();
		// Output:
		// Sitting on a modern chair
		// Lying on a modern sofa

		FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
		FurnitureClient victorianClient = new FurnitureClient(victorianFactory);
		victorianClient.useFurniture();
		// Output:
		// Sitting on an antique Victorian chair
		// Lying on a plush Victorian sofa
	}
}
