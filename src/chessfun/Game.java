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
import figures.*;
import java.util.Random;

enum ModeChess {CLASSIC, FISHER, KING_HILL}
enum ModeShape {ALPHA, CHESS24, MERIDA}

public class Game {
    
    private Cell[][] board;          // шахматная доска
    private ImageIcon icons[];       // иконки для отображения фигур
    private JFrame view;             // Основной JFrame для отображения 
    
    
    public Game(ModeChess modeChoice, ModeShape modeShape) // Конструктор
    {
        this.board = new Cell[8][8];
        this.icons = new ImageIcon[13];
        LoadTextures(modeShape);
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
                    chessBoard.add(board[i][j].GetLabel());

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
        view.repaint();
        // TODO logic (Move(...); ...)
        
        Move(2,0, 4, 2);
        view.repaint();
        Move(3,1,3,4);
        PrintCurrentInfoBoard();
        view.repaint();

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
    
    private void LoadTextures(ModeShape modeShape)
    {
        String folder = "../textures/";
        switch(modeShape)
        {
            case ALPHA: folder += "alpha"; break;
            case CHESS24: folder += "chess24"; break;
            case MERIDA: folder += "merida"; break;
        }
        icons[0] = new ImageIcon(getClass().getResource(folder + "/wP.png"));
        icons[1] = new ImageIcon(getClass().getResource(folder + "/wN.png"));
        icons[2] = new ImageIcon(getClass().getResource(folder + "/wB.png"));
        icons[3] = new ImageIcon(getClass().getResource(folder + "/wR.png"));
        icons[4] = new ImageIcon(getClass().getResource(folder + "/wQ.png"));
        icons[5] = new ImageIcon(getClass().getResource(folder + "/wK.png"));
        icons[6] = new ImageIcon(getClass().getResource(folder + "/bP.png"));
        icons[7] = new ImageIcon(getClass().getResource(folder + "/bN.png"));
        icons[8] = new ImageIcon(getClass().getResource(folder + "/bB.png"));
        icons[9] = new ImageIcon(getClass().getResource(folder + "/bR.png"));
        icons[10] = new ImageIcon(getClass().getResource(folder + "/bQ.png"));
        icons[11] = new ImageIcon(getClass().getResource(folder + "/bK.png"));
        icons[12] = new ImageIcon(getClass().getResource(folder + "/empty.png"));
    }
    
    private void StartClassic() // Классическая начальная расстановка фигур
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.board[i][j] = new Cell(i, j); // Инициализация клеток
        
        for(int i = 0; i < 8; i++)
            for(int j = 2; j < 6; j++)
            {
                this.board[i][j].shape = new EmptyFigure(); // пустые клетки в стартовой позиции
                this.board[i][j].SetIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        {
            this.board[i][1].shape = new Pawn(ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].SetIcon(icons[6]);
            this.board[i][6].shape = new Pawn(ColorFigure.WHITE); // Выставляем черные пешки
            this.board[i][6].SetIcon(icons[0]);
        }
                /*Классическая расстановка 8-ого ряда черных фигур*/
        this.board[0][0].shape = new Rock(ColorFigure.BLACK);   // a8
        this.board[0][0].SetIcon(icons[9]);
        this.board[1][0].shape = new Knight(ColorFigure.BLACK); // b8
        this.board[1][0].SetIcon(icons[7]);
        this.board[2][0].shape = new Bishop(ColorFigure.BLACK); // c8
        this.board[2][0].SetIcon(icons[8]);
        this.board[3][0].shape = new Queen(ColorFigure.BLACK);  // d8
        this.board[3][0].SetIcon(icons[10]);
        this.board[4][0].shape = new King(ColorFigure.BLACK);   // e8
        this.board[4][0].SetIcon(icons[11]);
        this.board[5][0].shape = new Bishop(ColorFigure.BLACK); // f8
        this.board[5][0].SetIcon(icons[8]);
        this.board[6][0].shape = new Knight(ColorFigure.BLACK); // g8
        this.board[6][0].SetIcon(icons[7]);
        this.board[7][0].shape = new Rock(ColorFigure.BLACK);   // h8
        this.board[7][0].SetIcon(icons[9]);
        
            /*Классическая расстановка 1-ого ряда белых фигур*/
        this.board[0][7].shape = new Rock(ColorFigure.WHITE);   // a1
        this.board[0][7].SetIcon(icons[3]);
        this.board[1][7].shape = new Knight(ColorFigure.WHITE); // b1
        this.board[1][7].SetIcon(icons[1]);
        this.board[2][7].shape = new Bishop(ColorFigure.WHITE); // c1
        this.board[2][7].SetIcon(icons[2]);
        this.board[3][7].shape = new Queen(ColorFigure.WHITE);  // d1
        this.board[3][7].SetIcon(icons[4]);
        this.board[4][7].shape = new King(ColorFigure.WHITE);   // e1
        this.board[4][7].SetIcon(icons[5]);
        this.board[5][7].shape = new Bishop(ColorFigure.WHITE); // f1
        this.board[5][7].SetIcon(icons[2]);
        this.board[6][7].shape = new Knight(ColorFigure.WHITE); // g1
        this.board[6][7].SetIcon(icons[1]);
        this.board[7][7].shape = new Rock(ColorFigure.WHITE);   // h1
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
                this.board[i][j].shape = new EmptyFigure(); // пустые клетки в стартовой позиции
                this.board[i][j].SetIcon(icons[12]);
            }
        
        for(int i = 0; i < 8; i++)
        {
            this.board[i][1].shape = new Pawn(ColorFigure.BLACK); // Выставляем белые пешки
            this.board[i][1].SetIcon(icons[6]);
            this.board[i][6].shape = new Pawn(ColorFigure.WHITE); // Выставляем черные пешки
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
        this.board[arr[0]][0].shape = new Rock(ColorFigure.BLACK);   // a8
        this.board[arr[0]][0].SetIcon(icons[9]);
        this.board[arr[1]][0].shape = new Knight(ColorFigure.BLACK); // b8
        this.board[arr[1]][0].SetIcon(icons[7]);
        this.board[arr[2]][0].shape = new Bishop(ColorFigure.BLACK); // c8
        this.board[arr[2]][0].SetIcon(icons[8]);
        this.board[arr[3]][0].shape = new Queen(ColorFigure.BLACK);  // d8
        this.board[arr[3]][0].SetIcon(icons[10]);
        this.board[arr[4]][0].shape = new King(ColorFigure.BLACK);   // e8
        this.board[arr[4]][0].SetIcon(icons[11]);
        this.board[arr[5]][0].shape = new Bishop(ColorFigure.BLACK); // f8
        this.board[arr[5]][0].SetIcon(icons[8]);
        this.board[arr[6]][0].shape = new Knight(ColorFigure.BLACK); // g8
        this.board[arr[6]][0].SetIcon(icons[7]);
        this.board[arr[7]][0].shape = new Rock(ColorFigure.BLACK);   // h8
        this.board[arr[7]][0].SetIcon(icons[9]);
        
            /*Случайная расстановка 1-ого ряда белых фигур*/
        this.board[arr[0]][7].shape = new Rock(ColorFigure.WHITE);   // a1
        this.board[arr[0]][7].SetIcon(icons[3]);
        this.board[arr[1]][7].shape = new Knight(ColorFigure.WHITE); // b1
        this.board[arr[1]][7].SetIcon(icons[1]);
        this.board[arr[2]][7].shape = new Bishop(ColorFigure.WHITE); // c1
        this.board[arr[2]][7].SetIcon(icons[2]);
        this.board[arr[3]][7].shape = new Queen(ColorFigure.WHITE);  // d1
        this.board[arr[3]][7].SetIcon(icons[4]);
        this.board[arr[4]][7].shape = new King(ColorFigure.WHITE);   // e1
        this.board[arr[4]][7].SetIcon(icons[5]);
        this.board[arr[5]][7].shape = new Bishop(ColorFigure.WHITE); // f1
        this.board[arr[5]][7].SetIcon(icons[2]);
        this.board[arr[6]][7].shape = new Knight(ColorFigure.WHITE); // g1
        this.board[arr[6]][7].SetIcon(icons[1]);
        this.board[arr[7]][7].shape = new Rock(ColorFigure.WHITE);   // h1
        this.board[arr[7]][7].SetIcon(icons[3]);
        
    }
    
    
    public void PrintCurrentInfoBoard()// Вывод состояния шахматной доски в консоль (пока не удалять!)
    {
        System.out.println("-----------------------");
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                System.out.println(this.board[j][i].GetName() + ": " + ((Shape)(this.board[j][i].shape)).GetName());
        System.out.println("-----------------------");
    }
    
    /// FIX
    public void Move(int x_from, int y_from, int x_to, int y_to)// Перемещение фигуры из from в to
    {
        if(!CheckMove(x_from, y_from, x_to, y_to))
           return;
            
        // Переписать метод
        switch(board[x_from][y_from].shape.GetName())
        {
            case "Pawn WHITE":  board[x_to][y_to].SetIcon(icons[0]); board[x_to][y_to].shape = new Pawn(ColorFigure.WHITE); break;
            case "Knight WHITE":board[x_to][y_to].SetIcon(icons[1]); board[x_to][y_to].shape = new Knight(ColorFigure.WHITE); break;
            case "Bishop WHITE":board[x_to][y_to].SetIcon(icons[2]);board[x_to][y_to].shape = new Bishop(ColorFigure.WHITE); break;
            case "Rock WHITE": board[x_to][y_to].SetIcon(icons[3]);board[x_to][y_to].shape = new Rock(ColorFigure.WHITE);break;
            case "Queen WHITE": board[x_to][y_to].SetIcon(icons[4]);board[x_to][y_to].shape = new Queen(ColorFigure.WHITE);break;
            case "King WHITE": board[x_to][y_to].SetIcon(icons[5]);board[x_to][y_to].shape = new King(ColorFigure.WHITE);break;
            case "Pawn BLACK": board[x_to][y_to].SetIcon(icons[6]);board[x_to][y_to].shape = new Pawn(ColorFigure.BLACK);break;
            case "Knight BLACK": board[x_to][y_to].SetIcon(icons[7]);board[x_to][y_to].shape = new Knight(ColorFigure.BLACK);break;
            case "Bishop BLACK":board[x_to][y_to].SetIcon(icons[8]);board[x_to][y_to].shape = new Bishop(ColorFigure.BLACK); break;
            case "Rock BLACK": board[x_to][y_to].SetIcon(icons[9]);board[x_to][y_to].shape = new Rock(ColorFigure.BLACK);break;
            case "Queen BLACK": board[x_to][y_to].SetIcon(icons[10]);board[x_to][y_to].shape = new Queen(ColorFigure.BLACK);break;
            case "King BLACK": board[x_to][y_to].SetIcon(icons[11]);board[x_to][y_to].shape = new King(ColorFigure.BLACK);break;
        }
        board[x_from][y_from].SetIcon(icons[12]);
    }
    
    public boolean CheckMove(int x_from, int y_from, int x_to, int y_to)
    {
        
        switch (board[x_from][y_from].GetName())
        {
            case "Pawn WHITE": return true;
            case "Knight WHITE": break;
            case "Bishop WHITE": return CheckBishop(x_from, y_from, x_to, y_to, ColorFigure.WHITE);
            case "Rock WHITE": break;
            case "Queen WHITE": break;
            case "King WHITE": break;
            case "Pawn BLACK": return true;
            case "Knight BLACK": break;
            case "Bishop BLACK": return CheckBishop(x_from, y_from, x_to, y_to, ColorFigure.BLACK);
            case "Rock BLACK": break;
            case "Queen BLACK": break;
            case "King BLACK": break;
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
