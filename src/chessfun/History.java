/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author My
 */
public class History {
    private final String startFen;        // стартовая позиция
    private final List<String> history;   // список записей
    
    public History(Cell[][] board)
    {
        startFen = Fen.getFEN(board);
        history = new ArrayList<>();
    }
    
    public History()
    {
        startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"; // смотреть https://ru.wikipedia.org/wiki/Нотация_Форсайта_—_Эдвардса
        history = new ArrayList<>();
    }
    
    public int getCountNotes()
    {
        return this.history.size();
    }
    
    public void addNote(String from, String to)
    {
        this.history.add(from + " " + to);
    }
    
    public void addNote(String str)
    {
        this.history.add(str);
    }
    
    public List<String> getNotes()
    {
        return this.history;
    }
    
    public String getPGN()
    {
        String res = "This is sample PGN";
        //TODO:
        //

        return res;
    }
    
    public void showHistory()
    {
        for(String note : history)
                System.out.println(note);
    }
}
