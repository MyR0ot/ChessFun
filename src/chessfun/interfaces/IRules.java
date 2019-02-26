/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.interfaces;

import chessfun.Cell;

/**
 *
 * @author My
 */
public interface IRules {
     public boolean checkMove(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkPawn(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkKnight(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkBishop(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkRock(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkQueen(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    public boolean checkKing(Cell[][] board, int x_from, int y_from, int x_to, int y_to);
    
    default boolean isSameColor(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return board[x_from][y_from].getColor() == board[x_to][y_to].getColor();
    }
    
    default boolean isSameField(int x_from, int y_from, int x_to, int y_to)
    {
        return x_from == x_to && y_from == y_to;
    }
    
    default boolean isEmptyLine(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        if(y_from == y_to)
        {
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
    
    default boolean isEmptyDiagonal(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        if(Math.abs(x_from-x_to)!=Math.abs(y_from-y_to))
            return false;
        
        if(x_to > x_from)
        {
            if(y_to > y_from)
            {
                for(int i = 1; i + x_from < x_to; i++)
                {
                    if(!board[x_from + i][y_from + i].isEmpty())
                        return false;
                }
                return true;
            }
            else
            {
                for(int i = 1; i + x_from < x_to; i++)
                {
                    if(!board[x_from + i][y_from - i].isEmpty())
                        return false;
                }
                return true;
            }
        }
        else
        {
            if(y_to > y_from)
            {
                for(int i = 1; i + y_from < y_to; i++)
                {
                    if(!board[x_from - i][y_from + i].isEmpty())
                        return false;
                }
                return true;
            }
            else
            {
                for(int i = 1; y_to < y_from - i; i++)
                {
                    if(!board[x_from - i][y_from - i].isEmpty())
                        return false;
                }
                return true;
            }
        }
    }
}
