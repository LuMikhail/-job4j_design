package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty().isEqualTo("Sphere").doesNotContain("Cube");
    }

    @Test
    void findVertices() {
        Box box = new Box(8, 12);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isLessThan(10)
                .isEqualTo(8);
    }

    @Test
    void ifNotFoundVertex() {
        Box box = new Box(9, 6);
        boolean result = box.isExist();
        assertThat(result).isNotNull().isFalse();
    }

    @Test
    void getAreaIfVertexEqualZero() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.64D, withPrecision(0.006d)).isLessThan(1257.12d);
    }

    @Test
    void getAreaIfVertexEqualEight() {
        Box box = new Box(8, 4);
        double result = box.getArea();
        assertThat(result).isEqualTo(96).isGreaterThan(95);
    }

    @Test
    void getAreaIfVertexEqualFour() {
        Box box = new Box(4, 4);
        double result = box.getArea();
        assertThat(result).isEqualTo(27.71D, withPrecision(0.05d))
                .isCloseTo(27.71D, withPrecision(0.01d));
    }
}