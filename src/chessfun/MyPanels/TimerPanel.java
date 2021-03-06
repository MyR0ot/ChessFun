/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.MyPanels;

import chessfun.Enums.ColorFigure;
import chessfun.Globals;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author My
 */
public class TimerPanel extends JPanel {
    private final JLabel label;
    private  final ColorFigure colorTimer;
    public static  String nameWhite;
    public static  String nameBlack;
    
    private final Timer timer;
    

  public TimerPanel(int startTime, ColorFigure colorTimer, int x_delta, int y_delta) {
    
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
    
    this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.add(label);
    this.setOpaque(true); // Делаем прозрачным
    this.setBounds(x_delta, y_delta, 150, 80);
    
    this.timer = new Timer(100, (ActionEvent e) -> {
        if(Globals.stepQueue == colorTimer)
        {
            if(Globals.startgame)
            {
                if(colorTimer == ColorFigure.WHITE)
                    label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeWhite--)) + "</font><br /><font size=\"4\">" + nameWhite +"</font></html>");
                else
                    label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeBlack--)) + "</font><br /><font size=\"4\">" + nameBlack +"</font></html>");
                if(Globals.timeWhite <= 0 || Globals.timeBlack <= 0)
                {
                    Globals.changeQueue();
                    Globals.game.openEndOfGame(Globals.stepQueue);
                    Globals.startgame = false;
                }
            }
            else
            {
                if(colorTimer == ColorFigure.WHITE)
                    label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeStart)) + "</font><br /><font size=\"4\">" + nameWhite +"</font></html>");
                else
                    label.setText("<html><font size=\"7\">" + String.valueOf(formate(Globals.timeStart)) + "</font><br /><font size=\"4\">" + nameBlack +"</font></html>");
            }
        }
      });
    
    timer.start();
  }
  
  public void start()
  {
      this.timer.start();
  }
  
  public void stop()
  {
      this.timer.stop();
  }
  
  public void resetTime()
  {
      Globals.timeWhite = Globals.timeStart;
      Globals.timeBlack = Globals.timeStart;  
      
      if(colorTimer == ColorFigure.WHITE)
          label.setText("<html><font size=\"7\">" +
                  String.valueOf(formate(Globals.timeWhite)) +
                  "</font><br /><font size=\"4\">" + nameWhite +"</font></html>");
      else
          label.setText("<html><font size=\"7\">" +
                  String.valueOf(formate(Globals.timeBlack)) +
                  "</font><br /><font size=\"4\">" + nameBlack +"</font></html>");
  }
  
  
  private static String formate(int time)
  {
      String res = "";
      int minutes = time/600;
      int seconds = (time - minutes*600)/10;
      int ms = time - minutes*600 - seconds*10;
      
      if(time > 600)
          return formateFix(minutes, 2) + ":" + formateFix(seconds, 2);
      else if(time > 200)
          return formateFix(minutes, 1) + ":" + formateFix(seconds, 2);
      else
          return String.valueOf(seconds)+"."+String.valueOf(ms);
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
