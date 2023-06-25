import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    private final JPanel contentPane;
    public static final int width = 445, height = 700;

    TetrisMusic tetrisMusic = new TetrisMusic();

    public Homepage() {
        setTitle("Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Utilisation de BoxLayout
        setContentPane(contentPane);

        TetrisMusic.playMenuMusic();

        JButton play = new JButton("Jouer !");
        JButton leaderboard = new JButton("Leaderboard");
        JButton versus = new JButton("Mode versus");
        JButton countdown = new JButton("Mode Contre la montre");

        JPanel buttonPanel = new JPanel(new FlowLayout()); // JPanel pour les boutons
        buttonPanel.add(countdown);
        buttonPanel.add(play);
        buttonPanel.add(leaderboard);
        buttonPanel.add(versus);
        contentPane.add(buttonPanel); // Ajouter le panel des boutons en dessous de l'image

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TetrisMusic.stopCurrentMusic();
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new GameWindow();
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TetrisMusic.stopCurrentMusic();
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new Leaderboard();
            }
        });

        versus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.stopCurrentMusic();
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new ModeVersus();
            }
        });

        countdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TetrisMusic.stopCurrentMusic();
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new ChoseTimer();
            }
        });

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage();
            }
        });
    }
}
