package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двухмерного массива int[][]
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод пропускает элементы, которые существуют в массиве, и удовлетворяют условиям итератора.
     *
     * @return проверяем, что элемент по найденным индексам существует в массиве и удовлетворяет нужным условиям.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        while (row < data.length) {
            if (column < data[row].length) {
                result = true;
                break;
            }
            row++;
            column = 0;
        }
        return result;
    }

    /**
     * В методе проверяем, что есть элемент к выдаче (за это отвечает проверка условия if(!hasNext()),
     * и дальше:
     *
     * @return выдаем элемент, при этом передвигаем индекс итератора на один шаг вперед.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = data[row][column++];
        if (column > data[row].length) {
            row++;
            column = 0;
        }
        return result;
    }
}
