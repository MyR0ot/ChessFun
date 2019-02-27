/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author My
 */
public class JPanelWithBackground extends JPanel {

  private final Image backgroundImage;

  // Some code to initialize the background image.
  // Here, we use the constructor to load the image. This
  // can vary depending on the use case of the panel.
  public JPanelWithBackground(String fileName) throws IOException {
        backgroundImage = ImageIO.read(new File(fileName));
  }  
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    // Draw the background image.
    g.drawImage(backgroundImage, Globals.delta_x, Globals.delta_y, this.backgroundImage.getWidth(this), this.backgroundImage.getHeight(this), this);
  }
}
