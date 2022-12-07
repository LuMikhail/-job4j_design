package ru.job4j.io.zip;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Пример взят из кладовки программиста.
 * * Показана запись данных в архив простым способом
 * * Показан запись всего каталога в архив.
 */
public class Write {
    public static void main(String[] args) throws Exception {
        writeZip("Test.zip");
    }

    /**
     * метод позволяет создать архив и записать в нем файлы с расширением txt., записать в нем данные.
     * @param zip Название архива
     * @throws IOException исключение если нет архива
     */
    private static void writeZip(String zip) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        ZipEntry ze1 = new ZipEntry("name1.txt");
        zos.putNextEntry(ze1);
        zos.write("RESULT".getBytes());
        ZipEntry ze2 = new ZipEntry("name2.txt");
        zos.putNextEntry(ze2);
        zos.write("RESULT2".getBytes());
        zos.close();
    }
}
