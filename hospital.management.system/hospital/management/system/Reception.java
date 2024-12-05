package hospital.management.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Reception extends JFrame {

    Reception() {
        // Set up the main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 160, 1905, 970);
        panel.setBackground(new Color(109, 164, 170));
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5, 5, 1905, 150);
        panel1.setBackground(new Color(109, 164, 170));
        add(panel1);

        // Set icons
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/doc.png"));
        Image image = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(image);
        JLabel label = new JLabel(i2);
        label.setBounds(1300, 0, 250, 250);
        panel1.add(label);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icon/hospital.png"));
        Image image1 = i11.getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT);
        ImageIcon i22 = new ImageIcon(image1);
        JLabel label1 = new JLabel(i22);
        label1.setBounds(1000, 50, 310, 90);
        panel1.add(label1);

        // Add buttons and their actions
        addButton(panel1, "Add New Patient", 30, 15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NEW_PATIENT();  // Create new instance of NEW_PATIENT
            }
        });

        addButton(panel1, "Room", 30, 58, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Room();
            }
        });

        addButton(panel1, "Department", 30, 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Department();
            }
        });

        addButton(panel1, "All Employee Info", 270, 15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Employee_info();
            }
        });

        addButton(panel1, "Patient Info", 270, 58, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ALL_Patient_Info();
            }
        });

        addButton(panel1, "Patient Discharge", 270, 100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new patient_discharge();
            }
        });

        // Add "Ambulance Service" button
        addButton(panel1, "Hospital Ambulance", 510, 15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AmbulanceService(); // Create new instance of AmbulanceService
            }
        });

        addButton(panel1, "Log out", 510, 58, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Get the screen dimensions and set the window size accordingly
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;

        // Set the JFrame to cover the entire screen
        setSize(width, height);
        setLocation(0, 0);  // Make sure the frame is positioned at the top-left corner

        setUndecorated(true);  // Optional: remove window borders
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
    }

    private void addButton(JPanel panel, String text, int x, int y, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 200, 30);
        button.setBackground(new Color(246, 215, 118));
        panel.add(button);
        button.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        new Reception();  // Launch the Reception window
    }
}
