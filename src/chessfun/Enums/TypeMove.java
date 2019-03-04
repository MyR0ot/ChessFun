/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.Enums;

/**
 *
 * @author My
 */
public enum TypeMove {
    DEFAULT, // обычный ход
    OO,     // короткая рокировка
    OOO,    // длинная рокировка
    BIG_STEP_PAWN, // Пешка идет на 2 клетки вперед 
    TAKE_ON_PASS,  // Взятие на проходе
    ERROR;          // невозможный ход (необходимо вернуться)
}
