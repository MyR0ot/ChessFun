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
                res+="8/";
            else
                res += getRowFromFigures((board), i);
        }     
       return res;
    }
    
    private static boolean isEmptyRow(Cell[][] board, int k/*номер строки*/)
    {
        for(int j = 0; j < 8; j++)
            if(!board[j][k].isEmpty())
                return false;
        return true;
    }
    
    public static String getRowFromFigures(Cell[][] board, int k)
    {
        String r = "";
        for(int j = 0; j < 8; j++)
        {
            switch(board[j][k].getNameFigure())
            {   
                case "PAWN WHITE": r += "P";break;
                case "KNIGHT WHITE": r += "N";break;
                case "BISHOP WHITE": r += "B";break;
                case "ROCK WHITE": r += "R";break;
                case "QUEEN WHITE": r += "Q";break;
                case "KING WHITE": r += "K";break;
                case "PAWN BLACK": r += "p";break;
                case "KNIGHT BLACK": r += "n";break;
                case "BISHOP BLACK": r += "b";break;
                case "ROCK BLACK": r += "r";break;
                case "QUEEN BLACK": r += "q";break;
                case "KING BLACK": r += "k";break;
            }
            if(j == 7 && k != 7)
                r += "/";
        }
        return r;
    }
    
}
