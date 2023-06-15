import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {

    private int points;
    private  String mode;

    public GameOverWindow(int points, String mode) {
        this.points = points;
        this.mode = mode;

        setTitle("GAME OVER!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JTextArea score = new JTextArea("Score: " + points);
        score.setEditable(false); // Make the JTextArea non-editable
        JTextField name = new JTextField(15);
        JButton ajout = new JButton("Ajouter score !");
        JButton home = new JButton("Home");

        add(score);
        add(name);
        add(home);
        add(ajout);

        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Homepage();
            }
        });

        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pseudo = name.getText();
                String leaderboardFile;

                if (mode.equals("SOLO") || mode.equals("VERSUS")) {
                    leaderboardFile = "leaderboard.txt";
                } else if (mode.equals("COUNTDOWN")) {
                    leaderboardFile = "leaderboardCountDown.txt";
                } else {
                    // Mode de jeu non reconnu, traitement d'erreur
                    System.out.println("Mode de jeu non reconnu.");
                    return;
                }

                Leaderboard.addEntry(pseudo, points, leaderboardFile);
                dispose();
                new Leaderboard();
            }
        });

        setVisible(true);
    }
}
