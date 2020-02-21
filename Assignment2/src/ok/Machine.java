package ok;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author AhmedShemy
 */

/** 
 * The Machine class represents the general machine with its save in memory method
 * that will be used in different difficulties that will extend this class. 
 * 
 * @param count1 Integer to end the loop for opening the first card.
 * @param count2 Integer to end the loop for opening the second card.
 * @param selectedCard Button_Cards object indicating which card is selected(Used for the first opened card only).
 * @param cards ArrayList of Button_Cards representing the cards that will be used in the current game.
 */

public abstract class Machine implements Runnable{
	private int count1;
	private int count2;
	private Button_Cards selectedCard;
	private ArrayList<Button_Cards> cards;

	/**
	 * Constructor of the Machine class setting parameter cards as the given ArrayList<Button_Cards> representing the cards displayed.
	 * (ArrayList cards has already been explained in PlayingFrame class)
	 *
	 * @param cards holds the given ArrayList<Button_cards> when a machine object is created
	 */

	public Machine(ArrayList<Button_Cards> cards) {
		this.cards = cards;
	}

	/**
	 * Implements the run method of the Runnable Machine class.
	 * Loops till a random, non-matched card is found, sets it as the selectedCard and click on it.
	 * Loops again until a random, non-matched and different from selectedCard "first card", card is found, and click on it as well.
	 * Extra logic will be added to check memory before clicking on cards based on difficulty chosen.
	 */

	@Override
	public void run() {
		Random haphazard = new Random();
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

	/**
	 * Method checks if any "memorized" Button_cards are matched through id and they were selected and not matched and not the same Button_card. 
	 * If so, click on them.
	 * 
	 * @return true if there are cards to be matched stored in memory, false otherwise.
	 */

	public boolean checkAll(){ 
		for(Button_Cards m : getCards()){
			for(Button_Cards n : getCards()){
				if(m.getId()==n.getId() && m.WasSelected() && n.WasSelected() && m.isMatched()==false && n.isMatched()==false && m!=n )
				{
					m.doClick();
					n.doClick();
					return true;
				}
			}
		}
		return false;
	}

	//getters and setters for different variables
	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public ArrayList<Button_Cards> getCards() {
		return cards;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public Button_Cards getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(Button_Cards selectedCard) {
		this.selectedCard = selectedCard;
	}
}
