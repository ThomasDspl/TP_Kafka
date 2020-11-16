package org.kafka.tp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //private final String url = "jdbc:postgresql://localhost/tpKafka";
    private final String url = "jdbc:postgres://farkmhhu:1SYc1FfQl6KC0OFg3nyeUbvoQf5YMSWW@kandula.db.elephantsql.com:5432/farkmhhu";
    //private final String user = "postgres";
    private final String user = "farkmhhu";
    private final String password = "1SYc1FfQl6KC0OFg3nyeUbvoQf5YMSWW";
    //private final String password = "root";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Success");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}

