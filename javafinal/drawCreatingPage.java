import javax.swing.*;
import java.awt.*;
public class drawCreatingPage extends JPanel{
    Color coverColor;
    Color pageColor;
    Color fontColor;
    JTextArea text;

    drawCreatingPage(){
        this.coverColor=new Color(102,51,0);//設定封面顏色
        this.pageColor=new Color(229,255,204);//設定紙本顏色
        this.setLayout(null); // 使用絕對定位
        text = new JTextArea("此處為測試文字");
        text.setBounds(150, 150, 200, 100); // 設定位置和大小
        text.setOpaque(false);
        text.setForeground(Color.BLACK);
        text.setFont(new Font(null,Font.PLAIN,20));
        this.add(text);
        this.setVisible(true);
    }
    drawCreatingPage(Color coverColor,Color pageColor){

        this.coverColor=coverColor;//設定封面顏色
        this.pageColor=pageColor;//設定紙本顏色
        this.setLayout(null); // 使用絕對定位
        text = new JTextArea("此處為測試文字");
        text.setBounds(150, 150, 200, 100); // 設定位置和大小
        text.setForeground(fontColor);
        text.setFont(new Font(null,Font.PLAIN,20));
        text.setOpaque(false);
        this.add(text);
        this.setVisible(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D draw=(Graphics2D) g;
        draw.setPaint(coverColor);
        draw.fillRect(100,100,300,400);
        draw.setPaint(pageColor);
        draw.fillRect(125,125,250,350);

    }//畫出書本樣式
    public void setCoverColor(Color coverColor){
        this.coverColor= coverColor;
    }
    public void setPageColor(Color pageColor){
        this.pageColor=pageColor;
    }
    public void setFontColor(Color fontColor){
        this.fontColor=fontColor;
        text.setForeground(fontColor);
    }
}
