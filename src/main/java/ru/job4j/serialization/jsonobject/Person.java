package ru.job4j.serialization.jsonobject;

import java.util.Arrays;

public class Person {
    private boolean sex;

    private int age;

    private Contact contact;

    private String[] statuses;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    public void setStatuses(String[] statuses) {
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
               + "sex=" + sex
               + ", age=" + age
               + ", contact=" + contact
               + ", statuses=" + Arrays.toString(statuses)
               + '}';
    }
}
