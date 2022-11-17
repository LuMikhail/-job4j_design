package ru.job4j.io;

import java.io.File;

/**
 * Класс выводит на консоль только имя файла и его размер.
 */
public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfile : file.listFiles()) {
            System.out.printf("File name %s,", subfile.getName());
            System.out.printf(" its size size %s%n", subfile.length());
        }
    }
}
