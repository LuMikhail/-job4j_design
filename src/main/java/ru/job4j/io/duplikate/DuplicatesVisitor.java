package ru.job4j.io.duplikate;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Класс позволяет с помощью метода находить дубликаты в папках.
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> mapHasOllPath = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        mapHasOllPath.putIfAbsent(fileProperty, new ArrayList<>());
        mapHasOllPath.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    /**
     * Метод создает коллекцию которая, состоит из дубликатов когда значение в map содержит больше одного элемента.
     *
     * @return Возвращаем коллекцию, которая содержит дубликаты.
     */
    public List<Path> getDuplicates() {
        List<Path> duplicates = new ArrayList<>();
        mapHasOllPath.values().stream()
                .filter(v -> v.size() > 1)
                .forEach(duplicates::addAll);
        return duplicates;
    }
}
