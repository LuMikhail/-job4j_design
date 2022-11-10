package ru.job4j.generics.container;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsSergey() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Sergey"));
        Role result = store.findById("2");
        assertThat(result.getRolename()).isEqualTo("Sergey");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Fedor"));
        Role result = store.findById("3");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsNikolay() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Nikolay"));
        store.add(new Role("1", "Inna"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Nikolay");
    }

    @Test
    void whenReplaceThenRoleNameIsSvetlana() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "Inna"));
        store.replace("2", new Role("2", "Svetlana"));
        Role result = store.findById("2");
        assertThat(result.getRolename()).isEqualTo("Svetlana");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Fedor"));
        store.replace("2", new Role("2", "Ivan"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Fedor");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Petr");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("1", new Role("1", "Maxim"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.replace("10", new Role("10", "Maxim"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}
