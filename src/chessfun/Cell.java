/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import figures.EmptyFigure;
import figures.Shape;

/**
 *
 * @author My
 */
public class Cell {
    
    private int row;        // номер строки
    private int column;     // номер столбцу
    private String name;    // Название поля
    public Shape shape;     // Фигура, принадлежащая данному полю
    
    
    Cell(int column, int row) {
        this.row = row;
        this.column = column;
        
        switch(column)
        {
            case 0 : this.name = "a"; break;
            case 1 : this.name = "b"; break;
            case 2 : this.name = "c"; break;
            case 3 : this.name = "d"; break;
            case 4 : this.name = "e"; break;
            case 5 : this.name = "f"; break;
            case 6 : this.name = "g"; break;
            case 7 : this.name = "h"; break;
            default: throw new UnsupportedOperationException("chess board is 8*8");
        }
        this.name = this.name + this.row + 1;
        this.shape = new EmptyFigure();
    }
    
    public String GetName()
    {
        String res = "";
        switch(this.column)
        {
            case 0: res = "a"; break;
            case 1: res = "b"; break;
            case 2: res = "c"; break;
            case 3: res = "d"; break;
            case 4: res = "e"; break;
            case 5: res = "f"; break;
            case 6: res = "g"; break;
            case 7: res = "h"; break;
        }
        
        res += (this.row + 1);
        
        return res;
    }
    
    public int GetRow()
    {
        return this.row;
    }
    
    public int GetColumn()
    {
        //hghg
        return this.column;
    }
    
    public String GetNameShape()
    {
        return this.shape.GetName();
    }
    
    public boolean IsEmpty()
    {
        return (this.shape instanceof EmptyFigure);
    }
}
