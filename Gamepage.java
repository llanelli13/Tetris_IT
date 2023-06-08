import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeoutException;


public class Gamepage extends JFrame{

    private JPanel grille;
    private Timer loop;
    private Timer temps;
    private JLabel scoreLabel;
    public Gamepage(){
        setTitle("Jeu Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);

        grille = new JPanel();
        grille.setLayout(new GridLayout(20, 10)); // Grille de 20 lignes x 10 colonnes

        // Ajouter les cellules à la grille
        for (int i = 0; i < 200; i++) {
            JPanel cellule = new JPanel();
            cellule.setBackground(Color.WHITE);
            cellule.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            grille.add(cellule);
        }


        JLabel timelabel = new JLabel("Time : 0");
        JLabel loopLabel = new JLabel("Score : 0");

        JPanel panelDroit = new JPanel();
        panelDroit.setLayout(new GridLayout(3, 1));
        panelDroit.add(timelabel);
        panelDroit.add(loopLabel);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());


        // Timer loop
        loop = new Timer(500, new ActionListener() {
            int n = 0;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                n++;
                loopLabel.setText("Score : " + n);
            }
        });

        temps = new Timer(1000, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                i++;
                timelabel.setText("Time : "+ i);

            }
        });

        JButton bouton = new JButton("Home");
        bouton.addActionListener(e -> {
            dispose();
            Homepage home = new Homepage();
            home.setVisible(true);
        });


        loop.start();
        temps.start();
        panelPrincipal.add(grille, BorderLayout.CENTER);
        panelPrincipal.add(panelDroit, BorderLayout.EAST);
        panelPrincipal.add(bouton, BorderLayout.SOUTH);
        add(panelPrincipal);
    }
}

