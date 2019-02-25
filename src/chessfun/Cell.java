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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cell {

    private int row;                // номер строки 0..8
    private int column;             // номер столбцу 0..8
    private String nameField;       // Название поля a1, a2, a3, ... , h7, h8
    private JLabel label;           // jLabel, привязанный к полю
    private JLabel labelSelected;     // Лабел, отвечающий за выделение фигуры
    private ColorFigure colorFigure;// Цвет фигуры, находящейся на данном поле
    private NameFigure nameFigure;  // иконки для отображения фигур
    public boolean selected;          // выбрана фигура или нет

    Cell(int column, int row) {
        SetFigure(NameFigure.EMPTY, ColorFigure.NONE);
        this.row = row;
        this.column = column;
        this.selected = false;
        this.label = new JLabel();
        this.labelSelected = new JLabel();
        this.label.setBounds(80 * column, 80 * row, 80, 80);
        this.labelSelected.setBounds(80 * column, 80 * row, 80, 80);
        this.label.addMouseListener(new MyMouseListener());
        switch (column) {
            case 0:
                this.nameField = "a";
                break;
            case 1:
                this.nameField = "b";
                break;
            case 2:
                this.nameField = "c";
                break;
            case 3:
                this.nameField = "d";
                break;
            case 4:
                this.nameField = "e";
                break;
            case 5:
                this.nameField = "f";
                break;
            case 6:
                this.nameField = "g";
                break;
            case 7:
                this.nameField = "h";
                break;
            default:
                throw new UnsupportedOperationException("chess board is 8*8");
        }
        this.nameField += (8 - this.row);
    }

    public String GetName() {
        return this.nameField;
    }

    public int GetRow() {
        return this.row;
    }

    public int GetColumn() {
        return this.column;
    }

    public boolean IsEmpty() {
        return (this.nameFigure == NameFigure.EMPTY);
    }

    public void SetIcon(ImageIcon icon) {
        this.label.setIcon(icon);
    }

    public ColorFigure GetColor() {
        return this.colorFigure;
    }

    public void SetFigure(NameFigure name, ColorFigure color) {
        this.colorFigure = color;
        this.nameFigure = name;
    }

    public String GetNameFigure() {
        return this.nameFigure.toString() + " " + this.colorFigure.toString();
    }

    public JLabel GetLabel() {
        return this.label;
    }

    public JLabel GetLabelSelect() {
        return this.labelSelected;
    }
    
    public void SetLabelSelect(ImageIcon icon) {
        this.labelSelected.setIcon(icon);
    }

    public class MyMouseListener implements MouseListener {
        
        @Override
        public void mouseClicked(MouseEvent e) {
            /*if (Globals.isSelectedFigure && selected) {
                    labelSelected.setIcon(Globals.iconEmpty);
                    selected = false;
                    Globals.isSelectedFigure = false;
                    
            } else if(!Globals.isSelectedFigure){
                    labelSelected.setIcon(Globals.iconSelected);
                    selected = true;
                    Globals.isSelectedFigure = true;
                    Globals.rowSelected = GetRow();
                    Globals.columnSelected = GetColumn();
            }*/
            
            if(Globals.isSelectedFigure)
            {
                fireEvent(Globals.from, nameField);
                //labelSelected.setIcon(Globals.iconEmpty);
                Globals.isSelectedFigure = false;
            }
            else
            {
                labelSelected.setIcon(Globals.iconSelected);
                Globals.rowSelected = GetRow();
                Globals.columnSelected = GetColumn();
                Globals.from = nameField;
                Globals.isSelectedFigure = true;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // System.out.println("i pressed");
            //.setText("i pressed");

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
 	    Globals.game.TryMove(new TryMoveEvent(this, from, to));
 	}
    }
}
