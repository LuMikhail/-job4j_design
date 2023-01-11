package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Реализует процесс преобразования объекта типа Employee в Json так и наоборот.
 */
public class Main {
    public static void main(String[] args) {
        final Employee employee = new Employee("Petrov Aleksandr", 67000, true,
                new String[]{"Fishing", "Swimming"}, new Kids("Gleb"));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(employee));

        final String readJson = "{"
                                + "\"name\":\"Sverid Nikolay\","
                                + "\"salary\":45000,"
                                + "\"isMarried\":false,"
                                + "\"hobbies\":"
                                + "[\"Reading \",\"Football\"],"
                                + "\"kid\":"
                                + "{"
                                + "\"name\":\"Olga\""
                                + "}"
                                + "}";
        final Employee employee2 = gson.fromJson(readJson, Employee.class);
        System.out.println(employee2);
    }
}
