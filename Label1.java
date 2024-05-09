package test.frame;
import javax.swing.*;
import java.awt.*;

public class Label1 extends JLabel
{
    Label1()
    {
        this.setText("this is a label");
        this.setForeground(Color.blue);
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.RIGHT);
    }
}