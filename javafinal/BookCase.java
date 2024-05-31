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
    private ImageButton Tomain;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private ImageButton addBookButton;
    private toMainListener mainListener;  // Define an event listener
    private toCreateListener createListener;

    // Method to set the listener
    public void setToMainListener(toMainListener listener) {
        this.mainListener = listener;
    }
    public void settoCreateListener(toCreateListener listener){this.createListener=listener;}
    BookCase(){

        super("BookCase");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        super.getContentPane().setBackground(new Color(255, 255 ,255));
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
                g2.drawLine(0, 140, width, 140);
                g2.drawLine(0, 300, width, 300);
                g2.fillRect(0,455,width,60);
            }
        };
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(35, 30, 45, 40); // 設置間隔
        gbc.fill = GridBagConstraints.NONE;
        bookCasePanel.setBounds( 275,30,490,520);
        bookCasePanel.setBackground(new Color(139 ,90 ,0));
        bookCasePanel.setBorder(bookcaseline);
        books = new ArrayList<Books>();

        ImageIcon toMainImage = new ImageIcon("home.png");
        Tomain = new ImageButton(toMainImage.getImage());
        Tomain.setBounds(0,0,100,100);
        Tomain.setBorderPainted(false);
        Tomain.setContentAreaFilled(false);
        Tomain.addActionListener(new ButtonListener());

        ImageIcon addBookImage = new ImageIcon("addbutton.png");
        addBookButton = new ImageButton(addBookImage.getImage());
        addBookButton.setBounds(60,2,100,100);
        addBookButton.setBorderPainted(false);
        addBookButton.setContentAreaFilled(false);
        addBookButton.addActionListener(new ButtonListener());


        ImageIcon nextArrowImage = new ImageIcon("nextarrow.png");
        nextButton = new ImageButton(nextArrowImage.getImage());
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBounds(150,350,70,70);
        nextButton.addActionListener(new ButtonListener());

        ImageIcon preArrowImage = new ImageIcon("prearrow.png");
        previousButton = new ImageButton(preArrowImage.getImage());
        previousButton.setBounds(20,350,70,70);
        previousButton.setBorderPainted(false);
        previousButton.setContentAreaFilled(false);


        previousButton.addActionListener(new ButtonListener());

        leftBackGroundPanel = new JPanel();
        leftBackGroundPanel.setLayout(null);
        leftBackGroundPanel.setBackground(new Color(255 ,193 ,193));
        currentBookCaseIndexLabel = new Label("Page 1");
        Font f1 = new Font("仿宋",Font.BOLD+ Font.ITALIC,50);
        currentBookCaseIndexLabel.setBounds(45,450,200,100);
        currentBookCaseIndexLabel.setFont(f1);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int height = screenSize.height;
        leftBackGroundPanel.setBounds(0,0,250,height);
        leftBackGroundPanel.add(Tomain);
        leftBackGroundPanel.add(currentBookCaseIndexLabel);
        leftBackGroundPanel.add(nextButton);
        leftBackGroundPanel.add(previousButton);
        leftBackGroundPanel.add(addBookButton);
        getContentPane().add(bookCasePanel);
        add(leftBackGroundPanel);
        setSize(800, 600);
        int centerX = (screenSize.width - getWidth()) / 2;
        int centerY = (screenSize.height - getHeight()) / 2;
        setLocation(centerX, centerY);
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
                    emptyLabel.setPreferredSize(new Dimension(20, 80));
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
                if(mainListener!=null){
                    mainListener.GotoMain();
                }
            }
            else if(e.getSource() == addBookButton){
                if(createListener!=null){
                    createListener.goToCreate();
                }
            }
        }
    }
    public interface toMainListener {
        void GotoMain();
    }
    public interface toCreateListener{
        void goToCreate();
    }
}