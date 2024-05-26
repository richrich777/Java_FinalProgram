import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        BookCase bookCase = new BookCase();
        bookCase.setLayout(null);
        bookCase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookCase.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bookCase.setResizable(false);
        bookCase.setVisible(true);
        //bookCase.getContentPane().setBackground(Color.YELLOW);
        bookCase.addBooksToBookCase("1");
        bookCase.addBooksToBookCase("2");
        bookCase.addBooksToBookCase("3");
        bookCase.addBooksToBookCase("4");
        bookCase.addBooksToBookCase("5");
        bookCase.addBooksToBookCase("6");
        bookCase.addBooksToBookCase("7");
        bookCase.addBooksToBookCase("8");
        bookCase.addBooksToBookCase("9");
        bookCase.addBooksToBookCase("10");
        bookCase.addBooksToBookCase("11");
        bookCase.addBooksToBookCase("12");
        bookCase.addBooksToBookCase("13");
        bookCase.addBooksToBookCase("14");
        bookCase.addBooksToBookCase("15");
        bookCase.addBooksToBookCase("22");

    }
}