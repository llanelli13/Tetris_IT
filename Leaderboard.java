import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard extends JFrame {

    public static final int width = 445, height = 700;
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final String COUNTDOWN_FILE = "LeaderboardCountDown.txt";
    private JTable leaderboardTable;
    private JTable countdownTable;

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Création du modèle de tableau en lecture seule pour le leaderboard
        ReadOnlyTableModel leaderboardModel = new ReadOnlyTableModel();
        leaderboardModel.addColumn("Pseudo");
        leaderboardModel.addColumn("Score");

        // Création du modèle de tableau en lecture seule pour le countdown
        ReadOnlyTableModel countdownModel = new ReadOnlyTableModel();
        countdownModel.addColumn("Pseudo");
        countdownModel.addColumn("Score");

        // Chargement des données du leaderboard
        List<String[]> leaderboardData = loadLeaderboardData(LEADERBOARD_FILE);
        for (String[] entry : leaderboardData) {
            leaderboardModel.addRow(entry);
        }

        // Chargement des données du countdown
        List<String[]> countdownData = loadLeaderboardData(COUNTDOWN_FILE);
        for (String[] entry : countdownData) {
            countdownModel.addRow(entry);
        }

        // Création de la JTable avec le modèle de tableau en lecture seule pour le leaderboard
        leaderboardTable = new JTable(leaderboardModel);
        leaderboardTable.setFocusable(false); // Désactiver la mise au point de la cellule

        // Création de la JTable avec le modèle de tableau en lecture seule pour le countdown
        countdownTable = new JTable(countdownModel);
        countdownTable.setFocusable(false); // Désactiver la mise au point de la cellule

        // Désactiver le redimensionnement des colonnes pour le leaderboard
        leaderboardTable.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < leaderboardTable.getColumnModel().getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setResizable(false);
        }

        // Désactiver le redimensionnement des colonnes pour le countdown
        countdownTable.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < countdownTable.getColumnModel().getColumnCount(); i++) {
            countdownTable.getColumnModel().getColumn(i).setResizable(false);
        }

        // Création d'un JScrollPane pour afficher le tableau du leaderboard avec des barres de défilement si nécessaire
        JScrollPane leaderboardScrollPane = new JScrollPane(leaderboardTable);

        // Création d'un JScrollPane pour afficher le tableau du countdown avec des barres de défilement si nécessaire
        JScrollPane countdownScrollPane = new JScrollPane(countdownTable);

        // Configuration du layout de la fenêtre
        setLayout(new BorderLayout());

        // Création d'un onglet pour le leaderboard
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Leaderboard", leaderboardScrollPane);
        tabbedPane.addTab("Countdown", countdownScrollPane);
        add(tabbedPane, BorderLayout.CENTER);

        JButton home = new JButton("Home");
        add(home, BorderLayout.SOUTH);

        home.addActionListener(e -> {
            dispose();
            new Homepage();
        });

        setVisible(true);
    }

    public static void addEntry(String pseudo, int score, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String entry = pseudo + "," + score;
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> loadLeaderboardData(String fileName) {
        List<String[]> leaderboardData = new ArrayList<>();

        try {
            Files.lines(Paths.get(fileName)).forEach(line -> {
                String[] entry = line.split(",");
                if (entry.length == 2) {
                    String pseudo = entry[0].trim();
                    String score = entry[1].trim();
                    leaderboardData.add(new String[]{pseudo, score});
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Tri du leaderboard
        Collections.sort(leaderboardData, (entry1, entry2) -> {
            int score1 = Integer.parseInt(entry1[1]);
            int score2 = Integer.parseInt(entry2[1]);
            return Integer.compare(score2, score1);
        });

        return leaderboardData;
    }

    // Classe personnalisée pour rendre la JTable en lecture seule
    private static class ReadOnlyTableModel extends DefaultTableModel {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
