package hospital.management.system;

import javax.swing.*;
import java.awt.*;

public class Room extends JFrame {

    // Constructor for the Room frame
    public Room() {
        // Set frame properties
        setTitle("Room Details");
        setSize(900, 600);
        setLocation(300, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create the main panel
        JPanel panel = createPanel();
        add(panel);

        // Add the image to the panel
        addImage(panel);

        // Add a table to the panel
        addTable(panel);

        // Set the frame to be visible
        setVisible(true);
    }

    // Create the main panel with background color and layout
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        return panel;
    }

    // Add an image to the panel
    private void addImage(JPanel panel) {
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/your-image.png")); // Replace with your image path
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 200, 200, 200);
        panel.add(label);
    }

    // Add a table with scroll pane to the panel
    private void addTable(JPanel panel) {
        JTable table = new JTable();
        table.setBackground(new Color(90, 156, 163));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 500, 400); // Set position and size
        panel.add(scrollPane);
    }

    // Main method to launch the Room window
    public static void main(String[] args) {
        new Room();
    }
}
