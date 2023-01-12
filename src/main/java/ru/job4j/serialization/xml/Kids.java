package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "kid")
public class Kids {

    @XmlAttribute
    private String name;

    public Kids() {
    }

    public Kids(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Kids(name=%s)",
                this.name);
    }
}
