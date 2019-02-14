/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

import chessfun.Cell;


public class EmptyFigure extends Shape {
    
    @Override
    public ColorFigure GetColorShape()
    {
        return ColorFigure.NONE;
    }
    
    @Override
    public  boolean CheckMove(Cell toCell)
    {
        return false;
    }

    @Override
    public boolean CheckBeat(Cell toCell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String GetName() {
        return "Empty";
    }
}
