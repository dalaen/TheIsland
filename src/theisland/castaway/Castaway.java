
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
    
    
    public Castaway() {
        
    }
    
    public Castaway(String name) {
        this.name = name;
        inventory = new ArrayList<Item>();
    }
    
    
    
    public void steal(Castaway stolenPlayer){
        Item stolenItem;
        int inventorySize;
        
        if(stolenPlayer.inventory.isEmpty())
        {
            Gui.displayError("Apart from stealing her clothes, you can't take something from this poor guy");
        }
        if(this.inventory.size() == 25)
        {
            Gui.displayError("Your inventory is already full");
        }
        else 
        {
          int itemNumber;
          
          inventorySize = stolenPlayer.inventory.size();
          itemNumber = (new Random()).nextInt(inventorySize + 1);
          stolenItem = stolenPlayer.inventory.get(itemNumber);
          this.inventory.add(stolenItem);
          stolenPlayer.inventory.remove(itemNumber);
          Gui.display("The object has been stolen :");
          Gui.display(stolenItem.getName());
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
    
    public void dealWith(Castaway player1, Castaway player2){
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
        
        if(player1.inventory.isEmpty())
        {
            Gui.displayError("Apart from exchanging her clothes, you can't exchange something with this poor guy");
        }
        else 
        {
          int itemNumber;
          Item exchangedItem;
          
          int inventorySize;
          inventorySize = player1.inventory.size();
          itemNumber = (new Random()).nextInt(inventorySize + 1);
          exchangedItem = player1.inventory.get(itemNumber);
          
          Gui.display("This castaway offers exchange:");
          Gui.display(exchangedItem.getName());
          Gui.display("Do you accept ? Say yes to accept, an other thing to deny");
          
          ///////////////
          
          player2.inventory.add(exchangedItem);
          player1.inventory.remove(itemNumber);
          Gui.display("Your object was exchanged with:");
          Gui.display(exchangedItem.getName());
        }
        
    }
    
    public int getAffinity(){
        return affinity;
    }
    public int getEnergy(){
        return energy;
    }
    public int getHealth(){
        return health;
    }
    public int getMoral(){
        return moral;
    }
    public int getStress(){
        return stress;
    }
    public String getName(){
        return name;
    }
    public ArrayList getInventory(){
        return inventory;
    }
    
}

