package test.frame;
import javax.swing.*;
import java.awt.*;
public class ImageLabel extends JLabel {
    private Image img;
    public ImageLabel(Image img) {
        this.img = img;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
        // 绘制文字
        g.setFont(getFont());
        g.setColor(getForeground());
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(getText(), x, y);
    }
}
