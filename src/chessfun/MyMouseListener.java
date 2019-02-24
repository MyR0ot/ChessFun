/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author My
 */
public class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("i clicked");
            //label.setText("i clicked");
 
        }
 
        @Override
        public void mousePressed(MouseEvent e) {
            // System.out.println("i pressed");
            //.setText("i pressed");
 
        }
 
        @Override
        public void mouseReleased(MouseEvent e) {
            // System.out.println("i released");
            //label.setText("i released");
 
        }
 
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
 
        }
 
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
 
        }
 
    }
