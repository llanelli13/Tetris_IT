import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    public Homepage() {
        setTitle("Menu de Redirection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton bouton1 = new JButton("Play ! ");
        JButton bouton2 = new JButton("Leaderboard");
        JButton bouton3 = new JButton("Exit");

        bouton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new JFrame("Game");
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        bouton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new JFrame("Page 2");
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        bouton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new JFrame("Page 3");
                frame.setSize(300, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

        add(bouton1);
        add(bouton2);
        add(bouton3);
    }
}

