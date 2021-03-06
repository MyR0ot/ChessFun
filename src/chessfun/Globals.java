/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import chessfun.Enums.ColorFigure;
import javax.swing.ImageIcon;

/**
 *
 * @author My
 */
public class Globals {
    public static Game game;                 // ссылка на класс игры (для подписки на обработку событий)
    public static ImageIcon iconSelected;    // картинка отображения выбранной фигуры (красная рамка)
    public static ImageIcon iconEmpty;       // пустая картинка
    public static boolean isSelectedFigure;  // на доске выбрана фигура
    public static int rowSelected;           // row выбранной фигуры
    public static int columnSelected;        // column выбранной фигуры
    public static String selectedCellName;   // имя поля выбранной фигуры
    public static ColorFigure stepQueue;     // Чей ход? (WHITE / BLACK)
    public static boolean allowCastleWhite;  // разрешена ли рокировка для белых?
    public static boolean allowCastleBlack;  // разрешена ли рокировка для черных?
    public static boolean[][] bigStepPawn;   // Ходили пешке на 2 клетки вперед на последнем ходу
    public static boolean castleAllowWhite;  // Разрешена рокировка белым?
    public static boolean castleAllowBlack;  // Разрешена рокировка белым?
    public static boolean startgame;         // Идет игра?
    public static int timeStart;             // Начальные показания часов
    public static boolean isAnalysis;        // Включен режим анализа?
    
    public static int timeWhite;
    public static int timeBlack;
    
    public static boolean isAllowWriteHistory; // Сохранять историю или нет
    
    
    public static int delta_x = 50; // Координаты позиционирования доски относительно фрейма
    public static int delta_y = 20; // Координаты позиционирования доски относительно фрейма
    
    public static String fieldWhiteKing; // Поле белого короля
    public static String fieldBlackKing; // Поля черного короля
    
    public static void clearBigStepPawnArray() // очистка массива 
    {
        for(int i=0;i<8;i++)
        {
            Globals.bigStepPawn[0][i] = false;
            Globals.bigStepPawn[1][i] = false;
        }   
    }
    
    public static String format(int i) {
        String result = String.valueOf(i);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
    
    
    public static void changeQueue()
    {
        if (Globals.stepQueue == ColorFigure.WHITE) {
            Globals.stepQueue = ColorFigure.BLACK;
            Globals.timeWhite += game.timeInc;
        } else {
            Globals.stepQueue = ColorFigure.WHITE;
            Globals.timeBlack += game.timeInc;
        }
    }
    
    public static ColorFigure getInvertColorFigure(ColorFigure clr)
    {
        switch(clr)
        {
            case WHITE: return ColorFigure.BLACK;
            case BLACK: return ColorFigure.WHITE;
            default: return ColorFigure.NONE;
        }
    }
    
}