import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
public class Books extends JPanel {
    private String bookName;
    private String outline;
    private JButton readButton;
    private static final Color colors[] = { Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK,
            Color.RED, Color.YELLOW, Color.WHITE };
    Books(String bookName){
        SecureRandom random = new SecureRandom();
        int colorIndex = random.nextInt(colors.length);
        super.setBackground(colors[colorIndex]);
        super.setPreferredSize(new Dimension(30, 100));
        Border blackline = BorderFactory.createLineBorder(Color.black,6);
        super.setBorder(blackline);
        this.bookName = bookName;
        addMouseListener(new MouseClickHandler());

    }
    private  class MouseClickHandler extends MouseAdapter{
        @Override
        public void  mouseClicked (MouseEvent e){
            showOutline();
        }
    }
    public  String getOutline(){
        return outline;
    }
    public  String getBookName(){
        return bookName;
    }
    public void showOutline(){
        JFrame bookFrame = new JFrame("Book outline");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        bookFrame.setBounds(width/4,height/4,width/2,height/2);
        bookFrame.setLayout(new GridLayout(2,1));
        Border blackline = BorderFactory.createLineBorder(Color.black,3);

        JPanel textPanel = new JPanel(new BorderLayout());
        JLabel text = new JLabel("Bookname: "+getBookName());
        text.setBounds(0,0,100,100);
        textPanel.add(text,BorderLayout.NORTH);
        textPanel.setBorder(blackline);

        JPanel readButtonPanel = new JPanel(null);
        JButton readButton = new JButton("Modify");
        readButton.setBounds(300,80,150,100);
        readButton.addActionListener(new ModifyListener());
        readButtonPanel.add(readButton);
        readButtonPanel.setBorder(blackline);

        bookFrame.add(textPanel);
        bookFrame.add(readButtonPanel);
        bookFrame.setVisible(true);
        bookFrame.setResizable(false);
    }
    private class ModifyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == readButton){
                    //To modity books content.
                }
            }
    }
}
