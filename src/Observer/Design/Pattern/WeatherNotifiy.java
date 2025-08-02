package src.Observer.Design.Pattern;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}

class PhoneDisplay implements Observer {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    private void display() {
        System.out.println("Phone Display: Temperature updated - " + temperature);
    }
}

class TVDisplay implements Observer {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    private void display() {
        System.out.println("TV Display: Temperature updated - " + temperature);
    }
}

class TabDisplay implements Observer {
    private float temperature;

    @Override
    public void update(float temperature) {
        this.temperature = temperature;
        display();
    }

    private void display() {
        System.out.println("Tab Display: Temperature updated - " + temperature);
    }
}

public class WeatherNotifiy {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phoneDisplay = new PhoneDisplay();
        Observer tvDisplay = new TVDisplay();
        Observer tabDisplay = new TabDisplay();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(tvDisplay);
        weatherStation.addObserver(tabDisplay);

        weatherStation.setTemperature(25.5f);
        // Output:
        // Phone Display: Temperature updated - 25.5
        // TV Display: Temperature updated - 25.5

        weatherStation.setTemperature(30.0f);
        // Output:
        // Phone Display: Temperature updated - 30.0
        // TV Display: Temperature updated - 30.0
    }

}