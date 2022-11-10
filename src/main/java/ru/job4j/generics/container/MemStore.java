package ru.job4j.generics.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс реализует универсальное хранилище.
 *
 * @param <T> Любой Объект реализующий интерфейс Store.
 */
public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    /**
     * Метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает.
     * Если добавляемый объект уже присутствует в хранилище, то добавления не происходит.
     *
     * @param model объект типа T
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода.
     *
     * @param id    объекта.
     * @param model объект типа T.
     * @return возвращает true, если замена выполнена успешно.
     */
    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, storage.get(id), model);
    }

    /**
     * Метод удаляет пару ключ-значение по id, который передается в метод.
     *
     * @param id объекта.
     * @return возвращает true, если удаление выполнено успешно.
     */
    @Override
    public boolean delete(String id) {
        return storage.remove(id, storage.get(id));
    }

    /**
     * Метод возвращает объект по ключу, который равен id,
     * передаваемый в качестве параметра метода или возвращает null если по такому ключу в Map еще нет пару ключ-значение.
     *
     * @param id объекта.
     * @return возвращает объект по ключу, который равен id.
     */
    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
