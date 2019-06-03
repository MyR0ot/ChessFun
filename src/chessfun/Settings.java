/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun;

import javax.swing.JMenuItem;

/**
 *
 * @author My
 */
public class Settings {
    
    private int fullTime;
    private int inc;
    private int delay;
    private JMenuItem item;

    public JMenuItem getItem() {
        return item;
    }

    public int getFullTime() {
        return fullTime;
    }

    public void setFullTime(int fullTime) {
        this.fullTime = fullTime;
    }

    public int getInc() {
        return inc;
    }

    public void setInc(int inc) {
        this.inc = inc;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    
    public Settings(int fullTime, int inc, int delay, JMenuItem item)
    {
        this.fullTime = fullTime;
        this.inc = inc;
        this.delay = delay;
        this.item = item;
    }
    
}
