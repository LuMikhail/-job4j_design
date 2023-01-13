package ru.job4j.serialization.jsonobject;

public class Contact {
    private String phone;

    public Contact() {

    }

    public Contact(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
               + "phone='" + phone + '\''
               + '}';
    }
}
