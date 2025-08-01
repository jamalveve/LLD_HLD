package LLD.Strategy.design.pattern;

public class WithStrategyDesignPattern {
public static void main(String[] args) {
	Vehicle1 obj1=new Goodsvehcile1();
	obj1.drive();
	Vehicle1 obj2=new RaceVehicle1();
	obj2.drive();
	Vehicle1 obj3=new PassengerVehicle1();
	obj3.drive();
}
}

interface Drive{
	public void drive();
}

class SportsDriveStrategy implements Drive{

	@Override
	public void drive() {
	System.out.println("own sports driving capabilty");
		
	}
	
}
class LoadDriveStrategy  implements Drive{

	@Override
	public void drive() {
	System.out.println("own Load driving capabilty");
		
	}
	
}
class PassengerDriveStrategy  implements Drive{

	@Override
	public void drive() {
	System.out.println("normal drive capabilty");
		
	}
	
}

class Vehicle1{
	
	Drive driveobj;
	
	Vehicle1(Drive driveobj){
		this.driveobj=driveobj;
	}
	
	public void drive() {
		driveobj.drive();
	}
}

class Goodsvehcile1 extends Vehicle1{
	
	//condtructor Injection
	Goodsvehcile1(){
		super(new LoadDriveStrategy ());
	}
}

class RaceVehicle1 extends Vehicle1{
	
	

	RaceVehicle1() {
		super(new SportsDriveStrategy ());
		// TODO Auto-generated constructor stub
	}

	
}

class PassengerVehicle1 extends Vehicle1{
	
	PassengerVehicle1() {
		super(new PassengerDriveStrategy ());
		// TODO Auto-generated constructor stub
	}	
}