package test.frame;
import javax.swing.*;
import test.frame.Label1;
import java.awt.*;
public class Homeframe extends JFrame
{
    Homeframe()
    {
        Label1 label = new Label1();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("This is a title");
        this.setSize(200, 200); 
        this.setVisible(true);
        // this.getContentPane().setBackground(Color.red);
        this.add(label);
    }
}