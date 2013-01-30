
package theisland.castaway;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import theisland.gui.Gui;
import theisland.item.Item;

/**
 *
 * @author jeremy
 */
public class Castaway {
    private int health = 100;    // Health between 0 and 100
    private int stress = 0;    // Stress between 0 and 100
    private int energy = 100;    // Energy between 0 and 100
    private int moral = 100;     // Moral between 0 and 100
    private String name;   // Name between 1 and 20 characters
    private ArrayList<Item> inventory;  // Inventory between 0 and 25 items
    private int affinity = 50;  // Affinity between 0 and 100
    
    protected final ArrayList<String> NAMES = new ArrayList<String>(); //Possible names 
    
    
    private static final Scanner SCANNER = new Scanner(System.in);
    public Castaway() {
        NAMES.add("MICHEL");
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
            stolenPlayer.setAffinity(0);
        }
        else if(stolenPlayer.affinity > 10)
        {
            stolenPlayer.setAffinity(stolenPlayer.affinity - 10);
        }
        
    }
    
    public void kill(Castaway killedPlayer){
        if(killedPlayer.energy < 10 && this.energy > 10)
        {
            killedPlayer.setHealth(0);
            killedPlayer.setEnergy(0);
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
            this.setMoral(this.moral + 10);
        }
        else if(this.moral > 90)
        {
            this.setMoral(100);
        }
        
        if(player1.moral <= 90)
        {
            player1.setMoral(player1.moral + 10);
        }
        else if(player1.moral > 90)
        {
            player1.setMoral(100);
        }
        
        if(player1.affinity <= 90)
        {
            player1.setAffinity(player1.affinity + 10);
        }
        else if(player1.affinity > 90)
        {
            player1.setAffinity(100);
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
            player1.setMoral(player1.moral + 10);
        }
        else if(player1.moral > 90)
        {
            player1.setMoral(100);
        }
        
        if(this.moral <= 90)
        {
            this.setMoral(this.moral + 10);
        }
        else if(this.moral > 90)
        {
            this.setMoral(100);
        }
        
        if(player1.affinity <= 90)
        {
            player1.setAffinity(player1.affinity + 10);
        }
        else if(player1.affinity > 90)
        {
            player1.setAffinity(100);
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

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param stress the stress to set
     */
    public void setStress(int stress) {
        this.stress = stress;
    }

    /**
     * @param energy the energy to set
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * @param moral the moral to set
     */
    public void setMoral(int moral) {
        this.moral = moral;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * @param affinity the affinity to set
     */
    public void setAffinity(int affinity) {
        this.affinity = affinity;
    }
    
}

