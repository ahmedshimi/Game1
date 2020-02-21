package ok;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author AhmedShemy
 */

/** 
 * The MediumMachine class extends the Machine class and will perform clicks on cards displayed randomly. 
 * However, it checks the memory in one of each 3 turns played by it before clicking. This class implements Runnable to be called via thread when needed.
 */

public class MediumMachine extends Machine{

	/**
	 * Constructor of the MediumMachine class setting parameter cards as the given ArrayList<Button_Cards> representing the cards displayed.
	 * (ArrayList cards has already been explained in PlayingFrame class)
	 *
	 * @param cards holds the given ArrayList<Button_cards> when a MediumMachine object is created
	 */

	public MediumMachine(ArrayList<Button_Cards> cards) {
		super(cards);
	}

	/**
	 * Implements the run method of the Runnable Machine class.
	 * check if this the turnNumber is divisible by 3 (1 in 3). If so, checks memory and do clicks if there is a match stored.
	 * Else, Loops till a random, non-matched card is found, sets it as the selectedCard and click on it.
	 * Loops again until a random, non-matched and different from selectedCard "first card", card is found, and click on it as well.
	 */

	@Override
	public void run() {
		Random haphazard = new Random();
		if(PlayerVsMachineFrame.getTurnNumber()%3 == 0) {
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
		}else{

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
