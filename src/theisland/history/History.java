/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.history;

import java.util.logging.Level;
import java.util.logging.Logger;
import theisland.gui.Gui;

/**
 *
 * @author isen
 */
public class History {
    
	private final static int WAITING_TIME = 2000;
	
    /**
     * Display the introduction of the game 
     * @param mode Mode of the game : SOLO, MULTIPLAYER
     */
    public static void displayIntroduction(Mode mode){
        Gui.display("On a beautiful summer day, the passengers of Flight 4125 from New York to Tahiti are serene, the flight is going well and dozens of beautiful islands are visible from the plane ...");
        
        try {
            Thread.sleep(WAITING_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Gui.display("But suddenly, a terrible noise is heard, the plane began to shake, turn on itself and fall steeply to the sea ... Everyone starts screaming ...");
        
        try {
            Thread.sleep(WAITING_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(mode.equals(Mode.SOLO)) {
            Gui.display("Sunlight wake you up, you're lying on the beach, the carcass of the plane, decomposed, is a little farther down the beach.Bodies are floating on the water and nobody seems to have survived apart from you ... ");
        }
    
        if(mode.equals(Mode.MULTIPLAYER)) {
            Gui.display("Sunlight wake you up, you're lying on the beach, the carcass of the plane, decomposed, is a little farther down the beach.Bodies are floating on the water, a person runs to you and asks if you're okay ... ");
        }
        
          try {
            Thread.sleep(WAITING_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
    }
}
