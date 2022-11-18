package ru.job4j.io;

import java.io.File;

/**
 * Класс выводит на консоль только имя файла и его размер.
 *  Данный метод работает корректно только с файлами.
 *  Для каталогов он возвращает 0, либо размер метаданных каталога, но не суммарный размер файлов в нем.
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
            System.out.printf(" its size %s%n", subfile.length());
        }
    }
}
