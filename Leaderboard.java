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
    private JTable leaderboardTable;

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Création du modèle de tableau en lecture seule
        ReadOnlyTableModel tableModel = new ReadOnlyTableModel();
        tableModel.addColumn("Pseudo");
        tableModel.addColumn("Score");

        // Chargement des données du leaderboard
        List<String[]> leaderboardData = loadLeaderboardData();
        for (String[] entry : leaderboardData) {
            tableModel.addRow(entry);
        }

        // Création de la JTable avec le modèle de tableau en lecture seule
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setFocusable(false); // Désactiver la mise au point de la cellule

        // Désactiver le redimensionnement des colonnes
        leaderboardTable.getTableHeader().setResizingAllowed(false);
        for (int i = 0; i < leaderboardTable.getColumnModel().getColumnCount(); i++) {
            leaderboardTable.getColumnModel().getColumn(i).setResizable(false);
        }

        // Création d'un JScrollPane pour afficher le tableau avec des barres de défilement si nécessaire
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        // Configuration du layout de la fenêtre
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JButton home = new JButton("Home");
        add(home, BorderLayout.SOUTH);

        home.addActionListener(e -> {
            dispose();
            new Homepage();
        });

        setVisible(true);
    }

    public static void addEntry(String pseudo, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LEADERBOARD_FILE, true))) {
            String entry = pseudo + "," + score;
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String[]> loadLeaderboardData() {
        List<String[]> leaderboardData = new ArrayList<>();

        try {
            Files.lines(Paths.get(LEADERBOARD_FILE)).forEach(line -> {
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
