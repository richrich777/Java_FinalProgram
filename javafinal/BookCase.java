import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
public class BookCase extends JFrame {
    private final int booksPerPage = 15;
    private final JPanel bookCasePanel;
    private final JPanel leftBackGroundPanel;
    public  ArrayList<Books> books;
    private ArrayList<Books> searchResults = new ArrayList<Books>();
    private int currentBookCaseIndex;
    private int currentSearchPageIndex = 0;
    private Label currentBookCaseIndexLabel;
    private GridBagConstraints gbc;
    private ImageButton Tomain;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private ImageButton addBookButton;
    private ImageButton searchButton;
    private ImageButton resetSearchButton;
    private toMainListener mainListener;  // Define an event listener
    private toCreateListener createListener;

    // Method to set the listener
    public void setToMainListener(toMainListener listener) {
        this.mainListener = listener;
    }
    public void settoCreateListener(toCreateListener listener){this.createListener=listener;}
    BookCase(){
        super("BookCase");
        super.getContentPane().setBackground(new Color(245, 245 ,245));
        currentBookCaseIndex = 0;
        Border bookCaseLine = BorderFactory.createLineBorder(new Color(139, 71 ,38),10);
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
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("宋體", Font.PLAIN, 14));
                if (searchResults.isEmpty()) {
                    drawBookNames(g2, books);
                } else {
                    drawBookNames(g2, searchResults);
                }
            }
        };
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(35, 30, 45, 40); // 設置間隔
        gbc.fill = GridBagConstraints.NONE;
        bookCasePanel.setBounds( 285,20,490,520);
        bookCasePanel.setBackground(new Color(139 ,90 ,0));
        bookCasePanel.setBorder(bookCaseLine);
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
        nextButton.setBounds(160,330,70,70);
        nextButton.addActionListener(new ButtonListener());

        ImageIcon preArrowImage = new ImageIcon("prearrow.png");
        previousButton = new ImageButton(preArrowImage.getImage());
        previousButton.setBounds(20,330,70,70);
        previousButton.setBorderPainted(false);
        previousButton.setContentAreaFilled(false);
        previousButton.addActionListener(new ButtonListener());

        JTextField searchField = new JTextField(15);
        searchField.setBounds(10, 230, 150, 30);

        ImageIcon searchImage = new ImageIcon("search.png");
        searchButton = new ImageButton(searchImage.getImage());
        searchButton.setBounds(160, 215, 45, 45);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                searchBooks(keyword);
            }
        });
        ImageIcon resetIamge = new ImageIcon("resetimg.png");
        resetSearchButton = new ImageButton(resetIamge.getImage());
        resetSearchButton.setBounds(10, 260, 50, 50);
        resetSearchButton.setBorderPainted(false);
        resetSearchButton.setContentAreaFilled(false);
        resetSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentBookCaseIndex = 0;
                searchField.setText("");
                searchResults.clear();
                updateBookCasePanel();
            }
        });

        leftBackGroundPanel = new JPanel();
        leftBackGroundPanel.setLayout(null);
        leftBackGroundPanel.setBackground(new Color(238 ,238 ,209));
        currentBookCaseIndexLabel = new Label("Page 1");
        currentBookCaseIndexLabel.setBounds(45,400,200,100);
        currentBookCaseIndexLabel.setFont(new Font("Lucida Console",Font.BOLD,50));
        leftBackGroundPanel.setBounds(20,25,250,500);

        leftBackGroundPanel.add(Tomain);
        leftBackGroundPanel.add(currentBookCaseIndexLabel);
        leftBackGroundPanel.add(nextButton);
        leftBackGroundPanel.add(previousButton);
        leftBackGroundPanel.add(addBookButton);
        leftBackGroundPanel.add(searchField);
        leftBackGroundPanel.add(searchButton);
        leftBackGroundPanel.add(resetSearchButton);
        setLayout(null);
        getContentPane().add(bookCasePanel);
        add(leftBackGroundPanel);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public int getBooksCount(){
        return books.size();
    }
    public int getCurrentBookCaseIndex(){
        return  currentBookCaseIndex;
    }
    public void addBooksToBookCase(String bookName,Color coverColor,Color pageColor,Color fontColor) {
        Books book = new Books(bookName,coverColor,pageColor,fontColor);
        books.add(book);
        updateBookCasePanel();
    }
    private void updateBookCasePanel() {
        bookCasePanel.removeAll();
        currentBookCaseIndexLabel.setBounds(45,400,200,100);
        currentBookCaseIndexLabel.setText("Page "+(currentBookCaseIndex+1));
        currentBookCaseIndexLabel.setFont(new Font("Lucida Console",Font.BOLD,50));
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

    private void searchBooks(String keyword) {
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(
                    BookCase.this,
                    "請輸入搜索關鍵字！",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        searchResults.clear();
        currentSearchPageIndex = 0;
        for (Books book : books) {
            if (book.getBookName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(book);
            }
        }
        updateSearchResults();
    }
    private void updateSearchResults() {
        bookCasePanel.removeAll();
        int resultsCount = searchResults.size();
        if(resultsCount <=0){
            JOptionPane.showMessageDialog(
                    BookCase.this,
                    "沒有結果!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else{
            int startIndex = currentSearchPageIndex * booksPerPage;
            int endIndex = Math.min(startIndex + booksPerPage, resultsCount);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    int index = startIndex + i * 5 + j;
                    if (index < endIndex) {
                        gbc.gridx = j; // Column index
                        gbc.gridy = i; // Row index
                        bookCasePanel.add(searchResults.get(index), gbc);
                    } else {
                        JLabel emptyLabel = new JLabel();
                        emptyLabel.setPreferredSize(new Dimension(20, 80));
                        gbc.gridx = j; // Column index
                        gbc.gridy = i; // Row index
                        bookCasePanel.add(emptyLabel, gbc);
                    }
                }
            }
            currentBookCaseIndexLabel.setBounds(35,400,200,100);
            currentBookCaseIndexLabel.setText("Search Page " + (currentSearchPageIndex + 1));
            currentBookCaseIndexLabel.setFont(new Font("Lucida Console", Font.BOLD, 25));
            revalidate();
            repaint();
        }
    }
    private void drawBookNames(Graphics2D g2d, ArrayList<Books> bookList) {
        int startIndex = currentBookCaseIndex * booksPerPage;
        int endIndex = Math.min(startIndex + booksPerPage, bookList.size());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int index = startIndex + i * 5 + j;
                if (index < endIndex) {
                    g2d.drawString(bookList.get(index).getBookName(), (j+1) * 90 - 42, (i + 1) * 162 - 16);
                }
            }
        }
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextButton) {
                if (searchResults != null && !searchResults.isEmpty()) {
                    if ((currentSearchPageIndex + 1) * booksPerPage < searchResults.size()) {
                        currentSearchPageIndex++;
                        updateSearchResults();
                    } else {
                        JOptionPane.showMessageDialog(
                                BookCase.this,
                                "沒有下一個搜尋結果頁面了！",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if ((currentBookCaseIndex + 1) * booksPerPage < getBooksCount()) {
                        currentBookCaseIndex++;
                        updateBookCasePanel();
                    } else {
                        JOptionPane.showMessageDialog(
                                BookCase.this,
                                "沒有下一個書櫃了！",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else if(e.getSource() == previousButton){
                if (!searchResults.isEmpty()) {
                    if (currentSearchPageIndex > 0) {
                        currentSearchPageIndex--;
                        updateSearchResults();
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                BookCase.this,
                                "已經是最前面了！",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    if(getCurrentBookCaseIndex() > 0 ){
                        currentBookCaseIndex--;
                        updateBookCasePanel();
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                BookCase.this,
                                "已經是最前面了！",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
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