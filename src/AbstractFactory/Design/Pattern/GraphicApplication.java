package src.AbstractFactory.Design.Pattern;

public class GraphicApplication {
    private Button button;
    private Checkbox checkbox;

    public GraphicApplication(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory;

        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }

        GraphicApplication app = new GraphicApplication(factory);
        app.paint();
    }
}

interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}

// concret for Windows
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a Windows button.");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a Windows checkbox.");
    }
}

// concret for Macos

class MacOSButton implements Button {
    public void paint() {
        System.out.println("Rendering a MacOS button.");
    }
}

class MacOSCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a MacOS checkbox.");
    }
}

// 1.You define an Abstract Factory interface declaring creation methods for
// each kind of product.

interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();
}

// 2.You create Concrete Factories, each implementing the Abstract Factory and
// producing a particular family (variant) of products.

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() {
        return new MacOSButton();
    }

    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
