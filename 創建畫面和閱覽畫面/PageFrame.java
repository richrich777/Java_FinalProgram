import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class PageFrame extends JFrame {
    Color coverColor;
    Color pageColor;
    Color fontColor;
    String name;
    drawPage draw ;
    PageText text;
    PageFrame(String name) {
        this.name=name;
        text = new PageText(name);
        draw=new drawPage();
        JButton saveButton=new JButton("save");
        JButton open=new JButton("open");
        JButton nextButton=new JButton("Next");
        JButton forwardButton=new JButton("forward");
        saveButton.setBounds(25,25,75,50);
        open.setBounds(100,25,75,50);
        nextButton.setBounds(175,25,75,50);
        forwardButton.setBounds(250,25,75,50);
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(text.area1);
        this.add(text.area2);
        this.add(saveButton);
        this.add(open);
        this.add(nextButton);
        this.add(forwardButton);
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.turnToPage((text.getPageNow()-1));
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.turnToPage(text.getPageNow()+1);
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.turnToPage(1);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.savePage();
            }
        });
        this.add(draw);
        draw.setBounds(100,100,800,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
