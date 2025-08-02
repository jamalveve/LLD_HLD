package src.AdapterDesignPattern;

interface RowingBoat {
    void row();
}

class FishingBoat {
    public void sail() {
        System.out.println("The fishing boat is sailing");
    }
}

class FishingBoatAdapter implements RowingBoat {
    private final FishingBoat boat;

    public FishingBoatAdapter() {
        this.boat = new FishingBoat();
    }

    @Override
    public void row() {
        boat.sail(); // adapt row() to sail()
    }
}

class Captain {
    private final RowingBoat rowingBoat;

    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row() {
        rowingBoat.row();
    }
}

public class FishingScenario {

    public static void main(String[] args) {
        RowingBoat boat = new FishingBoatAdapter(); // Adapter adapts FishingBoat to RowingBoat
        Captain captain = new Captain(boat);
        captain.row(); // Outputs: The fishing boat is sailing

    }

}
