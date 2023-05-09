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
    private int winner;

    public HorseRacingGame() {
        super("Horse Race Simulator");
        setSize(600, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        
        ImageIcon icon = new ImageIcon("HorseLogo.png"); // Image needs to be in project folder.
        setIconImage(icon.getImage());
        JPanel horsePanel = new JPanel(new GridLayout(4, 2)); //setup the "racetrack"
        for (int i = 0; i < 4; i++) {//runs arrays until 4
            JLabel horseLabel = new JLabel("Horse " + (i + 1));
            horseLabels[i] = horseLabel;
            horsePanel.add(horseLabel);

            JLabel progressLabel = new JLabel();
            progressLabels[i] = progressLabel;
            horsePanel.add(progressLabel);
        }
        add(horsePanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(startButton);

        JLabel betLabel = new JLabel("Bet on horse (1-4):");
        controlPanel.add(betLabel);
        controlPanel.add(betField);
        controlPanel.add(betButton);
        add(controlPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startRace());
        betButton.addActionListener(e -> placeBet(winner));

        setVisible(true);
    }

    private void startRace() {//method for the horses moving
        startButton.setEnabled(false);
        betButton.setEnabled(false);

        Random random = new Random(); //this is the random for the positions of the horses
        int[] positions = new int[4]; //position array for the horses

        int winner = -1; // store the winner horse's number
        boolean finished = false; //this boolean stops the horses from moving when true
        while (!finished) {//runs when not finished
            for (int j = 0; j < 4; j++) {//this takes care of the random positioning of the sprites
                int newPosition = positions[j] + random.nextInt(3) + 1;
                if (newPosition > 50) {
                    newPosition = 50;
                }
                positions[j] = newPosition;
                updateProgress(j, positions[j]);
                
                if (positions[j] == 50) {//when the first horse to finish it makes it winner 
                    winner = j;
                    finished = true;//when first place finishes all horses stop
                    break;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        JOptionPane.showMessageDialog(this, "Horse " + (winner + 1) + " wins!");

        // calls placeBet with the winner horse's number
        placeBet(winner + 1);
        
        startButton.setEnabled(true);
        betButton.setEnabled(true);
    }

    private void placeBet(int winnerHorse) { //this method tells the user whether their bet was correct or not
        try {//all of these statements check if input is correct or whether their bet was any good
            int bet = Integer.parseInt(betField.getText());
            if (bet < 1 || bet > 4) {
                JOptionPane.showMessageDialog(this, "Invalid bet! Choose a horse number between 1 and 4.");
                return;
            }
            if (bet != winnerHorse) { 
                JOptionPane.showMessageDialog(this, "You lose!");
            } else {
                JOptionPane.showMessageDialog(this, "You win!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid bet! Enter a number between 1 and 4.");
        }
    }

    private void updateProgress(int horseIndex, int position) {//this method is called in the startrace method and used to update the position of the horses. 
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 50; i++) {//array 50 represents the number of posistions on the race track
            if (i == position) {
                builder.append(horseIndex + 1);
            } else {
                builder.append(" ");
            }
        }
        progressLabels[horseIndex].setText(builder.toString());
    }
    public static void main(String[] args) { 
        HorseRacingGame game = new HorseRacingGame();

        // tells user to place a bet before starting the race
        int bet = 0;
        //runs if there bet is between 1-4 
        while (bet < 1 || bet > 4) {
            String input = JOptionPane.showInputDialog(game, "Enter a bet on horse (1-4):");
            if (input == null) {
                // pressing cancel exits the program
                System.exit(0);
            }
            try {
                bet = Integer.parseInt(input);
                if (bet < 1 || bet > 4) {
                    JOptionPane.showMessageDialog(game, "Invalid bet! Choose a horse number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(game, "Invalid bet! Enter a number between 1 and 4.");
            }
        }

        //place the bet
        game.betField.setText(Integer.toString(bet));

        //start the race
        game.startRace();
        // game.placeBet(bet);
    }
}