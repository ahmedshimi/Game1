package ok;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author AhmedShemy
 */

/** 
 * The HardMachine class extends the Machine class and will perform clicks on cards displayed randomly if there are no Button_Cards to be matched stored in memory.
 * This class implements Runnable to be called via thread when needed.
 */

public class HardMachine extends Machine{

	/**
	 * Constructor of the HardMachine class setting parameter cards as the given ArrayList<Button_Cards> representing the cards displayed.
	 * (ArrayList cards has already been explained in PlayingFrame class)
	 *
	 * @param cards holds the given ArrayList<Button_cards> when a HardMachine object is created
	 */

	public HardMachine(ArrayList<Button_Cards> cards) {
		super(cards);
	}

	/**
	 * Implements the run method of the Runnable Machine class.
	 * Checks memory and do clicks if there is a match stored.
	 * Else, Loops till a random, non-matched card is found, sets it as the selectedCard and click on it.
	 * Loops again until a random, non-matched and different from selectedCard "first card", card is found, and click on it as well.
	 */

	@Override
	public void run() {
		Random haphazard = new Random();
		if(!checkAll()) 
		{
			do{
				setCount1(0);
				int i = haphazard.nextInt(PlayerVsMachineFrame.getPics().length);
				for(Button_Cards m : getCards()){
					if(m.getId() == i && m.isMatched() == false){
						setSelectedCard(m);
						setCount1(getCount1() + 1);
						m.doClick();
						break;
					}
				}        
			}while(getCount1()==0);
		}	
		if(!checkAll()) 
		{
			do{
				setCount2(0);
				int j = haphazard.nextInt(PlayerVsMachineFrame.getPics().length);
				for(Button_Cards n : getCards()){
					if(n.getId() == j && n.isMatched() == false && n!=getSelectedCard()){
						setCount2(getCount2() + 1);
						n.doClick();
						break;
					}
				}

			}while(getCount2()==0);
		}
	}
}
