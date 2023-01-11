package ru.job4j.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Показана запись в лог переменные разных типов.
 * Первый параметр метода - это шаблон. Шаблон содержит текст и отметки, которые заменяются на параметры.
 * <p>Параметры указываются после шаблона.
 * <p>Метки заменяются последовательно. Первая метка заменится первым параметром, вторая - вторым и так далее.
 * <p> Если меток или параметров будет разное количество логгер проигнорирует метку или параметр.
 */
public class UsageSlf4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Sergey Fedorov";
        int age = 38;
        float height = 1.80F;
        boolean programmingLanguageJava = true;
        char letter = 'j';
        long month = 12;
        double percent = 89.88;
        short year = 2023;
        byte monthGraduation = 6;
        LOG.debug("User info name : {}, age : {}, height : {}, programming Language Java : {}, "
                  + "the first letter of the course : {}, average course completion time : {}, "
                  + "guarantee of employment : {}, graduation of year {}  month {}",
                name, age, height, programmingLanguageJava, letter, month, percent, year, monthGraduation);
    }
}
