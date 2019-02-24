/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.Random;


public class Game {
    
    private Cell[][] board;          // шахматная доска
    private ImageIcon icons[];       // иконки для отображения фигур
    private JFrame view;             // Основной JFrame для отображения 
    
    
    public Game(ModeChess modeChoice, ModeShape modeShape) // Конструктор
    {
        this.board = new Cell[8][8];
        this.icons = new ImageIcon[14];
        LoadTextures(modeShape);
        Globals.iconEmpty = icons[12];
        Globals.iconSelected = icons[13];
        Globals.isSelectedFigure = false;
        
        switch (modeChoice)
        {
            case CLASSIC: StartClassic(); break;
            case FISHER: StartFisher(); break;     // не реализовано
            case KING_HILL: StartClassic(); break;
        }
        view = new JFrame(); // Путь джедая!
        view.setTitle("Chess Fun");
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Корректное завершение работы, при закрытии окна
        view.setSize(1024, 768); // Размеры окна
        try
        {                           
            JPanelWithBackground chessBoard = new JPanelWithBackground("src/textures/chessboard_640.jpg"); // JPanel с перегруженным методом paintComponent()
            chessBoard.setLayout(null);
            chessBoard.setSize(640, 640);// Размеры шахматной доски
                      
            
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    chessBoard.add(board[i][j].GetLabel());
                    chessBoard.add(board[i][j].GetLabelSelect());    
                }
                    
            view.getContentPane().add(chessBoard); // добавление JPanel к JFrame
            view.setVisible(true); // Делаем видимым наш фрейм
            
        }
        catch (IOException ex)
        {
            System.err.print("Game.Start() It is trap");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Start()/* throws IOException */// Можно описывать ходы
    {
        Random r = new Random();
        // TODO logic (Move(...); ...)
        
        Move("e2", "e4");
        Move("e7", "e5");
        Move("g1", "f3");
        Move("b8", "c6");
        Move("f1", "c4");
        Move("f8", "c5");

        //Move(0, 0, 7, 5);
        PrintCurrentInfoBoard();


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
    
    
    private void LoadTextures(ModeShape modeShape)
    {
        String folder = "../textures/";
        switch(modeShape)
        {
            case ALPHA: folder += "alpha/"; break;
            case CHESS24: folder += "chess24/"; break;
            case MERIDA: folder += "merida/"; break;
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
    
    
    private void StartClassic() // Классическая начальная расстановка фигур
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i = 0; i < 8; i++)
            for(int j = 2; j < 6; j++)
            {
                this.board[i][j].SetFigure(NameFigure.EMPTY, ColorFigure.NONE); // пустые клетки в стартовой позиции
                this.board[i][j].SetIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        {
            
            this.board[i][1].SetFigure(NameFigure.PAWN, ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].SetIcon(icons[6]);
            this.board[i][6].SetFigure(NameFigure.PAWN, ColorFigure.WHITE); // Выставляем черные пешки
            this.board[i][6].SetIcon(icons[0]);
        }
                /*Классическая расстановка 8-ого ряда черных фигур*/
                
        this.board[0][0].SetFigure(NameFigure.ROCK, ColorFigure.BLACK);
        this.board[1][0].SetFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[2][0].SetFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[3][0].SetFigure(NameFigure.QUEEN, ColorFigure.BLACK);
        this.board[4][0].SetFigure(NameFigure.KING, ColorFigure.BLACK);
        this.board[5][0].SetFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[6][0].SetFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[7][0].SetFigure(NameFigure.ROCK, ColorFigure.BLACK);
            
        this.board[0][0].SetIcon(icons[9]);      
        this.board[1][0].SetIcon(icons[7]);     
        this.board[2][0].SetIcon(icons[8]); 
        this.board[3][0].SetIcon(icons[10]);      
        this.board[4][0].SetIcon(icons[11]);     
        this.board[5][0].SetIcon(icons[8]);
        this.board[6][0].SetIcon(icons[7]);       
        this.board[7][0].SetIcon(icons[9]);
        
            /*Классическая расстановка 1-ого ряда белых фигур*/
        this.board[0][7].SetFigure(NameFigure.ROCK, ColorFigure.WHITE);
        this.board[1][7].SetFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[2][7].SetFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[3][7].SetFigure(NameFigure.QUEEN, ColorFigure.WHITE);
        this.board[4][7].SetFigure(NameFigure.KING, ColorFigure.WHITE);
        this.board[5][7].SetFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[6][7].SetFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[7][7].SetFigure(NameFigure.ROCK, ColorFigure.WHITE);
        
        this.board[0][7].SetIcon(icons[3]);
        this.board[1][7].SetIcon(icons[1]);
        this.board[2][7].SetIcon(icons[2]);
        this.board[3][7].SetIcon(icons[4]);
        this.board[4][7].SetIcon(icons[5]);
        this.board[5][7].SetIcon(icons[2]);
        this.board[6][7].SetIcon(icons[1]);
        this.board[7][7].SetIcon(icons[3]);
    }
    
    private void StartFisher()  // Расстановка 1-1ого и 8-ого ряда случайным образом
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i = 0; i < 8; i++)
            for(int j = 2; j < 6; j++)
            {
                this.board[i][j].SetFigure(NameFigure.EMPTY, ColorFigure.NONE); // пустые клетки в стартовой позиции
                this.board[i][j].SetIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        {
            this.board[i][1].SetFigure(NameFigure.PAWN, ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].SetIcon(icons[6]);
            this.board[i][6].SetFigure(NameFigure.PAWN, ColorFigure.WHITE); // Выставляем черные пешки
            this.board[i][6].SetIcon(icons[0]);
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
        this.board[arr[0]][0].SetFigure(NameFigure.ROCK, ColorFigure.BLACK);
        this.board[arr[1]][0].SetFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[arr[2]][0].SetFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[arr[3]][0].SetFigure(NameFigure.QUEEN, ColorFigure.BLACK);
        this.board[arr[4]][0].SetFigure(NameFigure.KING, ColorFigure.BLACK);
        this.board[arr[5]][0].SetFigure(NameFigure.BISHOP, ColorFigure.BLACK);
        this.board[arr[6]][0].SetFigure(NameFigure.KNIGHT, ColorFigure.BLACK);
        this.board[arr[7]][0].SetFigure(NameFigure.ROCK, ColorFigure.BLACK);
            
        this.board[arr[0]][0].SetIcon(icons[9]);      
        this.board[arr[1]][0].SetIcon(icons[7]);     
        this.board[arr[2]][0].SetIcon(icons[8]); 
        this.board[arr[3]][0].SetIcon(icons[10]);      
        this.board[arr[4]][0].SetIcon(icons[11]);     
        this.board[arr[5]][0].SetIcon(icons[8]);
        this.board[arr[6]][0].SetIcon(icons[7]);       
        this.board[arr[7]][0].SetIcon(icons[9]);
        
            /*Случайная расстановка 1-ого ряда белых фигур*/
        this.board[arr[0]][7].SetFigure(NameFigure.ROCK, ColorFigure.WHITE);
        this.board[arr[1]][7].SetFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[arr[2]][7].SetFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[arr[3]][7].SetFigure(NameFigure.QUEEN, ColorFigure.WHITE);
        this.board[arr[4]][7].SetFigure(NameFigure.KING, ColorFigure.WHITE);
        this.board[arr[5]][7].SetFigure(NameFigure.BISHOP, ColorFigure.WHITE);
        this.board[arr[6]][7].SetFigure(NameFigure.KNIGHT, ColorFigure.WHITE);
        this.board[arr[7]][7].SetFigure(NameFigure.ROCK, ColorFigure.WHITE);
        
        this.board[arr[0]][7].SetIcon(icons[3]);
        this.board[arr[1]][7].SetIcon(icons[1]);
        this.board[arr[2]][7].SetIcon(icons[2]);
        this.board[arr[3]][7].SetIcon(icons[4]);
        this.board[arr[4]][7].SetIcon(icons[5]);
        this.board[arr[5]][7].SetIcon(icons[2]);
        this.board[arr[6]][7].SetIcon(icons[1]);
        this.board[arr[7]][7].SetIcon(icons[3]);
        
    }
    
    
    public void PrintCurrentInfoBoard()// Вывод состояния шахматной доски в консоль (пока не удалять!)
    {
        System.out.println("-----------------------");
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                System.out.println(this.board[j][i].GetName() + ": " + this.board[j][i].GetNameFigure());
        System.out.println("-----------------------");
    }
    
    /// FIX
    public void Move(int x_from, int y_from, int x_to, int y_to)// Перемещение фигуры из from в to
    {
        if(!CheckMove(x_from, y_from, x_to, y_to))
           return;
            
        // Переписать метод
        switch(board[x_from][y_from].GetNameFigure())
        {
            case "PAWN WHITE":   board[x_to][y_to].SetIcon(icons[0]);    board[x_to][y_to].SetFigure(NameFigure.PAWN, ColorFigure.WHITE); break;
            case "KNIGHT WHITE": board[x_to][y_to].SetIcon(icons[1]);    board[x_to][y_to].SetFigure(NameFigure.KNIGHT, ColorFigure.WHITE); break;
            case "BISHOP WHITE": board[x_to][y_to].SetIcon(icons[2]);    board[x_to][y_to].SetFigure(NameFigure.BISHOP, ColorFigure.WHITE); break;
            case "ROCK WHITE":   board[x_to][y_to].SetIcon(icons[3]);    board[x_to][y_to].SetFigure(NameFigure.ROCK, ColorFigure.WHITE); break;
            case "QUEEN WHITE":  board[x_to][y_to].SetIcon(icons[4]);    board[x_to][y_to].SetFigure(NameFigure.QUEEN, ColorFigure.WHITE); break;
            case "KING WHITE":   board[x_to][y_to].SetIcon(icons[5]);    board[x_to][y_to].SetFigure(NameFigure.KING, ColorFigure.WHITE); break;
            case "PAWN BLACK":   board[x_to][y_to].SetIcon(icons[6]);    board[x_to][y_to].SetFigure(NameFigure.PAWN, ColorFigure.BLACK); break;
            case "KNIGHT BLACK": board[x_to][y_to].SetIcon(icons[7]);    board[x_to][y_to].SetFigure(NameFigure.KNIGHT, ColorFigure.BLACK); break;
            case "BISHOP BLACK": board[x_to][y_to].SetIcon(icons[8]);    board[x_to][y_to].SetFigure(NameFigure.BISHOP, ColorFigure.BLACK); break;
            case "ROCK BLACK":   board[x_to][y_to].SetIcon(icons[9]);    board[x_to][y_to].SetFigure(NameFigure.ROCK, ColorFigure.BLACK); break;
            case "QUEEN BLACK":  board[x_to][y_to].SetIcon(icons[10]);   board[x_to][y_to].SetFigure(NameFigure.QUEEN, ColorFigure.BLACK); break;
            case "KING BLACK":   board[x_to][y_to].SetIcon(icons[11]);   board[x_to][y_to].SetFigure(NameFigure.KING, ColorFigure.BLACK); break;
        }
        board[x_from][y_from].SetIcon(icons[12]);
        board[x_from][y_from].SetFigure(NameFigure.EMPTY, ColorFigure.NONE);
    }
    
    public void Move(String from, String to)// Перемещение фигуры из from в to, согласно нотации
    {
        int x_from = 7  + from.charAt(0) - 'h';
        int y_from = '8' - from.charAt(1);
        int x_to = 7  + to.charAt(0) - 'h';
        int y_to = '8' - to.charAt(1);
        Move(x_from, y_from, x_to, y_to);
    }
    
    public boolean CheckMove(int x_from, int y_from, int x_to, int y_to)
    {
        
        switch (board[x_from][y_from].GetNameFigure())
        {
            case "PAWN WHITE": return true;
            case "KNIGHT WHITE": break;
            case "BISHOP WHITE": return CheckBishop(x_from, y_from, x_to, y_to, ColorFigure.WHITE);
            case "ROCK WHITE": break;
            case "QUEEN WHITE": break;
            case "KING WHITE": break;
            case "PAWN BLACK": return true;
            case "KNIGHT BLACK": break;
            case "BISHOP BLACK": return CheckBishop(x_from, y_from, x_to, y_to, ColorFigure.BLACK);
            case "ROCK BLACK": break;
            case "QUEEN BLACK": break;
            case "KING BLACK": break;
            default: return false;
        }
        
        return true;
    }
    
    public boolean CheckBishop(int x_from, int y_from, int x_to, int y_to, ColorFigure color) // Проверить
    {
        if(x_to == x_from || y_to == y_from)
            return false;
        if(x_to>x_from)
        {
            if(y_to > y_from)
            {
                while(x_from < x_to && x_from < 8 && y_from < 8)
                {
                    y_from++;
                    x_from++;
                    if(!board[x_from][y_from].IsEmpty())
                        return false;     
                }
                if(x_from == x_to && y_from == y_to)
                    return color != board[x_to][y_to].GetColor();
                return false;
            }
            else
            {
                while(x_from < x_to && x_from < 8 && y_from >= 0)
                {
                    y_from--;
                    x_from++;
                    if(!board[x_from][y_from].IsEmpty())
                        return false;     
                }
                if(x_from == x_to && y_from == y_to)
                    return color != board[x_to][y_to].GetColor();
                return false;
            }
        }
        else
        {
            if(y_to > y_from)
            {
                while(x_from > x_to && x_from >= 0 && y_from < 8)
                {
                    y_from++;
                    x_from--;
                    if(!board[x_from][y_from].IsEmpty())
                        return false;     
                }
                if(x_from == x_to && y_from == y_to)
                    return color != board[x_to][y_to].GetColor();
                return false;
            }
            else
            {
                while(x_from > x_to && x_from >= 0 && y_from >= 0)
                {
                    y_from--;
                    x_from--;
                    if(!board[x_from][y_from].IsEmpty())
                        return false;     
                }
                if(x_from == x_to && y_from == y_to)
                    return color != board[x_to][y_to].GetColor();
                return false;
            }
        }      
    }
    
    public String GetFEN()
    {
       String res = "";
       for(int i=0;i<8;i++)
       {
           if(IsEmptyRow(board[i]))
               res+="8/";
           // TODO:
           //
       }
            
       return res;
    }
    
    public boolean IsEmptyRow(Cell[] c)
    {
        
        return true;
    }
}
