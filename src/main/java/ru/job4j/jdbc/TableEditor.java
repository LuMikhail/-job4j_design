package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

/**
 * Реализует различные операции с базой данных через специальный интерфейс: Statement.
 * Properties - служебный класс, предназначенный для обработки конфигурационных файлов,
 * в которых хранятся параметры в виде пар ключ-значение.
 * Ресурсные файлы (*.properties) должны храниться в папке resources по пути src/main/resources.
 * Для загрузки настроек используется ClassLoader.
 */
public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    /**
     * Подключает к базе данных
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("connection.driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("connection.url"),
                properties.getProperty("connection.login"),
                properties.getProperty("connection.password"));
    }

    /**
     * Метод с помощью execute() выполняет любые команды.
     * execute() возвращает true, если результатом выполнения является ResultSet (то есть был выполнен SELECT запрос),
     * или false, если результатом является int (количество изменённых строк)
     *
     * @param sql запрос sql
     */
    private void statementExecute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        statementExecute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName
        );
        statementExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s",
                tableName, columnName, type
        );
        statementExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s",
                tableName, columnName
        );
        statementExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s",
                tableName, columnName, newColumnName
        );
        statementExecute(sql);
    }

    /**
     * Выводит схему таблицы, а именно ее столбцы и их типы
     *
     * @param tableName имя таблицы
     * @return схема таблицы
     * @throws Exception
     */
    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Метод main(), в котором последовательно изменять состояние таблицы, вызывая написанные выше методы,
     * и после каждого метода  для контроля выводиться схема, а именно ее столбцы и их типы, используя метод getTableScheme()
     *
     * @param args запуск
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            String table = "book";
            tableEditor.createTable(table);
            tableEditor.addColumn(table, "name", "text");
            tableEditor.addColumn(table, "title", "text");
            tableEditor.addColumn(table, "price", "int");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.renameColumn(table, "name", "author");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropColumn(table, "price");
            System.out.println(tableEditor.getTableScheme(table));
            tableEditor.dropTable(table);
            tableEditor.createTable(table);
            System.out.println(tableEditor.getTableScheme(table));
        }
    }
}
