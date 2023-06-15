import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.zip.CheckedOutputStream;

public class ChoseTimer extends JFrame {


    private GameArea gameArea;

    public ChoseTimer() {
        setTitle("CountDown");
        setSize(445, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());


        JLabel text = new JLabel("Choississez un temps :");
        JTextField temps = new JTextField(15);
        JButton apply = new JButton("Let's go !");


        add(text);
        add(temps);
        add(apply);


        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int time = Integer.parseInt(temps.getText());
                dispose();
                new CountDownMode(time);

            }
        });


        setVisible(true);
    }
    }




