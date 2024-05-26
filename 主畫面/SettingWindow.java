package test.frame;
import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame
{
    private JSlider slider;
    private JLabel label1;

    public SettingWindow()
    {
        label1 = new JLabel("設定BGM音量大小");
        slider = new JSlider(0, 100, 50);
        this.setSize(400,400);
        this.add(label1);
        this.add(slider);
        this.setVisible(true);
    }
}