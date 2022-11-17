package ru.job4j.io;

import java.io.*;

/**
 * Класс реализует метод unavailable() который находит диапазоны, когда сервер не работал.
 * Источником данных - source. Результат записывается в target.
 */
public class Analisis {
    public static void unavailable(String source, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader read = new BufferedReader(new FileReader(source))) {
            String ranges;
            boolean serverDoNotWork = true;
            while ((ranges = read.readLine()) != null) {
                if ((ranges.contains("400") || ranges.contains("500")) && serverDoNotWork) {
                    out.write("начало простоя - " + ranges.substring(3) + ";");
                    serverDoNotWork = false;
                }
                if ((ranges.contains("200") || ranges.contains("300")) && !serverDoNotWork) {
                    out.write(" конец простоя - " + ranges.substring(3) + ";");
                    out.write(System.lineSeparator());
                    serverDoNotWork = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./server/server_sours.csv";
        String target = "./server/unavailable.csv";
        Analisis.unavailable(source, target);
    }
}
