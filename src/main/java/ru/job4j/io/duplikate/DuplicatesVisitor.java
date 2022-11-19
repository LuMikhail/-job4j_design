package ru.job4j.io.duplikate;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Класс позволяет с помощью метода находить дубликаты в папках.
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> duplicates = new ArrayList<>();
    private Map<FileProperty, Path> single = new HashMap<>();

    public List<Path> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!single.containsKey(fileProperty)) {
            single.put(fileProperty, file);
        } else {
            duplicates.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
