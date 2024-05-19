package test.frame;
import javax.swing.*;
import java.awt.*;

public class Label1 extends JLabel
{
    Label1(String text, int x, int y, int width)
    {
        this.setText(text);
        this.setBounds(x + 90, y, width, 100);
    }
}