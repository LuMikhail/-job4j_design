package ru.job4j.io.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Класс показывает работу Log4j.
 * <p>Настройки добавлены в файл /src/main/resources/log4j.properties
 * <p>Формат записи. В логах удобно получать информацию о времени выполнении в классе и строчке кода,
 * где была сделана запись.
 * <p>Дата: %d{ISO8601}. Уровень сообщения: %5p. Класс, метод, строчка: %c:%M:%L
 * <p>Текст сообщения: %m%n. Уровень логирования log4j.rootLogger=DEBUG, console
 * <p>Настройки добавлены в файл /src/main/resources/log4j.properties
 */
public class UsageLog4j {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
