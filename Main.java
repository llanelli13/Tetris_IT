import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Homepage fenetre = new Homepage();
            fenetre.setVisible(true);
        });
    }
}
