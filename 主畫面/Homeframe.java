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
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private Panel1 panel;
    private Clip clip;
    private boolean isSettingWindowOpen = false;

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
        label1 = new JLabel("Welcome to the Diary!");
        label1.setBounds(this.getWidth() / 4 + 90, this.getHeight() / 5, this.getWidth(), 100);
        label1.setFont(new Font(null, Font.PLAIN, 60));
        label1.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(label1);
        

        //entry bookcase button
        button1 = new JButton("Entry Bookcase");
        button1.setBounds(this.getWidth() / 2 - 200, this.getHeight() / 2 + 80, 400, 80);
        // button1.setLayout(new FlowLayout(FlowLayout.CENTER));
        button1.addActionListener(new myActionListener());
        button1.setFont(new Font(null, Font.PLAIN, 40));
        button1.setForeground(Color.GREEN);
        button1.setBackground(Color.WHITE);
        this.add(button1);

        // setting button
        button2 = new JButton("setting");
        button2.setBounds(this.getWidth() - 85, 0, 75, 40);
        button2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        button2.addActionListener(new myActionListener());
        this.add(button2);

        //最後再加不然有bug
        this.add(panel);
    }

    private void playBackgroundMusic(String filePath) 
    {
        try 
        {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
            if(e.getSource() == button1)
            {
                // open bookcase window
            }
            else if(e.getSource() == button2)
            {
                SettingWindow newWindow = new SettingWindow();
                isSettingWindowOpen = true;
                newWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        isSettingWindowOpen = false;
                    }
                });
            }
        }
    }
}