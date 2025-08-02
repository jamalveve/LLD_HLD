package src.Decorator.Design.Pattern;

public class PizzaShop {
	public static void main(String[] args) {
		Pizza pizza = new PlainPizza();
		System.out.println(pizza.getDescription() + " $" + pizza.cost());

		pizza = new CheeseDecorator(pizza);
		System.out.println(pizza.getDescription() + " $" + pizza.cost());

		pizza = new PepperoniDecorator(pizza);
		System.out.println(pizza.getDescription() + " $" + pizza.cost());
	}
}

// Component (Interface/Abstract Class): Defines the base type for core
// functionality.

interface Pizza {
	String getDescription();

	double cost();
}

// Concrete Component: Provides the default implementation.

class PlainPizza implements Pizza {
	@Override
	public String getDescription() {
		return "Plain pizza";
	}

	@Override
	public double cost() {
		return 8.0;
	}
}
// Decorator (Abstract): Implements the same interface, contains a reference to
// the component, and forwards operations to it.

abstract class PizzaDecorator implements Pizza {
	protected Pizza decoratedPizza;

	public PizzaDecorator(Pizza decoratedPizza) {
		this.decoratedPizza = decoratedPizza;
	}

	@Override
	public String getDescription() {
		return decoratedPizza.getDescription();
	}

	@Override
	public double cost() {
		return decoratedPizza.cost();
	}
}

// Concrete Decorators: Extend the decorator to add new behavior before/after
// calling the wrapped object.

class CheeseDecorator extends PizzaDecorator {
	public CheeseDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return decoratedPizza.getDescription() + ", cheese";
	}

	@Override
	public double cost() {
		return decoratedPizza.cost() + 1.5;
	}
}

class PepperoniDecorator extends PizzaDecorator {
	public PepperoniDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return decoratedPizza.getDescription() + ", pepperoni";
	}

	@Override
	public double cost() {
		return decoratedPizza.cost() + 2.0;
	}
}

class MushroomDecorator extends PizzaDecorator {
	public MushroomDecorator(Pizza pizza) {
		super(pizza);
	}

	@Override
	public String getDescription() {
		return decoratedPizza.getDescription() + ", mushroom";
	}

	@Override
	public double cost() {
		return decoratedPizza.cost() + 3.0;
	}
}
