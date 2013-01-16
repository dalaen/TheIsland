
package theisland.castaway;

import java.util.ArrayList;
import theisland.item.Item;
import theisland.gui.Gui;

/**
 *
 * @author isen
 */
public class Castaway {
    protected int health = 100;    // Health between 0 and 100
    protected int stress = 0;    // Stress between 0 and 100
    protected int energy = 100;    // Energy between 0 and 100
    protected int moral = 100;     // Moral between 0 and 100
    protected String name;   // Name between 1 and 20 characters
    protected ArrayList<Item> inventory;  // Inventory between 0 and 25 items
    protected int affinity = 50;  // Affinity between 0 and 100
    
    public void steal(Castaway player){
        Item stolenItem;
        int inventorySize;
        
        if(player.inventory.isEmpty())
        {
            Gui.display("hello");
            // Apart from stealing her clothes, you can't take someting from this poor guy
        }
        else 
        {
          inventorySize = player.inventory.size();
          
          //inventory.add(stolenItem);  
        }
        if(player.affinity <= 10)
        {
            player.affinity = 0;
        }
        else if(player.affinity > 10)
        {
            player.affinity = player.affinity - 10;
        }
        
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
        
        if(player1.affinity <= 90)
        {
            player1.affinity = player1.affinity + 10;
        }
        else if(player1.affinity > 90)
        {
            player1.affinity = 100;
        }
        
        // Reste à créer une liste de dialogues possibles
        
        // - How are you ? 
        // - I'm fine, what about you ? 
        // - Me too, thanks !
        
        // - The weather is pretty good today
        // - Ya, but I'm not motivated to do something ...
        // - Worry, you'll feel better tomorrow
        
    }
    
    public void dealWith(){
        
    }
}

