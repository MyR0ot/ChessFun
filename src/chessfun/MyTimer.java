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
    private  final ColorFigure colorTimer;
    public static  String nameWhite;
    public static  String nameBlack;
    
    

  public MyTimer(int startTime, ColorFigure colorTimer, int x_delta, int y_delta) {
    
    this.colorTimer = colorTimer;
    if(colorTimer == ColorFigure.WHITE)
    {
         Globals.timeWhite = startTime;
         this.setBackground(Color.WHITE);
         label = new JLabel("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeWhite)) + "</font><br /><font size=\"4\">" + nameWhite +"</font></html>", JLabel.CENTER);
    }
       
    else
    {
        Globals.timeBlack = startTime;
        this.setBackground(Color.LIGHT_GRAY);
        label = new JLabel("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeBlack)) + "</font><br /><font size=\"4\">" + nameBlack +"</font></html>", JLabel.CENTER);
    }
        
    this.add(label);
    this.setOpaque(true); // Делаем прозрачным
    this.setBounds(x_delta, y_delta, 150, 80);
    new Timer(100, (ActionEvent e) -> {
        if(Globals.stepQueue == colorTimer && Globals.startgame)
        {
            if(colorTimer == ColorFigure.WHITE)
            {
               
                label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeWhite--)) + "</font><br /><font size=\"4\">" + nameWhite +"</font></html>");
            }
            else
            {
                label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeBlack--)) + "</font><br /><font size=\"4\">" + nameBlack +"</font></html>");
            }
        }
        
      }).start();
  }
  
  private static String formate(int time)
  {
      String res = "";
      int minutes = time/600;
      int seconds = (time - minutes*600)/10;
      
      if(time > 200)
          return formateFix(minutes, 2) + ":" + formateFix(seconds, 2);

      int ms = time - minutes*600 - seconds*10;
      
      return formateFix(seconds, 2)+"."+String.valueOf(ms);
  }
  
  private static String formateFix(int number, int size)
  {
      String res = String.valueOf(number);
      while(res.length()<size)
      {
          res = "0" + res;
      }
      
      return res;
  }
}
