package ok;

import java.util.ArrayList;

/**
 * @author AhmedShemy
 */

/** 
 * The EasyMachine class extends the Machine class and will perform clicks on cards displayed randomly without checking memory. 
 */

public class EasyMachine extends Machine{	 

	/**
	 * Constructor of the EasyMachine class setting parameter cards as the given ArrayList<Button_Cards> representing the cards displayed.
	 * (ArrayList cards has already been explained in PlayingFrame class).This class implements Runnable to be called via thread when needed.
	 *
	 * @param cards holds the given ArrayList<Button_cards> when an EasyMachine object is created
	 */

	public EasyMachine(ArrayList<Button_Cards> cards) {
		super(cards);
	}

	@Override
	public void run() {
		super.run();
	}
}

