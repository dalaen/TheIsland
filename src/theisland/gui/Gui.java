package theisland.gui;

/**
 *
 * @author Xavier
 */
public class Gui {
	/**
	 * Display a string with a \n return at the end
	 */
    public static void display(String displayString) {
        if (!displayString.isEmpty()) {
            System.out.println(displayString);
        }
    }
    
    /**
     * Display a string without a \n return at the end.
     * Useful when information is asked to the user.
     */
    public static void displayInline(String displayString) {
        if (!displayString.isEmpty()) {
            System.out.print(displayString);
        }
    }
    
    /**
     * Display an error with a \n return at the end
     */
    public static void displayError(String displayString) {
    	if (!displayString.isEmpty()) {
    		System.err.println(displayString);
    	}
    }
}
