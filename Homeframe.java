package test.frame;
import javax.swing.*;
import java.awt.*;
public class Homeframe extends JFrame
{
    Homeframe(String text)
    {
        // JLayeredPane layeredPane = new JLayeredPane();
        // layeredPane.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
        Panel1 panel = new Panel1();
        Button1 button = new Button1("Entry Bookcase", this.getWidth() / 2, this.getHeight() / 2);
        Label1 label = new Label1("Welcome to the Diary!", this.getWidth() / 4, this.getHeight() / 5, this.getWidth());
        label.setFont(new Font(null, Font.PLAIN, 60));
        button.setFont(new Font(null, Font.PLAIN, 40));
        button.setForeground(Color.GREEN);
        button.setBackground(Color.WHITE);
        // button.setOpaque(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(text);
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(label);
        this.add(button);
        this.add(panel);
        // this.add(layeredPane);
    }
}