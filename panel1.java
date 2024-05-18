package test.frame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class panel1 extends JPanel
{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制像素风格背景
        drawPixelBackground(g);
    }

    // 方法来绘制像素风格背景
    private void drawPixelBackground(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int pixelSize = 3; // 每个像素的大小

        // 定义柔和的颜色调色板
        Color[] softColors = {
            new Color(255, 182, 193), // 浅粉色
            new Color(255, 223, 186), // 浅橙色
            new Color(255, 239, 213), // 浅杏色
            new Color(224, 255, 255), // 浅青色
            new Color(230, 230, 250), // 薰衣草色
            new Color(240, 248, 255), // 爱丽丝蓝
            new Color(255, 228, 225) , // 雪白色
            new Color(135, 206, 250), // 浅天蓝色
            new Color(176, 224, 230), // 粉蓝色
            new Color(173, 216, 230), // 淡蓝色
            new Color(176, 196, 222), // 淡钢蓝色
            new Color(240, 248, 255), // 爱丽丝蓝
            new Color(175, 238, 238), // 宝石蓝
            new Color(224, 255, 255), // 淡青色
            new Color(230, 230, 250), // 薰衣草色
            new Color(245, 245, 245)  // 白烟色
            };
        
        Random rand = new Random();

        // 创建一个缓冲图像
        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pixelImage.createGraphics();

        // 填充缓冲图像
        for (int y = 0; y < height; y += pixelSize) {
            for (int x = 0; x < width; x += pixelSize) {
                // 随机选择柔和颜色
                Color color = softColors[rand.nextInt(softColors.length)];
                g2d.setColor(color);
                g2d.fillRect(x, y, pixelSize, pixelSize);
            }
        }

            // 绘制缓冲图像到面板
            g.drawImage(pixelImage, 0, 0, this);
        }
}