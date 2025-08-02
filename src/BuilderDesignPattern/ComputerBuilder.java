package src.BuilderDesignPattern;

public class ComputerBuilder {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.ComputerBuilder("Intel i9", "32GB")
                .storage("1TB SSD")
                .graphicsCard("NVIDIA RTX 3090")
                .build();

        Computer officePC = new Computer.ComputerBuilder("Intel i5", "16GB")
                .build(); // No optional config

        System.out.println(gamingPC);
        System.out.println(officePC);

    }

}

class Computer {
    // Mandatory fields
    private String cpu;
    private String ram;

    // Optional fields
    private String storage;
    private String graphicsCard;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage + ", GraphicsCard=" + graphicsCard
                + "]";
    }

    public static class ComputerBuilder {
        private final String cpu;
        private final String ram;

        private String storage;
        private String graphicsCard;

        public ComputerBuilder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public ComputerBuilder storage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
