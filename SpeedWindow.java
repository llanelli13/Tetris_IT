import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedWindow extends JFrame {

    private String mode;
    public static final int width = 445, height = 700;

    public SpeedWindow(String mode) {
        this.mode = mode;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SpeedWindow");
        setSize(250, 400);
        setLocationRelativeTo(null); // Centre la fenêtre au milieu de l'écran

        // Crée un JPanel avec GridBagLayout pour aligner les éléments au milieu
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0); // Espacement vertical entre les éléments

        // Ajoute le texte "Choisissez une vitesse :"
        JLabel label = new JLabel("Choisissez une vitesse :");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(label, constraints);

        // Crée les boutons
        JButton n1 = new JButton("x1");
        JButton n2 = new JButton("x2");
        JButton n3 = new JButton("x3");

        // Ajoute les boutons au JPanel avec les contraintes de mise en page
        constraints.gridy = 1;
        panel.add(n1, constraints);

        constraints.gridy = 2;
        panel.add(n2, constraints);

        constraints.gridy = 3;
        panel.add(n3, constraints);

        // Ajoute le JPanel à la frame
        add(panel);

        n1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                if (mode.equals("Solo")) {
                    new GameWindow(600);
                } else if (mode.equals("Versus")) {
                    new ModeVersus(600);
                }

            }
        });

        n2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                if (mode.equals("Solo")) {
                    new GameWindow(400);
                } else if (mode.equals("Versus")) {
                    new ModeVersus(400);
                }
            }
        });
        n3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                if (mode.equals("Solo")) {
                    new GameWindow(200);
                } else if (mode.equals("Versus")) {
                    new ModeVersus(200);
                }
            }
        });

        setVisible(true);
    }
}
