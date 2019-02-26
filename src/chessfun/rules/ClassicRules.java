/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.rules;

import chessfun.Cell;
import chessfun.interfaces.IRules;

/**
 *
 * @author My
 */
public class ClassicRules implements IRules {
    
    @Override
    public boolean checkMove(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
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
    
    @Override
    public boolean checkPawn(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        //TODO:
        //
        // добавить взятие на проходе, превращение в ферзя ...
        // добавить превращение в ферзя
        
        return true;
    }
    
    @Override
    public boolean checkKnight(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return Math.abs(x_from-x_to) == 2 && Math.abs(y_from-y_to) == 1 || Math.abs(x_from-x_to) == 1 && Math.abs(y_from-y_to) == 2;
    }
    
    @Override
    public boolean checkBishop(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return isEmptyDiagonal(board, x_from, y_from, x_to, y_to);
    }
    
    @Override
    public boolean checkRock(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return isEmptyLine(board, x_from, y_from, x_to, y_to);
    }
    
    @Override
    public boolean checkQueen(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        return isEmptyDiagonal(board, x_from, y_from, x_to, y_to) || isEmptyLine(board, x_from, y_from, x_to, y_to);
    }
    
    @Override
    public boolean checkKing(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        // + проверка на присутствие шаха или битого поля [x_to][y_to]
        // + добавить возможность рокировки
        return Math.abs(x_to - x_from) == 1 || Math.abs(y_to - y_from) == 1 ;
    }
    
}
