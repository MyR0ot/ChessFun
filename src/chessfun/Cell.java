/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell {
    
    private int row;                // номер строки 0..8
    private int column;             // номер столбцу 0..8
    private String name;            // Название поля a1, a2, a3, ... , h7, h8
    private JLabel label;           // jLabel, привязанный к полю
    private ColorFigure colorFigure;
    private NameFigure nameFigure;
    
    
    Cell(int column, int row)
    {
        this.colorFigure = ColorFigure.NONE;
        this.nameFigure = NameFigure.EMPTY;
        
        this.row = row;
        this.column = column;
        this.label = new JLabel();
        this.label.setBounds(80*column, 80*row, 80, 80);
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
        this.name += (this.row + 1);
    }
    
    public String GetName()
    {
        return this.name;
    }
    
    public int GetRow()
    {
        return this.row;
    }
    
    public int GetColumn()
    {
        return this.column;
    }
    
    public NameFigure GetNameShape()
    {
        return this.nameFigure;
    }
    
    public boolean IsEmpty()
    {
        return (this.nameFigure == NameFigure.EMPTY);
    }
    
    public void SetIcon(ImageIcon icon)
    {
        this.label.setIcon(icon);
    }
    
    public ColorFigure GetColor()
    {
        return this.colorFigure;
    }
    
    public void SetFigure(NameFigure name, ColorFigure color)
    {
        this.colorFigure = color;
        this.nameFigure = name;
    }
    
    public String GetNameFigure()
    {
        return this.nameFigure.toString()+" " + this.colorFigure.toString();
    }
}
