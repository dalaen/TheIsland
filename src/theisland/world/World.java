package theisland.world;

import java.util.Random;
import java.util.Scanner;

import theisland.gui.Gui;

/**
 *
 * @author Xavier
 */
public final class World {
    private static final Scanner SCANNER = new Scanner(System.in);

	private static final World INSTANCE = new World();
    
    private final int MAXIMUM_CASTAWAY = 6;
    private Weather weather;
    private int numberOfCastaway;
    private int dayNumber;
    
    /*
     * World Constructor
     */
    private World() {
        weather = Weather.STORM;
        dayNumber = 1;
        // Prompt the user for how many castaway he wanna play with
        Gui.display("How many castaway are on the island? ");
        int enteredNumber = SCANNER.nextInt();
        setNumberOfCastaway(enteredNumber);
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
     * @param weather New weather to set
     */
    public void changeWeather(Weather weather) {
        this.weather = weather;
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
     * Set the number of castaway
     * @param numberOfCastaway The number of castaway
     */
    private void setNumberOfCastaway(int numberOfCastaway) {
    	if (numberOfCastaway <= MAXIMUM_CASTAWAY) {
    		this.numberOfCastaway = numberOfCastaway;
    	}
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
