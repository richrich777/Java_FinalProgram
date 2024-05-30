package test.frame;
import javax.swing.*;

import test.frame.Panel1;

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
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private Panel1 panel;
    private Clip clip;
    private JLayeredPane layeredPane;
    private FloatControl volumeControl;
    private boolean isSettingWindowOpen = false;

    Homeframe(String text)
    {
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        setLocationRelativeTo(null); 
        this.setTitle(text);

        // 分層
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        // background
        panel = new Panel1();
        panel.setBounds(0, 0, 800, 600);
        layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);

        //GUI Title
        label1 = new JLabel("Welcome to the Diary!");
        label1.setBounds(this.getWidth() / 6 - 10, this.getHeight() / 5, this.getWidth(), 100);
        label1.setFont(new Font(null, Font.PLAIN, 55));
        layeredPane.add(label1, JLayeredPane.PALETTE_LAYER);
        

        //entry bookcase button
        button1 = new JButton("Entry Bookcase");
        button1.setBounds(this.getWidth() / 2 - 160, this.getHeight() / 3 + 80, 300, 70);
        button1.setLayout(null);
        button1.addActionListener(new myActionListener());
        button1.setFont(new Font(null, Font.PLAIN, 35));
        button1.setForeground(Color.GREEN);
        button1.setBackground(Color.WHITE);
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // quit button
        button3 = new JButton("Quit Diary");
        button3.setBounds(this.getWidth() / 2 - 135, this.getHeight() / 3 + 180, 250, 60);
        button3.setLayout(null);
        button3.addActionListener(new myActionListener());
        button3.setFont(new Font(null, Font.PLAIN, 35));
        button3.setForeground(Color.GREEN);
        button3.setBackground(Color.WHITE);
        layeredPane.add(button3, JLayeredPane.PALETTE_LAYER);

        // setting button
        button2 = new JButton("setting");
        button2.setBounds(this.getWidth() - 85, 0, 75, 40);
        button2.addActionListener(new myActionListener());
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);
        this.add(layeredPane);

        playBackgroundMusic("music.wav");
    }

    private class SettingWindow extends JFrame
    {
        private JSlider slider;
        private JLabel label1;
        private JPanel panel;
        private JCheckBox checkBox1;
    
        public SettingWindow()
        {
            this.setSize(400,400);
            this.setLayout(new GridLayout(8,1));
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setResizable(false);
            setLocationRelativeTo(null); 

            // 文字欄位
            label1 = new JLabel("BGM音量");
            label1.setFont(new Font(null, Font.PLAIN, 20));
            label1.setLayout(new FlowLayout(FlowLayout.RIGHT));
            
            // 開關
            checkBox1 = new JCheckBox("開啟");
            checkBox1.setSelected(true);
            checkBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    if (checkBox1.isSelected()) 
                    {
                        if (clip != null && !clip.isRunning()) 
                        {
                            clip.start();
                        }
                        slider.setEnabled(true);
                    } 
                    else 
                    {
                        if (clip != null && clip.isRunning()) 
                        {
                            clip.stop();
                            slider.setEnabled(false);
                        }
                    }
                }
            });
            panel = new JPanel();
            panel.add(label1);
            panel.add(checkBox1);
            this.add(panel);
            
            slider = new JSlider(0, 100, 50);
            slider.setMajorTickSpacing(20);
            slider.setMinorTickSpacing(5); 
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(e -> {
                if (clip != null && clip.isOpen()) {
                    volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    int value = slider.getValue();
                    float dB = (float) (Math.log(value / 100.0) / Math.log(10.0) * 20.0);
                    volumeControl.setValue(dB);
                }
            });
            this.add(slider);

            this.setVisible(true);
        }
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
            else if(e.getSource() == button3)
            {
                Homeframe.this.dispose();
            }
        }
    }
}