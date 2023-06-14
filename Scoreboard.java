import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Scoreboard extends JFrame{

    public Scoreboard(){
        setTitle("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());


        JButton home = new JButton("Home");
        
        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Homepage home = new Homepage();
                home.setVisible(true);
            }
        });


        add(home);
    }
}
