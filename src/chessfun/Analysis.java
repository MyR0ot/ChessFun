/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.MyPanels.JPanelWithBackground;
import chessfun.MyPanels.ExitPanel;
import chessfun.MyPanels.DirectionPanel;
import chessfun.Enums.ColorFigure;
import chessfun.Enums.DirectionStep;
import chessfun.Enums.ModeChess;
import chessfun.Enums.ModeShape;
import chessfun.Enums.NameFigure;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author My
 */
public class Analysis {
    
    // <editor-fold defaultstate="collapsed" desc="Поля класса">
    public History history;                // история
    private Cell[][] board;                 // шахматная доска
    private ImageIcon icons[];              // иконки для отображения фигур
    private JFrame view;                    // Основной JFrame для отображения
    private JFrame settings;                // настройки
    private ModeChess modeGame;             // тип игры
    private ModeShape modeShape;            // стиль фигур
    private JPanelWithBackground chessBoard;// логическая сущность доски
    
    private DirectionPanel backPanel;
    private DirectionPanel forwardPanel;
    private ExitPanel exitPanel;
    
    
    
    // </editor-fold>
    
    public Analysis(History history, ModeChess modeChoice, ModeShape modeShape, ImageIcon[] icons) // Конструктор
    {
        Globals.game.view.setVisible(false);
        Globals.isAnalysis = true;
        this.icons = icons;
        this.board = new Cell[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
            }
        }

        this.modeGame = modeChoice;
        this.modeShape = modeShape;
        this.history = history;
        startPosition();
        loadView();
    }
    
    
    private void loadView()
    {
        view = new JFrame();
        view.setTitle("Chess Fun - Analysys");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Корректное завершение работы, при закрытии окна
        
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(); // Полный экран
        view.setSize(sSize);
        view.setExtendedState(MAXIMIZED_BOTH);

        try {
            chessBoard = new JPanelWithBackground("src/textures/chessboard_border_640.jpg"); // JPanel с перегруженным методом paintComponent()            
            chessBoard.setLayout(null);
            chessBoard.setSize(644 + Globals.delta_x, 644 + Globals.delta_y);// Размеры шахматной доски
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chessBoard.add(board[i][j].getLabel());
                    chessBoard.add(board[i][j].getLabelSelect());
                }
            }
            startPosition();
            backPanel = new DirectionPanel(Globals.delta_x + 500, Globals.delta_y,DirectionStep.BACK, icons[16], this);
            forwardPanel = new DirectionPanel(Globals.delta_x + 600, Globals.delta_y,DirectionStep.FORWARD, icons[17], this);
            exitPanel = new ExitPanel(Globals.delta_x + 740, Globals.delta_y, icons[18], view);
            
            view.add(backPanel);
            view.add(forwardPanel);
            view.add(exitPanel);
            
            view.getContentPane().add(chessBoard); // добавление JPanel к JFrame
            view.setVisible(true); // Делаем видимым фрейм  
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    private void reDraw()
    {
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j].setIcon(getImageByField(board[i][j]));
    }
    
    private ImageIcon getImageByField(Cell cell)
    {
        switch(cell.getNameFigure())
        {
            case ("PAWN WHITE"): return icons[0];
            case ("KNIGHT WHITE"): return icons[1];
            case ("BISHOP WHITE"): return icons[2];
            case ("ROCK WHITE"): return icons[3];
            case ("QUEEN WHITE"): return icons[4];
            case ("KING WHITE"): return icons[5];
            case ("PAWN BLACK"): return icons[6];
            case ("KNIGHT BLACK"): return icons[7];
            case ("BISHOP BLACK"): return icons[8];
            case ("ROCK BLACK"): return icons[9];
            case ("QUEEN BLACK"): return icons[10];
            case ("KING BLACK"): return icons[11];
            default: return icons[12]; // пустая клетка   
        }
    }
    
    private void startPosition() // начальная расстановка фигур
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                this.board[i][j].setFigure(NameFigure.EMPTY, ColorFigure.NONE); // пустые клетки в стартовой позиции
            }
        }

        for (int i = 0; i < 8; i++) {
            this.board[i][1].setFigure(NameFigure.PAWN, ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][6].setFigure(NameFigure.PAWN, ColorFigure.WHITE); // Выставляем черные пешки
        }
        /*Классическая расстановка 8-ого ряда черных фигур*/

        this.board[0][0].setFigure(NameFigure.ROCK, ColorFigure.BLACK);
        this.board[1][0].setFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[2][0].setFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[3][0].setFigure(NameFigure.QUEEN, ColorFigure.BLACK);
        this.board[4][0].setFigure(NameFigure.KING, ColorFigure.BLACK);
        this.board[5][0].setFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[6][0].setFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[7][0].setFigure(NameFigure.ROCK, ColorFigure.BLACK);

        /*Классическая расстановка 1-ого ряда белых фигур*/
        this.board[0][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        this.board[1][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[2][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[3][7].setFigure(NameFigure.QUEEN, ColorFigure.WHITE);
        this.board[4][7].setFigure(NameFigure.KING, ColorFigure.WHITE);
        this.board[5][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[6][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[7][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        
        reDraw();
    }
    
    
    public void move(String from, String to)// Перемещение фигуры из from в to, согласно нотации
    {
        int x_from = 7 + from.charAt(0) - 'h';
        int y_from = '8' - from.charAt(1);
        int x_to = 7 + to.charAt(0) - 'h';
        int y_to = '8' - to.charAt(1);
        move(x_from, y_from, x_to, y_to);
    }
    
    private void move(int x_from, int y_from, int x_to, int y_to)
    {
        board[x_to][y_to].setIcon(board[x_from][y_from].getIcon());
        board[x_from][y_from].setIcon(icons[12]);
    }
}
