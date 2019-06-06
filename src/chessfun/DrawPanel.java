/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.ColorFigure;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class DrawPanel extends JPanel{
    private final JLabel label;
    
    public DrawPanel(int x_delta, int y_delta, ImageIcon icon)
    {
        this.label = new JLabel(icon);
        this.add(label);
        this.setBounds(x_delta + 160, y_delta - 4, 88, 88);
        label.addMouseListener(new MyMouseListener());
    }

    
    private void FireEvent()
    {
        Globals.game.openEndOfGame(ColorFigure.NONE);
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
