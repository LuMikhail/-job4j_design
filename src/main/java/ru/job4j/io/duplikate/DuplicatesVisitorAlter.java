package ru.job4j.io.duplikate;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitorAlter extends SimpleFileVisitor<Path> {
    private List<Path> duplicates = new ArrayList<>();
    private Map<FileProperty, Path> single = new HashMap<>();

    public List<Path> getDuplicates() {
        return duplicates.stream().distinct().toList();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!single.containsKey(fileProperty)) {
            single.put(fileProperty, file);
        } else {
            duplicates.add(file);
            duplicates.add(single.get(fileProperty));
        }
        return super.visitFile(file, attrs);
    }
}
