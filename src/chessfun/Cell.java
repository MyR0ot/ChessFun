/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import figures.ColorFigure;
import figures.EmptyFigure;
import figures.Shape;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell {
    
    private int row;            // номер строки 0..8
    private int column;         // номер столбцу 0..8
    private String name;        // Название поля a1, a2, a3, ... , h7, h8
    public Shape shape;         // Фигура, принадлежащая данному полю
    private JLabel label;        // jLabel, привязанный к полю
    
    
    Cell(int column, int row)
    {
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
        this.shape = new EmptyFigure();
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
    
    public String GetNameShape()
    {
        return this.shape.GetName();
    }
    
    public boolean IsEmpty()
    {
        return (this.shape instanceof EmptyFigure);
    }
    
    public void SetIcon(ImageIcon icon)
    {
        this.label.setIcon(icon);
    }
    public JLabel GetLabel()
    {
        return this.label;
    }
    
    
    public ColorFigure GetColor()
    {
        return this.shape.GetColorShape();
    }
}
