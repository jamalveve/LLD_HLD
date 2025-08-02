package src.NullObjectDesignPattern;

import java.util.HashMap;
import java.util.Map;

interface Student {
    String getName();

    int getGrade();

    boolean isNull();
}

class RealStudent implements Student {
    private String name;
    private int grade;

    public RealStudent(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGrade() {
        return grade;
    }

    @Override
    public boolean isNull() {
        return false;
    }
}

class NullStudent implements Student {

    @Override
    public String getName() {
        return "No Student Found";
    }

    @Override
    public int getGrade() {
        return -1; // or any default value indicating no grade
    }

    @Override
    public boolean isNull() {
        return true;
    }
}

public class StudentManagementSystem {

    private static final Map<String, Integer> studentDatabase = new HashMap<>();

    static {
        studentDatabase.put("kamal", 85);
        studentDatabase.put("sri", 90);
        studentDatabase.put("john", 78);
    }

    public static Student getStudent(String name) {
        if (studentDatabase.containsKey(name)) {
            return new RealStudent(name, studentDatabase.get(name));
        } else {
            return new NullStudent();
        }
    }

}
