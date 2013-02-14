
package theisland.castaway;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import theisland.castaway.exception.*;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.item.food.Food;
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
    public void addItemToInventory(Item item) throws InventoryOutOfRange
    {
    	if (this.inventory.size() < INVENTORY_MAXIMUM_SIZE) {
    		this.inventory.add(item);
    	} else {
    		throw new InventoryOutOfRange();
    	}
    }
    
    /**
     * Allows the castaway to care itself, but it decrease energy
     */
    public void care()
    {
        removeEnergy(10);
        addHealth(10);
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
    		try {
				addItemToInventory(World.getInstance().getRandomItem());
			} catch (InventoryOutOfRange e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    /**
     * Steal randomly an object of a castaway's inventory
     * @param stolenPlayer Castaway to steal
     */
    public void steal(Castaway stolenPlayer){
        Item stolenItem;
        int inventorySize;
        
        removeEnergy(5);
        
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
          itemNumber = (new Random()).nextInt(inventorySize);
          stolenItem = stolenPlayer.inventory.get(itemNumber);
          this.inventory.add(stolenItem);
          stolenPlayer.inventory.remove(itemNumber);
          Gui.display("The object has been stolen :");
          Gui.display(stolenItem.getName());
        }
        
        stolenPlayer.removeAffinity(10);
    }
    
    /**
     * Kill a castaway(reduce his live and energy to 0)
     * @param killedPlayer Castaway to kill
     */
    public void kill(Castaway killedPlayer){
        removeEnergy(20);
        
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

    public void lookForItem() {
    	int diceRoll = (new Random()).nextInt(20);
    	
        Item proposedItem = World.getInstance().getRandomItem();
        
        removeEnergy(25);
        
        if (diceRoll == 10) {
        	removeHealth(50);
        }
        Gui.displayInline("You have found " + proposedItem.getName() + ". Do you want to keep it? (Y/n) ");
        
        if (Gui.promptYesNoQuestion(SCANNER) == true) {
        	try {
				addItemToInventory(proposedItem);
			} catch (InventoryOutOfRange e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

	/**
     * Speak with another castaway, increase moral and affinity
     * @param player1  Castaway you want to speak with
     */
    public void speakTo(Castaway player1){
        
    	this.removeEnergy(5);
    	this.addMoral(10);
    	player1.addMoral(10);
    	player1.addAffinity(10);
        
    	// TODO: Improve this part
        if(player1.moral >= 50)
        {
            Gui.display("- How are you ?");
            Gui.display("- I'm fine what about you ?");
            Gui.display("- Me too, thanks!");
        }
        else if(player1.moral < 50 && player1.moral > 20)
        {
            Gui.display("- The weather is pretty good today");
            Gui.display("- Ya, but I'm not motivated to do anything ...");
            Gui.display("- Don't worry, you'll feel better tomorrow!");
        }
        else if(player1.moral <= 20)
        {
            Gui.display("- You look bad, you okay ??");
            Gui.display("- Yes, I'm tired of being here ...");
            Gui.display("- Worry, you'll feel better soon!");
        }
    }
    
    /**
     * Deal an item with another castaway, increase moral and affinity
     * @param player1 Castaway you want to deal with
     */
    public void dealWith(Castaway player1){
        if(player1.affinity >= 50){
        	
        	this.removeEnergy(5);
        
            player1.addMoral(10);
            this.addMoral(10);

            if(player1.inventory.isEmpty())
            {
                Gui.displayError("Apart from exchanging their clothes, you can't exchange something with this poor guy");
            }
            else 
            {
              int itemNumber;
              Item exchangedItem;

              int inventorySize;
              inventorySize = player1.inventory.size();
              itemNumber = (new Random()).nextInt(inventorySize);
              exchangedItem = player1.inventory.get(itemNumber);

              Gui.display("This castaway offers exchange:");
              Gui.display(exchangedItem.getName());
              Gui.display("Do you accept ? (Y/n) ");
              boolean answer = Gui.promptYesNoQuestion(SCANNER);
              if (answer == true) {
                  player1.addAffinity(10);
            	  
            	  displayInventory();
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
              } else {
            	  player1.removeAffinity(10);
              }
            }
        } else {
            Gui.display("You can't deal with him because your affinity is too low");
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
    	if (inventory.isEmpty()) {
    		Gui.display("Your inventory is empty...");
    	} else {
	    	int i = 0;
	    	
	    	Gui.display("Hero's inventory");
	    	for (Item item : inventory) {
	    		// TODO: Display if rotten
	    		String rotten = new String("");
	    		if (item.isFood()) {
	    			Food food = (Food) item;
	    			if (food.isRotten()) {
	    				rotten = " (rotten)";
	    			}
	    		}
	    		Gui.display((i++ + 1) + ". " + item.getName() + rotten);
	    	}
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
     * Add health to the castaway
     * @param health the health to add
     */
    public void addHealth(int health) {
    	if (health > 0) {
	    	if ((this.health + health) > 100) {
	    		this.health = 100;
	    	} else {
	    		this.health += health;
	    	}
    	}
    }
    
    public void removeHealth(int i) {
		// TODO Auto-generated method stub
		if (i > 0) {
	    	if (health - i <= 0) {
				// TODO: Castaway dies
			} else {
				health -= i;
			}
		}
	}

    /**
     * Add an amount of energy to the castaway
     * @param energy the energy to add
     */
    public void addEnergy(int energy) {
    	if (energy > 0) {
	    	if ((this.energy + energy) > 100) {
	    		this.energy = 100;
	    	} else {
	    		this.energy += energy;
	    	}
    	}
    }
    
    /**
     * Remove an amount of energy to the castaway
     * @param energy the energy to remove
     */
    public void removeEnergy(int energy) {
    	if (energy > 0) {
    		if ((this.energy - energy) < 0) {
    			this.energy = 0;
    		} else {
    			this.energy -= energy;
    		}
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
     * Add an amount of moral to the castaway
     * @param moral the moral to add
     */
    public void addMoral(int moral) {
    	if (moral > 0) {
	    	if ((this.moral + moral) > 100) {
	    		this.moral = 100;
	    	} else {
	    		this.moral += moral;
	    	}
    	}
    }
    
    /**
     * Remove an amount of moral to the castaway
     * @param moral the moral to remove
     */
    public void removeMoral(int moral) {
    	if (moral > 0) {
    		if ((this.moral - moral) < 0) {
    			this.moral = 0;
    		} else {
    			this.moral -= moral;
    		}
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
     * Add affinity to the castaway towards the hero
     * @param affinity the affinity to add
     */
    public void addAffinity(int affinity) {
    	if (affinity > 0) {
	    	if ((this.affinity + affinity) > 100) {
	    		this.affinity = 100;
	    	} else {
	    		this.affinity += affinity;
	    	}
    	}
    }
    
    /**
     * Remove an amount of affinity to the castaway
     * @param affinity the affinity to remove
     */
    public void removeAffinity(int affinity) {
    	if (energy > 0) {
    		if ((this.affinity - affinity) < 0) {
    			this.affinity = 0;
    		} else {
    			this.affinity -= affinity;
    		}
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
    


