package LLD.Strategy.design.pattern;

public class WIthoutStratgeyDesignPattern {

}

class Vehicle{
	public void drive() {
		System.out.println("normal drive capabilty");
	}
}

class Goodsvehcile extends Vehicle{
	
	//different capabilty needed
	public void drive() {///we are not reusinhg the code just dupliactijn boiker plate code
		System.out.println("special drive capabilty acpridng to goodsvehicle");
	}
}

class RaceVehicle extends Vehicle{
	
	//different capabilty needed

	public void drive() {
		System.out.println("special drive capabilty according to race vehiucle");
	}
}

class PassengerVehicle extends Vehicle{
	
	public void drive() {
		System.out.println("normal drive capabilty");
	}
}