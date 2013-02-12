
package theisland.castaway;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import theisland.castaway.exception.*;
import theisland.gui.Gui;
import theisland.item.Item;

/**
 *
 * @author jeremy
 */
public class Castaway {
    private int health = 100;    // Health between 0 and 100
    private int energy = 100;    // Energy between 0 and 100
    private int moral = 100;     // Moral between 0 and 100
    private String name;   // Name between 1 and 20 characters
    private ArrayList<Item> inventory;  // Inventory between 0 and 25 items
    private int affinity = 50;  // Affinity between 0 and 100
    private boolean isHero;
    
    protected final ArrayList<String> NAMES = new ArrayList<>(); //Possible names 
    
    
    private static final Scanner SCANNER = new Scanner(System.in);
    public Castaway(boolean isHero) {
        int numberOfNames;    
        int nameNumber;
        NAMES.add("MICHEL");
        NAMES.add("ALFRED");
        NAMES.add("JOSEPH");
        NAMES.add("JEAN-MARIE");
        NAMES.add("JEAN-CLAUDE");
        NAMES.add("MICHELINE");
        NAMES.add("ALPHONSE");
        NAMES.add("CLAUDETTE");
        NAMES.add("GEORGETTE");
        numberOfNames = NAMES.size();
        nameNumber = (new Random()).nextInt(numberOfNames - 1);
        this.name = NAMES.get(nameNumber);
        inventory = new ArrayList<>();
        this.isHero = isHero;
    }
    
    public Castaway(String name, boolean isHero) {
        this.name = name;
        inventory = new ArrayList<>();
        this.isHero = isHero;
    }
    
    public void addItemToInventory(Item item)
    {
        this.inventory.add(item);
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
            try {
                stolenPlayer.setAffinity(0);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(stolenPlayer.affinity > 10)
        {
            try {
                stolenPlayer.setAffinity(stolenPlayer.affinity - 10);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void kill(Castaway killedPlayer){
        if(killedPlayer.energy < 10 && this.energy > 10)
        {
            try {
                killedPlayer.setHealth(0);
            } catch (HealthOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                killedPlayer.setEnergy(0);
            } catch (EnergyOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gui.display("May he soul rest in Peace ...");
        }
        else
        {
            Gui.displayError("Are you nuts ??!!");
        }
    }
    
    public void take(){
        
    }
    
    public void speakTo(Castaway player1){
        
        if(this.moral <= 90)
        {
            try {
                this.setMoral(this.moral + 10);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.moral > 90)
        {
            try {
                this.setMoral(100);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(player1.moral <= 90)
        {
            try {
                player1.setMoral(player1.moral + 10);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(player1.moral > 90)
        {
            try {
                player1.setMoral(100);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(player1.affinity <= 90)
        {
            try {
                player1.setAffinity(player1.affinity + 10);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(player1.affinity > 90)
        {
            try {
                player1.setAffinity(100);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(player1.moral >= 50)
        {
            Gui.display("- How are you ? \t- I'm fine what about you ? \t- Me too, thanks ! ");
        }
        else if(player1.moral < 50 && player1.moral > 20)
        {
            Gui.display("- The weather is pretty good today \t- Ya, but I'm not motivated to do something ... \t- Worry, you'll feel better tomorrow ! ");
        }
        else if(player1.moral <= 20)
        {
            Gui.display("- You seem to be bad, you okay ?? \t- Yes, I'm tired of being here ... \t- Worry, you'll feel better soon!");
        }
    }
    
    public void dealWith(Castaway player1){
        if(player1.moral <= 90)
        {
            try {
                player1.setMoral(player1.moral + 10);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(player1.moral > 90)
        {
            try {
                player1.setMoral(100);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(this.moral <= 90)
        {
            try {
                this.setMoral(this.moral + 10);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(this.moral > 90)
        {
            try {
                this.setMoral(100);
            } catch (MoralOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(player1.affinity <= 90)
        {
            try {
                player1.setAffinity(player1.affinity + 10);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(player1.affinity > 90)
        {
            try {
                player1.setAffinity(100);
            } catch (AffinityOutOfRange ex) {
                Logger.getLogger(Castaway.class.getName()).log(Level.SEVERE, null, ex);
            }
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
          Gui.display("Do you accept ? Say Yes to accept, No to deny");
          
          String answer = SCANNER.next();
          while (answer.contentEquals("Yes") || answer.contentEquals("No")) {
	        
            Gui.displayInline("Yes or No !");
	    answer = SCANNER.next();
            }
          if(answer.contentEquals("Yes")){
              Gui.display("Enter the number of the object you want to exchange");
              int answerInt = SCANNER.nextInt();
              while(answerInt <= 0 || answerInt > this.inventory.size()){
                  Gui.display("First solution : You piss me off, second solution : You don't know how to read .. Try again!");
              }
          this.inventory.add(exchangedItem);
          player1.inventory.remove(itemNumber);
          exchangedItem = this.inventory.get(answerInt);
          player1.inventory.add(exchangedItem);
          this.inventory.remove(answerInt);
          Gui.display("Exchange done");
          }
        }
        
    }
    
    public boolean isHero(){
        return isHero;
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
    public String getName(){
        return name;
    }
    public ArrayList getInventory(){
        return inventory;
    }
    

    /**
     * @param health the health to set
     */
    public void setHealth(int health) throws HealthOutOfRange {
        if (health >= 0 && health <= 100) {
            this.health = health;
        } else {
            throw new HealthOutOfRange();
        }
       
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) throws EnergyOutOfRange {
        if(energy >= 0 && energy <= 100){
            this.energy = energy;
        } else {
            throw new EnergyOutOfRange();
        }
    }

    /**
     * @param moral the moral to set
     */
    public void setMoral(int moral) throws MoralOutOfRange {
        if(moral >= 0 && moral <= 100){
            this.moral = moral;
        } else {
            throw new MoralOutOfRange();
        }
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) throws NameOutOfRange {
        if(name.length() >= 1 && name.length() <= 20) {
            this.name = name;
        } else {
            throw new NameOutOfRange();
        }
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(ArrayList<Item> inventory) throws InventoryOutOfRange {
        if(inventory.size() >= 0 && inventory.size() <= 25){
            this.inventory = inventory;
        } else {
            throw new InventoryOutOfRange();
        }
    }

    /**
     * @param affinity the affinity to set
     */
    public void setAffinity(int affinity) throws AffinityOutOfRange {
        if(affinity >=0 && affinity <= 100){
            this.affinity = affinity;
        } else {
            throw new AffinityOutOfRange();
        }
    
}
    }
    


