package ru.job4j.io.duplikate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicates = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("D:\\Рабочий стол\\дубликаты"), duplicates);
        duplicates.getDuplicates().forEach(System.out::println);
    }
}
