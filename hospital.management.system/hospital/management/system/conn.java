package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conn {
    public Connection connection;
    public Statement statement;

    public conn() {
        try {
            System.out.println("INSIDE CONN");

            // Establish connection to the database
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root",
                    "ajitesh@@09"
            );

            // Create a statement object to execute queries
            statement = connection.createStatement();
            System.out.println("Connection established successfully!");

        } catch (SQLException e) {
            System.out.println("INSIDE Error");
            e.printStackTrace(); // Print error details
        }
    }
}
