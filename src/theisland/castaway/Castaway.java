
package theisland.castaway;

import java.util.ArrayList;
import java.util.Random;
import theisland.gui.Gui;
import theisland.item.Item;

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
    
    public void steal(Castaway thief, Castaway stolenPlayer){
        Item stolenItem;
        int inventorySize;
        
        if(stolenPlayer.inventory.isEmpty())
        {
            Gui.displayError("Apart from stealing her clothes, you can't take someting from this poor guy");
        }
        if(thief.inventory.size() == 25)
        {
            Gui.displayError("Your inventory is already full");
        }
        else 
        {
          int itemNumber;
          
          inventorySize = stolenPlayer.inventory.size();
          itemNumber = (new Random()).nextInt(inventorySize + 1);
          stolenItem = stolenPlayer.inventory.get(itemNumber);
          thief.inventory.add(stolenItem);
          stolenPlayer.inventory.remove(itemNumber);
          Gui.display("The object has been stolen");
        }
        
        if(stolenPlayer.affinity <= 10)
        {
            stolenPlayer.affinity = 0;
        }
        else if(stolenPlayer.affinity > 10)
        {
            stolenPlayer.affinity = stolenPlayer.affinity - 10;
        }
        
    }
    
    public void kill(Castaway killedPlayer, Castaway killer){
        if(killedPlayer.energy < 10 && killer.energy > 10)
        {
            killedPlayer.health = 0;
            killedPlayer.energy = 0;
            Gui.display("May he soul rest in Peace ...");
        }
        else
        {
            Gui.displayError("Are you nuts ??!!");
        }
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
        
        if(player2.moral >= 50)
        {
            Gui.display("- How are you ? \t- I'm fine what about you ? \t- Me too, thanks ! ");
        }
        else if(player2.moral < 50 && player2.moral > 20)
        {
            Gui.display("- The weather is pretty good today \t- Ya, but I'm not motivated to do something ... \t- Worry, you'll feel better tomorrow ! ");
        }
        else if(player2.moral <= 20)
        {
            Gui.display("- You seem to be bad, you okay ?? \t- Yes, I'm tired of being here ... \t- Worry, you'll feel better soon!");
        }
    }
    
    public void dealWith(){
        
    }
}

