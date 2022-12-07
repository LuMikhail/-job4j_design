package ru.job4j.io;

import ru.job4j.io.search.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * В Configurations класса определены следующие аргументы
 * <p>-d - directory - которую мы хотим архивировать.</p>
 * <p>-e - exclude - исключить файлы с расширением class.</p>
 * <p>-o - output - во что мы архивируем.</p>
 * Класс реализует приложение для архивации папки.
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project
 * 2. В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта.
 */
public class Zip {
    /**
     * Метод архивирует проект
     *
     * @param source проект, который архивируем.
     * @param target за архивируемый проект.
     */
    public void packFile(List<Path> source, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path s : source) {
                zos.putNextEntry(new ZipEntry(s.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(s.toFile()))) {
                    zos.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет архивировать одни файл
     *
     * @param source файл который нужно заархивировать
     * @param target заархивированный файл
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        File directory = new File(argsName.get("d"));
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String
                    .format("Directory is missing %s check Configurations argument", directory));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Exclusion must start from . : %s", argsName.get("e")));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("The zip extension %s is not set correctly", Path.of(argsName.get("o"))));
        }
    }

    /**
     * Производит архивацию файла с помощью packSingleFile или проекта - packFile.
     *
     * @param args определяет аргументы приложения.
     * @throws IOException выбрасывает исключение, если аргументы отсутствуют.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    String.format("Check the arguments there should be three of them and not %s", args.length));
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        validate(argsName);
        List<Path> list = Search.search(Paths.get(argsName.get("d")),
                path -> !path.toFile().getName().endsWith(argsName.get("e")));
        zip.packFile(list, new File(argsName.get("o")));

        Zip zipSingle = new Zip();
        zipSingle.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}