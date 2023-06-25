import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.LineUnavailableException;



public class Homepage extends JFrame {
    private final JPanel contentPane;
    public static final int width = 445, height = 700;
    private static Audio audioplayer = new Audio();

    ImageIcon imageIcon = new ImageIcon("C:\\Users\\pierr\\Documents\\GitHub\\Tetris_IT\\tetris.jpg");

    public Homepage(Audio audioplayer) {
        setTitle("Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.audioplayer = audioplayer;

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS)); // Utilisation de BoxLayout
        setContentPane(contentPane);

        JButton play = new JButton("Jouer !");
        JButton leaderboard = new JButton("Leaderboard");
        JButton versus = new JButton("Mode versus");
        JButton countdown = new JButton("Mode Contre la montre");

        JLabel fond = new JLabel(imageIcon);
        fond.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer l'image horizontalement

        contentPane.add(fond); // Ajouter l'image en haut du JPanel

        JPanel buttonPanel = new JPanel(new FlowLayout()); // JPanel pour les boutons
        buttonPanel.add(countdown);
        buttonPanel.add(play);
        buttonPanel.add(leaderboard);
        buttonPanel.add(versus);
        contentPane.add(buttonPanel); // Ajouter le panel des boutons en dessous de l'image

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new GameWindow();
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Leaderboard();
            }
        });

        versus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new ModeVersus();
            }
        });

        countdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ChoseTimer();
            }
        });

        setVisible(true);
    }

    public static void playClear() {
        audioplayer.playClearLine();
    }

    public static void playGame() {
        audioplayer.playGameOver();
    }

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            try {
                Audio audioplayer = new Audio();
                new Homepage(audioplayer);
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    });
}
}