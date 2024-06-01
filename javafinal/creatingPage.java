import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class creatingPage extends JFrame{
   Color coverColor=new Color(102,51,0);
   Color pageColor=new Color(229,255,204);
   Color fontColor=Color.BLACK;
   drawCreatingPage drawPage=new drawCreatingPage(coverColor,pageColor);
   JButton creatingButton = new JButton("創建");
   JRadioButton coverButton= new JRadioButton("cover");
   JRadioButton pageButton = new JRadioButton("page");
   JRadioButton fontButton = new JRadioButton("font");
   JPanel[] sliderPanel=new JPanel[3];
   ButtonGroup group = new ButtonGroup();
   JSlider redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100);
   JSlider blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100);
   JSlider greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100);
   JLabel color=new JLabel("目標顏色調整:");
   JLabel decision = new JLabel("調整目標:");
   JLabel redLabel=new JLabel("紅:");
   JLabel blueLabel=new JLabel("藍:");
   JLabel greenLabel=new JLabel("綠:");
   JLabel nameText=new JLabel("書本名稱:");
   JTextPane nameInsertArea=new JTextPane();
   String name;
   private CreateListener createListener;  // Define an event listener

   // Method to set the listener
   public void setCreateListener(CreateListener listener) {
      this.createListener = listener;
   }
   public creatingPage(){
      for (int i = 0; i < sliderPanel.length; i++) {
         sliderPanel[i] = new JPanel();
      }

      this.setLayout(null);

      nameText.setBounds(425,20,100,30);
      nameInsertArea.setBounds(440,60,300,30);
      coverButton.setBounds(440,150,80,30);
      pageButton.setBounds(520,150,80,30);
      fontButton.setBounds(600,150,80,30);
      redLabel.setBounds(440,250,300,30);
      color.setBounds(425,200,300,30);
      decision.setBounds(425 ,100,300,30);
      redSlider.setBounds(450,300 ,300,20);
      greenLabel.setBounds(440,330,300,30);
      greenSlider.setBounds(450,380,300,30);
      blueLabel.setBounds(440,410,300,30);
      blueSlider.setBounds(450,460,300,30);
      creatingButton.setBounds(525,500,150,50);
      nameText.setFont(new Font(null, Font.PLAIN, 20));
      nameInsertArea.setFont(new Font(null, Font.PLAIN, 20));
      color.setFont(new Font(null, Font.PLAIN, 20));
      redLabel.setFont(new Font(null, Font.PLAIN, 18));
      greenLabel.setFont(new Font(null, Font.PLAIN, 18));
      blueLabel.setFont(new Font(null, Font.PLAIN, 18));
      decision.setFont(new Font(null, Font.PLAIN, 20));
      drawPage.setBounds(-40,-40,400,500);
      group.add(coverButton);
      group.add(pageButton);
      group.add(fontButton);
      this.add(nameText);
      this.add(nameInsertArea);
      this.add(decision);
      this.add(redSlider);
      this.add(coverButton);
      this.add(pageButton);
      this.add(fontButton);
      this.add(color);
      this.add(redLabel);
      this.add(blueLabel);
      this.add(blueSlider);
      this.add(greenLabel);
      this.add(greenSlider);
      this.add(creatingButton);
      this.add(drawPage);

      nameInsertArea.setBackground(new Color(173,230,255));

      this.setSize(800,600);
      this.setVisible(true);
      this.setLocationRelativeTo(null);

      coverButton.addActionListener(new RadioButtonListener());
      pageButton.addActionListener(new RadioButtonListener());
      fontButton.addActionListener(new RadioButtonListener());
      creatingButton.addActionListener(new createButtonListener());

      redSlider.addChangeListener(new SliderListener());
      greenSlider.addChangeListener(new SliderListener());
      blueSlider.addChangeListener(new SliderListener());
   }
   private class RadioButtonListener implements ActionListener {
      int R,G,B;
      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == coverButton) {
            R=coverColor.getRed();
            G=coverColor.getGreen();
            B=coverColor.getBlue();
         } else if (e.getSource() == pageButton) {
            R=pageColor.getRed();
            G=pageColor.getGreen();
            B=pageColor.getBlue();
         } else if (e.getSource() == fontButton) {
            R=fontColor.getRed();
            G=fontColor.getGreen();
            B=fontColor.getBlue();
         }
         redSlider.setValue(R);
         greenSlider.setValue(G);
         blueSlider.setValue(B);

      }
   }
   private class SliderListener implements ChangeListener{
      @Override
      public void stateChanged(ChangeEvent e) {
         int RedValue = redSlider.getValue();
         int BlueValue=blueSlider.getValue();
         int GreenValue=greenSlider.getValue();
         if(coverButton.isSelected()){
            coverColor=new Color(RedValue,GreenValue,BlueValue);
            drawPage.setCoverColor(coverColor);
            drawPage.repaint();
         }
         else if (pageButton.isSelected()){
            pageColor=new Color(RedValue,GreenValue,BlueValue);
            drawPage.setPageColor(pageColor);
            drawPage.repaint();
         }
         else{
            fontColor=new Color(RedValue,GreenValue,BlueValue);
            drawPage.setFontColor(fontColor);
         }
      }
   }
   private class createButtonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e){
         name = nameInsertArea.getText();
         if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(creatingPage.this, "書本名稱不得為空!","Warning", JOptionPane.WARNING_MESSAGE);
         } else {
            try {
               // 獲取當前類的路徑
               String path = creatingPage.class.getProtectionDomain().getCodeSource().getLocation().getPath();
               String decodedPath = URLDecoder.decode(path, "UTF-8");

               // 獲取當前類所在資料夾的 File 對象
               File currentDir = new File(decodedPath).getParentFile();
               System.out.println("當前類的路徑: " + currentDir.getAbsolutePath());

               // 獲取上一層資料夾的 File 對象
               File grandParentDir = currentDir.getParentFile().getParentFile();
               System.out.println("上一層資料夾的路徑: " + grandParentDir.getAbsolutePath());
               File sourcesDir = new File(grandParentDir, "sources");
               if (!sourcesDir.exists()) {
                  System.out.println("'sources' 資料夾不存在：" + sourcesDir.getAbsolutePath());
                  return;
               } else {
                  System.out.println("'sources' 資料夾已經存在：" + sourcesDir.getAbsolutePath());
               }
               // 在上一層資料夾中建立一個新子資料夾
               File newDir = new File(sourcesDir, name);
               if (newDir.exists()) {
                  System.out.println("資料夾已經存在：" + newDir.getAbsolutePath());
               } else {
                  // 在上一層資料夾中建立一個新子資料夾
                  if (newDir.mkdirs()) {
                     System.out.println("成功建立子資料夾：" + newDir.getAbsolutePath());
                     for (int i = 1; i <= 10; i++) {
                        File newFile = new File(newDir, "Page" + i + ".txt");
                        if (newFile.createNewFile()) {
                           System.out.println("成功建立檔案：" + newFile.getAbsolutePath());
                        } else {
                           System.out.println("建立檔案失敗：" + newFile.getAbsolutePath());
                        }
                     }
                     File coverColorData = new File (newDir,"coverColor.txt");
                     File pageColorData = new File (newDir,"pageColor.txt");
                     File fontColorData = new File(newDir,"fontColor.txt");
                     if(coverColorData.createNewFile()){
                        System.out.println("成功建立檔案：" + coverColorData.getAbsolutePath());
                     }
                     else{
                        System.out.println("建立檔案失敗：" + coverColorData.getAbsolutePath());
                     }
                     if(pageColorData.createNewFile()){
                        System.out.println("成功建立檔案：" + pageColorData.getAbsolutePath());
                     }
                     else{
                        System.out.println("建立檔案失敗：" + pageColorData.getAbsolutePath());
                     }
                     if(fontColorData.createNewFile()){
                        System.out.println("成功建立檔案：" + fontColorData.getAbsolutePath());
                     }
                     else{
                        System.out.println("建立檔案失敗：" + fontColorData.getAbsolutePath());
                     }
                     try (FileWriter writer = new FileWriter(coverColorData)) {
                        writer.write(coverColor.getRed()+","+coverColor.getGreen()+","+coverColor.getBlue());
                     } catch (IOException ex) {
                        System.out.println("寫入檔案失敗：" + coverColorData.getAbsolutePath());
                        ex.printStackTrace();
                     }
                     try (FileWriter writer = new FileWriter(pageColorData)) {
                        writer.write( pageColor.getRed()+","+pageColor.getGreen()+","+pageColor.getBlue());
                     } catch (IOException ex) {
                        System.out.println("寫入檔案失敗：" + coverColorData.getAbsolutePath());
                        ex.printStackTrace();
                     }
                     try (FileWriter writer = new FileWriter(fontColorData)) {
                        writer.write(  fontColor.getRed()+","+fontColor.getGreen()+","+fontColor.getBlue());
                     } catch (IOException ex) {
                        System.out.println("寫入檔案失敗：" + coverColorData.getAbsolutePath());
                        ex.printStackTrace();
                     }

                     if (createListener != null) {
                        createListener.onCreate(name);
                     }
                  }
                  else {
                     System.out.println("建立子資料夾失敗");
                  }


                  // Hide current frame
                  //creatingPage.this.dispose();
               }
            } catch (UnsupportedEncodingException e1) {
               e1.printStackTrace();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
         }
      }
   }
   public interface CreateListener {
      void onCreate(String name);
   }
}
