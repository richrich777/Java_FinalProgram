package test.frame;
import javax.swing.*;
import java.awt.*;

public class Button1 extends JButton
{
    Button1(String text)
    {
        this.setText(text);
        this.setBounds(500, 500, 300, 50);
        this.setLayout(null);
        // this.setFont(new Font("Arial", Font.PLAIN, 30));
    }
}