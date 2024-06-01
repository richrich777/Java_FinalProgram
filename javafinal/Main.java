import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        BookCase bookCase = new BookCase();
        bookCase.setLayout(null);
        bookCase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookCase.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bookCase.setResizable(false);
    }
}