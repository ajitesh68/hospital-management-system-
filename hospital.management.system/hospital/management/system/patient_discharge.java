package hospital.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Choice;

public class patient_discharge extends JFrame {

    patient_discharge() {

        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        // "Check-Out" label
        JLabel label = new JLabel("Check-Out");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.white);
        panel.add(label);

        // Customer ID label and choice
        JLabel label2 = new JLabel("Customer ID");
        label2.setBounds(30, 80, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 20);
        panel.add(choice);

        try {
            conn c = new conn();
            String q = "select * from patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            while (resultSet.next()) {
                choice.add(resultSet.getString("patient_id")); // Use patient_id instead of number
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number label and text field
        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30, 130, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField RNo = new JTextField();
        RNo.setBounds(200, 130, 150, 20);
        panel.add(RNo);

        // In Time label and text field
        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30, 180, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField INTime = new JTextField();
        INTime.setBounds(200, 180, 150, 20);
        panel.add(INTime);

        // Out Time label and text field
        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30, 230, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField OUTTime = new JTextField();
        OUTTime.setBounds(200, 230, 150, 20);
        panel.add(OUTTime);

        // Discharge button
        JButton Discharge = new JButton("Discharge");
        Discharge.setBounds(30, 300, 120, 30);
        Discharge.setBackground(Color.BLACK);
        Discharge.setForeground(Color.WHITE);
        panel.add(Discharge);

        // Action listener for Discharge button
        Discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    // Perform discharge operations
                    String patientId = choice.getSelectedItem(); // Use patient_id
                    String roomNumber = RNo.getText();
                    String outTime = OUTTime.getText();

                    // Update the room availability
                    String updateRoomQuery = "UPDATE Room SET availability = TRUE WHERE room_number = '" + roomNumber + "'"; // Adjust room availability
                    c.statement.executeUpdate(updateRoomQuery);

                    // Remove patient from the table
                    String deleteQuery = "DELETE FROM patient_info WHERE patient_id = '" + patientId + "'"; // Use patient_id
                    c.statement.executeUpdate(deleteQuery);

                    // Optionally, add out-time to patient data (if required)
                    String updateOutTimeQuery = "UPDATE patient_info SET time = ? WHERE patient_id = '" + patientId + "'";
                    PreparedStatement pst = c.connection.prepareStatement(updateOutTimeQuery);
                    pst.setString(1, outTime);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Patient Discharged Successfully!");
                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Check button
        JButton Check = new JButton("Check");
        Check.setBounds(170, 300, 120, 30);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.WHITE);
        panel.add(Check);

        // Action listener for Check button
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn c = new conn();
                try {
                    String selectedPatient = choice.getSelectedItem(); // Use patient_id
                    ResultSet resultSet = c.statement.executeQuery("SELECT * FROM patient_info WHERE patient_id = '" + selectedPatient + "'"); // Use patient_id
                    if (resultSet.next()) {
                        RNo.setText(resultSet.getString("room_no"));
                        INTime.setText(resultSet.getString("time"));
                        OUTTime.setText(new Date().toString());  // Display the current date and time for discharge
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Back button
        JButton Back = new JButton("Back");
        Back.setBounds(300, 300, 120, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);

        // Action listener for Back button
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame settings
        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
