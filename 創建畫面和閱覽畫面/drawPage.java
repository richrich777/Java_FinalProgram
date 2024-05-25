import javax.swing.*;
import java.awt.*;
public class drawPage  extends JPanel{
    Color coverColor;
    Color pageColor;
    drawPage(){
        this.setPreferredSize(new Dimension(800,600));//設定大小
        this.coverColor=new Color(102,51,0);//設定封面顏色
        this.pageColor=new Color(229,255,204);//設定紙本顏色
    }
    drawPage(Color coverColor,Color pageColor){
        this.setPreferredSize(new Dimension(800,600));//設定大小
        this.coverColor=coverColor;//設定封面顏色
        this.pageColor=pageColor;//設定紙本顏色
    }
    public void paintComponent(Graphics g){
        Graphics2D draw=(Graphics2D) g;
        draw.setPaint(coverColor);
        draw.fillRect(100,100,600,400);
        draw.setPaint(pageColor);
        draw.fillRect(125,125,550,350);
        draw.setPaint(Color.BLACK);
        draw.setStroke(new BasicStroke(3));
        draw.drawLine(400,125,400,475);
    }//畫出書本樣式
}
