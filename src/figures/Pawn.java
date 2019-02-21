/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

import chessfun.Cell;

public class Pawn extends Shape{
    
    private ColorFigure colorShape; // цвет фигуры

    public Pawn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ColorFigure GetColorShape()
    {
        return colorShape;
    }
    
    @Override
    public  boolean CheckMove(Cell toCell)
    {
        return true;
    }

    @Override
    public boolean CheckBeat(Cell toCell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Pawn(ColorFigure colorFigure) {
        this.colorShape = colorFigure;
    }
    
    @Override
    public String GetName() {
        return "Pawn " + this.colorShape.toString();
    }
}
