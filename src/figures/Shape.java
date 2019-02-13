/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

import chessfun.Cell;


public abstract class Shape {
    public abstract ColorFigure GetColorShape();
    public abstract boolean CheckMove(Cell toCell); // Можно ли ходить фигурой в клетку toCell?
    public abstract boolean CheckBeat(Cell toCell);
    public abstract boolean CheckTransform(Cell toCell);
    public abstract String GetNameShape();
}
