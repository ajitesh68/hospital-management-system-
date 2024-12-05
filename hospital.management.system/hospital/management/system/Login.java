package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame {
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton, cancelButton;

    public Login() {
        setTitle("Login");
        setLayout(null);
        setBounds(400, 200, 400, 300);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 160, 100, 30);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 160, 100, 30);
        add(cancelButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn();
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    String query = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
                    ResultSet rs = c.statement.executeQuery(query);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        setVisible(false);
                        // Navigate to the next screen (Dashboard or another JFrame)
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Credentials!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}

