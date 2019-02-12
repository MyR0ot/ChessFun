/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

/**
 *
 * @author My
 */
public class Field {
    public String name;
    public short horizontal;        // 1, 2, 3, 4, 5, 6, 7, 8
    public char vertical;           // a, b, c, d, e, f, g, h
    public boolean isEmpty;         // Свободное поле?
    public boolean isEngagedWhite;  // Занято белой фигурой
    public boolean isEngagedBlack;  // Занято черное фигурой
    
    public Field(char vertical, short horizontal)
    {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.isEmpty = true;
        this.isEngagedWhite = false;
        this.isEngagedBlack = false;
        this.name = this.vertical + Short.toString(horizontal);
    }
    }
