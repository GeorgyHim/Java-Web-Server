import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class Main {
    private static String query = "SELECT * FROM jc_contact";

    static void connectWithDriverManager() {
        try (Connection conn = DriverManager
                .getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "postgres", "supergeorgyuser555");
             PreparedStatement preparedStatement = conn.prepareStatement(query) ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name"));
            }
            // У меня работает, вывело имена из тестовой таблицы jc_contact
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void connectWithDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"localhost"});
        dataSource.setPortNumbers(new int[]{5432});
        dataSource.setDatabaseName("test_db");
        dataSource.setUser("postgres");
        dataSource.setPassword("supergeorgyuser555");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query) ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connectWithDriverManager();
        connectWithDataSource();
    }
}
