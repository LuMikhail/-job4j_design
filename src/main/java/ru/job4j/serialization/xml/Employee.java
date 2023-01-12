package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private int salary;

    @XmlAttribute
    private boolean isMarried;

    @XmlElementWrapper(name = "hobbieses")
    @XmlElement(name = "hobbies")
    private String[] hobbieses;

    private Kids kid;

    public Employee() {
    }

    public Employee(String name, int salary, boolean isMarried, String[] hobbies, Kids kid) {
        this.name = name;
        this.salary = salary;
        this.isMarried = isMarried;
        this.hobbieses = hobbies;
        this.kid = kid;
    }

    @Override
    public String toString() {
        return String.format("Employee(name=%s ,age=%s ,isMarried=%s ,hobbies=%s ,kid=%s)",
                this.name,
                this.salary,
                this.isMarried,
                Arrays.toString(this.hobbieses),
                this.kid);
    }
}
