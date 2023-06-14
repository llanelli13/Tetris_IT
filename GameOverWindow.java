import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {

    private int points;

    public GameOverWindow(int points) {
        this.points = points;
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
                Leaderboard.addEntry(pseudo, points); // Ajouter le nom et les points dans le fichier leaderboard
                dispose();
                new Leaderboard();
            }
        });

        setVisible(true);
    }
}
