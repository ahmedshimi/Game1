package ok;

import javax.swing.JOptionPane;

/**
 * @author AhmedShemy
 */

/** 
 * The MultiPlayerFrame class represents the mode of 1 human player playing against another human player
 * extending the PlayingFrame class.
 * 
 * @param scorePlayer2 Integer used to keep track of the score of Player 2.
 */

public class MultiPlayerFrame extends PlayingFrame{

	private static final long serialVersionUID = 1L;

	private static int scorePlayer2 = 0;

	@Override
	public void turnCard(){
		super.turnCard();
	}

	/**
	 *  Checks if two button_cards are matched. 
	 *
	 *  If so, disables them and set them to matched, increments the score of the player that got the match 
	 *  using its score formula and give him extra turn if the matched cards were mango "special effect", 
	 *  increases the number of matched cards by 2, change the turn displayed to the other player, change the score 
	 *  displayed to the other player and checks if the game is finished or not. If so, it will load the high score, 
	 *  checks if one of the players equalized or exceeded high score and shows a message that game has ended with final scores 
	 *  and that a certain player has achieved a high score if it occurred.
	 *   
	 *  Else, sets the button_cards to the cardBack icon, decrement the score of the player that got mismatch by -1,
	 *  change the turn displayed to the other player, change the score displayed to the other player. 
	 *  If one of the cards that was not matched is chocolate , the cards id and so positions will be exchanged 
	 *  applying the exchange method on the two opened cards.
	 *  
	 *  Then finally, it will set the openedCard1 & openedCard2 to null again.
	 */

	@Override
	public void check()
	{
		if (getOpenedCard1().getId() == getOpenedCard2().getId())
		{
			if (getOpenedCard2().getId() == 4) {
				EastPanel.setTurnField();
				JOptionPane.showMessageDialog(this, "Extra Turn for Mangos");
			}
			getOpenedCard1().setEnabled(false); 
			getOpenedCard2().setEnabled(false);

			getOpenedCard1().setMatched(true);
			getOpenedCard2().setMatched(true);

			if(EastPanel.getTurnField().getText().contentEquals("Player 2")) 
			{
				setScorePlayer2(getScorePlayer2() + ((StartFrame.calculateGrid() - getCardsMatched())/ 2 * 3));
			}
			else if (EastPanel.getTurnField().getText().contentEquals("Player 1"))
			{
				setScorePlayer1(getScorePlayer1() + ((StartFrame.calculateGrid() - getCardsMatched())/ 1 * 2));
			}

			setCardsMatched(getCardsMatched() + 2);
			EastPanel.setTurnField();
			EastPanel.setScoreField();

			if (this.isFinished())
			{
				HighScores.loadHighScore();
				if(getScorePlayer1() >= HighScores.getHighScore()) 
				{
					JOptionPane.showMessageDialog(this, "Great! Player 1 got a new highscore.");

				}
				if(getScorePlayer2() >= HighScores.getHighScore())
				{
					JOptionPane.showMessageDialog(this, "Great! Player 2 got a new highscore.");

				}
				JOptionPane.showMessageDialog(this, "Great! Game is over with score: "+ getScorePlayer1() + " for player 1 and " + getScorePlayer2() + " for Player 2.");
			}  
		}

		else{
			getOpenedCard1().setIcon(getCardBack());
			getOpenedCard2().setIcon(getCardBack());

			if (getOpenedCard1().getId() == 9 |getOpenedCard2().getId() == 9) {
				exchange(getOpenedCard1(), getOpenedCard2());
				JOptionPane.showMessageDialog(this, "Chocolate is not a friend of fruits!"
						+ " Cards positions has been exchanged.");
			}

			if(EastPanel.getTurnField().getText().contentEquals("Player 2")) 
			{
				setScorePlayer2(getScorePlayer2() - 1);
			}
			else if (EastPanel.getTurnField().getText().contentEquals("Player 1"))
			{
				setScorePlayer1(getScorePlayer1() - 1);
			}

			EastPanel.setTurnField();
			EastPanel.setScoreField();

		}
		setOpenedCard1(null);
		setOpenedCard2(null);
	}

	@Override
	public void exchange(Button_Cards openedCard1, Button_Cards openedCard2) {
		super.exchange(openedCard1, openedCard2);
	}

	@Override
	public boolean isFinished(){
		return super.isFinished();
	}

	//getter and setter for ScorePlayer2
	public static int getScorePlayer2() {
		return scorePlayer2;
	}

	public static void setScorePlayer2(int scorePlayer2) {
		MultiPlayerFrame.scorePlayer2 = scorePlayer2;
	}

}
