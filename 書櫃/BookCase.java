import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class BookCase extends JFrame {
    private final int booksPerPage = 15;
    private final JPanel bookCasePanel;
    private final JPanel leftBackGroundPanel;
    private final ArrayList<Books> books;
    private int currentBookCaseIndex;
    private Label currentBookCaseIndexLabel;
    private GridBagConstraints gbc;
    private JButton Tomain;
    private JButton nextButton;
    private JButton previousButton;
    BookCase(){
        super("BookCase");
        super.getContentPane().setBackground(new Color(224, 255 ,255));
        currentBookCaseIndex = 0;
        Border bookcaseline = BorderFactory.createLineBorder(new Color(139, 71 ,38),10);
        bookCasePanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(new Color(139, 71 ,38));
                g2.setStroke(new BasicStroke(10));
                int width = getWidth();
                g2.drawLine(0, 172, width, 172);
                g2.drawLine(0, 390, width, 390);
                g2.fillRect(0,610,width,90);
            }
        };
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(50, 70, 70, 50); // 設置間隔
        gbc.fill = GridBagConstraints.NONE;
        bookCasePanel.setBounds( 600,70,820,700);
        bookCasePanel.setBackground(new Color(139 ,90 ,0));
        bookCasePanel.setBorder(bookcaseline);
        books = new ArrayList<Books>();

        Tomain = new JButton("Home");
        Tomain.addActionListener(new ButtonListener());
        Tomain.setBounds(10,10,70,70);

        nextButton = new JButton("Next");
        previousButton = new JButton("Back");
        nextButton.setBounds(1450,690,70,70);
        previousButton.setBounds(500,690,70,70);
        nextButton.addActionListener(new ButtonListener());
        previousButton.addActionListener(new ButtonListener());

        leftBackGroundPanel = new JPanel();
        leftBackGroundPanel.setLayout(null);
        leftBackGroundPanel.setBackground(new Color(255 ,193 ,193));
        currentBookCaseIndexLabel = new Label("Page 1");
        Font f1 = new Font("Times New Roman",Font.BOLD+ Font.ITALIC,60);
        currentBookCaseIndexLabel.setBounds(0,350,200,200);
        currentBookCaseIndexLabel.setFont(f1);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int height = screenSize.height;
        leftBackGroundPanel.setBounds(0,0,350,height);
        leftBackGroundPanel.add(Tomain);
        leftBackGroundPanel.add(currentBookCaseIndexLabel);
        getContentPane().add(bookCasePanel);
        add(nextButton);
        add(previousButton);
        add(leftBackGroundPanel);
    }

    public int getBooksCount(){
        return books.size();
    }
    public int getCurrentBookCaseIndex(){
        return  currentBookCaseIndex;
    }
    public void addBooksToBookCase(String bookName) {
        Books book = new Books(bookName);
        books.add(book);
        updateBookCasePanel();
    }
    private void updateBookCasePanel() {
        bookCasePanel.removeAll();
        currentBookCaseIndexLabel.setText("Page"+(currentBookCaseIndex+1));
        int startIndex = currentBookCaseIndex * booksPerPage;
        int endIndex = Math.min(startIndex + booksPerPage, getBooksCount());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int index = startIndex + i * 5 + j;
                if (index < endIndex) {
                    gbc.gridx = j; // Column index
                    gbc.gridy = i; // Row index
                    bookCasePanel.add(books.get(index), gbc);
                } else {
                    JLabel emptyLabel = new JLabel();
                    emptyLabel.setPreferredSize(new Dimension(30, 100));
                    gbc.gridx = j; // Column index
                    gbc.gridy = i; // Row index
                    bookCasePanel.add(emptyLabel, gbc);
                }
            }
        }
        revalidate();
        repaint();
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == nextButton){
                if ((currentBookCaseIndex + 1) * booksPerPage < getBooksCount()) {
                    currentBookCaseIndex++;
                    updateBookCasePanel();
                }
                else {
                    JOptionPane.showMessageDialog(
                            BookCase.this,
                            "Cannot move to the next page. There are no more books.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == previousButton){
                if(getCurrentBookCaseIndex() > 0 ){
                    currentBookCaseIndex--;
                    updateBookCasePanel();
                }
                else {
                    JOptionPane.showMessageDialog(
                            BookCase.this,
                            "Cannot move to the previous page. You are already on the first page.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(e.getSource() == Tomain){
                //go to Home screen
            }
        }
    }

}