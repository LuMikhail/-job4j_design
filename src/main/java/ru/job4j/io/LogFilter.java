package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс с помощью метода читает файл и возвращает строки, в которых предпоследний элемент содержит - 404.
 *
 */
public class LogFilter {
    /**
     * Метод filter читает файл и возвращает строки, где предпоследнее значение - это 404.
     *
     * @param file загруженный файл
     * @return возврат строки с предпоследнем элементом 404.
     */
    public List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            in.lines()
                    .filter(f -> {
                        String[] predicate = f.split(" ");
                        return predicate[predicate.length - 2].equals("404")
                                && !predicate[predicate.length - 1].equals("-");
                    })
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
