package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Класс с помощью метода load загружает пару ключ-значение в Map values соблюдая следующие условия:
 * <p>1. Если в файле могут быть пустые строки и комментарии их нужно пропускать.</p>
 * <p>2. Если файла с нарушением шаблона ключ=значение (напр. =значение, или ключ=
 * в этом случае нужно ожидать исключение IllegalArgumentException. </p>
 * <p>3. Строка вида "ключ=значение=1" или "ключ=значение=" должна быть обработана
 * и распознана как ключ "ключ" и значение "значение=1" или "значение=" соответственно.</p>
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(str -> !str.contains("#"))
                    .filter(str -> !str.isEmpty())
                    .forEach(str -> {
                        String[] map = str.split("=", 2);
                        if (map.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        values.put(map[0], map[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
