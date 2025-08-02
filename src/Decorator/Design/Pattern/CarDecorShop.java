package src.Decorator.Design.Pattern;

public class CarDecorShop {
	public static void main(String[] args) {
		Car sportsCar = new SportsCar(new BasicCar());
		sportsCar.assemble();
		System.out.println("\n*****");

		Car LuxuryCar = new LuxuryCar(new BasicCar());
		LuxuryCar.assemble();
		System.out.println("\n*****");

		Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
		sportsLuxuryCar.assemble();
		System.out.println("\n*****");

		Car sportsLoadCar = new SportsCar(new LoadCar(new BasicCar()));
		sportsLoadCar.assemble();
		System.out.println("\n*****");

		Car LoadSportsLuxury = new SportsCar(new LuxuryCar(new LoadCar(new BasicCar())));
		LoadSportsLuxury.assemble();
		System.out.println("\n*****");

		// Output:
		// Basic Car. Adding features of Luxury Car. Adding features of Sports Car.
	}
}

// Interface Component
interface Car {
	void assemble();
}

// Concrete Component
class BasicCar implements Car {
	@Override
	public void assemble() {
		System.out.print("Basic Car.");
	}
}

// Absrtact Decorator
abstract class CarDecorator implements Car {
	protected Car decoratedCar;

	public CarDecorator(Car c) {
		this.decoratedCar = c;
	}

	@Override
	public void assemble() {
		decoratedCar.assemble();
	}
}

// Concrete Decorators
class SportsCar extends CarDecorator {
	public SportsCar(Car c) {
		super(c);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Sports Car.");
	}
}

class LuxuryCar extends CarDecorator {
	public LuxuryCar(Car c) {
		super(c);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Luxury Car.");
	}
}

class LoadCar extends CarDecorator {
	public LoadCar(Car c) {
		super(c);
	}

	@Override
	public void assemble() {
		super.assemble();
		System.out.print(" Adding features of Load Car.");
	}
}
