package hospital.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils; // Correct library import

public class Department extends JFrame {

    public Department() {
        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        // JTable setup
        JTable table = new JTable();
        table.setBounds(0, 40, 700, 350);
        table.setBackground(new Color(90, 156, 163));
        table.setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setGridColor(Color.WHITE); // Set grid color for better visibility
        table.setShowGrid(true); // Enable gridlines
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 40, 690, 350); // Add scrolling support
        panel.add(scrollPane);

        // Fetch and populate table
        try {
            conn c = new conn();
            String q = "SELECT * FROM department";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet)); // Populate JTable
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Department label
        JLabel label = new JLabel("Department");
        label.setBounds(310, 11, 150, 20); // Centered position
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(400, 410, 130, 30);
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        panel.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Properly dispose of frame resources
            }
        });

        // Frame settings
        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(350, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
