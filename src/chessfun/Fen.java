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
public class Fen {
    
    public static String getFEN(Cell[][] board) // FIX
    {
        String res = "";
        for(int i = 0; i < 8; i++)
        {
            if(isEmptyRow(board, i))
                res += "8/";
            else
                res += getRowFromFigures(board, i);
        }     
       return res;
    }
    
    private static boolean isEmptyRow(Cell[][] board, int row)
    {
        for(int j = 0; j < 8; j++)
            if(!board[j][row].isEmpty())
                return false;
        return true;
    }
    
    public static String getRowFromFigures(Cell[][] board, int row)
    {
        String res = "";
        for(int j = 0; j < 8; j++)
        {
            switch(board[j][row].getNameFigure())
            {   
                case "PAWN WHITE": res += "P";break;
                case "KNIGHT WHITE": res += "N";break;
                case "BISHOP WHITE": res += "B";break;
                case "ROCK WHITE": res += "R";break;
                case "QUEEN WHITE": res += "Q";break;
                case "KING WHITE": res += "K";break;
                case "PAWN BLACK": res += "p";break;
                case "KNIGHT BLACK": res += "n";break;
                case "BISHOP BLACK": res += "b";break;
                case "ROCK BLACK": res += "r";break;
                case "QUEEN BLACK": res += "q";break;
                case "KING BLACK": res += "k";break;
                default: /* пустая строка */ break;
            }
            if(j == 7 && row != 7)
                res += "/";
        }
        return res;
    }
    
}
