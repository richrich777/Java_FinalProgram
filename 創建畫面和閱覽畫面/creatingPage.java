import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class creatingPage {
   Color coverColor=new Color(102,51,0);
   Color pageColor=new Color(229,255,204);
   Color fontColor=Color.BLACK;
   drawCreatingPage drawPage=new drawCreatingPage(coverColor,pageColor);
   JFrame frame = new JFrame();
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

   public creatingPage(){
      for (int i = 0; i < sliderPanel.length; i++) {
         sliderPanel[i] = new JPanel();
      }

      frame.setLayout(null);

      coverButton.setBounds(440,100,80,30);
      pageButton.setBounds(520,100,80,30);
      fontButton.setBounds(600,100,80,30);
      redLabel.setBounds(440,200,300,30);
      color.setBounds(425,150,300,30);
      decision.setBounds(425 ,50,300,30);
      redSlider.setBounds(450,250 ,300,20);
      greenLabel.setBounds(440,280,300,30);
      greenSlider.setBounds(450,330,300,30);
      blueLabel.setBounds(440,360,300,30);
      blueSlider.setBounds(450,410,300,30);
      creatingButton.setBounds(525,475,150,50);
      color.setFont(new Font(null, Font.PLAIN, 20));
      redLabel.setFont(new Font(null, Font.PLAIN, 18));
      greenLabel.setFont(new Font(null, Font.PLAIN, 18));
      blueLabel.setFont(new Font(null, Font.PLAIN, 18));
      decision.setFont(new Font(null, Font.PLAIN, 20));
      drawPage.setBounds(-40,-40,400,500);
      group.add(coverButton);
      group.add(pageButton);
      group.add(fontButton);
      frame.add(decision);
      frame.add(redSlider);
      frame.add(coverButton);
      frame.add(pageButton);
      frame.add(fontButton);
      frame.add(color);
      frame.add(redLabel);
      frame.add(blueLabel);
      frame.add(blueSlider);
      frame.add(greenLabel);
      frame.add(greenSlider);
      frame.add(creatingButton);
      frame.add(drawPage);
      // 添加 panel 到框架的中央位置

      frame.setSize(800,600);
      frame.setVisible(true);


      coverButton.addActionListener(new RadioButtonListener());
      pageButton.addActionListener(new RadioButtonListener());
      fontButton.addActionListener(new RadioButtonListener());

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
   public static void main(String[] args){
      creatingPage create = new creatingPage();
   }

}
