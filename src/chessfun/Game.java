/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import figures.Shape;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game {
    
    private ChessBoard chessBoard;
    private ImageIcon icons[];
    
    
    public Game() // Конструктор
    {
        this.chessBoard = new ChessBoard();
        this.icons = new ImageIcon[13];
        LoadTextures(2);
        
    }
    
    
    public void Start()/* throws IOException */// Можно описывать ходы
    {
        try
        {                           
            JFrame view = new JFrame(); // Путь джедая!
            view.setTitle("Chess Fun");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Корректное завершение работы, при закрытии окна
            view.setSize(1024, 768); // Размеры окна
            
            JPanelWithBackground board = new JPanelWithBackground("src/textures/chessboard_640.jpg"); // JPanel с перегруженным методом paintComponent()
            board.setLayout(null);
            board.setSize(640, 640);// Размеры шахматной доски
            
                 
            /*Отладочный вывод png - фигур*/
            
            // TODO:
            // Привязать все jLabels к соответствующим полям шахматной доски, возможно хранить в другой структуре данных (пример - JLabel[][])
          
            
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    ChessBoard.board[i][j].label.setIcon(icons[(i+j)%13]);
                    board.add(ChessBoard.board[i][j].label);
                }

            view.getContentPane().add(board); // добавление JPanel к JFrame
            
            view.setVisible(true); // Делаем видимым наш фрейм
            
            
        } catch (IOException ex) {
            System.err.print("Game.Start() It is trap");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private ImageIcon GetIcon(Shape sh)
    {
        
        switch (sh.GetName())
        {
            case "Pawn WHITE": return icons[0];
            case "Knight WHITE": return icons[1];
            case "Bishop WHITE": return icons[2];
            case "Rock WHITE": return icons[3];
            case "Queen WHITE": return icons[4];
            case "King WHITE": return icons[5];
            case "Pawn BLACK": return icons[6];
            case "Knight BLACK": return icons[7];
            case "Bishop BLACK": return icons[8];
            case "Rock BLACK": return icons[9];
            case "Queen BLACK": return icons[10];
            case "King BLACK": return icons[11];
            default: return icons[12];
        }
    }
    
    private void LoadTextures(int choose)
    {
        String folder = "";
        switch(choose)
        {
            case 1: folder = "alpha"; break;
            case 2: folder = "chess24"; break;
            default: folder = "merida"; break;
        }
        
        icons[0] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wP.png"));
        icons[1] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wN.png"));
        icons[2] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wB.png"));
        icons[3] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wR.png"));
        icons[4] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wQ.png"));
        icons[5] = new ImageIcon(getClass().getResource("../textures/" + folder + "/wK.png"));
        icons[6] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bP.png"));
        icons[7] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bN.png"));
        icons[8] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bB.png"));
        icons[9] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bR.png"));
        icons[10] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bQ.png"));
        icons[11] = new ImageIcon(getClass().getResource("../textures/" + folder + "/bK.png"));
        icons[12] = new ImageIcon(getClass().getResource("../textures/" + folder + "/empty.png"));
    }
    
    
    
}
