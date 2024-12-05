package hospital.management.system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AmbulanceService extends JFrame {

    // Constructor to set up the Ambulance Service form
    AmbulanceService() {
        setTitle("Ambulance Service");
        setSize(500, 400);  // Set the window size
        setLocationRelativeTo(null);  // Center the window on screen
        getContentPane().setBackground(new Color(109, 164, 170)); // Background color
        setLayout(null); // Set layout to null for custom positioning

        // Create labels for each field
        JLabel labelTitle = new JLabel("Ambulance Service Request", SwingConstants.CENTER);
        labelTitle.setBounds(100, 10, 300, 30);
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setFont(labelTitle.getFont().deriveFont(18f));  // Set title font size
        add(labelTitle);

        JLabel nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(30, 60, 120, 25);
        nameLabel.setForeground(Color.WHITE);
        add(nameLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(30, 100, 120, 25);
        addressLabel.setForeground(Color.WHITE);
        add(addressLabel);

        JLabel reasonLabel = new JLabel("Reason for Ambulance:");
        reasonLabel.setBounds(30, 140, 180, 25);
        reasonLabel.setForeground(Color.WHITE);
        add(reasonLabel);

        // Create text fields for user input
        JTextField nameField = new JTextField();
        nameField.setBounds(200, 60, 250, 25);
        add(nameField);

        JTextField addressField = new JTextField();
        addressField.setBounds(200, 100, 250, 25);
        add(addressField);

        JTextField reasonField = new JTextField();
        reasonField.setBounds(200, 140, 250, 25);
        add(reasonField);

        // Create a submit button
        JButton submitButton = new JButton("Request Ambulance");
        submitButton.setBounds(150, 200, 200, 30);
        submitButton.setBackground(new Color(246, 215, 118));  // Set background color
        add(submitButton);

        // Create a back button to return to the reception window
        JButton backButton = new JButton("Back to Reception");
        backButton.setBounds(150, 240, 200, 30);
        backButton.setBackground(new Color(246, 215, 118));  // Set background color
        add(backButton);

        // Set action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientName = nameField.getText();
                String address = addressField.getText();
                String reason = reasonField.getText();

                // Check if all fields are filled
                if (patientName.isEmpty() || address.isEmpty() || reason.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Insert the request into the database
                    saveRequestToDatabase(patientName, address, reason);
                }
            }
        });

        // Set action listener for back button to close the ambulance service form and go back to reception
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close the current window
                new Reception();  // Go back to Reception window
            }
        });

        // Set the window visibility
        setVisible(true);
    }

    // Method to save the ambulance request to the database
    private void saveRequestToDatabase(String patientName, String address, String reason) {
        String dbURL = "jdbc:mysql://localhost:3306/hospital_management_system";  // Updated database name
        String dbUsername = "root";  // MySQL username (usually 'root')
        String dbPassword = "ajitesh@@09";  // MySQL password (set accordingly)

        // SQL query to insert ambulance request into database
        String sql = "INSERT INTO ambulance_requests (patient_name, address, reason) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            stmt.setString(1, patientName);
            stmt.setString(2, address);
            stmt.setString(3, reason);

            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Ambulance request sent successfully for " + patientName + ".\nAddress: " + address + "\nReason: " + reason, "Request Sent", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: Unable to save request to database.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Main method to test the AmbulanceService window
    public static void main(String[] args) {
        new AmbulanceService();  // Launch the Ambulance Service window
    }
}
