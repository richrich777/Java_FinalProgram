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
        int pixelSize = 15;


        Color[] softColors = {
            new Color(255, 182, 193), 
            new Color(255, 223, 186), 
            new Color(255, 239, 213), 
            new Color(224, 255, 255), 
            new Color(230, 230, 250), 
            new Color(240, 248, 255), 
            new Color(255, 228, 225) , 
            new Color(135, 206, 250), 
            new Color(176, 224, 230), 
            new Color(173, 216, 230), 
            new Color(176, 196, 222), 
            new Color(240, 248, 255), 
            new Color(175, 238, 238), 
            new Color(224, 255, 255), 
            new Color(230, 230, 250), 
            new Color(245, 245, 245) 
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