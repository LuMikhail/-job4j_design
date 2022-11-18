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
        Path start = Paths.get(".");
        search(start, p -> p.toFile().getName().endsWith(".js")).forEach(System.out::println);
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
}