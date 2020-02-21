package ok;

public interface Playable {

	// A method for showing the card picture that is clicked
	void turnCard();

	// A method for checking if two cards are matched
	void check();

	//A method to make two cards on the grid exchange positions
	void exchange(Button_Cards openedCard1, Button_Cards openedCard2);

	//A method to check if the game is finished
	boolean isFinished();
}