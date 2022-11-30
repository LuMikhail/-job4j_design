package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс находит с помощью метода и выводит на консоль путь к файлу в соответствие с условием поиска.
 * Так как нужен только поиск используется SimpleFileVisitor.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * Метод производит поиск файлов в соответствии с заданным критерием
     *
     * @param root      источник в котором происходит фильтрация.
     * @param condition условие при котором происходит фильтрация.
     * @return возвращается коллекция содержащая путь к файлу.
     * @throws IOException выбрасывает исключение.
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * Метод проверяет, что в программу передано нужное количество данных, а затем каждый принятый
     * параметр проверяется на соответствие его содержимого требованиям программы.
     *
     * @param args аргументы, которые задаются в Configurations класса Search.
     */
    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Configurations arguments is no file extension or Directory");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String
                    .format("Directory is missing %s check Configurations argument", Path.of(args[0])));
        }
        if (!args[1].equals("js")) {
            throw new IllegalArgumentException(String
                    .format("The file extension %s is not set correctly", Path.of(args[1])));
        }
    }
}