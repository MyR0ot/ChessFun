/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.ColorFigure;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author My
 */
public class MyTimer extends JPanel {
    private final JLabel label;
    private  ColorFigure colorTimer;
    
    

  public MyTimer(int startTime, ColorFigure colorTimer, int x_delta, int y_delta) {
    label = new JLabel(formate(startTime), JLabel.CENTER);
    this.colorTimer = colorTimer;
    if(colorTimer == ColorFigure.WHITE)
        Globals.timeWhite = startTime;
    else
        Globals.timeBlack = startTime;
    if(colorTimer== ColorFigure.WHITE)
        this.setBackground(Color.WHITE);
    else
        this.setBackground(Color.LIGHT_GRAY);
    this.add(label);
    this.setOpaque(true); // Делаем прозрачным
    this.setBounds(x_delta, y_delta, 150, 80);
    new Timer(100, (ActionEvent e) -> {
        if(Globals.stepQueue == colorTimer)
        {
            if(colorTimer == ColorFigure.WHITE)
            {
                label.setText(String.valueOf(formate(Globals.timeWhite--)));
            }
            else
            {
                label.setText(String.valueOf(formate(Globals.timeBlack--)));
            }
        }
        
      }).start();
  }
  
  private static String formate(int time)
  {
      String res = "";
      int minutes = time/600;
      int seconds = (time - minutes*600)/10;
      int ms = time - minutes*600 - seconds*10;
       
      return String.valueOf(minutes) + ":"+String.valueOf(seconds)+"."+String.valueOf(ms);
  }
}
