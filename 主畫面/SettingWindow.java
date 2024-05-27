package test.frame;
import javax.swing.*;
import java.awt.*;

public class SettingWindow extends JFrame
{
    private JSlider slider;
    private JLabel label1;

    public SettingWindow()
    {
        this.setSize(400,400);
        this.setLayout(new GridLayout(8,3));


        label1 = new JLabel("BGM音量大小");
        label1.setFont(new Font(null, Font.PLAIN, 20));
        label1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(label1);

        slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5); 
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider);

        this.setVisible(true);
    }
}