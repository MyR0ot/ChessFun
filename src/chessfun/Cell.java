/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.*;
import chessfun.events.TryMoveEvent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell {

    private int row;                // номер строки 0..8
    private int column;             // номер столбцу 0..8
    private String nameField;       // Название поля a1, a2, a3, ... , h7, h8
    private JLabel label;           // jLabel, привязанный к полю
    private JLabel labelSelected;   // Лабел, отвечающий за выделение фигуры
    private ColorFigure colorFigure;// Цвет фигуры, находящейся на данном поле
    private NameFigure nameFigure;  // иконки для отображения фигур
    

    Cell(int column, int row) {
        setFigure(NameFigure.EMPTY, ColorFigure.NONE);
        this.row = row;
        this.column = column;
        this.label = new JLabel();
        this.labelSelected = new JLabel();
        this.label.setBounds(Globals.delta_x + 80 * column,Globals.delta_y+ 80 * row, 80, 80);
        this.labelSelected.setBounds(Globals.delta_x+ 80 * column,Globals.delta_y+ 80 * row, 80, 80);
        this.label.addMouseListener(new MyMouseListener());
        switch (column) {
            case 0: this.nameField = "a"; break;
            case 1: this.nameField = "b"; break;
            case 2: this.nameField = "c"; break;
            case 3: this.nameField = "d"; break;
            case 4: this.nameField = "e"; break;
            case 5: this.nameField = "f"; break;
            case 6: this.nameField = "g"; break;
            case 7: this.nameField = "h"; break;
            default: throw new UnsupportedOperationException("chess board is 8*8");
        }
        this.nameField += (8 - this.row);
    }

    public String getName() {
        return this.nameField;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean isEmpty() {
        return (this.nameFigure == NameFigure.EMPTY);
    }

    public void setIcon(ImageIcon icon) {
        this.label.setIcon(icon);
    }

    public ColorFigure getColor() {
        return this.colorFigure;
    }

    public void setFigure(NameFigure name, ColorFigure color) {
        this.colorFigure = color;
        this.nameFigure = name;
    }

    public String getNameFigure() {
        return this.nameFigure.toString() + " " + this.colorFigure.toString();
    }

    public JLabel getLabel() {
        return this.label;
    }

    public JLabel getLabelSelect() {
        return this.labelSelected;
    }
    
    public void setLabelSelect(ImageIcon icon) {
        this.labelSelected.setIcon(icon);
    }
    
    public List<Cell> getBeatFieldsByThisFigure() // получение полей, бьющееся фигурой, находящейся на данном поле
    {
        List<Cell> res = new ArrayList <Cell>();
        // TODO:
        //
        
        
        
        return res;
    }

    public class MyMouseListener implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(Globals.isSelectedFigure)
            {   
                fireEvent(Globals.selectedCellName, nameField);
                Globals.isSelectedFigure = false;
            }
            else
            {
                if(nameFigure == NameFigure.EMPTY || colorFigure != Globals.stepQueue)
                    return;
                labelSelected.setIcon(Globals.iconSelected);
                Globals.rowSelected = row;
                Globals.columnSelected = column;
                Globals.selectedCellName = nameField;
                Globals.isSelectedFigure = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // System.out.println("i released");
            //label.setText("i released");

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        
        protected void fireEvent(String from, String to){
 	    Globals.game.tryMove(new TryMoveEvent(this, from, to));
 	}
        
        
    }
}
