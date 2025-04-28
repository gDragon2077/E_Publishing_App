import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class Frame_Main extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JTextField textField;
    ImageIcon searchIcon;
    JComboBox produseBox;

    Frame_Main(){
        // Crearea Frameului pentru pagina principala

        this.setTitle("Carturaresti");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(960, 540);
        this.setLayout(null);


        ImageIcon image = new ImageIcon("Iconita.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(Color.white);

        searchIcon = new ImageIcon("search.png");
        // Partea de sus de navigare ------------------------------------------------------------

        //Layerele de culoare
        JLabel label1= new JLabel();
        label1.setOpaque(true);
        label1.setBackground(new Color(92, 128, 1));
        label1.setBounds(0, 0, 960 , 42);

        JLabel label2= new JLabel();
        label2.setOpaque(true);
        label2.setBackground(Color.white);
        label2.setBounds(160, 0, 630 , 42);

        JLabel back1 = new JLabel();
        back1.setOpaque(true);
        back1.setBackground(Color.lightGray);
        back1.setBounds(0, 0, 960, 540);

        JLabel back2 = new JLabel();
        back2.setOpaque(true);
        back2.setBackground(Color.white);
        back2.setBounds(160, 0, 630, 540);

        //Suprapunerea layerelor
        JLayeredPane layeredPane = new JLayeredPane();
        JLayeredPane backPane = new JLayeredPane();

        layeredPane.setBounds(0, 0, 960, 42);
        backPane.setBounds(0, 40, 960, 540);

        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER); // pune pe layerul de sus
        layeredPane.add(label2, JLayeredPane.DRAG_LAYER); // pune pe layerul de jos

        backPane.add(back1, JLayeredPane.DEFAULT_LAYER);
        backPane.add(back2, JLayeredPane.DRAG_LAYER);
        //Butoane ------------------------------------------------------------

        //Probabil Shopping Cartul
        button1 = new JButton();
        button1.setBounds(700, 5, 30, 30);
        button1.addActionListener(this);

        //Probabil butonul de Login
        button2 = new JButton();
        button2.setBounds(740, 5, 30, 30);
        button2.addActionListener(this);

        //Text field de search
        textField = new JTextField();
        textField.setBounds(280, 5, 150, 30);
        textField.setFont(new Font("Comic Sans", Font.PLAIN, 15));
        textField.setText("Cautare");

        //Button submit la search
        button3 = new JButton("Submit");
        button3.setBounds(430, 5, 80 ,30);
        button3.addActionListener(this);
        button3.setIcon(searchIcon);

        //ComboBox pentru produse/carti
        String[] categorii = {"Categorii", "manga", "fantezie", "romanesti"};  // inca e de lucrat la asta------------------

        produseBox = new JComboBox(categorii);
        produseBox.setBounds(165, 5, 100, 30);
        produseBox.addActionListener(this);

        // Adugarea elementelor la Frame ------------------------------------------------------------
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(textField);
        this.add(produseBox);
        this.add(layeredPane);
        this.add(backPane);


        this.setVisible(true);
    }


    // Actiunile pentru butoane ------------------------------------------------------------ inca de lucrat


    @Override
    public void actionPerformed(ActionEvent e) {
        //cos
        if (e.getSource() == button1) {
            System.out.println("poo");
        }
        //login
        if (e.getSource() == button2) {
            System.out.println("poo_sad");
        }
        //search box
        if (e.getSource() == button3) {
            System.out.println("Ai Cautat : " + textField.getText());
        }
        //categorii carti
        if (e.getSource()==produseBox){
            System.out.println(produseBox.getSelectedItem());
        }
    }
}
