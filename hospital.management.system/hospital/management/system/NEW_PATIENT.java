package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NEW_PATIENT extends JFrame {

    public JComboBox<String> comboBox, roomComboBox;
    public JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposite;
    public JRadioButton r1, r2;
    public ButtonGroup genderGroup;
    public JButton b1, b2;
    public JLabel labelDate;

    public NEW_PATIENT() {
        // Set frame properties
        setTitle("New Patient Form");
        setSize(850, 650); // Adjusted height for additional labels
        setLocation(300, 150);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 640);
        panel.setBackground(new Color(34, 45, 65)); // Set background color
        panel.setLayout(null);
        add(panel);

        // Add the form title
        JLabel labelTitle = new JLabel("NEW PATIENT FORM");
        labelTitle.setBounds(250, 20, 300, 30);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        labelTitle.setForeground(Color.WHITE);
        panel.add(labelTitle);

        // Add ID Type label and combo box
        JLabel labelID = new JLabel("ID Type:");
        labelID.setBounds(50, 80, 100, 30);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelID.setForeground(Color.WHITE);
        panel.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(180, 80, 200, 30);
        panel.add(comboBox);

        // Add Number label and text field
        JLabel labelNumber = new JLabel("Number:");
        labelNumber.setBounds(50, 130, 100, 30);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNumber.setForeground(Color.WHITE);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(180, 130, 200, 30);
        panel.add(textFieldNumber);

        // Add Name label and text field
        JLabel labelName = new JLabel("Name:");
        labelName.setBounds(50, 180, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        textName = new JTextField();
        textName.setBounds(180, 180, 200, 30);
        panel.add(textName);

        // Add Gender label and radio buttons
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(50, 230, 100, 30);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(Color.WHITE);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(180, 230, 80, 30);
        r1.setBackground(new Color(34, 45, 65));
        r1.setForeground(Color.WHITE);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(270, 230, 100, 30);
        r2.setBackground(new Color(34, 45, 65));
        r2.setForeground(Color.WHITE);
        panel.add(r2);

        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        // Add Disease label and text field
        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(50, 280, 100, 30);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(Color.WHITE);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(180, 280, 200, 30);
        panel.add(textFieldDisease);

        // Add Room No label and combo box for available rooms
        JLabel labelRoom = new JLabel("Room No:");
        labelRoom.setBounds(50, 330, 100, 30);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(Color.WHITE);
        panel.add(labelRoom);

        roomComboBox = new JComboBox<>();
        roomComboBox.setBounds(180, 330, 200, 30);
        panel.add(roomComboBox);

        // Add Date/Time label and display
        JLabel labelDateTitle = new JLabel("Date/Time:");
        labelDateTitle.setBounds(50, 380, 100, 30);
        labelDateTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDateTitle.setForeground(Color.WHITE);
        panel.add(labelDateTitle);

        labelDate = new JLabel(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        labelDate.setBounds(180, 380, 200, 30);
        labelDate.setForeground(Color.WHITE);
        panel.add(labelDate);

        // Add Deposit label and text field
        JLabel labelDeposite = new JLabel("Deposit:");
        labelDeposite.setBounds(50, 430, 100, 30);
        labelDeposite.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDeposite.setForeground(Color.WHITE);
        panel.add(labelDeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(180, 430, 200, 30);
        panel.add(textFieldDeposite);

        // Add buttons
        b1 = new JButton("Submit");
        b1.setBounds(150, 500, 100, 30);
        b1.setBackground(new Color(0, 123, 255));
        b1.setForeground(Color.WHITE);
        panel.add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(270, 500, 100, 30);
        b2.setBackground(new Color(220, 53, 69));
        b2.setForeground(Color.WHITE);
        panel.add(b2);

        // Add action listener for submit button
        b1.addActionListener(e -> insertPatientData());

        // Add action listener for cancel button
        b2.addActionListener(e -> {
            // Clear all fields when the Cancel button is pressed
            resetFormFields();
        });

        // Fetch available rooms from the database
        fetchAvailableRooms();

        // Set frame visibility
        setVisible(true);
    }

    public void fetchAvailableRooms() {
        try {
            // Query to fetch available rooms from the Room table
            String query = "SELECT room_number FROM Room WHERE availability = TRUE";

            conn c = new conn();
            Connection connection = c.connection;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear existing items
            roomComboBox.removeAllItems();

            // Populate combo box with available rooms
            while (resultSet.next()) {
                roomComboBox.addItem(resultSet.getString("room_number"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching available rooms: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertPatientData() {
        try {
            // Generate a unique patient ID (UUID)
            String patientID = "PAT-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // Get the form data
            String name = textName.getText();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : "";
            String disease = textFieldDisease.getText();
            String roomNo = (String) roomComboBox.getSelectedItem(); // Get the selected room number
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            double deposit;

            // Parse the deposit amount and validate input
            try {
                deposit = Double.parseDouble(textFieldDeposite.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid deposit amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert data into patient_info table
            String query = "INSERT INTO patient_info (patient_id, patient_name, gender, disease, room_no, time, deposit) VALUES (?, ?, ?, ?, ?, ?, ?)";
            conn c = new conn();
            PreparedStatement preparedStatement = c.connection.prepareStatement(query);
            preparedStatement.setString(1, patientID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, disease);
            preparedStatement.setString(5, roomNo);
            preparedStatement.setString(6, dateTime);
            preparedStatement.setDouble(7, deposit);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Optionally, update room availability to false
            updateRoomAvailability(roomNo);

            // Reset the form after submission
            resetFormFields();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error inserting data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateRoomAvailability(String roomNo) {
        try {
            String query = "UPDATE Room SET availability = FALSE WHERE room_number = ?";
            conn c = new conn();
            PreparedStatement preparedStatement = c.connection.prepareStatement(query);
            preparedStatement.setString(1, roomNo);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating room availability: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resetFormFields() {
        textFieldNumber.setText("");
        textName.setText("");
        genderGroup.clearSelection();
        textFieldDisease.setText("");
        roomComboBox.setSelectedIndex(0);
        textFieldDeposite.setText("");
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
