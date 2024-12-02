package hospital.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    Login() {
        // Username label
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(40, 20, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        add(nameLabel);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 70, 100, 30); // Adjusted the y-coordinate for the password field
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);
        add(passwordLabel);

        // Username text field
        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        // Password field
        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        // Image icon
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/img1.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(300, 30, 400, 300);
        add(label);

        // Login button
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        // Cancel button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        // Set background color for the frame
        getContentPane().setBackground(new Color(109, 164, 170));

        // Set frame size, location, and layout
        setSize(750, 300);
        setLocation(400, 270);
        setLayout(null);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn(); // Assuming you have a conn class for database connection
                String user = textField.getText();
                String pass = new String(jPasswordField.getPassword());

                // Corrected SQL query syntax
                String q = "SELECT * FROM login WHERE ID = '" + user + "' AND PW = '" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new test(); // Assuming 'test' is another frame class
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
