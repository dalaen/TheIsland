/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package theisland.castaway;

/**
 *
 * @author isen
 */
public class Castaway {
    protected int health;
    protected int stress;
    protected int energy;
    protected int moral;
    protected String name;
    
    public void steal(){
    
    }
    
    public void kill(Castaway player){
        player.health = 0;
        player.energy = 0;
    }
    
    public void take(){
        
    }
    
    public void speakTo(Castaway player1, Castaway player2){
        
        if(player1.moral <= 90)
        {
            player1.moral = player1.moral + 10; 
        }
        else if(player1.moral > 90)
        {
            player1.moral = 100;
        }
        
        if(player2.moral <= 90)
        {
            player2.moral = player2.moral + 10;
        }
        else if(player2.moral > 90)
        {
            player2.moral = 100;
        }
        
    }
    
    public void dealWith(){
        
    }
}

