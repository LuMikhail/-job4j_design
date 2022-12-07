package ru.job4j.io.zip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Пример взят из кладовки программиста.
 * Позволяет читать данные в формате txt из архива указанной в методе readZip
 */
public class Read {
    public static void main(String[] args) throws IOException {
        readZip();
    }

    /**
     * Метод позволяет выводить из архива все файлы с расширением txt. и показывает содержимое этого файла
     * @throws IOException исключение если например нет архива
     */
    private static void readZip() throws IOException {
        ZipFile zipFile = new ZipFile("./Test.zip");
        for (Enumeration<? extends ZipEntry> iter = zipFile.entries(); iter.hasMoreElements();) {
            ZipEntry ze = iter.nextElement();
            System.out.println("Entry " + ze.getName());
            if (!ze.isDirectory()) {
               BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(ze)));
               String line;
               while ((line = reader.readLine()) != null) {
                   System.out.println(line);
               }
                System.out.println("------------->>>>");
            }
        }
    }
}
