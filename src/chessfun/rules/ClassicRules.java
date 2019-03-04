/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.rules;

import chessfun.Cell;
import chessfun.Enums.ColorFigure;
import chessfun.Game;
import chessfun.Globals;
import chessfun.interfaces.IRules;
import java.util.HashSet;
import java.util.Set;

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
    public boolean checkPawn(Cell[][] board, int x_from, int y_from, int x_to, int y_to) // добавить взятие на проходе, превращение в ферзя ...
    {
        if(x_from == x_to)
        {
            if(!board[x_to][y_to].isEmpty())
                return false;
            if(Globals.stepQueue == ColorFigure.WHITE)
            {
                if(y_from == 6)
                {
                    return y_to == 5 || y_to == 4;
                }
                return y_to == y_from-1;
            }
            else
            {
                if(y_from == 1)
                {
                    return y_to == 2 || y_to == 3;
                }
                return y_to == y_from + 1;
            }
        }
        if(Math.abs(x_from - x_to) != 1 || Math.abs(y_from - y_to)!= 1)
            return false;
        
        if(Globals.stepQueue == ColorFigure.WHITE)
            return y_to == y_from - 1 && board[x_to][y_to].getColor() == ColorFigure.BLACK || Globals.bigStepPawn[1][x_to] && y_to == 2;
       
        return y_to == y_from + 1 && board[x_to][y_to].getColor() == ColorFigure.WHITE || Globals.bigStepPawn[0][x_to] && y_to == 5;
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
    public boolean checkKing(Cell[][] board, int x_from, int y_from, int x_to, int y_to) // добавить рокировку
    {
        String nameTo = Cell.generateNameField(x_to, y_to);
        Set<String> beatsField = new HashSet();
        for(int i=0;i<8;i++)
            for(int j=0; j<8;j++)
            {
                if(board[i][j].getColor() != ColorFigure.NONE && board[i][j].getColor() != board[x_from][y_from].getColor())
                {
                    Set<String> beats = IRules.super.getBeatFiels(board, i, j);
                    beatsField.addAll(beats);
                    if(beats.contains(nameTo))
                        return false; // Нельзя ходить королем по битым полям
                }
            }

        if(isCastle(x_from, y_from, x_to, y_to))
            return checkCastle(board, x_from, y_from, x_to, y_to, beatsField);
        
        return Math.abs(x_to - x_from) <= 1 && Math.abs(y_to - y_from) <= 1 && (
                Math.abs(x_to - x_from) !=0 || Math.abs(y_to - y_from) != 0);
    }
    
    public static boolean isBeatField(Cell[][] board, int x, int y)
    {
        return false;
    }
    
    public static boolean checkCastle(Cell[][] board, int x_from, int y_from, int x_to, int y_to, Set<String> beatFields)
    {
        if(board[x_from][y_from].getColor() == ColorFigure.WHITE)
            return Globals.allowCastleWhite && !beatFields.contains(Cell.generateNameField((x_from + x_to) / 2, y_to));            
        return Globals.allowCastleBlack && !beatFields.contains(Cell.generateNameField((x_from + x_to) / 2, y_to));  
    }
}
