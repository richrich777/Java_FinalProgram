import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
public class PageText {
    private  int maxLengthForArea1=0;
    private  int pageNow=0;
    private  int maxPage=1;
    private  String  name;
    public JTextArea area1;
    public JTextArea area2;
    PageText(String name){
        this.name=name;
        area1=new JTextArea();
        area2=new JTextArea();
        area1.setBounds(150,150,225,325);
        area1.setOpaque(false);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        area2.setLineWrap(true);
        area2.setBounds(425,150,225,325);
        area2.setOpaque(false);
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
        File file = new File("C:\\Users\\88693\\IdeaProjects\\CreatePage\\src\\textSource\\"+name+maxPage+".txt");
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
        turnToPage(maxPage-1);
    }
    public void turnToPage(int page){
        area1.setText("");
        area2.setText("");
        int length=0;
        double maxLength=(area1.getWidth())*19;
        int whichPage=0;
        int character;
        String filePath="C:\\Users\\88693\\IdeaProjects\\CreatePage\\src\\textSource\\"+name+page+".txt";
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
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void savePage(){
        String text = area1.getText()+area2.getText();
        String filePath = "C:\\Users\\88693\\IdeaProjects\\CreatePage\\src\\textSource\\"+name+pageNow+".txt";
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
}
