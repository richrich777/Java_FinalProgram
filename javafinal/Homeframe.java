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
    private ImageButton entryButton;
    private ImageButton setttingButton;
    private ImageButton quitButton;
    private ImagePanel welcomePanel;
    private ImagePanel backgroundPanel;
    private Clip clip;
    private JLayeredPane layeredPane;
    private FloatControl volumeControl;
    private boolean isSettingWindowOpen = false;

    private HomeListener homeListener;  // Define an event listener

    // Method to set the listener
    public void setHomeListener(HomeListener listener) {
        this.homeListener = listener;
    }
    Homeframe(String text)
    {
        // background
        ImageIcon mainBackGround = new ImageIcon("mainbackground.png");
        backgroundPanel = new ImagePanel(mainBackGround.getImage());
        backgroundPanel.setBounds(0, 0, 800, 600);
        backgroundPanel.setLayout(null);

        //Title
        ImageIcon titleFrame = new ImageIcon("titleframe.png");
        welcomePanel = new ImagePanel(titleFrame.getImage());
        welcomePanel.setBounds(175, 60, 460, 200);
        welcomePanel.setFont(new Font(null, Font.PLAIN, 45));


        //entry bookcase button
        ImageIcon entryFrameImg = new ImageIcon("entryframe.png");
        entryButton = new ImageButton(entryFrameImg.getImage());
        entryButton.setBorderPainted(false);
        entryButton.setContentAreaFilled(false);
        entryButton.setBounds(275,280,250,150);
        entryButton.addActionListener(new myActionListener());

        // quit button
        ImageIcon leaveButtonImg = new ImageIcon("leavebutton.png");
        quitButton = new ImageButton(leaveButtonImg.getImage());
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBounds(10,10,55,55);
        quitButton.addActionListener(new myActionListener());

        // setting button
        ImageIcon settingButtonImg = new ImageIcon("voicebutton.png");
        setttingButton = new ImageButton(settingButtonImg.getImage());
        setttingButton.setBorderPainted(false);
        setttingButton.setContentAreaFilled(false);
        setttingButton.setBounds(700,10,75,60);
        setttingButton.addActionListener(new myActionListener());

        backgroundPanel.add(welcomePanel);
        backgroundPanel.add(entryButton);
        backgroundPanel.add(quitButton);
        backgroundPanel.add(setttingButton);
        this.add(backgroundPanel);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        setLocationRelativeTo(null);
        this.setTitle(text);
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
            if(e.getSource() == entryButton)
            {
                if (homeListener != null) {
                    homeListener.caseCreate();
                }
                //Homeframe.this.dispose();
            }
            else if(e.getSource() == setttingButton)
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
            else if(e.getSource() == quitButton)
            {
                closeAllFrames();
            }
        }
    }
    private void closeAllFrames() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                window.dispose();
            }
        }
    }
    public interface HomeListener {
        void caseCreate();
    }
}