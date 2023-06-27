import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedWindow extends JFrame{

    public SpeedWindow() {
        setTitle("SpeedWindow");
        setSize(445, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());


        JLabel text = new JLabel("Choississez une vitesse :");
        JButton n1 = new JButton("x1");
        JButton n2 = new JButton("x2");
        JButton n3 = new JButton("x3");

        JButton apply = new JButton("Let's go !");


        add(text);
        add(n1);
        add(n2);
        add(n3);
        add(apply);

        n1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new GameWindow(600);
            }
        });

        n2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new GameWindow(450);
            }
        });
        n3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TetrisMusic.playLineCompleteMusic();
                dispose();
                new GameWindow(300);
            }
        });


        setVisible(true);
    }
}
