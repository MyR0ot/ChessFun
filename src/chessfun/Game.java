/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.rules.ClassicRules;
import chessfun.Enums.*;
import chessfun.events.TryMoveEvent;
import chessfun.interfaces.IRules;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import chessfun.interfaces.ITryMoveListener;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game implements ITryMoveListener {
    
    private Cell[][] board;                 // шахматная доска
    private ImageIcon icons[];              // иконки для отображения фигур
    private JFrame view;                    // Основной JFrame для отображения
    private IRules rules;                   // Модуль правил
    private final History history;          // История игры
    
    
    
    
    
    public Game(ModeChess modeChoice, ModeShape modeShape) // Конструктор
    {
        this.board = new Cell[8][8];
        this.icons = new ImageIcon[14];
        
        loadTextures(modeShape);
        setGlobals();
        
        switch (modeChoice)
        {
            case CLASSIC:   startClassic(); this.rules = new ClassicRules(); break;
            case FISHER:    startFisher();  this.rules = new ClassicRules(); break;
            case KING_HILL: startClassic(); this.rules = new ClassicRules(); break;
        }
        this.history = new History(board);
        
        loadView();
    }
    
    private void setGlobals()
    {
        Globals.iconEmpty = icons[12];
        Globals.iconSelected = icons[13];
        Globals.isSelectedFigure = false;
        Globals.stepQueue = ColorFigure.WHITE;
        Globals.allowCastleBlack = true;
        Globals.allowCastleBlack = true;
        
        Globals.bigStepPawn = new boolean[2][8];
        Globals.clearBigStepPawnArray();
        Globals.allowCastleWhite = true;
        Globals.allowCastleBlack = true;
    }
    
    
    private void loadView()
    {
        view = new JFrame(); // Путь джедая!
        view.setTitle("Chess Fun");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Корректное завершение работы, при закрытии окна
        view.setSize(1300, 768); // Размеры окна
        try
        {                           
            JPanelWithBackground chessBoard = new JPanelWithBackground("src/textures/chessboard_640.jpg"); // JPanel с перегруженным методом paintComponent()
            
            final JLabel timerWhite = new JLabel("03:00");
            final JLabel timerBlack = new JLabel("03:00");
            timerWhite.setOpaque(true); // Делаем прозрачным
            timerBlack.setOpaque(true);
            timerWhite.setBounds(Globals.delta_x + 660, Globals.delta_y, 150, 80); // Выразить через Globals.params полностью
            timerBlack.setBounds(Globals.delta_x + 660, Globals.delta_y + 560, 150, 80);
            //timerWhite.setBackground(new Color(0, 0, 0));
            //timerBlack.setBackground(new Color(255, 255, 255));
            
            // Написать события переключения хода
            final javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
            int time = 180;
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                timerWhite.setText(Globals.format(time / 60) + ":" + Globals.format(time % 60));
                if (time == 0) {
                    final javax.swing.Timer timer = (javax.swing.Timer) e.getSource();
                    //timer.stop();
                }
            }
        });
            
            chessBoard.setLayout(null);
            
            chessBoard.setSize(640 + Globals.delta_x, 640 + Globals.delta_y);// Размеры шахматной доски
            
            
                      
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    chessBoard.add(board[i][j].getLabel());
                    chessBoard.add(board[i][j].getLabelSelect());    
                }
            
            chessBoard.add(timerWhite);
            chessBoard.add(timerBlack);
 
            view.getContentPane().add(chessBoard); // добавление JPanel к JFrame
            view.setVisible(true); // Делаем видимым наш фрейм  
        }
        catch (IOException ex)
        {
            System.err.print("Game.Start() It is trap");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Deprecated
    private static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    
    public void Start()
    {
        rules.showBeatFieldsByThisShape(board, 1, 1);
    }
    
    
    private ImageIcon GetIcon(NameFigure figure, ColorFigure color)
    {
        
        switch (figure.toString()+" " + color.toString())
        {
            case "PAWN WHITE": return icons[0];
            case "KNIGHT WHITE": return icons[1];
            case "BISHOP WHITE": return icons[2];
            case "ROCK WHITE": return icons[3];
            case "QUEEN WHITE": return icons[4];
            case "KING WHITE": return icons[5];
            case "PAWN BLACK": return icons[6];
            case "KNIGHT BLACK": return icons[7];
            case "BISHOP BLACK": return icons[8];
            case "ROCK BLACK": return icons[9];
            case "QUEEN BLACK": return icons[10];
            case "KING BLACK": return icons[11];
            default: return icons[12];
        }
    }
    
    
    private void loadTextures(ModeShape modeShape)
    {
        String folder = "../textures/";
        switch(modeShape)
        {
            case ALPHA: folder += "alpha/"; break;
            case CHESS24: folder += "chess24/"; break;
            case MERIDA: folder += "merida/"; break;
            case SYMBOL: folder += "symbol/"; break;
            case WIKIPEDIA: folder += "wikipedia/"; break;
            case CHEQ: folder += "cheq/"; break;
            case METRO: folder += "metro/"; break;
        }
        icons[0] = new ImageIcon(getClass().getResource(folder + "wP.png"));
        icons[1] = new ImageIcon(getClass().getResource(folder + "wN.png"));
        icons[2] = new ImageIcon(getClass().getResource(folder + "wB.png"));
        icons[3] = new ImageIcon(getClass().getResource(folder + "wR.png"));
        icons[4] = new ImageIcon(getClass().getResource(folder + "wQ.png"));
        icons[5] = new ImageIcon(getClass().getResource(folder + "wK.png"));
        icons[6] = new ImageIcon(getClass().getResource(folder + "bP.png"));
        icons[7] = new ImageIcon(getClass().getResource(folder + "bN.png"));
        icons[8] = new ImageIcon(getClass().getResource(folder + "bB.png"));
        icons[9] = new ImageIcon(getClass().getResource(folder + "bR.png"));
        icons[10] = new ImageIcon(getClass().getResource(folder + "bQ.png"));
        icons[11] = new ImageIcon(getClass().getResource(folder + "bK.png"));
        icons[12] = new ImageIcon(getClass().getResource("../textures/empty.png"));
        icons[13] = new ImageIcon(getClass().getResource("../textures/is.png"));
    }
    
    
    private void startClassic() // Классическая начальная расстановка фигур
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i = 0; i < 8; i++)
            for(int j = 2; j < 6; j++)
            {
                this.board[i][j].setFigure(NameFigure.EMPTY, ColorFigure.NONE); // пустые клетки в стартовой позиции
                this.board[i][j].setIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        { 
            this.board[i][1].setFigure(NameFigure.PAWN, ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].setIcon(icons[6]);
            this.board[i][6].setFigure(NameFigure.PAWN, ColorFigure.WHITE); // Выставляем черные пешки
            this.board[i][6].setIcon(icons[0]);
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
            
        this.board[0][0].setIcon(icons[9]);      
        this.board[1][0].setIcon(icons[7]);     
        this.board[2][0].setIcon(icons[8]); 
        this.board[3][0].setIcon(icons[10]);      
        this.board[4][0].setIcon(icons[11]);     
        this.board[5][0].setIcon(icons[8]);
        this.board[6][0].setIcon(icons[7]);       
        this.board[7][0].setIcon(icons[9]);
        
            /*Классическая расстановка 1-ого ряда белых фигур*/
        this.board[0][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        this.board[1][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[2][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[3][7].setFigure(NameFigure.QUEEN, ColorFigure.WHITE);
        this.board[4][7].setFigure(NameFigure.KING, ColorFigure.WHITE);
        this.board[5][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[6][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[7][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        
        this.board[0][7].setIcon(icons[3]);
        this.board[1][7].setIcon(icons[1]);
        this.board[2][7].setIcon(icons[2]);
        this.board[3][7].setIcon(icons[4]);
        this.board[4][7].setIcon(icons[5]);
        this.board[5][7].setIcon(icons[2]);
        this.board[6][7].setIcon(icons[1]);
        this.board[7][7].setIcon(icons[3]);
    }
    
    
    private void startFisher()  // Расстановка 1-ого и 8-ого ряда случайным образом
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i = 0; i < 8; i++)
            for(int j = 2; j < 6; j++)
            {
                this.board[i][j].setFigure(NameFigure.EMPTY, ColorFigure.NONE); // пустые клетки в стартовой позиции
                this.board[i][j].setIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        {
            this.board[i][1].setFigure(NameFigure.PAWN, ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].setIcon(icons[6]);
            this.board[i][6].setFigure(NameFigure.PAWN, ColorFigure.WHITE); // Выставляем черные пешки
            this.board[i][6].setIcon(icons[0]);
        }
        
        int[] arr = {4,3,1,6,7,5,2,0};
        int index1;
        int index2;
        int tmp;
        Random r = new Random();
        for(int i = 0; i < 50; i++)
        {
            index1 = Math.abs(r.nextInt()%8);
            index2 = Math.abs(r.nextInt()%8);
            
            tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;
        }
        
            /*Случайная расстановка 8-ого ряда черных фигур*/
        this.board[arr[0]][0].setFigure(NameFigure.ROCK, ColorFigure.BLACK);
        this.board[arr[1]][0].setFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[arr[2]][0].setFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[arr[3]][0].setFigure(NameFigure.QUEEN, ColorFigure.BLACK);
        this.board[arr[4]][0].setFigure(NameFigure.KING, ColorFigure.BLACK);
        this.board[arr[5]][0].setFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[arr[6]][0].setFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[arr[7]][0].setFigure(NameFigure.ROCK, ColorFigure.BLACK);
            
        this.board[arr[0]][0].setIcon(icons[9]);      
        this.board[arr[1]][0].setIcon(icons[7]);     
        this.board[arr[2]][0].setIcon(icons[8]); 
        this.board[arr[3]][0].setIcon(icons[10]);      
        this.board[arr[4]][0].setIcon(icons[11]);     
        this.board[arr[5]][0].setIcon(icons[8]);
        this.board[arr[6]][0].setIcon(icons[7]);       
        this.board[arr[7]][0].setIcon(icons[9]);
        
            /*Случайная расстановка 1-ого ряда белых фигур*/
        this.board[arr[0]][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        this.board[arr[1]][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[arr[2]][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[arr[3]][7].setFigure(NameFigure.QUEEN, ColorFigure.WHITE);
        this.board[arr[4]][7].setFigure(NameFigure.KING, ColorFigure.WHITE);
        this.board[arr[5]][7].setFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[arr[6]][7].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[arr[7]][7].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
        
        this.board[arr[0]][7].setIcon(icons[3]);
        this.board[arr[1]][7].setIcon(icons[1]);
        this.board[arr[2]][7].setIcon(icons[2]);
        this.board[arr[3]][7].setIcon(icons[4]);
        this.board[arr[4]][7].setIcon(icons[5]);
        this.board[arr[5]][7].setIcon(icons[2]);
        this.board[arr[6]][7].setIcon(icons[1]);
        this.board[arr[7]][7].setIcon(icons[3]);
        
    }
    
    
    private void move(int x_from, int y_from, int x_to, int y_to)// Перемещение фигуры из from в to
    {

        board[Globals.columnSelected][Globals.rowSelected].setLabelSelect(Globals.iconEmpty); // Снятие выделения в любом случае
        if(!rules.checkMove(this.board, x_from, y_from, x_to, y_to)) // Если не прошли проверку на валидность хода
           return;
        
        TypeMove typeMove = swapFigure(x_from, y_from, x_to, y_to); // Получение типа хода + действия с логической моделью и view
        
        Globals.clearBigStepPawnArray();
        
        switch(typeMove)
        {
            case OO:
                history.addNote("0-0");
                if(Globals.stepQueue == ColorFigure.WHITE)
                    Globals.allowCastleWhite = false;
                else
                    Globals.allowCastleBlack = false;
                break;
            case OOO:
                history.addNote("0-0-0");
                if(Globals.stepQueue == ColorFigure.WHITE)
                    Globals.allowCastleWhite = false;
                else
                    Globals.allowCastleBlack = false;
                break;
            case BIG_STEP_PAWN:
                if(Globals.stepQueue == ColorFigure.WHITE)
                    Globals.bigStepPawn[0][x_to] = true;
                else
                    Globals.bigStepPawn[1][x_to] = true;
   
            default: history.addNote(board[x_from][y_from].getName(), board[x_to][y_to].getName()); break;
        }        
        Globals.stepQueue = Globals.stepQueue == ColorFigure.WHITE ? ColorFigure.BLACK : ColorFigure.WHITE; // Передача хода   
        
        /*if(history.getCountNotes()> 12)
            history.showHistory();*/
        rules.getBeatFiels(board, x_to, y_to);
    }
    
    private void move(String from, String to)// Перемещение фигуры из from в to, согласно нотации
    {
        int x_from = 7  + from.charAt(0) - 'h';
        int y_from = '8' - from.charAt(1);
        int x_to = 7  + to.charAt(0) - 'h';
        int y_to = '8' - to.charAt(1);
        move(x_from, y_from, x_to, y_to);
    }
    
    
    private TypeMove swapFigure(int x_from, int y_from, int x_to, int y_to) // Упростить!
    {
        TypeMove res = TypeMove.DEFAULT;
        switch(board[x_from][y_from].getNameFigure()) // передвижение фигуры
        {
            case "PAWN WHITE":
                if(rules.isTakeOnPass(x_from, y_from, x_to, y_to))
                {
                    board[x_to][y_to + 1].setIcon(Globals.iconEmpty);
                    board[x_to][y_to + 1].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                    res = TypeMove.TAKE_ON_PASS;
                }
                board[x_to][y_to].setIcon(icons[0]);
                board[x_to][y_to].setFigure(NameFigure.PAWN, ColorFigure.WHITE);
            if(y_to == y_from - 2)
            {
                Globals.bigStepPawn[0][x_to] = true;
                 res = TypeMove.BIG_STEP_PAWN;
            }
            break;
            case "KNIGHT WHITE": board[x_to][y_to].setIcon(icons[1]);    board[x_to][y_to].setFigure(NameFigure.KNIGHT, ColorFigure.WHITE); break;
            case "BISHOP WHITE": board[x_to][y_to].setIcon(icons[2]);    board[x_to][y_to].setFigure(NameFigure.BISHOP, ColorFigure.WHITE); break;
            case "ROCK WHITE":   board[x_to][y_to].setIcon(icons[3]);    board[x_to][y_to].setFigure(NameFigure.ROCK, ColorFigure.WHITE); break;
            case "QUEEN WHITE":  board[x_to][y_to].setIcon(icons[4]);    board[x_to][y_to].setFigure(NameFigure.QUEEN, ColorFigure.WHITE); break;
            case "KING WHITE":
                if(rules.isCastle(x_from, y_from, x_to, y_to))
                {
                    if(x_to == 6)
                    {
                        board[x_to-1][y_to].setIcon(icons[3]);
                        board[x_to-1][y_to].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
                        board[7][y_to].setIcon(Globals.iconEmpty);
                        board[7][y_to].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                        res = TypeMove.OO;
                    }
                    else // x_to == 2
                    {
                        board[x_to+1][y_to].setIcon(icons[3]);
                        board[x_to+1][y_to].setFigure(NameFigure.ROCK, ColorFigure.WHITE);
                        board[0][y_to].setIcon(Globals.iconEmpty);
                        board[0][y_to].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                        res = TypeMove.OOO;
                    }
                }
                board[x_to][y_to].setIcon(icons[5]);
                board[x_to][y_to].setFigure(NameFigure.KING, ColorFigure.WHITE);
                break;
            case "PAWN BLACK":
                if(rules.isTakeOnPass(x_from, y_from, x_to, y_to))
                {
                    board[x_to][y_to - 1].setIcon(Globals.iconEmpty);
                    board[x_to][y_to - 1].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                    res = TypeMove.TAKE_ON_PASS;
                }
                board[x_to][y_to].setIcon(icons[6]);
                board[x_to][y_to].setFigure(NameFigure.PAWN, ColorFigure.BLACK);
            if(y_to == y_from + 2){
                Globals.bigStepPawn[1][x_to] = true;
                res = TypeMove.BIG_STEP_PAWN;
            } break;
            case "KNIGHT BLACK": board[x_to][y_to].setIcon(icons[7]);    board[x_to][y_to].setFigure(NameFigure.KNIGHT, ColorFigure.BLACK); break;
            case "BISHOP BLACK": board[x_to][y_to].setIcon(icons[8]);    board[x_to][y_to].setFigure(NameFigure.BISHOP, ColorFigure.BLACK); break;
            case "ROCK BLACK":   board[x_to][y_to].setIcon(icons[9]);    board[x_to][y_to].setFigure(NameFigure.ROCK, ColorFigure.BLACK); break;
            case "QUEEN BLACK":  board[x_to][y_to].setIcon(icons[10]);   board[x_to][y_to].setFigure(NameFigure.QUEEN, ColorFigure.BLACK); break;
            case "KING BLACK":
                if(rules.isCastle(x_from, y_from, x_to, y_to))
                {
                    if(x_to == 6)
                    {
                        board[x_to-1][y_to].setIcon(icons[9]);
                        board[x_to-1][y_to].setFigure(NameFigure.ROCK, ColorFigure.BLACK);
                        board[7][y_to].setIcon(Globals.iconEmpty);
                        board[7][y_to].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                        res = TypeMove.OO;
                    }
                    else // x_to == 2
                    {
                        board[x_to+1][y_to].setIcon(icons[9]);
                        board[x_to+1][y_to].setFigure(NameFigure.ROCK, ColorFigure.BLACK);
                        board[0][y_to].setIcon(Globals.iconEmpty);
                        board[0][y_to].setFigure(NameFigure.EMPTY, ColorFigure.NONE);
                        res = TypeMove.OOO;
                    }
                }
                
                board[x_to][y_to].setIcon(icons[11]);
                board[x_to][y_to].setFigure(NameFigure.KING, ColorFigure.BLACK);
                break;
        }
        board[x_from][y_from].setIcon(Globals.iconEmpty); // задаем пустой отображение (откуда был сделан ход)
        board[x_from][y_from].setFigure(NameFigure.EMPTY, ColorFigure.NONE); // установка "пустой фигуры"
        return res;
    }
    
    
    @Override
    public void tryMove(TryMoveEvent e) {
        move(e.getFrom(), e.getTo());
    }
    
    
    @Deprecated
    public void printCurrentInfoBoard()// Вывод состояния шахматной доски в консоль (пока не удалять!)
    {
        System.out.println("-----------------------");
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                System.out.println(this.board[j][i].getName() + ": " + this.board[j][i].getNameFigure());
        System.out.println("-----------------------");
    }
    

}
