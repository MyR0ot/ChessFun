/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.NameFigure;

/**
 *
 * @author My
 */
public class Rules {
    
    public static boolean checkPawn(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkKnight(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return Math.abs(x_from-x_to) == 2 && Math.abs(y_from-y_to) == 1 || Math.abs(x_from-x_to) == 1 && Math.abs(y_from-y_to) == 2;
    }
    
    public static boolean checkBishop(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkRock(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        if(y_from == y_to)
        {
            System.err.println("y_from == y_to");
            if(x_from > x_to)
            {
                for(int i = x_from - 1; i > x_to; i--)
                    if(!board[i][y_from].isEmpty())
                        return false;
                return true;
            }
            else
            {
                for(int i = x_from + 1; i < x_to; i++)
                    if(!board[i][y_from].isEmpty())
                        return false;
                return true;
            }
        }
        else if(x_from == x_to)
        {
            if(y_from > y_to)
            {
                for(int j = y_from - 1; j > y_to; j--)
                    if(!board[x_from][j].isEmpty())
                        return false;
                return true;
            }
            else
            {
                for(int j = y_from + 1; j < y_to; j++)
                    if(!board[x_from][j].isEmpty())
                        return false;
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkQueen(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkKing(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkMove(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        if(isSameField(x_from, y_from, x_to, y_to) || isSameColor(board, x_from, y_from,  x_to, y_to))
            return false;
        
        switch (board[x_from][y_from].getNameFigure().split(" ")[0])
        {
            case "PAWN":    return checkPawn(board, x_from, y_from, x_to, y_to);
            case "KNIGHT":  return checkKnight(board, x_from, y_from, x_to, y_to);       
            case "BISHOP":  return checkBishop(board, x_from, y_from, x_to, y_to);        
            case "ROCK":    return checkRock(board, x_from, y_from, x_to, y_to);       
            case "QUEEN":   return checkQueen(board, x_from, y_from, x_to, y_to);
            case "KING":    return checkKing(board, x_from, y_from, x_to, y_to);
            
            default: System.err.println("Неопознанная фигура"); return false;
        }
    }
    
    
    private static boolean isSameColor(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return board[x_from][y_from].getColor() == board[x_to][y_to].getColor();
    }
    
    private static boolean isSameField(int x_from, int y_from, int x_to, int y_to)
    {
        return x_from == x_to && y_from == y_to;
    }
}
