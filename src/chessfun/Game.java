/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

public class Game {
    
    private ChessBoard chessBoard;
    
    
    public Game() // Конструктор
    {
        this.chessBoard = new ChessBoard();
    }
    
    
    public void Start() // Можно описывать ходы
    {
        this.chessBoard.GetBoardScrean(); // Вывод состояния доски в консоль
        this.chessBoard.Move(ChessBoard.board[1][0], ChessBoard.board[2][2]);
        this.chessBoard.GetBoardScrean();
        this.chessBoard.Move(ChessBoard.board[2][2], ChessBoard.board[4][1]);
        this.chessBoard.GetBoardScrean();
        this.chessBoard.Move(ChessBoard.board[7][7], ChessBoard.board[4][1]);
        this.chessBoard.GetBoardScrean();
        return;
    }
}
