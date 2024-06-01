import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
public class PageText {
    private  int maxLengthForArea1=0;
    private  int pageNow=1;
    private  int maxPage=10;
    private  String  name;
    public JTextArea area1;
    public JTextArea area2;
    Color fontColor;
    PageText (String name,Color fontColor){
        this.fontColor=fontColor;
        this.name=name;
        area1=new JTextArea();
        area2=new JTextArea();
        area1.setBounds(150,150,225,325);
        area1.setOpaque(false);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        area1.setForeground(fontColor);
        area2.setLineWrap(true);
        area2.setBounds(425,150,225,325);
        area2.setOpaque(false);
        area2.setForeground(fontColor);
        area1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length=area1.getFontMetrics(area1.getFont()).stringWidth(area1.getText());
                double maxLength=(area1.getWidth())*19;
                if (length > maxLength) {
                    maxLengthForArea1=area1.getCaretPosition();
                    area2.requestFocus();
                    area2.append(String.valueOf(e.getKeyChar()));
                    e.consume(); // 阻止插入新字符
                }

            }
            public void keyPressed(KeyEvent e){
                int caretPosition = area1.getCaretPosition();
                String textUpToCaret = area1.getText().substring(0, caretPosition);
                int length = textUpToCaret.length();
                if (caretPosition == maxLengthForArea1&&e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    area2.requestFocus();
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                }
            }
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    if(!area2.getText().isEmpty()){
                        area1.append(String.valueOf(area2.getText().charAt(0)));
                        area2.setText(area2.getText().substring(1));
                        area1.requestFocus();
                    }
                }
            }
        });
        area2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int length=area2.getFontMetrics(area2.getFont()).stringWidth(area2.getText());
                double maxLength=(area2.getWidth())*19;
                if (length > maxLength) {
                    e.consume(); // 阻止插入新字符
                }

            }
            public void keyPressed(KeyEvent e){

                int caretPosition = area2.getCaretPosition();
                if(e.getKeyCode() == KeyEvent.VK_ENTER){

                    e.consume();

                }
                if(caretPosition==0&&e.getKeyCode()==KeyEvent.VK_LEFT){
                    area1.requestFocus();
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
                    if(area2.getCaretPosition()==0){
                        area1.requestFocus();
                        if(!area1.getText().isEmpty()) {
                            area1.setText(area1.getText().substring(0, area1.getCaretPosition() - 1));
                            if(!area2.getText().isEmpty()){
                                area1.append(String.valueOf(area2.getText().charAt(0)));
                                area2.setText(area2.getText().substring(1));
                                area1.requestFocus();
                                area1.setCaretPosition(area1.getCaretPosition()-1);
                            }
                        }
                    }
                }
            }
        });
    }
    //C:\Users\88693\IdeaProjects\readingPage\out\測試2
    public void addNewPage(){

        File file = new File(getParentDir()+"\\"+name+"\\Page"+(maxPage+1)+".txt");
        maxPage++;
        try {
            if (file.createNewFile()) {
                System.out.println("Success！");
            } else {
                System.out.println("text is existed。");
            }
        } catch (IOException e) {
            System.err.println("failure: " + e.getMessage());
        }
        turnToPage(maxPage);
    }
    public void turnToPage(int page){
        if(page>0||page<=maxPage){
            area1.setText("");
            area2.setText("");
            int length=0;
            double maxLength=(area1.getWidth())*19;
            int whichPage=0;
            int character;
            String filePath=getParentDir()+"\\"+name+"\\Page"+page+".txt";
            System.out.println(filePath);
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                while ((character = reader.read()) != -1) {
                    char ch = (char) character;
                    if(whichPage==0){
                        area1.append(String.valueOf(ch));
                        length=area1.getFontMetrics(area1.getFont()).stringWidth(area1.getText());
                        if(length>=maxLength){
                            area2.requestFocus();
                            whichPage=1;
                        }
                    }
                    else{
                        area2.append(String.valueOf(ch));
                    }
                }

                pageNow=page;
            } catch (IOException e) {
                //JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void savePage(){
        String text = area1.getText()+area2.getText();
        String filePath = getParentDir()+"\\"+name+"\\Page"+pageNow+".txt";
        System.out.println(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
            JOptionPane.showMessageDialog(null, "File saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public int getPageNow(){
        return pageNow;
    }
    public String getParentDir(){
        File sourcesDir = null;
        try {
            // 獲取當前類的路徑
            String path = PageText.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String decodedPath = URLDecoder.decode(path, "UTF-8");

            // 獲取當前類所在資料夾的 File 對象
            File currentDir = new File(decodedPath).getParentFile();
            System.out.println("當前類的路徑: " + currentDir.getAbsolutePath());

            // 獲取上一層資料夾的 File 對象
            sourcesDir = new File(currentDir.getParentFile().getParentFile(),"sources");
            System.out.println("上一層資料夾的路徑: " + sourcesDir.getAbsolutePath());

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return sourcesDir != null ? sourcesDir.getAbsolutePath() : null;
    }
    public void setOpacity(float opacity) {
        area1.setForeground(new Color(0, 0, 0, opacity));
        area2.setForeground(new Color(0, 0, 0, opacity));
    }
    public void setName(String name){
        this.name=name;
    }
    public void setFontColor(Color fontColor){
        this.fontColor=fontColor;
    }
    public int getMaxPage(){
        return maxPage;
    }
}