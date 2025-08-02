package src.ProxyDesignpattern;

interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

public class LazyLoadingImage {

    public static void main(String[] args) {
        Image image = new ProxyImage("example.jpg");

        // First call, image loaded from disk
        image.display();

        // Second call, image not loaded again, uses cached object
        image.display();

    }

}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // load only when needed
        }
        realImage.display();
    }
}
