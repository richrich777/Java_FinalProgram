package test.frame;
import javax.swing.*;
import java.awt.*;
public class Homeframe extends JFrame
{
    Homeframe(String text)
    {
        panel1 panel = new panel1();
        Button1 button = new Button1("進入書櫃");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(text);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
        panel.add(button);
        this.add(panel);
    }
}