/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyPanels;

import chessfun.Globals;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class ExitPanel extends JPanel {
    private final JLabel label;
    private JFrame anlParent;
    

  public ExitPanel(int x_delta, int y_delta, ImageIcon image, JFrame parent) {
    this.label = new JLabel(image);
    this.anlParent = parent;
    this.add(label);
    this.setBounds(x_delta + 160, y_delta - 4, 88, 88);// FIX
    
    this.label.addMouseListener(new MyMouseListener());
  }
  

  class MyMouseListener implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            anlParent.setVisible(false);
            Globals.game.view.setVisible(true);
            Globals.isAnalysis = false;
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
