/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyPanels;

import chessfun.Enums.ColorFigure;
import chessfun.Globals;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class ResignPanel extends JPanel{
    
    private final JLabel label; 
    private final ColorFigure color;

  public ResignPanel(ColorFigure clr, int x_delta, int y_delta, ImageIcon imageIcon) {
    this.label = new JLabel(imageIcon);
    this.color = clr;
    this.add(label);
    this.setBounds(x_delta + 160, y_delta - 4, 88, 88);

    label.addMouseListener(new MyMouseListener());
  }
  
  private void FireEvent()
  {     
      if(Globals.stepQueue == this.color)
      {
          Globals.changeQueue();
          Globals.game.openEndOfGame(Globals.stepQueue);
      }
  }
  

  class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            FireEvent();
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
  

