import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {
    private  Image img;
    public ImageButton(Image img){
        this.img = img;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(img != null){
            g.drawImage(img,0,0,getWidth(),getHeight(),this);
        }
    }
}
