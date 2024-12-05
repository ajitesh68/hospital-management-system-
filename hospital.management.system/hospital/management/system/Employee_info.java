package hospital.management.system;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;


public class Employee_info extends JFrame {

    Employee_info() {

        // Panel setup
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setLayout(null);
        panel.setBackground(new Color(109, 164, 170));
        add(panel);

        // JTable setup
        JTable table = new JTable();
        table.setBounds(10, 34, 980, 450);
        table.setBackground(new Color(109, 164, 170));
        // table.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(table);

        try {
            conn c = new conn();  // Assuming conn class handles database connection
            String q = "SELECT * FROM EMP_INFO";  // Modify as per your database table name
            ResultSet resultSet = c.statement.executeQuery(q);  // Execute query to fetch data
            table.setModel(DbUtils.resultSetToTableModel(resultSet));  // Populate table with data

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Label setup for Employee Info headers
        JLabel label1 = new JLabel("Name");
        label1.setBounds(41, 9, 70, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Age");
        label2.setBounds(180, 9, 70, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Phone Number");
        label3.setBounds(350, 9, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label4 = new JLabel("Salary");
        label4.setBounds(550, 9, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);

        JLabel label5 = new JLabel("Gmail");
        label5.setBounds(730, 9, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);

        JLabel label6 = new JLabel("Aadhar Number");
        label6.setBounds(830, 9, 150, 20);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label6);

        // Button for going back
        JButton button = new JButton("BACK");
        button.setBounds(350, 500, 120, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);  // Close current frame
            }
        });

        // Frame settings
        setUndecorated(true);
        setSize(1000, 600);
        setLayout(null);
        setLocation(350, 230);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();  // Launch Employee Info window
    }
}
