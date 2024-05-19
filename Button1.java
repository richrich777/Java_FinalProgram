package test.frame;
import javax.swing.*;
import java.awt.*;

public class Button1 extends JButton
{
    Button1(String text, int x, int y)
    {
        this.setText(text);
        this.setBounds(x - 200, y + 80, 400, 80);;
        this.setLayout(null);
        // this.setFont(new Font("Arial", Font.PLAIN, 30));
    }
}