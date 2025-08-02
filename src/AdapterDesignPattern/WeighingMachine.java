package src.AdapterDesignPattern;

// Existing interface returning weight in pounds
interface WeightMachine {
    double getWeightInPounds();
}

class WeightMachineImpl implements WeightMachine {
    @Override
    public double getWeightInPounds() {
        return 150; // example weight in pounds
    }
}

// Target interface expected by client returning weight in kgs
interface WeightMachineAdapter {
    double getWeightInKg();
}

class WeightMachineAdapterImpl implements WeightMachineAdapter {
    private final WeightMachine weightMachine;

    public WeightMachineAdapterImpl(WeightMachine weightMachine) {
        this.weightMachine = weightMachine;
    }

    @Override
    public double getWeightInKg() {
        double weightInPounds = weightMachine.getWeightInPounds();
        // Convert pounds to kilograms (1 pound = 0.453592 kg)
        return weightInPounds * 0.453592;
    }
}

public class WeighingMachine {
    public static void main(String[] args) {
        WeightMachine weightMachine = new WeightMachineImpl();
        WeightMachineAdapter adapter = new WeightMachineAdapterImpl(weightMachine);

        System.out.println("Weight in pounds: " + weightMachine.getWeightInPounds());
        System.out.println("Weight in kilograms (via adapter): " + adapter.getWeightInKg());
    }

}
