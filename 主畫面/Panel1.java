package test.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Panel1 extends JPanel
{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPixelBackground(g);
    }

    private void drawPixelBackground(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int pixelSize = 5;

        Color[] softColors = {
            new Color(255, 182, 193),  // 淡粉紅色
            new Color(255, 223, 186),  // 淺橙色
            new Color(255, 239, 213),  // 淺黃色
            new Color(224, 255, 255),  // 淺綠色
            new Color(230, 230, 250),  // 淺藍色
            new Color(240, 248, 255),  // 淺紫色
            new Color(255, 228, 225),  // 淡灰色
            new Color(135, 206, 250),  // 淡棕色
            new Color(176, 224, 230),  // 淡紅色
            new Color(173, 216, 230),  // 淺灰色
            new Color(176, 196, 222),  // 淺粉色
            new Color(240, 248, 255),  // 淺橄欖色
            new Color(175, 238, 238),  // 淺桃紅色
            new Color(224, 255, 255),  // 淺紫羅蘭色
            new Color(230, 230, 250),  // 淺珊瑚色
            new Color(240, 248, 255),  // 淺金色
            new Color(240, 248, 255),  // 淺青色
            new Color(240, 255, 240),  // 淺銀綠色
            new Color(253, 245, 230),  // 淺橄欖綠色
            new Color(250, 240, 230),  // 淺蒼色
            new Color(255, 228, 181),  // 淺銀色
            new Color(255, 222, 173),  // 淺珊瑚色
            new Color(255, 218, 185),  // 淺杏色
            new Color(255, 215, 0),    // 淺卡其色
            new Color(255, 192, 203),  // 淺桃色
            new Color(255, 182, 193),  // 淺檸檬色
            new Color(255, 160, 122),  // 淺薰衣草色
            new Color(255, 127, 80),   // 淺橙紅色
            new Color(255, 105, 180),  // 淺葡萄色
            new Color(255, 105, 97),   // 淺橙黃色
            new Color(250, 250, 210)   // 淺桔黃色
        };
        
        
        Random rand = new Random();

        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pixelImage.createGraphics();


        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {

                Color color = softColors[rand.nextInt(softColors.length)];
                g2d.setColor(color);
                g2d.fillRect(x, y, pixelSize, pixelSize);
            }
        }

            g.drawImage(pixelImage, 0, 0, this);
        }
}