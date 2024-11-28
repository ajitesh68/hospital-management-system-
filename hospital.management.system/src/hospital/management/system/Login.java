package hospital.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.*;

public class Login extends JFrame {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1,b2;

    Login() {
        // Username label
        JLabel nameLabel = new JLabel("Username");
        nameLabel.setBounds(40, 20, 100, 30);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        add(nameLabel);

        // Password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 70, 100, 30);  // Adjusted the y-coordinate for the password field
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        passwordLabel.setForeground(Color.BLACK);
        add(passwordLabel);

        textField =new JTextField();
        textField.setBounds(150,20,150,30);
        textField.setFont(new Font("Tahoma", Font.BOLD, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        jPasswordField =new JPasswordField();
        jPasswordField.setBounds(150,70,150,30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/img1.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label= new JLabel(imageIcon1);
        label.setBounds(300, 30, 400, 300);
        add(label);

        b1=new JButton("Login");
        b1.setBounds(40,140,120,30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        add(b1);

        b2=new JButton("Cancel");
        b2.setBounds(180,140,120,30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
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

    public static void main(String[] args) {
        new Login();
    }
}
