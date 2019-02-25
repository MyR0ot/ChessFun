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
    
    private String message;

    public TryMoveEvent(Object source, String message) {
 	   super(source);
 	   this.message = message;
   }

   public TryMoveEvent(Object source){
 	   this(source, "");
   }

   public TryMoveEvent(String s){
 	   this(null, s);
   }

   public String getMessage(){
 	  return message;
   }
   
   @Override
   public String toString(){
 	  return getClass().getName() + "[source = " + getSource() + ", message = " + message + "]";
   }
}
