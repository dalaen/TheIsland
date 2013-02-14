package theisland.world;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
import theisland.item.Item;
import theisland.item.Stone;
import theisland.item.Wood;
import theisland.item.food.ChickenLeg;
import theisland.item.food.Food;
import theisland.item.food.Mushroom;
import theisland.savesystem.Load;
import theisland.world.exception.*;

/**
 *
 * @author Xavier
 */
public final class World {
    private static final Scanner SCANNER = new Scanner(System.in);

	private static final World INSTANCE = new World();
    
    private final int MAXIMUM_CASTAWAY = 6;
    private ArrayList<Castaway> castaways = new ArrayList<Castaway>();
    private Weather weather = Weather.STORM;
    private int dayNumber = 1;
    private boolean cabinBuilt = false;
    private boolean worldCreated = false;
    private boolean newGame;
    
    private World() {
    }
        
    /**
     * This function is called upon world creation
     */
    public void createWorld() {
    	if (!worldCreated) {
	        boolean saveCorrupted = false;
	    	if ((new File("config.sav").exists())) {
	    		// Load previous game
	    		if (promptUserIfLoadGame()) {
	    			saveCorrupted = Load.getInstance().load();
	    			newGame = false;
	    		} else {
	    			promptCastawayNumber();
	    		}
	    	} 
	    	if (saveCorrupted || !(new File("config.sav").exists())) {
	            // New game
	            promptCastawayNumber();
	    	}
	    	
	    	worldCreated = true;
    	}
    }
    
    private void promptCastawayNumber() {
    	Gui.displayInline("How many castaway are on the island? ");
    	
    	int enteredNumber = SCANNER.nextInt();
    	
        while (castaways.size() > MAXIMUM_CASTAWAY || castaways.size() < 2) {
	        try {
				initCastaway(enteredNumber);
			} catch (TooManyCastaway | TooFewCastaway e) {
				Gui.displayError("You cannot either play with more than "+ MAXIMUM_CASTAWAY +" castaway, or play alone!");
				// Prompts the user again
	        	Gui.displayInline("How many castaway are on the island? ");
	            enteredNumber = SCANNER.nextInt();
			}
        }
        
        newGame = true;
    }
    
    private boolean promptUserIfLoadGame() {
    	Gui.displayInline("A save file exists. Do you wanna load it? (Y/n) ");
    	
    	return Gui.promptYesNoQuestion(SCANNER);
    }
    
    public static World getInstance() {
        return INSTANCE;
    }
    
    /**
     * Change the weather on the world
     * @param weather New weather to set from Weather enumeration
     */
    public void changeWeather(Weather weather) {
        this.weather = weather;
    }
    
    /**
     * Change the weather on the world
     * @param weather New weather to set from String
     */
    public void changeWeather(String weather) {
    	if (weather.equalsIgnoreCase("rain")) {
    		this.weather = Weather.RAIN;
    	} else if (weather.equalsIgnoreCase("storm")) {
    		this.weather = Weather.STORM;
    	} else if (weather.equalsIgnoreCase("snow")) {
    		this.weather = Weather.SNOW;
    	} else if (weather.equalsIgnoreCase("sun")) {
    		this.weather = Weather.SUN;
    	}
    }
    
    /**
     * Process the events to get to the next day
     */
    public void nextDay() {
    	int diceRoll;
    	getHero().addEnergy(10);
    	
    	for (Item item : getHero().getInventory()) {
    		if (item.isFood()) {
    			Food food = (Food) item;
    			food.decreaseLifetime();
    		}
    	}
    	
    	for (int i = 1 ; i < getNumberOfCastaway() ; i++) {
    		if (getCastaway(i).getAffinity() < 50) {
    			diceRoll = (new Random()).nextInt(50);
    			if (diceRoll == 25) {
    				Gui.display(getCastaway(i).getName() + " is luring at you... He's eating you!!");
    				castawayDeath(getHero());
    			}
    		}
    	}
    	
    	// New weather to the next day
        diceRoll = (new Random()).nextInt(100);
        // Odds: 50% SUN, 16% RAIN, 17% SNOW, 17% STORM 
        if (diceRoll >= 83) {
            changeWeather(Weather.STORM);
        } else if (diceRoll < 83 && diceRoll >= 66) {
            changeWeather(Weather.SNOW);
        } else if (diceRoll < 66 && diceRoll >= 50) {
            changeWeather(Weather.RAIN);
        } else {
            changeWeather(Weather.SUN);
        }
        dayNumber++;
    }
    
    /**
     * Gives the day we are in
     * @return the day number (day one: 1, etc.)
     */
    public int getDayNumber() {
        return dayNumber;
    }
    
    /**
     * Set the day number. This is only used from Load class under world creation and won't work otherwise.
     * @param dayNumber the new day number
     */
    public void setDayNumber(int dayNumber) throws InvalidDayNumber {
    	if (!worldCreated) {
	    	if (dayNumber > 0) {
	    		this.dayNumber = dayNumber;
	    	} else {
	    		throw new InvalidDayNumber();
	    	}
    	}
    }
    
