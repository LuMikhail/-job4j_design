package ru.job4j.jdbc;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Класс позволяет записать данные из файла в базу psql с помощью интерфейса PreparedStatement
 */
public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Создает коллекцию из файла
     * @return возвращает коллекцию с данными из файла
     * @throws IOException
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] us = s.split(";");
                User user = new User(us[0], us[1]);
                users.add(user);
            });
        }
        return users;
    }

    /**
     * Создает соединение с базой данный с помощью Property и показывает отправка запросов в psql
     * с помощью интерфейса PreparedStatement
     * @param users файл преобразованный в коллекцию
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("connection.driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("connection.url"),
                cfg.getProperty("connection.login"),
                cfg.getProperty("connection.password")
        )) {
            try (Statement st = cnt.createStatement()) {
                st.execute(String.format("create table users(%s, %s, %s);",
                        "id serial primary key", "name_users text", "email text"));
            }
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name_users, email) values (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
