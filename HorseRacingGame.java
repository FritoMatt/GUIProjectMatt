import javax.swing.*;
import java.awt.*;
import java.util.Random;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HorseRacingGame extends JFrame {
    private JFrame frame;
    private final JLabel[] horseLabels = new JLabel[4];
    private final JLabel[] progressLabels = new JLabel[4];
    private final JButton startButton = new JButton("Start Race");
    private final JTextField betField = new JTextField(10);
    private final JButton betButton = new JButton("Place Bet");


    public HorseRacingGame() {
        super("Horse Race");
        setSize(600, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        // Set icon.
        ImageIcon icon = new ImageIcon("cookie.png"); // Image needs to be in project folder.
        setIconImage(icon.getImage());
    }
    
}
