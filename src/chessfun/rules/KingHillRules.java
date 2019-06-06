/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.rules;

import chessfun.Cell;

/**
 *
 * @author My
 */
public class KingHillRules extends ClassicRules {

    @Override
    public  boolean checkEndOfGame(Cell[][] board)
    {
        for(int i=3;i<5;i++)
            for(int j=3;j<5;j++)
            {
                System.err.append(board[i][j].getNameFigure() + "\n");
                if("KING WHITE".equals(board[i][j].getNameFigure()) || "KING BLACK".equals(board[i][j].getNameFigure()))
                {
                    System.err.append(i + " " + j + "\n");
                    return true;
                }
                    
            }
        return false;
    }
    
}
