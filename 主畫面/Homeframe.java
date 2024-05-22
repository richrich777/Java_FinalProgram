package test.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import java.net.MalformedURLException;

public class Homeframe extends JFrame
{
    private Button1 button;
    private Label1 label;
    private JLabel label1;
    private Panel1 panel;
    private Clip clip;

    Homeframe(String text)
    {
        // JLayeredPane layeredPane = new JLayeredPane();
        // layeredPane.setBounds(0, 0, this.getWidth(), this.getHeight());
        playBackgroundMusic("music.wav");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(text);

        //background
        Panel1 panel = new Panel1();

        //GUI Title
        label = new Label1("Welcome to the Diary!", this.getWidth() / 4, this.getHeight() / 5, this.getWidth());
        label.setFont(new Font(null, Font.PLAIN, 60));
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(label);
        
        //setting icon
        ImageIcon settingIcon = new ImageIcon("setting.png");
        label1 = new JLabel(settingIcon);
        label1.setBounds(this.getWidth() - 250, -10, 200, 200);
        // label1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(label1);
        // label1.setFont(new Font(null, Font.BOLD, 20));

        //entry bookcase button
        button = new Button1("Entry Bookcase", this.getWidth() / 2, this.getHeight() / 2);
        button.setLayout(new FlowLayout(FlowLayout.CENTER));
        button.addActionListener(new myActionListener());
        button.setFont(new Font(null, Font.PLAIN, 40));
        button.setForeground(Color.GREEN);
        button.setBackground(Color.WHITE);
        // button.setOpaque(false);

        this.add(button);
        this.add(panel);
        // this.add(layeredPane);
    }

    private void playBackgroundMusic(String filePath) 
    {
        try 
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // 设置循环播放
            clip.start();
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
        {
            e.printStackTrace();
        }
    }
    public class myActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == button)
            {
                // open bookcase window
            }
        }
    }
}