package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс с помощью методов:
 * <p> 1. Читает файл и возвращает строки, в которых предпоследний элемент содержит - 404.</p>
 * <p> 2. Записывает отфильтрованные данные в новый файл.</p>
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
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(f -> {
                        String[] predicate = f.split(" ");
                        return predicate[predicate.length - 2].equals("404");
                    })
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод записывает отобранные данные в новый файл.
     *
     * @param log  входящая коллекция с новыми данными.
     * @param file в который записываются новые данные.
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");
    }
}
