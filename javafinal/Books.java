import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Books extends JPanel {
    private String bookName;
    private String outline;
    private ImageButton modifyButton;
    private JFrame bookFrame;
    Color coverColor;
    Color pageColor;
    Color fontColor;
    private goToReadListener goToRead;
    public void setGoToRead(goToReadListener listener) {
        this.goToRead = listener;
    }
    Books(String bookName,Color coverColor,Color pageColor,Color fontColor){
        this.coverColor=coverColor;
        this.pageColor=pageColor;
        this.fontColor=fontColor;
        super.setBackground(coverColor);
        super.setPreferredSize(new Dimension(20, 80));
        Border blackline = BorderFactory.createLineBorder(Color.black,5);
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
        bookFrame = new JFrame("Book outline");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        bookFrame.setBounds(width/4,height/4,width/2,height/2);
        bookFrame.setLayout(new GridLayout(3,1));
        Border blackline = BorderFactory.createLineBorder(Color.black,3);

        JPanel textPanel = new JPanel(new BorderLayout());
        JLabel text = new JLabel("Bookname: "+getBookName());
        text.setBounds(0,0,100,100);
        textPanel.add(text,BorderLayout.NORTH);
        textPanel.setBorder(blackline);

        ImageIcon modifyButtonImg = new ImageIcon("modifyimg.png");
        modifyButton = new ImageButton(modifyButtonImg.getImage());
        modifyButton.setBounds(650,30,100,100);
        modifyButton.setBorderPainted(false);
        modifyButton.setContentAreaFilled(false);
        modifyButton.addActionListener(new ModifyListener());

        JPanel outlinePanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(5, 30);
        String content = getContent();
        textArea.setText(content);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        outlinePanel.setBorder(blackline);
        outlinePanel.add(scrollPane,BorderLayout.CENTER);

        JPanel modifyButtonPanel = new JPanel(null);
        modifyButtonPanel.add(modifyButton);
        modifyButtonPanel.setBorder(blackline);

        bookFrame.add(textPanel);
        bookFrame.add(outlinePanel);
        bookFrame.add(modifyButtonPanel);
        bookFrame.setVisible(true);
        bookFrame.setResizable(false);
    }

    private String getContent() {
        int pagenumber = 1;
        String content = "";
        while (pagenumber <= 10) {
            String filePath = "sources/" + bookName + "/Page" + pagenumber + ".txt";
            try {
                String pageContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
                content += pageContent;
                pagenumber++;
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                break;
            }
        }
        return content;
    }

    private class ModifyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            if(e.getSource() == modifyButton){
                if(goToRead!=null){
                    bookFrame.dispose();
                    goToRead.goToReading(bookName);
                }
            }
        }
    }
    public interface goToReadListener{
        void goToReading(String name);
    }
}