    /**
     * Add a castaway to the world
     * @param castaway the castaway character to add
     */
    public void addCastaway(Castaway castaway) throws TooManyCastaway {
    	if (castaways.size() == MAXIMUM_CASTAWAY) {
    		throw new TooManyCastaway();
    	} else {
    		castaways.add(castaway);
    	}
    }
    
    /**
     * Give access to the castaway by its id
     * @param id the castaway id (0 = hero)
     * @return the sought castaway
     */
    public Castaway getCastaway(int id) {
    	if (id >= 0 && id < MAXIMUM_CASTAWAY) {
    		return castaways.get(id);
    	}
		return null;
    }
    
    /**
     * Give direct access to the hero
     * @return the hero's data
     */
    public Castaway getHero() {
    	return getCastaway(0);
    }
    
    /**
     * Initialize the castaways
     * @param numberOfCastaway the number of castaway
     */
    public void initCastaway(int numberOfCastaway) throws TooManyCastaway, TooFewCastaway {
    	if (numberOfCastaway <= MAXIMUM_CASTAWAY && numberOfCastaway > 1) {
    		addCastaway(new Castaway("Hero", true));
    		
    		int i;
    		for (i = 1 ; i < numberOfCastaway ; i++) {
    			Castaway castawayToAdd = new Castaway(false);
    			castawayToAdd.createRandomInventory();
    			addCastaway(castawayToAdd);
    		}
    	} else if (numberOfCastaway > MAXIMUM_CASTAWAY) {
    		throw new TooManyCastaway();
    	} else if (numberOfCastaway < 2) {
    		throw new TooFewCastaway();
    	}
    }
    
    /**
     * Gives the number of castaway left alive, including you
     * @return the number of castaway left, including you
     */
    public int getNumberOfCastaway() {
    	return castaways.size();
    }
    
    /**
     * Prints the current weather
     */
    public void printWeather() {
    	if (weather.equals(Weather.SUN)) {
    		Gui.display("It is sunny today!");
    	} else if (weather.equals(Weather.RAIN)) {
    		Gui.display("It's raining a lot!");
    	} else if (weather.equals(Weather.SNOW)) {
    		Gui.display("The snow is falling in a hurling wind");
    	} else if (weather.equals(Weather.STORM)) {
    		Gui.display("Thunder is ramming the earth all round you, along with a heavy rain");
    	}
    }
    
    /**
     * Returns the current weather
     * @return the current weather
     */
    public Weather getWeather() {
    	return weather;
    }
    
    /**
     * Give a random item with the same probability for each object
     * @return a random item created within the method
     */
    public Item getRandomItem() {
    	int diceRoll = (new Random()).nextInt(100);
    	
    	if (diceRoll >= 75 && diceRoll < 100) {
    		return new ChickenLeg();
    	} else if (diceRoll >= 50 && diceRoll < 75) {
    		return new Mushroom();
    	} else if (diceRoll >= 25 && diceRoll < 50) {
    		return new Stone();
    	} else {
    		return new Wood();
    	}
    }
    
    /**
     * Called when a castaway is gone
     * @param castaway the castaway to remove
     */
    public void removeCastaway(Castaway castaway) {
    	if (!castaway.isHero()) {
	    	if (castaways.size() > 1) {
	    		castaways.remove(castaway);
	    	}
	    	if (castaways.size() == 1) {
	    		endGame();
	    	}
    	}
    }
    
    public boolean isNew() {
		return newGame;
	}

	/**
     * End of the game
     */
    private void endGame() {
    	Gui.display("You won! :D");
    }

	public void castawayDeath(Castaway castaway) {
		if (castaway.isHero()) {
			gameOver();
		} else {
			castaways.remove(castaway);
		}
	}

	private void gameOver() {
		Gui.display("You are dead... Ahahahah");
	}

	/**
	 * Try to build a cabin
	 * Required mats: 2 woods, 1 stone
	 */
	public void tryBuildCabin() {
		int requiredNumberOfWood = 2;
		int requiredNumberOfStone = 1;
		
		for (Item item : getHero().getInventory()) {
			if (item.getClass() == Wood.class) {
				requiredNumberOfWood--;
			} else if (item.getClass() == Stone.class) {
				requiredNumberOfStone--;
			}
		}
		
		if (requiredNumberOfWood <= 0 && requiredNumberOfStone <= 0) {
			cabinBuilt = true;
		}
	}
	
	/**
	 * Destroys the cabin
	 */
	public void destroyCabin() {
		cabinBuilt = false;
	}

	public void setCabinBuilt(boolean cabinBuilt) {
		this.cabinBuilt = cabinBuilt;
	}

	public boolean isCabinBuilt() {
		return cabinBuilt;
	}
}
