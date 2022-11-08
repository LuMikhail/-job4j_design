package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .anySatisfy(s -> assertThat(s).isEqualTo("three"))
                .allMatch(e -> e.length() < 7)
                .anyMatch(e -> e.length() == 5)
                .noneMatch(e -> e.length() < 4);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "second", "four", "three", "second", "four");
        assertThat(set).filteredOn(s -> s.length() < 5)
                .first().isEqualTo("four");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("cucumber price", "tomato price", "grapes price");
        map.replace("cucumber price", 110);
        map.replace("tomato price", 80);
        map.replace("grapes price", 130);
        assertThat(map).hasSize(3)
                .containsKeys("cucumber price", "tomato price", "grapes price")
                .containsValues(110, 80, 130)
                .doesNotContainKey("potato price")
                .doesNotContainValue(90)
                .containsEntry("tomato price", 80);
    }
}