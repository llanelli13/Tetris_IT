import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    private final JLayeredPane layeredPane;
    public static final int width = 445, height = 700;

    TetrisMusic tetrisMusic = new TetrisMusic();

    public Homepage() {
        setTitle("Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        TetrisMusic.playMenuMusic();

        // Créer un label pour afficher l'image en arrière-plan
        JLabel imageLabel = new JLabel();
        ImageIcon menuImage = new ImageIcon("menuv2.png");
        imageLabel.setIcon(menuImage);

        // Positionner l'image en arrière-plan au niveau le plus bas de la pile
        imageLabel.setBounds(0, 0, width, height);
        layeredPane.add(imageLabel, Integer.valueOf(0));

        JButton play = new JButton("Jouer !");
        JButton leaderboard = new JButton("Leaderboard");
        JButton versus = new JButton("Mode versus");
        JButton countdown = new JButton("Mode Contre la montre");

        // Styliser les boutons
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        play.setFont(buttonFont);
        leaderboard.setFont(buttonFont);
        versus.setFont(buttonFont);
        countdown.setFont(buttonFont);

        play.setBackground(Color.YELLOW);
        leaderboard.setBackground(Color.GREEN);
        versus.setBackground(Color.BLUE);
        countdown.setBackground(Color.RED);

        Dimension buttonSize = new Dimension(75, 30); // Définir la taille préférée des boutons
        play.setPreferredSize(buttonSize);
        leaderboard.setPreferredSize(buttonSize);
        versus.setPreferredSize(buttonSize);
        countdown.setPreferredSize(buttonSize);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // Utiliser GridLayout pour aligner les boutons verticalement
        buttonPanel.setOpaque(false); // Assurez-vous que le panel des boutons est transparent
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(height / 2 - 100, 0, 0, 0)); // Ajouter une marge pour centrer les boutons

        buttonPanel.add(countdown);
        buttonPanel.add(play);
        buttonPanel.add(leaderboard);
        buttonPanel.add(versus);

        // Positionner le panel des boutons au-dessus de l'image au niveau supérieur de la pile
        buttonPanel.setBounds(95, 25, 250, height/2);
        layeredPane.add(buttonPanel, Integer.valueOf(1));

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
