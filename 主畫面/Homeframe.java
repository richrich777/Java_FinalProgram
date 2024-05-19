package test.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Homeframe extends JFrame
{
    private Button1 button;
    private Label1 label;
    private Label1 label1;
    private Panel1 panel;
    Homeframe(String text)
    {
        // JLayeredPane layeredPane = new JLayeredPane();
        // layeredPane.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
        Panel1 panel = new Panel1();
        button = new Button1("Entry Bookcase", this.getWidth() / 2, this.getHeight() / 2);
        button.addActionListener(new myActionListener());
        label = new Label1("Welcome to the Diary!", this.getWidth() / 4, this.getHeight() / 5, this.getWidth());
        label.setFont(new Font(null, Font.PLAIN, 60));
        label1 = new Label1("setting", this.getWidth() - 200, 0, 500);
        label1.setFont(new Font(null, Font.BOLD, 20));
        button.setFont(new Font(null, Font.PLAIN, 40));
        button.setForeground(Color.GREEN);
        button.setBackground(Color.WHITE);
        // button.setOpaque(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(text);
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(label1);
        this.add(label);
        this.add(button);
        this.add(panel);
        // this.add(layeredPane);
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