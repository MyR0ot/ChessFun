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
    private String nameField;       // Название поля a1, a2, a3, ... , h7, h8
    private JLabel label;           // jLabel, привязанный к полю
    private ColorFigure colorFigure;
    private NameFigure nameFigure;
    
    
    Cell(int column, int row)
    {
        SetFigure(NameFigure.EMPTY, ColorFigure.NONE);
        
        this.row = row;
        this.column = column;
        this.label = new JLabel();
        this.label.setBounds(80*column, 80*row, 80, 80);
        switch(column)
        {
            case 0 : this.nameField = "a"; break;
            case 1 : this.nameField = "b"; break;
            case 2 : this.nameField = "c"; break;
            case 3 : this.nameField = "d"; break;
            case 4 : this.nameField = "e"; break;
            case 5 : this.nameField = "f"; break;
            case 6 : this.nameField = "g"; break;
            case 7 : this.nameField = "h"; break;
            default: throw new UnsupportedOperationException("chess board is 8*8");
        }
        this.nameField += (8-this.row);
    }
    
    public String GetName()
    {
        return this.nameField;
    }
    
    public int GetRow()
    {
        return this.row;
    }
    
    public int GetColumn()
    {
        return this.column;
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
        return this.nameFigure.toString()+ " " + this.colorFigure.toString();
    }
    
    public JLabel GetLabel()
    {
        return this.label;
    }
}
