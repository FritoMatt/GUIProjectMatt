import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JButton playButton;

    public Menu() {
        setTitle("Menu Screen");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GREEN);

        Font font = new Font("Times New Roman", Font.PLAIN, 16);

        playButton = new JButton("Play");
        playButton.setFont(font);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HorseRacingGame.main(null);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.GREEN);
        panel.add(playButton, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
    private void startGame() {
        dispose(); // Close the menu screen
    
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HorseRacingGame horseRacingGame = new HorseRacingGame();
                JFrame frame = new JFrame("Horse Race Simulator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(Color.GREEN);
                frame.add(horseRacingGame);
                frame.pack();
                frame.setVisible(true);
                horseRacingGame.startRace();
            }
        });
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menuScreen = new Menu();
                
            }
        });
    }
}
