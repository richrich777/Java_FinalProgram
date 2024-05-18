package test.frame;
import javax.swing.*;
import java.awt.*;

public class Label1 extends JLabel
{
    Label1(String text)
    {
        this.setText(text);
        this.setForeground(Color.blue);
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
        // this.setBounds(50, 50, 100, 100);
    }
}