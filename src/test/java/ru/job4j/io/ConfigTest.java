package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithoutIllegalArgumentException() {
        String path = "./data/pair_without_void.properties";
        Config config = new Config(path);
        config.load();
    }


    @Test
    void whenPairWithoutHasSeveral () {
        String path = "./data/pair_without_app_double.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password=0305");
    }


}