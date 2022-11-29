package ru.job4j.io;

import java.io.File;

/**
 * Класс выводит на консоль только имя файла и его размер.
 * Данный метод работает корректно только с файлами.
 * Для каталогов он возвращает 0, либо размер метаданных каталога, но не суммарный размер файлов в нем.
 * Аргумент указан в Dir - Edit configuration.
 */
public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
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
