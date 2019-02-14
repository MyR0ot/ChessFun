/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import figures.*;

enum ModeChess {CLASSIC, FISHER, KING_HILL}

public class ChessBoard {
    public static Cell[][] board = new Cell[8][8];
    
    
    public ChessBoard()
    {
        this.board = new Cell[8][8];
        StartClassic(); // Стандартная расстоновка
                
    }
    
    private void StartClassic() // Классическая начальная расстоновка фигур
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i=0; i<8; i++)
            for(int j=2; j<6; j++)
                this.board[i][j].shape = new EmptyFigure(); // пустые клетки в стартовой позиции
        
        for(int i=0;i<8;i++)
        {
            this.board[i][1].shape = new Pawn(ColorFigure.WHITE); // Выставляем белые пешки
            this.board[i][6].shape = new Pawn(ColorFigure.BLACK); // Выставляем черные пешки
        }
        
            /*Классическая расстановка белых фигур*/
        this.board[0][0].shape = new Rock(ColorFigure.WHITE);
        this.board[1][0].shape = new Knight(ColorFigure.WHITE);
        this.board[2][0].shape = new Bishop(ColorFigure.WHITE);
        this.board[3][0].shape = new Queen(ColorFigure.WHITE);
        this.board[4][0].shape = new King(ColorFigure.WHITE);
        this.board[5][0].shape = new Bishop(ColorFigure.WHITE);
        this.board[6][0].shape = new Knight(ColorFigure.WHITE);
        this.board[7][0].shape = new Rock(ColorFigure.WHITE);
        
            /*Классическая расстановка черных фигур*/
        this.board[0][7].shape = new Rock(ColorFigure.BLACK);
        this.board[1][7].shape = new Knight(ColorFigure.BLACK);
        this.board[2][7].shape = new Bishop(ColorFigure.BLACK);
        this.board[3][7].shape = new Queen(ColorFigure.BLACK);
        this.board[4][7].shape = new King(ColorFigure.BLACK);
        this.board[5][7].shape = new Bishop(ColorFigure.BLACK);
        this.board[6][7].shape = new Knight(ColorFigure.BLACK);
        this.board[7][7].shape = new Rock(ColorFigure.BLACK);
    }
    
    
    public void GetBoardScrean()// Вывод состояния шахматной доски в консоль
    {
        System.out.println("-----------------------");
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                System.out.println(this.board[j][i].GetName() + ": " + ((Shape)(this.board[j][i].shape)).GetName());
        System.out.println("-----------------------");
    }
    
    
    
    public void Move(Cell from, Cell to)// Перемещение фигуры из from в to
    {
        to.shape = from.shape;
        if(from.GetRow() == 7 && to.shape instanceof Pawn) // превращение в ферзя пещки
            to.shape = new Queen(to.shape.GetColorShape());
        from.shape = new EmptyFigure();
    }
}
