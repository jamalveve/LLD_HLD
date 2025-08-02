package src.StateDesignPattern;

interface TrafficLightState {
    void showLight(TrafficLightContext context);
}

class RedState implements TrafficLightState {
    @Override
    public void showLight(TrafficLightContext context) {
        System.out.println("Red light - Stop!");
        context.setState(new GreenState()); // After Red, go to Green
    }
}

class GreenState implements TrafficLightState {
    @Override
    public void showLight(TrafficLightContext context) {
        System.out.println("Green light - Go!");
        context.setState(new YellowState()); // After Green, go to Yellow
    }
}

class YellowState implements TrafficLightState {
    @Override
    public void showLight(TrafficLightContext context) {
        System.out.println("Yellow light - Slow Down!");
        context.setState(new RedState()); // After Yellow, go to Red
    }
}

class TrafficLightContext {
    private TrafficLightState currentState;

    public TrafficLightContext() {
        currentState = new RedState(); // Initial state
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void showLight() {
        currentState.showLight(this);
    }
}

public class TrafficLight {
    public static void main(String[] args) throws InterruptedException {
        TrafficLightContext trafficLight = new TrafficLightContext();

        for (int i = 0; i < 6; i++) { // Loop through states multiple times
            trafficLight.showLight();
            Thread.sleep(1000); // delay to simulate time between changes
        }
    }
}
