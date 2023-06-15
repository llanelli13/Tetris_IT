import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.CheckedOutputStream;

public class Homepage extends JFrame {
    private JPanel contentPane;
    public static final int width = 445, height = 700;

    public Homepage() {
        setTitle("Menu");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        setContentPane(contentPane);

        JButton play = new JButton("Jouer !");
        JButton leaderboard = new JButton("Leaderboard");
        JButton versus = new JButton("Mode versus");
        JButton countdown = new JButton("Mode Contre la montre");

        contentPane.add(countdown);
        contentPane.add(play);
        contentPane.add(leaderboard);
        contentPane.add(versus);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Homepage();
            }
        });
    }
}
