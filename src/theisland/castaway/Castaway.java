
package theisland.castaway;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import theisland.castaway.exception.*;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.world.World;

/**
 *
 * @author jeremy
 */
public class Castaway {
    private int health = 100;    // Health between 0 and 100
    private int energy = 100;    // Energy between 0 and 100
    private int moral = 100;     // Moral between 0 and 100
    private String name;   // Name between 1 and 20 characters
    private ArrayList<Item> inventory;  // Inventory between 0 and INVENTORY_MAXIMUM_SIZE items
    private int affinity = 50;  // Affinity between 0 and 100
    private boolean isHero;
	private static final int INVENTORY_MAXIMUM_SIZE = 10;
    
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
    /**
     * Add an item to the inventory of the castaway, decrease affinity
     * @param item Item to add to the inventory
     * @throws InventoryOutOfRange 
     */
    public void addItemToInventory(Item item)
    {
    	if (this.inventory.size() < INVENTORY_MAXIMUM_SIZE) {
    		this.inventory.add(item);
    	}
    }
    
    /**
     * Allows the castaway to care itself, but it decrease energy
     */
    public void care()
    {
        if(this.energy > 10) {
            this.energy = this.energy - 10;
        } else if(this.energy <= 10) {
            this.energy = 0;
        }
        
        if(this.health <= 90) {
            this.health = this.health + 10;
        } else if(this.health > 90){
            this.health = 100;
        }
            
    }
    
    /**
     * Delete an item from the inventory without precision of its index
     * @param item Item to delete from the inventory
     */
    public void deleteItemFromInventory(Item item)
    {
        int index;
        index = this.inventory.indexOf(item);
        this.inventory.remove(index);
    }
    
    /**
     * Provide the castaway a whole new random inventory
     */
    public void createRandomInventory() {
    	for (int i = 0 ; i < INVENTORY_MAXIMUM_SIZE ; i++) {
    		addItemToInventory(World.getInstance().getRandomItem());
    	}
    }
    
    /**
     * Steal randomly an object of a castaway's inventory
     * @param stolenPlayer Castaway to steal
     */
    public void steal(Castaway stolenPlayer){
        Item stolenItem;
        int inventorySize;
        
        if(this.energy > 5) {
            this.energy = this.energy - 5;
        }
        else if(this.energy <= 5) {
            this.energy = 0;
        }
        if(stolenPlayer.inventory.isEmpty())
        {
            Gui.displayError("Apart from stealing her clothes, you can't take something from this poor guy");
        }
        if(this.inventory.size() == INVENTORY_MAXIMUM_SIZE)
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
    
    /**
     * Kill a castaway(reduce his live and energy to 0)
     * @param killedPlayer Castaway to kill
     */
    public void kill(Castaway killedPlayer){
        if(this.energy > 5) {
            this.energy = this.energy - 5;
        }
        else if(this.energy <= 5) {
            this.energy = 0;
        }
        
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
        if(this.energy > 5) {
            this.energy = this.energy - 5;
        }
        else if(this.energy <= 5) {
            this.energy = 0;
        }
        
    }
    
    /**
     * Speak with another castaway, increase moral and affinity
     * @param player1  Castaway you want to speak with
     */
    public void speakTo(Castaway player1){
        
        if(this.energy > 5) {
            this.energy = this.energy - 5;
        }
        else if(this.energy <= 5) {
            this.energy = 0;
        }
        
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
    
    /**
     * Deal an item with another castaway, increase moral and affinity
     * @param player1 Castaway you want to deal with
     */
    public void dealWith(Castaway player1){
        if(player1.affinity >= 50){
        
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
        } else {
            gui.display("You can't deal with him because your affinity is too low");
        }
    }
    
    /**
     * 
     * @return Return true if the castaway is an hero, 0 otherwise
     */
    public boolean isHero(){
        return isHero;
    }
    
    /**
     * 
     * @return Return the affinity of the castaway with a specific another castaway
     */
    public int getAffinity(){
        return affinity;
    }
    
    /**
     * 
     * @return Return the energy of the castaway
     */
    public int getEnergy(){
        return energy;
    }
    
    /**
     * 
     * @return Return the healt of the castaway
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * 
     * @return Return the moral of a castaway
     */
    public int getMoral(){
        return moral;
    }
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return Return the inventory of a castaway
     */
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    
    /**
     * Display the inventory
     */
    public void displayInventory() {
    	int i = 0;
    	
    	Gui.display("Hero's inventory");
    	for (Item item : inventory) {
    		Gui.display((i++ + 1) + ". " + item.getName());
    	}
    }
    

    /**
     * Set the health of the castaway
     * @param health the health to set
     */
    public void setHealth(int health){
        if (health >= 0 && health <= 100) {
            this.health = health;
        }
    }

    /**
     * Set the energy of the castaway
     * @param energy the energy to set
     */
    public void setEnergy(int energy){
        if(energy >= 0 && energy <= 100){
            this.energy = energy;
        } 
    }

    /**
     * Set the moral of the castaway
     * @param moral the moral to set
     */
    public void setMoral(int moral){
        if(moral >= 0 && moral <= 100){
            this.moral = moral;
        } 
    }

    /**
     * Set the name of the castaway
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
     * Set the inventory of the castaway
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
     * Set the affinity of the castaway with a specific castaway
     * @param affinity the affinity to set
     */
    public void setAffinity(int affinity){
        if(affinity >=0 && affinity <= 100){
            this.affinity = affinity;
        } 
    }
}
    


