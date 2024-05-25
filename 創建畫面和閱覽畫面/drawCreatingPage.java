import javax.swing.*;
import java.awt.*;
public class drawCreatingPage extends JPanel{
    Color coverColor;
    Color pageColor;
    drawCreatingPage(){
        this.coverColor=new Color(102,51,0);//設定封面顏色
        this.pageColor=new Color(229,255,204);//設定紙本顏色
    }
    drawCreatingPage(Color coverColor,Color pageColor){

        this.coverColor=coverColor;//設定封面顏色
        this.pageColor=pageColor;//設定紙本顏色
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D draw=(Graphics2D) g;
        draw.setPaint(coverColor);
        draw.fillRect(100,100,300,400);
        draw.setPaint(pageColor);
        draw.fillRect(125,125,250,350);

    }//畫出書本樣式
}
