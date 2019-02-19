/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        try
        {                           
            JFrame view = new JFrame(); // Путь джедая!
            view.setTitle("Chess Fun");
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Корректное завершение работы, при закрытии окна
            view.setSize(1024, 768); // Размеры окна
            
            JPanelWithBackground board = new JPanelWithBackground("src/textures/chessboard_640.jpg"); // JPanel с перегруженным методом paintComponent()
            board.setSize(640, 640);// Размеры шахматной доски
                 
            /*Отладочный вывод png - фигур*/
            
            // TODO:
            // Привязать все jLabels к соответствующим полям шахматной доски, возможно хранить в другой структуре данных (пример - JLabel[][])
            JLabel a1 = new JLabel();
            JLabel a2 = new JLabel();
            JLabel a3 = new JLabel();
            JLabel a4 = new JLabel();
            JLabel a5 = new JLabel();
            JLabel a6 = new JLabel();
            JLabel a7 = new JLabel();
            JLabel a8 = new JLabel();
            
          
            a1.setIcon(icons[0]);// Привязка изображения к определенному jLabel 
            a2.setIcon(icons[1]);
            a3.setIcon(icons[2]);
            a4.setIcon(icons[3]);
            a5.setIcon(icons[4]);
            a6.setIcon(icons[5]);
            a7.setIcon(icons[6]);
            a8.setIcon(icons[7]);            
            
            board.add(a1); // Привязка всех 64 jLabels к jPanels(шахматная доска)
            board.add(a2);
            board.add(a3);
            board.add(a4);
            board.add(a5);
            board.add(a6);
            board.add(a7);
            board.add(a8);

            view.getContentPane().add(board); // добавление JPanel к JFrame
            
            view.setVisible(true); // Делаем видимым наш фрейм
            
            
        } catch (IOException ex) {
            System.err.print("Game.Start() It is trap");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
