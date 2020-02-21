package ok;

import javax.swing.JButton;

/**
 * @author AhmedShemy
 */

/** 
 * The Button_cards class implements all cards used during the game as buttons.
 * Each card used in the game is an object of this class extending JButton.
 *
 * @param id Integer used to indicate the icon Id assigned to this card.
 * @param wasSelected Boolean that stays false unless the card is flipped to be used for the memory creation of the Machine class.
 * @param isMatched Boolean that stays false unless 2 cards are matched then they are both set to be true.
 */

public class Button_Cards extends JButton{

	private static final long serialVersionUID = 1L;
	private int id;
	private boolean wasSelected = false;
	private boolean isMatched = false;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean WasSelected() {
		return wasSelected;
	}
	public void setWasSelected() {
		wasSelected = true;
	}
	public boolean isMatched() {
		return isMatched;
	}
	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

}
