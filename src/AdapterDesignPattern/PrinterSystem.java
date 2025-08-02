package src.AdapterDesignPattern;

interface Printer {
    void print();
}

class LegacyPrinter {
    public void printDocument() {
        System.out.println("Legacy Printer is printing a document.");
    }
}

class PrinterAdapter implements Printer {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter(LegacyPrinter legacyPrinter) {
        this.legacyPrinter = legacyPrinter;
    }

    @Override
    public void print() {
        legacyPrinter.printDocument();
    }
}

public class PrinterSystem {
    public static void main(String[] args) {
        LegacyPrinter legacyPrinter = new LegacyPrinter();
        Printer printerAdapter = new PrinterAdapter(legacyPrinter);

        printerAdapter.print(); // Output: Legacy Printer is printing a document.
    }
}
