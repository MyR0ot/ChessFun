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
    private final List<String> notes;   // список записей
    
    public History(Cell[][] board)
    {
        startFen = Fen.getFEN(board);
        notes = new ArrayList<>();
    }
    
    public History()
    {
        startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"; // смотреть https://ru.wikipedia.org/wiki/Нотация_Форсайта_—_Эдвардса
        notes = new ArrayList<>();
    }
    
    public int getCountNotes()
    {
        return this.notes.size();
    }
    
    public void addNote(String from, String to)
    {
        this.notes.add(from + " " + to);
    }
    
    public void addNote(String str) // 0-0 или 0-0-0
    {
        this.notes.add(str);
    }
    
    public List<String> getNotes()
    {
        showHistory();
        return this.notes;
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
        for(String note : notes)
                System.out.println(note);
    }
}
