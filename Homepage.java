import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    public Homepage() {
        setTitle("Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton bouton1 = new JButton("Play ! ");
        JButton bouton2 = new JButton("Leaderboard");
        JButton bouton3 = new JButton("Exit");

        bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre actuelle
                Gamepage gamePage = new Gamepage();
                gamePage.setVisible(true);
            }
        });

        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Scoreboard score = new Scoreboard();
                score.setVisible(true);
            }
        });

        bouton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(bouton1);
        add(bouton2);
        add(bouton3);
    }
}

