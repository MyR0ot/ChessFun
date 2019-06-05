/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.interfaces;

import chessfun.Cell;
import chessfun.Enums.ColorFigure;
import chessfun.Globals;
import java.util.HashSet;
import java.util.Set;

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
    public boolean checkEndOfGame(Cell[][] board);
    
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
    
    default boolean isCastle(int x_from, int y_from, int x_to, int y_to)
    {
        return (x_from == 4 && y_from == 0 && (x_to == 2 && y_to == 0 || x_to == 6 && y_to == 0)) ||
                (x_from == 4 && y_from == 7 && (x_to == 2 && y_to == 7 || x_to == 6 && y_to == 7));           
    }
    
    default boolean isTakeOnPass(int x_from, int y_from, int x_to, int y_to)
    {
        return Globals.bigStepPawn[1][x_to] && y_to == 2 && y_from == 3 || Globals.bigStepPawn[0][x_to] && y_to == 5 && y_from == 4;    
    }
    
    default Set<String> getBeatFields(Cell[][] board, int x, int y)
    {
        Set<String> res = new HashSet<>();
        switch(board[x][y].getNameFigure())
        {
            // <editor-fold desc="Pawns">
            case "PAWN WHITE":
                if(Cell.isValidField(x + 1, y - 1))
                    res.add(Cell.generateNameField(x + 1, y - 1));
                if(Cell.isValidField(x - 1, y - 1))
                    res.add(Cell.generateNameField(x - 1, y - 1));
                break;
            case "PAWN BLACK":
                if(Cell.isValidField(x + 1, y + 1))
                    res.add(Cell.generateNameField(x + 1, y + 1));
                if(Cell.isValidField(x - 1, y + 1))
                    res.add(Cell.generateNameField(x - 1, y + 1));
                break;
            // </editor-fold>
            // <editor-fold desc="Knights">
              case "KNIGHT WHITE":
            case "KNIGHT BLACK":
                if(Cell.isValidField(x + 1, y + 2))
                    res.add(Cell.generateNameField(x + 1, y + 2));
                if(Cell.isValidField(x + 1, y - 2))
                    res.add(Cell.generateNameField(x + 1, y - 2));
                if(Cell.isValidField(x - 1, y + 2))
                    res.add(Cell.generateNameField(x - 1, y + 2));
                if(Cell.isValidField(x - 1, y - 2))
                    res.add(Cell.generateNameField(x - 1, y - 2));
                if(Cell.isValidField(x + 2, y + 1))
                    res.add(Cell.generateNameField(x + 2, y + 1));
                if(Cell.isValidField(x + 2, y - 1))
                    res.add(Cell.generateNameField(x + 2, y - 1));
                if(Cell.isValidField(x - 2, y + 1))
                    res.add(Cell.generateNameField(x - 2, y + 1));
                if(Cell.isValidField(x - 2, y - 1))
                    res.add(Cell.generateNameField(x - 2, y - 1));
                break;
            // </editor-fold>
            // <editor-fold desc="Bishops">   
            case "BISHOP WHITE":
            case "BISHOP BLACK":
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x + i, y + i))
                    {
                        res.add(Cell.generateNameField(x + i, y + i));
                        if(board[x + i][y + i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x - i, y - i))
                    {
                        res.add(Cell.generateNameField(x - i, y - i));
                        if(board[x - i][y - i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x - i, y + i))
                    {
                        res.add(Cell.generateNameField(x - i, y + i));
                        if(board[x - i][y + i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x + i, y - i))
                    {
                        res.add(Cell.generateNameField(x + i, y - i));
                        if(board[x + i][y - i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                break;
            // </editor-fold>
            // <editor-fold desc="Rocks">
            case "ROCK WHITE":
            case "ROCK BLACK":
                for(int i=x+1; i<8; i++)
                {
                    if(Cell.isValidField(i, y))
                    {
                        res.add(Cell.generateNameField(i, y));
                        if(board[i][y].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i=x-1; i>=0; i--)
                {
                    if(Cell.isValidField(i, y))
                    {
                        res.add(Cell.generateNameField(i, y));
                        if(board[i][y].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int j=y+1; j < 8; j++)
                {
                    if(Cell.isValidField(x, j))
                    {
                        res.add(Cell.generateNameField(x, j));
                        if(board[x][j].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else break;
                }
                for(int j=y-1; j>=0; j--)
                {
                    if(Cell.isValidField(x, j))
                    {
                        res.add(Cell.generateNameField(x, j));
                        if(board[x][j].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else break;
                }
                break;
            // </editor-fold>
            // <editor-fold desc="Queens">     
            case "QUEEN WHITE":
            case "QUEEN BLACK":
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x + i, y + i))
                    {
                        res.add(Cell.generateNameField(x + i, y + i));
                        if(board[x + i][y + i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x - i, y - i))
                    {
                        res.add(Cell.generateNameField(x - i, y - i));
                        if(board[x - i][y - i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x - i, y + i))
                    {
                        res.add(Cell.generateNameField(x - i, y + i));
                        if(board[x - i][y + i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i = 1; i < 8; i++)
                {
                    if(Cell.isValidField(x + i, y - i))
                    {
                        res.add(Cell.generateNameField(x + i, y - i));
                        if(board[x + i][y - i].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i=x+1; i<8; i++)
                {
                    if(Cell.isValidField(i, y))
                    {
                        res.add(Cell.generateNameField(i, y));
                        if(board[i][y].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int i=x-1; i>=0; i--)
                {
                    if(Cell.isValidField(i, y))
                    {
                        res.add(Cell.generateNameField(i, y));
                        if(board[i][y].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else
                        break;
                }
                for(int j=y+1; j < 8; j++)
                {
                    if(Cell.isValidField(x, j))
                    {
                        res.add(Cell.generateNameField(x, j));
                        if(board[x][j].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else break;
                }
                for(int j=y-1; j>=0; j--)
                {
                    if(Cell.isValidField(x, j))
                    {
                        res.add(Cell.generateNameField(x, j));
                        if(board[x][j].getColor() != ColorFigure.NONE)
                            break;
                    }
                    else break;
                }
                break;    
            // </editor-fold>
            // <editor-fold desc="Kings">
            case "KING WHITE":
            case "KING BLACK":
                if(Cell.isValidField(x - 1, y + 1))
                    res.add(Cell.generateNameField(x - 1, y + 1));
                if(Cell.isValidField(x, y + 1))
                    res.add(Cell.generateNameField(x, y + 1));
                if(Cell.isValidField(x + 1, y + 1))
                    res.add(Cell.generateNameField(x + 1, y + 1));
                if(Cell.isValidField(x - 1, y))
                    res.add(Cell.generateNameField(x - 1, y));
                if(Cell.isValidField(x + 1, y))
                    res.add(Cell.generateNameField(x + 1, y));
                if(Cell.isValidField(x - 1, y - 1))
                    res.add(Cell.generateNameField(x - 1, y - 1));
                if(Cell.isValidField(x, y - 1))
                    res.add(Cell.generateNameField(x, y - 1));
                if(Cell.isValidField(x + 1, y - 1))
                    res.add(Cell.generateNameField(x + 1, y - 1));
                break;
            // </editor-fold>
            default: return res;
        }
        return res;
    }
    
    default Set<String> getBeatFieldsByBlack(Cell[][] board)
    {
       Set<String> res = new HashSet<>();
       for(int i=0;i<8;i++)
           for(int j=0; j<8;j++)
           {
               if(board[i][j].getColor() == ColorFigure.BLACK)
                   res.addAll(getBeatFields(board, i, j));
           }
        return res;
    }
    
    default Set<String> getBeatFieldsByWhite(Cell[][] board)
    {
       Set<String> res = new HashSet<>();
       for(int i = 0; i < 8; i++)
           for(int j = 0; j < 8; j++)
           {
               if(board[i][j].getColor() == ColorFigure.WHITE)
                   res.addAll(getBeatFields(board, i, j));
           }
        return res;
    }
    
    default boolean isRisk(Cell[][] board, int x, int y, ColorFigure colorRisk) // colorRisk - цвет угрожающей стороны
    {
        
        Set<String> riskFields;
        if(colorRisk == ColorFigure.WHITE)
        {
            return getBeatFieldsByWhite(board).contains(Cell.generateNameField(x, y));
        }
        else if(colorRisk == ColorFigure.BLACK)
        {
            return getBeatFieldsByBlack(board).contains(Cell.generateNameField(x, y));
        }
        
        return false;
    }
    
    
    default boolean isKingSafety(Cell[][] board, int x_from, int y_from, int x_to, int y_to)
    {
        ColorFigure colorKing = board[x_from][y_from].getColor();
        
        if(colorKing == ColorFigure.WHITE && getBeatFieldsByBlack(board).contains(Cell.generateNameField(x_to, y_to))) return false;
        
        if(colorKing == ColorFigure.BLACK && getBeatFieldsByWhite(board).contains(Cell.generateNameField(x_to, y_to))) return false;
        
        return true;
    }
    
    
    @Deprecated
    default void showBeatFieldsByThisShape(Cell board[][], int x, int y)
    {
        Set<String> set = getBeatFields(board, x, y);
        System.out.print(board[x][y].getName()+ " ");
        System.out.print(board[x][y].getNameFigure());
        System.out.println(" " + set.size());
        for(String s : set)
            System.out.println(s);
    }
}
