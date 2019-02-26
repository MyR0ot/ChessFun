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
    public static boolean allowСastleWhite;  // разрешена ли рокировка для белых?
    public static boolean allowCastleBlack;  // разрешена ли рокировка для черных?
    public static boolean[][] bigStepPawn;   // Ходили пешке на 2 клетки вперед на последнем ходу
    
    
    
    
    public static void clearBigStepPawnArray() // очистка массива
    {
        for(int i=0;i<8;i++)
        {
            Globals.bigStepPawn[0][i] = false;
            Globals.bigStepPawn[1][i] = false;
        }   
    }
}
