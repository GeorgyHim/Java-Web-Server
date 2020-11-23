import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String query = "SELECT * FROM jc_contact";

        try (Connection conn = DriverManager
                .getConnection("jdbc:postgresql://127.0.0.1:5432/test_db", "postgres", "supergeorgyuser555");
             PreparedStatement preparedStatement = conn.prepareStatement(query) ) {

//            if (conn != null) {
//                System.out.println("Connected to the database!");
//            } else {
//                System.out.println("Failed to make connection!");
//            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name"));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
