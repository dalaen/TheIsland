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
    
    /**
     * Display the introduction of the game 
     * @param mode Mode of the game : 1 for solo mode, 0 for multiplayer mode
     */
    public void displayIntroduction(int mode){
        Gui.display("On a beautiful summer day, the passengers of Flight 4125 from New York to Tahiti are serene, the flight is going well and dozens of beautiful islands are visible from the plane ...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Gui.display("But suddenly, a terrible noise is heard, the plane began to shake, turn on itself and fall steeply to the sea ... Everyone starts screaming ...");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(mode == 1) {
            Gui.display("Sunlight wake you up, you're lying on the beach, the carcass of the plane, decomposed, is a little farther down the beach.Bodies are floating on the water and nobody seems to have survived apart from you ... ");
        }
    
        if(mode == 0) {
            Gui.display("Sunlight wake you up, you're lying on the beach, the carcass of the plane, decomposed, is a little farther down the beach.Bodies are floating on the water, a person runs to you and asks if you're okay ... ");
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        Gui.display("If you want to survive, you will have to find yourself items which help you like food, materials, in order to create a caban and make fire for example ... ");
                 
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Gui.display("I advise you to search items and to eat something each day, because you have to keep your health and your energy, moreover, speak with us if you want to keep your moral ! Good luck !!");
  
    }
    
}
