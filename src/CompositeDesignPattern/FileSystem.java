package src.CompositeDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent {
    void showDetails();
}

class File implements FileSystemComponent {
    private String name;
    private int size; // size in KB

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name + " [Size: " + size + "KB]");
    }
}

class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

public class FileSystem {
    public static void main(String[] args) {
        // Create files
        File file1 = new File("File1.txt", 10);
        File file2 = new File("File2.txt", 20);

        // Create a directory and add files
        Directory dir1 = new Directory("Documents");
        dir1.addComponent(file1);
        dir1.addComponent(file2);

        // Create a subdirectory with its own files
        Directory dir2 = new Directory("Music");
        dir2.addComponent(new File("Song1.mp3", 5000));
        dir2.addComponent(new File("Song2.mp3", 7000));

        // Create root directory that contains both folders
        Directory root = new Directory("Root");
        root.addComponent(dir1);
        root.addComponent(dir2);

        // Show all files and directories
        root.showDetails();
    }

}
