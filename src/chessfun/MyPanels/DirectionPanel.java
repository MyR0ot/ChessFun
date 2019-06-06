/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyPanels;

import chessfun.Enums.DirectionStep;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class DirectionPanel extends JPanel {
    private final JLabel label;    

  public DirectionPanel(int x_delta, int y_delta, DirectionStep direction, ImageIcon image) {
    this.label = new JLabel(image);
    this.add(label);
    this.setBounds(x_delta + 160, y_delta - 4, 88, 88);// FIX
    label.addMouseListener(new MyMouseListener(direction));
  }
  

  
  
  private void moveBack()
  {
      System.err.append("move back!\n");
  }
  
  private void moveForward()
  {
      System.err.append("move forward!\n");
  }
  

  class MyMouseListener implements MouseListener {
      
      private DirectionStep direction;
        MyMouseListener(DirectionStep dir)
        {
            direction = dir;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(direction)
            {
                case BACK: moveBack(); break;
                case FORWARD: moveForward(); break;
            }
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
 
        @Override
        public void mouseReleased(MouseEvent e) {

        }
 
        @Override
        public void mouseEntered(MouseEvent e) {
 
        }
 
        @Override
        public void mouseExited(MouseEvent e) {
 
        }
 
    }
    
}
