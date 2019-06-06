/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyPanels;

import chessfun.Analysis;
import chessfun.Enums.ColorFigure;
import chessfun.Enums.DirectionStep;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class DirectionPanel extends JPanel {
    private final JLabel label;    
    private final Analysis analysis;
    private List<String> notes;
    private static int curNote;
    private ColorFigure clrQueue;
    

  public DirectionPanel(int x_delta, int y_delta, DirectionStep direction, ImageIcon image, Analysis anlParent) {
    this.label = new JLabel(image);
    this.analysis = anlParent;
    this.notes = analysis.history.getNotes();
    this.curNote = 0;
    this.clrQueue = ColorFigure.WHITE;
    this.add(label);
    this.setBounds(x_delta + 160, y_delta - 4, 88, 88);// FIX
    label.addMouseListener(new MyMouseListener(direction));
  }
  

  
  
  private void moveBack()
  {
      if(curNote == 0)
          return;
      curNote--;
      
      if(notes.get(curNote).equals("0-0"))
      {
          changeQueue();
          return;
      }
      
      if(notes.get(curNote).equals("0-0-0"))
      {
          changeQueue();
          return;
      }
      String[] arr = notes.get(curNote).split(" ");
      analysis.move(arr[1], arr[0]);
      changeQueue();
      System.err.append("move back!\n");
  }
  
  private void moveForward()
  {
      if(curNote == notes.size() - 1)
          return;
      if(notes.get(curNote).equals("0-0"))
      {
          curNote++;
          changeQueue();
          return;
      }
      
      if(notes.get(curNote).equals("0-0-0"))
      {
          
          curNote++;
          changeQueue();
          return;
      }
      
      String[] arr = notes.get(curNote).split(" ");
      analysis.move(arr[0], arr[1]);
      
      curNote++;
      changeQueue();
      System.err.append("move forward!\n");
  }
  
  private void changeQueue()
  {
      if(clrQueue == ColorFigure.BLACK)
          clrQueue = ColorFigure.WHITE;
      else
          clrQueue = ColorFigure.BLACK;
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
