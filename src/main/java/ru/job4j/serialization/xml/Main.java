package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Показывает как сериализовать/десериализовать объекты в/c XML с промощью следующих шагов:
 * <p>Получаем контекст для доступа к АПИ - context.
 * <p>Создаем сериализатор - marshaller. Указываем, что нам нужно форматирование.
 * <p>Сериализуем  - marshaller.marshal(employee, writer);.
 * Для десериализации  создаем десериализатор - unmarshaller, десериализуем.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final Employee employee = new Employee("Sverid Nikolay", 45000, false,
                new String[]{"Fishing", "Swimming"}, new Kids("Gleb"));
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Employee result = (Employee) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
