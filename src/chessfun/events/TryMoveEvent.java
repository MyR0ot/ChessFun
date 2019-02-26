/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.events;

import java.util.EventObject;

/**
 *
 * @author My
 */
public class TryMoveEvent  extends EventObject {
    
    private String from;
    private String to;

    public TryMoveEvent(Object source, String from, String to) {
 	   super(source); // вызов конструктора родителя
 	   this.from = from;
           this.to = to;
   }
    
    public String GetFrom(){
        return this.from;
    }
    
    public String GetTo(){
        return this.to;
    }
}
