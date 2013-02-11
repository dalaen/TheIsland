package theisland.world;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

import theisland.castaway.Castaway;
import theisland.gui.Gui;
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
    private Castaway[] castaways = new Castaway[MAXIMUM_CASTAWAY];
    private Weather weather = Weather.STORM;
    private int numberOfCastaway;
    private int dayNumber = 1;
    
    /*
     * World Constructor
     */
    private World() {
    }
    
    public void createWorld() {
        boolean saveCorrupted = false;
    	if ((new File("config.sav").exists())) {
    		// Load previous game
    		Gui.display("Config file exists");
    		saveCorrupted = Load.getInstance().load();
    	} 
    	if (saveCorrupted || !(new File("config.sav").exists())) {
            // New game
            promptCastawayNumber();
    	}
    }
    
    private void promptCastawayNumber() {
    	Gui.displayInline("How many castaway are on the island? ");
    	
    	int enteredNumber = SCANNER.nextInt();
    	
        while (numberOfCastaway > MAXIMUM_CASTAWAY || numberOfCastaway < 2) {
	        try {
				initCastaway(enteredNumber);
			} catch (TooManyCastaway | TooFewCastaway e) {
				Gui.displayError("You cannot either play with more than "+ MAXIMUM_CASTAWAY +" castaway, or play alone!");
				// Prompts the user again
				// TODO: fix display error
	        	Gui.displayInline("How many castaway are on the island? ");
	            enteredNumber = SCANNER.nextInt();
			}
        }
    }
    
    /*
     * Gives the instance of the World, to use it
     * @return Return the World Instance
     */
    public static World getInstance() {
        return INSTANCE;
    }
    
    /*
     * Change the weather on the world
     * @param weather New weather to set from Weather enumeration
     */
    public void changeWeather(Weather weather) {
        this.weather = weather;
    }
    
    /* 
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
    
    /*
     * Process the events to get to the next day
     */
    public void nextDay() {
        int diceRoll = (new Random()).nextInt(100);
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
    
    /*
     * Gives the day we are in
     * @return Returns the dayNumber (day one: 1, etc.)
     */
    public int getDayNumber() {
        return dayNumber;
    }
    
    /*
     * Set the day number
     * @param dayNumber The new day number (used from Load class)
     */
    public void setDayNumber(int dayNumber) throws InvalidDayNumber {
    	if (dayNumber > 0) {
    		this.dayNumber = dayNumber;
    	} else {
    		throw new InvalidDayNumber();
    	}
    }
    
    /*
     * Add a castaway to the world
     * @param castaway The castaway character to add
     */
    public void addCastaway(Castaway castaway) throws TooManyCastaway {
    	if (numberOfCastaway == MAXIMUM_CASTAWAY) {
    		throw new TooManyCastaway();
    	} else {
    		castaways[numberOfCastaway] = castaway;
    		numberOfCastaway++;
    	}
    }
    
    /*
     * Give access to the castaway by its id
     * @param id The castaway id (0 = hero)
     * @return The castaway seeked
     */
    public Castaway getCastaway(int id) {
    	if (id >= 0 && id < MAXIMUM_CASTAWAY) {
    		return castaways[id];
    	}
		return null;
    }
    
    /*
     * Initialize the castaways
     * @param numberOfCastaway The number of castaway
     */
    public void initCastaway(int numberOfCastaway) throws TooManyCastaway, TooFewCastaway {
    	if (numberOfCastaway <= MAXIMUM_CASTAWAY && numberOfCastaway > 1) {
    		addCastaway(new Castaway("Hero", true));
    		
    		int i;
    		for (i = 1 ; i < numberOfCastaway ; i++) {
    			// TODO: NO!!!!!
    			addCastaway(new Castaway("castaway_"+i, false));
    		}
    	} else if (numberOfCastaway > MAXIMUM_CASTAWAY) {
    		throw new TooManyCastaway();
    	} else if (numberOfCastaway < 2) {
    		throw new TooFewCastaway();
    	}
    }
    
    /*
     * Gives the number of castaway left alive, including you
     * @return The number of castaway left, including you
     */
    public int getNumberOfCastaway() {
    	return numberOfCastaway;
    }
    
    /*
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
    
    /*
     * Returns the current weather
     * @return The current weather
     */
    public Weather getWeather() {
    	return weather;
    }
    
    /*
     * Called when a castaway is gone
     */
    public void removeCastaway() {
    	if (numberOfCastaway > 1) {
    		numberOfCastaway--;
    	}
    	if (numberOfCastaway == 1) {
    		endGame();
    	}
    }
    
    /*
     * End of the game
     */
    private void endGame() {
    	Gui.display("You won! :D");
    }
}
