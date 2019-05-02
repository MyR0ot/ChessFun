/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.*;

public class ChessFun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {  
        Game game = new Game(ModeChess.CLASSIC, ModeShape.WIKIPEDIA, 60, 2, "Hikaru", "LachesisQ");
        Globals.game = game;
        game.Start();
        
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });*/
    }    
}