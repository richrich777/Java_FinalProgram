import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PageFrame extends JFrame {
    Color coverColor;
    Color pageColor;
    Color fontColor;
    String name;
    drawPage draw;
    PageText text;
    JLabel pageLabel;
    JLayeredPane layeredPane;
    public goToBookcaseListener bookcaseListener;
    public void setBookcaseListener(goToBookcaseListener listener){
        this.bookcaseListener=listener;
    }
    PageFrame(String name) {
        this.name = name;
        text = new PageText(name);
        draw = new drawPage();
        JButton saveButton = new JButton("save");
        JButton open = new JButton("open");
        JButton back=new JButton("back");
        JButton nextButton = new JButton("Next");
        JButton forwardButton = new JButton("forward");
        saveButton.setBounds(25, 25, 75, 50);
        open.setBounds(100, 25, 75, 50);
        back.setBounds(325,25,75,50);
        nextButton.setBounds(175, 25, 75, 50);
        forwardButton.setBounds(250, 25, 75, 50);

        pageLabel = new JLabel("Page " + text.getPageNow());
        pageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pageLabel.setBounds(650, 500, 100, 50);
        pageLabel.setForeground(new Color(0, 0, 0, 0));
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(layeredPane);

        layeredPane.add(text.area1, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(text.area2, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(saveButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(open, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(nextButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(forwardButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(back,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(pageLabel, JLayeredPane.PALETTE_LAYER);

        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getPageNow() == 1) {
                    JOptionPane.showMessageDialog(null, "已經是最前面了！");
                } else {
                    animatePageTurn(false);
                }
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.getPageNow() == text.getMaxPage()) {
                    JOptionPane.showMessageDialog(null, "已經是最後一頁了！");
                } else {
                    animatePageTurn(true);
                }
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animatePageOpen();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (bookcaseListener != null) {
                    bookcaseListener.goToBookcase();
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.savePage();
            }
        });

        this.add(draw);
        draw.setBounds(100, 100, 800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void animatePageTurn(boolean isNext) {
        animateTextArea(isNext);
        animateLabel(isNext);
    }

    public void animatePageOpen() {
        animateLabelOpen();
        animateTextAreaOpen();
    }

    private void animateTextArea(boolean isNext) {
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0) {
                    timer.stop();
                    text.turnToPage(isNext ? text.getPageNow() + 1 : text.getPageNow() - 1);
                    Timer timer2 = new Timer(30, null);
                    timer2.addActionListener(new ActionListener() {
                        float opacity2 = 0.0f;

                        @Override
                        public void actionPerformed(ActionEvent e2) {
                            opacity2 += 0.05f;
                            if (opacity2 >= 1) {
                                timer2.stop();
                            }
                            text.setOpacity(opacity2);
                        }
                    });
                    timer2.start();
                }
                text.setOpacity(opacity);
            }
        });
        timer.start();
    }

    private void animateLabel(boolean isNext) {
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0) {
                    timer.stop();
                    updatePageLabel();
                    Timer timer2 = new Timer(30, null);
                    timer2.addActionListener(new ActionListener() {
                        float opacity2 = 0.0f;

                        @Override
                        public void actionPerformed(ActionEvent e2) {
                            opacity2 += 0.05f;
                            if (opacity2 >= 1) {
                                timer2.stop();
                            }
                            pageLabel.setForeground(new Color(0, 0, 0, opacity2));
                        }
                    });
                    timer2.start();
                }
                pageLabel.setForeground(new Color(0, 0, 0, opacity));
            }
        });
        timer.start();
    }

    private void animateLabelOpen() {
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1) {
                    timer.stop();
                }
                pageLabel.setForeground(new Color(0, 0, 0, opacity));
            }
        });
        pageLabel.setForeground(new Color(0, 0, 0, 0));
        updatePageLabel();
        timer.start();
    }

    private void animateTextAreaOpen() {
        Timer timer = new Timer(30, null);
        timer.addActionListener(new ActionListener() {
            float opacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity += 0.05f;
                if (opacity >= 1) {
                    timer.stop();
                }
                text.setOpacity(opacity);
            }
        });
        text.setOpacity(0.0f);
        text.turnToPage(1);
        timer.start();
    }

    private void updatePageLabel() {
        pageLabel.setText("Page " + text.getPageNow());
    }

    //public static void main(String[] args) {
       // PageFrame create = new PageFrame("Hello");
    //}
    public void setName(String name){
        this.name=name;
        text.turnToPage(1);
    }
    public interface goToBookcaseListener{
        void goToBookcase();
    }
}
