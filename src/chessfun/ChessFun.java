/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class ChessFun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {  
        Game game = new Game(ModeChess.CLASSIC, ModeShape.WIKIPEDIA);
        Globals.game = game;
        game.Start();

    }   
    
    

    
}