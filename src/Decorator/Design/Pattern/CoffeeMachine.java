package src.Decorator.Design.Pattern;

//Component
interface Coffee {
    String getDescription();

    double cost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double cost() {
        return 5.0;
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double cost() {
        return coffee.cost();
    }
}

// Concrete Decorators
class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    public double cost() {
        return coffee.cost() + 1.0;
    }
}

class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }
}

class ExtraChoclate extends CoffeeDecorator {
    public ExtraChoclate(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Extra Choclate";
    }

    public double cost() {
        return coffee.cost() + 2.0;
    }
}

class ExtraDarkLatte extends CoffeeDecorator {
    public ExtraDarkLatte(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + ", Extra Choclate";
    }

    public double cost() {
        return coffee.cost() + 3.0;
    }
}

// Usage
public class CoffeeMachine {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        coffee = new WithMilk(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        coffee = new WithSugar(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        coffee = new ExtraChoclate(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

        coffee = new ExtraDarkLatte(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.cost());

    }
}
