/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessfun.interfaces;

import chessfun.events.TryMoveEvent;

/**
 *
 * @author My
 */
public interface ITryMoveListener {
    public void tryMove(TryMoveEvent e);
}