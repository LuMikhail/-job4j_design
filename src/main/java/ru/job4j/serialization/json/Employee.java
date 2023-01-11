package ru.job4j.serialization.json;

import java.util.Arrays;

public class Employee {
    private String name;
    private int salary;
    private boolean isMarried;
    private String[] hobbies;
    private Kids kid;

    public Employee(String name, int salary, boolean isMarried, String[] hobbies, Kids kid) {
        this.name = name;
        this.salary = salary;
        this.isMarried = isMarried;
        this.hobbies = hobbies;
        this.kid = kid;
    }

    @Override
    public String toString() {
        return String.format("Employee(name=%s ,age=%s ,isMarried=%s ,hobbies=%s ,kid=%s)",
                this.name,
                this.salary,
                this.isMarried,
                Arrays.toString(this.hobbies),
                this.kid);
    }
}
