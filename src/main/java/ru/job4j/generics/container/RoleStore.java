package ru.job4j.generics.container;

/**
 * Класс содержит реализацию для пользователя с использованием композиции объектов.
 * Здесь методы делают тоже что и в универсальном хранилище MemStore.
 * Однако заново реализовывать их не нужно так как вызываться реализации этих методов из MemStore,
 * поскольку объект именно этого типа мы используем в качестве хранилища.
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    @Override
    public void add(Role model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return store.delete(id);
    }

    @Override
    public Role findById(String id) {
        return store.findById(id);
    }
}
