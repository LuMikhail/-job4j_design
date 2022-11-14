package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Клас из файла even.txt извлекает информация:
 *  для каждого числа праверяетcя является ли оно четным числом.
 *  Ответ вывести на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int number;
            while ((number = in.read()) != -1) {
                text.append((char) number);
            }
            String[] result = text.toString().split(System.lineSeparator());
            System.out.println(Arrays.toString(result));
            for (String r : result) {
                if (Integer.parseInt(r) % 2 == 0) {
                    System.out.println("Четное число - " + r);
                } else {
                    System.out.println("Не четное число - " + r);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
