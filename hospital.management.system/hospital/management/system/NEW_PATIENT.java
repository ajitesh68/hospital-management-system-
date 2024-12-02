package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NEW_PATIENT extends JFrame {

    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldRoom, textFieldDeposite;
    JRadioButton r1, r2;
    ButtonGroup genderGroup;
    JButton b1, b2;
    JLabel labelDate;

    NEW_PATIENT() {
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

        // Add an image to the panel
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 20, 200, 200);
        panel.add(label);

        // Add the form title
        JLabel labelTitle = new JLabel("NEW PATIENT FORM");
        labelTitle.setBounds(250, 20, 300, 30);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        labelTitle.setForeground(Color.WHITE);
        panel.add(labelTitle);

        // Add ID label and combo box
        JLabel labelID = new JLabel("ID Type:");
        labelID.setBounds(50, 80, 100, 30);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelID.setForeground(Color.WHITE);
        panel.add(labelID);

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(180, 80, 200, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setForeground(Color.BLACK);
        comboBox.setBackground(Color.WHITE);
        panel.add(comboBox);

        // Add number label and text field
        JLabel labelNumber = new JLabel("Number:");
        labelNumber.setBounds(50, 130, 100, 30);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNumber.setForeground(Color.WHITE);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(180, 130, 200, 30);
        panel.add(textFieldNumber);

        // Add name label and text field
        JLabel labelName = new JLabel("Name:");
        labelName.setBounds(50, 180, 100, 30);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        textName = new JTextField();
        textName.setBounds(180, 180, 200, 30);
        panel.add(textName);

        // Add gender label and radio buttons
        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(50, 230, 100, 30);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(Color.WHITE);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r1.setForeground(Color.WHITE);
        r1.setBackground(new Color(34, 45, 65));
        r1.setBounds(180, 230, 80, 30);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        r2.setForeground(Color.WHITE);
        r2.setBackground(new Color(34, 45, 65));
        r2.setBounds(270, 230, 100, 30);
        panel.add(r2);

        // Group the radio buttons
        genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        // Add disease label and text field
        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(50, 280, 100, 30);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(Color.WHITE);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(180, 280, 200, 30);
        panel.add(textFieldDisease);

        // Add room label and text field
        JLabel labelRoom = new JLabel("Room No.:");
        labelRoom.setBounds(50, 330, 100, 30);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(Color.WHITE);
        panel.add(labelRoom);

        textFieldRoom = new JTextField();
        textFieldRoom.setBounds(180, 330, 200, 30);
        panel.add(textFieldRoom);

        // Add date label and current date/time display
        JLabel labelDateTitle = new JLabel("Date/Time:");
        labelDateTitle.setBounds(50, 380, 100, 30);
        labelDateTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDateTitle.setForeground(Color.WHITE);
        panel.add(labelDateTitle);

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        labelDate = new JLabel(formattedDateTime);
        labelDate.setBounds(180, 380, 200, 30);
        labelDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelDate.setForeground(Color.WHITE);
        panel.add(labelDate);

        // Add deposit label and text field
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
        b1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(270, 500, 100, 30);
        b2.setBackground(new Color(220, 53, 69));
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(b2);

        // Set frame visibility
        setVisible(true);
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
