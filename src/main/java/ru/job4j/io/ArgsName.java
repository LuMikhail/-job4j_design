package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс позволяет принимать массив параметров и разбивает их на пары: ключ, значение
 * в соответствие с шаблоном "-key=value".
 */
public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid key %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        for (String elem : args) {
            validate(elem);
            String[] array = elem.split("=", 2);
            values.put(array[0].substring(1), array[1]);
        }
    }

    /**
     * Метод проверяет данные в соответствие с шаблоном "-key=value", а также:
     * <p>на отсутствие ключа "-=значение"</p>
     * <p>на отсутствие символа "=" "-ключ:значение"</p>
     * <p>на отсутствие символа "-" "ключ=значение"</p>
     *
     * @param argument входящие данные
     */
    public void validate(String argument) {
        if (!argument.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("this argument: '%s' should starts with symbol '-'.", argument));
        }
        if (!argument.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this argument: %s does not contain the symbol '='.", argument));
        }
        if (argument.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("this argument: %s does not contain a key", argument));
        }
        if (argument.indexOf("=") == argument.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this argument: %s does not contain a value", argument));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
