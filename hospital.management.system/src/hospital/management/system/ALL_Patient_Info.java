package hospital.management.system;

import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ALL_Patient_Info extends JFrame {
    ALL_Patient_Info(){


        JPanel panel= new JPanel();
        panel.setBounds(5,5,890,590);
        panel.setLayout(null);
        panel.setBackground(new Color(90,156,163));            
        add (panel);

        JTable table = new JTable();
        table.setBounds(10,40,900,450);
        table.setBackground(new Color(90,156,163));
        // table.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(table);


        try{
            conn c = new conn();
            String q= "select * from Patient_Info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label1= new JLabel("ID");
        label1.setBounds(31,11,100,14);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2= new JLabel("Number");
        label2.setBounds(150,11,100,14);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3= new JLabel("Name");
        label3.setBounds(270,11,100,14);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4= new JLabel("Gender");
        label4.setBounds(360,11,100,14);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);
        
        JLabel label5= new JLabel("Disease");
        label5.setBounds(480,11,100,14);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label5);

        JLabel label6= new JLabel("Time");
        label6.setBounds(680,11,100,14);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label6);

        JLabel label8= new JLabel("Deposit");
        label8.setBounds(800,11,100,14);
        label8.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label8);

        JButton button=new JButton("BACK");
            button.setBounds(450,510,120,30);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            panel.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                }
            });

        setUndecorated(true); 
        setSize(900,600);
        setLayout(null);
        setLocation(430,100);
        setVisible(true);
    }

}