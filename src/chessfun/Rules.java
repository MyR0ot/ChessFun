/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

/**
 *
 * @author My
 */
public class Rules {
    public static boolean checkBishop(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkKnight(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
    }
    
    public static boolean checkPawn(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return true;
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
        if(x_from == x_to && y_from == y_to || board[x_from][y_from].getColor() == board[x_to][y_to].getColor())
            return false;
        
        switch (board[x_from][y_from].getNameFigure())
        {
            case "PAWN WHITE": break;
            case "KNIGHT WHITE": break;
            case "BISHOP WHITE": break;
            case "ROCK WHITE": break;
            case "QUEEN WHITE": break;
            case "KING WHITE": break;
            case "PAWN BLACK": break;
            case "KNIGHT BLACK": break;
            case "BISHOP BLACK": break;
            case "ROCK BLACK": break;
            case "QUEEN BLACK": break;
            case "KING BLACK": break;
            default: return false;
        }
        
        return true;
    }
}
