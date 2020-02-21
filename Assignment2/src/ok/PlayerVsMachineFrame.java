package ok;

import javax.swing.JOptionPane;

/**
 * @author AhmedShemy
 */

/** 
 * The PlayerVsMachineFrame class represents the mode of 1 player playing against the computer extending
 * the PlayingFrame class.
 * 
 * @param threadComputer Thread used to create a Machine object whether it is EasyMachine, MediumMachine or HardMachine.
 * @param scoreComputer Integer used to keep track of the score of computer.
 * @param turnNumber Integer to keep track of number of turns played by MediumMachine for applying check memory (1 in 3).
 */

public class PlayerVsMachineFrame extends PlayingFrame {

	private static final long serialVersionUID = 1L;

	Thread threadComputer;
	private static int scoreComputer = 0;
	private static int turnNumber = 0;

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
	 *  displayed to the other player and checks if the game is finished or not.If so, it will load the high score, 
	 *  checks if the player equalized or exceeded high score and shows a message that game has ended with final scores 
	 *  and that the player has achieved a high score if it occurred. Else if not finished and it is computer's turn, 
	 *  it will start the thread computer with the corresponding difficulty chosen by the user in the start panel 
	 *  and increases TurnNumber in case of MediumMachine as it will be used in its logic.
	 *   
	 *  Else, sets the button_cards to the cardBack icon, decrement the score of the player that got mismatch by -1,
	 *  change the turn displayed to the other player, change the score displayed to the other player. 
	 *  If one of the cards that was not matched is chocolate , the cards id and so positions will be exchanged 
	 *  applying the exchange method on the two opened cards. If it is computer's turn, 
	 *  it will start the thread computer with the corresponding difficulty chosen by the user in the start panel.
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

			if(EastPanel.getTurnField().getText().contentEquals("Computer"))
			{
				setScoreComputer(getScoreComputer() + ((StartFrame.calculateGrid() - getCardsMatched())/ 2 * 1));
			}
			else if (EastPanel.getTurnField().getText().contentEquals("Player 1"))
			{
				setScorePlayer1(getScorePlayer1() + ((StartFrame.calculateGrid() - getCardsMatched())/ 2 * 2));
			}

			setCardsMatched(getCardsMatched() + 2);
			EastPanel.setTurnField();
			EastPanel.setScoreField();

			if (this.isFinished() == false) {
				if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb1.isSelected())
				{
					threadComputer = new Thread(new EasyMachine(getCards()));
					threadComputer.start();
				}
				else if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb2.isSelected())
				{
					setTurnNumber(getTurnNumber() + 1);
					threadComputer = new Thread(new MediumMachine(getCards()));
					threadComputer.start();
				}

				else if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb3.isSelected())
				{
					threadComputer = new Thread(new HardMachine(getCards()));
					threadComputer.start();
				}
			}
			else{
				HighScores.loadHighScore();
				if(EastPanel.getTurnField().getText().contentEquals("Computer")) {
					EastPanel.setTurnField();
				}
				if(getScorePlayer1() >= HighScores.getHighScore()) 
				{
					JOptionPane.showMessageDialog(this, "Great! Player 1 got a new highscore.");

				}
				JOptionPane.showMessageDialog(this, "Great! Game is over with score: "+ getScorePlayer1() + " for player 1 and " + getScoreComputer() + " for computer." );
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

			if(EastPanel.getTurnField().getText().contentEquals("Computer")) 
			{
				setScoreComputer(getScoreComputer() - 1);
			}
			else if (EastPanel.getTurnField().getText().contentEquals("Player 1"))
			{
				setScorePlayer1(getScorePlayer1() - 1);
			}

			EastPanel.setTurnField();
			EastPanel.setScoreField();

			if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb1.isSelected())
			{
				threadComputer = new Thread(new EasyMachine(getCards()));
				threadComputer.start();
			}
			if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb2.isSelected())
			{
				setTurnNumber(getTurnNumber() + 1);
				threadComputer = new Thread(new MediumMachine(getCards()));
				threadComputer.start();
			}
			if(EastPanel.getTurnField().getText().contentEquals("Computer") && StartFrame.rb3.isSelected())
			{
				threadComputer = new Thread(new HardMachine(getCards()));
				threadComputer.start();
			}
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

	//getter and setter for ScoreComputer and TurnNumber
	public static int getScoreComputer() {
		return scoreComputer;
	}

	public void setScoreComputer(int scoreComputer) {
		PlayerVsMachineFrame.scoreComputer = scoreComputer;
	}

	public static int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int d) {
		PlayerVsMachineFrame.turnNumber = d;
	}
}
