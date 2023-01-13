package ru.job4j.serialization.json;

public class Kids {
    private String name;

    public Kids(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Kids(name=%s)",
                this.name);
    }
}
