package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class Room extends JFrame {

    public Room() {
        // Set frame properties
        setTitle("Room Details");
        setSize(900, 600);
        setLocation(300, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        // Add a table to the panel
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 850, 400); // Set position and size
        panel.add(scrollPane);

        // Fetch and display room details in the table
        updateRoomDetails(table);

        // Set the frame to be visible
        setVisible(true);
    }

    // Method to fetch and display updated room details in the table
    public static void updateRoomDetails(JTable table) {
        try {
            conn c = new conn(); // Create an instance of the conn class to get the connection
            String query = "SELECT room_number, room_type, capacity, availability, price FROM Room"; // Your SQL query
            Statement stmt = c.connection.createStatement(); // Use the connection to create a Statement
            ResultSet rs = stmt.executeQuery(query); // Execute the query
            table.setModel(DbUtils.resultSetToTableModel(rs)); // Use DbUtils to set the table model
        } catch (SQLException e) {
            e.printStackTrace(); // Print error if the query execution fails
        }
    }

    // Method to add a new patient and assign a room
    public static void addPatient(String patientID, String patientName, String gender, String disease, String roomNumber, double deposit) {
        try {
            conn c = new conn();
            Connection connection = c.connection;

            // First, check if the room is available
            String checkRoomQuery = "SELECT availability FROM Room WHERE room_number = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkRoomQuery);
            checkStmt.setString(1, roomNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getBoolean("availability")) {
                // Insert the new patient into the patient_info table
                String insertPatientQuery = "INSERT INTO patient_info (patient_id, patient_name, gender, disease, room_no, time, deposit) VALUES (?, ?, ?, ?, ?, NOW(), ?)";
                PreparedStatement insertStmt = connection.prepareStatement(insertPatientQuery);
                insertStmt.setString(1, patientID);
                insertStmt.setString(2, patientName);
                insertStmt.setString(3, gender);
                insertStmt.setString(4, disease);
                insertStmt.setString(5, roomNumber);
                insertStmt.setDouble(6, deposit);
                insertStmt.executeUpdate();

                // Update the room availability to false
                String updateRoomQuery = "UPDATE Room SET availability = false WHERE room_number = ?";
                PreparedStatement updateRoomStmt = connection.prepareStatement(updateRoomQuery);
                updateRoomStmt.setString(1, roomNumber);
                updateRoomStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Patient " + patientName + " has been added and room " + roomNumber + " assigned.");

                // Refresh the room details after adding a patient
                updateRoomDetails(new JTable());
            } else {
                JOptionPane.showMessageDialog(null, "Room " + roomNumber + " is not available!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to discharge a patient and free up the room
    public static void dischargePatient(String patientID) {
        try {
            conn c = new conn();
            Connection connection = c.connection;

            // Get the room number assigned to the patient
            String getRoomQuery = "SELECT room_no FROM patient_info WHERE patient_id = ?";
            PreparedStatement getRoomStmt = connection.prepareStatement(getRoomQuery);
            getRoomStmt.setString(1, patientID);
            ResultSet rs = getRoomStmt.executeQuery();

            if (rs.next()) {
                String roomNumber = rs.getString("room_no");

                // Set the room availability to true
                String updateRoomQuery = "UPDATE Room SET availability = true WHERE room_number = ?";
                PreparedStatement updateRoomStmt = connection.prepareStatement(updateRoomQuery);
                updateRoomStmt.setString(1, roomNumber);
                updateRoomStmt.executeUpdate();

                // Clear the room number for the patient in the patient_info table
                String updatePatientQuery = "UPDATE patient_info SET room_no = NULL WHERE patient_id = ?";
                PreparedStatement updatePatientStmt = connection.prepareStatement(updatePatientQuery);
                updatePatientStmt.setString(1, patientID);
                updatePatientStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Patient ID: " + patientID + " successfully discharged.");

                // Refresh the room details after discharging a patient
                updateRoomDetails(new JTable());
            } else {
                JOptionPane.showMessageDialog(null, "Patient not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to launch the Room window
    public static void main(String[] args) {
        new Room();
    }
}
