package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Класс записывает в файл таблицу умножения.
 */
public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiplication_table.txt")) {
            int[] pif = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            for (int q : pif) {
                out.write(Integer.toString(q).getBytes());
                out.write(" |".getBytes());
                for (int w : pif) {
                    out.write(Integer.toString(q * w).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
