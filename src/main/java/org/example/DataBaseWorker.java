package org.example;

import java.sql.*;

public class DataBaseWorker {
    private static final String dataBaseURL = "jdbc:postgresql://192.168.1.15:5432/testDB";
    private static final String dataBaseUser = "admin";
    private static final String dataBasePassword = "123";

    public User getUserByLogin(String login) {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {

            connection = DriverManager.getConnection(
                    dataBaseURL, dataBaseUser, dataBasePassword);

            statement = connection.createStatement();

            String sql =
                    "SELECT f.login, f.password, f.date, s.email " +
                            "FROM firsttable f " +
                            "JOIN secondtable s ON f.login = s.login " +
                            "WHERE f.login = '" + login + "'";

            rs = statement.executeQuery(sql);

            if (rs.next()) {
                return new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getDate("date"),
                        rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (statement != null) statement.close(); } catch (Exception ignored) {}
            try { if (connection != null) connection.close(); } catch (Exception ignored) {}
        }

        return null;
    }

    public int insertUser(User user) {

        String insertFirst =
                "INSERT INTO firsttable(login, password, date) " +
                        "VALUES (?, ?, ?)";

        String insertSecond =
                "INSERT INTO secondtable(login, email) " +
                        "VALUES (?, ?)";

        try (Connection connection =
                     DriverManager.getConnection(
                             dataBaseURL, dataBaseUser, dataBasePassword)) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement ps1 =
                            connection.prepareStatement(insertFirst);

                    PreparedStatement ps2 =
                            connection.prepareStatement(insertSecond)
            ) {

                ps1.setString(1, user.getLogin());
                ps1.setString(2, user.getPassword());
                ps1.setDate(3, user.getCreatedDate());

                int rows1 = ps1.executeUpdate();

                ps2.setString(1, user.getLogin());
                ps2.setString(2, user.getEmail());

                int rows2 = ps2.executeUpdate();

                connection.commit();

                return rows1 + rows2;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
