/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class Game {
    
    private ChessBoard chessBoard;
    private ImageIcon icons[];
    
    
    public Game() // Конструктор
    {
        this.chessBoard = new ChessBoard();
        this.icons = new ImageIcon[12];
        icons[0] = new ImageIcon(getClass().getResource("../textures/alpha/wP.png"));
        icons[1] = new ImageIcon(getClass().getResource("../textures/alpha/wN.png"));
        icons[2] = new ImageIcon(getClass().getResource("../textures/alpha/wB.png"));
        icons[3] = new ImageIcon(getClass().getResource("../textures/alpha/wR.png"));
        icons[4] = new ImageIcon(getClass().getResource("../textures/alpha/wQ.png"));
        icons[5] = new ImageIcon(getClass().getResource("../textures/alpha/wK.png"));
        icons[6] = new ImageIcon(getClass().getResource("../textures/alpha/bP.png"));
        icons[7] = new ImageIcon(getClass().getResource("../textures/alpha/bN.png"));
        icons[8] = new ImageIcon(getClass().getResource("../textures/alpha/bB.png"));
        icons[9] = new ImageIcon(getClass().getResource("../textures/alpha/bR.png"));
        icons[10] = new ImageIcon(getClass().getResource("../textures/alpha/bQ.png"));
        icons[11] = new ImageIcon(getClass().getResource("../textures/alpha/bK.png"));
    }
    
    
    public void Start()/* throws IOException */// Можно описывать ходы
    {
        try /* throws IOException */ // Можно описывать ходы
        {
            this.chessBoard.GetBoardScrean(); // Вывод состояния доски в консоль
            this.chessBoard.Move(ChessBoard.board[1][0], ChessBoard.board[2][2]);
            this.chessBoard.GetBoardScrean();
            this.chessBoard.Move(ChessBoard.board[2][2], ChessBoard.board[4][1]);
            this.chessBoard.GetBoardScrean();
            this.chessBoard.Move(ChessBoard.board[7][7], ChessBoard.board[4][1]);
            this.chessBoard.GetBoardScrean();
            
            
            // View view = new View(); // Путь слабака...
            // view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // view.setVisible(true);
            
            JFrame view = new JFrame(); // Путь джедая!
            view.setTitle("Chess Fun");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            view.setSize(1024, 768);
            
            JPanelWithBackground board = new JPanelWithBackground("src/textures/chessboard_640.jpg");
            board.setSize(640, 640);
            
            board.setLayout(new FlowLayout()); //  Разобраться с необходимым лейаутом
                        
            
            JLabel a1 = new JLabel();
            JLabel a2 = new JLabel();
            JLabel a3 = new JLabel();
            
            
            a1.setIcon(icons[3]);
            a2.setIcon(icons[1]);
            a3.setIcon(icons[2]);
            
            
            board.add(a1);
            board.add(a2);
            board.add(a3);

            view.getContentPane().add(board);
            
            view.setVisible(true);
            
            
        } catch (IOException ex) {
            System.out.print("try-catch");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
