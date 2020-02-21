package ok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * @author AhmedShemy
 */

/** 
 * The PlayingFrame represents the general playing frame implementing Playable interface that will be extended by 
 * the two game modes with all its features.
 *
 * @param selectedCard Button_Cards object indicating which card is selected.
 * @param openedCard1 Button_Cards object which holds the first opened card every player's turn.
 * @param openedCard2 Button_Cards object which holds the second opened card every player's turn.
 * @param timer timer will delay the start of each turn and check if flipped cards matches or not.
 * @param cards ArrayList of Button_Cards representing the cards that will be used in the current game.
 * @param hashMap HashMap of Integers and Strings to assign each string referring to image path to a unique id.
 * @param idList ArrayList of Integers representing the ids of the cards that will be used in the current game.
 * @param cardBack ImageIcon representing the back of all my cards.
 * @param pics String Array holding the image paths of the al the images that will be used as cards.
 * @param scorePlayer1 Integer used to keep track of the score of Player 1 in any playing mode.
 * @param cardsMatched Integer used to keep track of the number of cards matched to be used in Score calculation formula.
 * @param boardPanel Jpanel used to hold the Button_Cards grid.
 */

public abstract class PlayingFrame extends JFrame implements Playable{

	private static final long serialVersionUID = 1L;

	private Button_Cards selectedCard;
	private Button_Cards openedCard1;
	private Button_Cards openedCard2;
	private Timer timer;
	private static ArrayList<Button_Cards> cards;
	private HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
	private ArrayList<Integer> idList;
	private ImageIcon cardBack = new ImageIcon(getClass().getResource("/MemoryBoardGamePhotos/fruits.jpg"));
	private static String[] pics = {"/MemoryBoardGamePhotos/apple.jpeg", "/MemoryBoardGamePhotos/pear.jpeg",
			"/MemoryBoardGamePhotos/banana.jpg","/MemoryBoardGamePhotos/dragonfruit.jpg",
			"/MemoryBoardGamePhotos/mango.jpeg","/MemoryBoardGamePhotos/grapes.jpg",
			"/MemoryBoardGamePhotos/peach.jpg","/MemoryBoardGamePhotos/strawberry.jpg", 
			"/MemoryBoardGamePhotos/orange.jpg", "/MemoryBoardGamePhotos/Chocolate.jpg"};
	private static int scorePlayer1 = 0;
	private int cardsMatched = 0;
	JPanel boardPanel = new JPanel();

	/**
	 * Constructor. Initializes cards and idList Lists and the hash map, add desired number of 
	 * Button_cards to the grid and assigning random id from the idList to every Button_card.
	 * and creates the frame for the two game modes combining the board panel and the east panel.
	 */

	public PlayingFrame() {
		setTitle("Shemy Memory Game");
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1200,1200));
		setLayout(new BorderLayout(0,0));

		boardPanel.setBorder(new EmptyBorder(0,0,0,0));
		boardPanel.setLayout(new GridLayout((Integer)StartFrame.spinnerOne.getValue(),(Integer)StartFrame.spinnerOne.getValue(),0,0));
		boardPanel.setBackground(Color.BLACK);
		add(boardPanel, BorderLayout.CENTER);
		boardPanel.setVisible(true);

		cards = new ArrayList<Button_Cards>();
		idList = new ArrayList<Integer>();

		for(int i = 0 ; i<pics.length; i++){
			hashMap.put(i, pics[i]); 
		}

		int k = 0;
		int l = 0;
		while (k < (StartFrame.calculateGrid() / 2)){
			k++;
			idList.add(l);
			idList.add(l);
			l++;
			if(l == pics.length){
				l = 0;
			}
		}

		Collections.shuffle(idList);

		for (int i = 0; i < StartFrame.calculateGrid(); i++){
			Button_Cards back = new Button_Cards();
			back.setId(idList.get(i));
			back.setIcon(cardBack);
			back.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					selectedCard = back;
					turnCard();
					back.setWasSelected();
				}
			});
			cards.add(back);
		}

		timer = new Timer(600, new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				check();
			}
		});

		timer.setRepeats(false);

		for (Button_Cards i : cards)
		{
			boardPanel.add(i);
		}

		EastPanel eastPanel = new EastPanel();
		add(eastPanel, BorderLayout.EAST);
		eastPanel.setVisible(true);
		pack();
		setVisible(true);
	}

	/**
	 * Method used to flip a card when it is clicked setting openedCard1 as the selectedCard when clicked and get the corresponding string 
	 * from the hash map using its id that will fetch the image from its path. Does the same for openedCard2 when a second card is selected 
	 * then starts the timer.
	 */

	@Override
	public void turnCard()
	{
		if (openedCard1 == null && openedCard2 == null)
		{
			openedCard1 = selectedCard;
			ImageIcon card1 = new ImageIcon(getClass().getResource(hashMap.get(openedCard1.getId())));
			Image card1Image = card1.getImage();
			Image newCard1Image = card1Image.getScaledInstance(openedCard1.getWidth(), openedCard1.getHeight(),java.awt.Image.SCALE_SMOOTH);
			ImageIcon card1_1 = new ImageIcon(newCard1Image);
			openedCard1.setIcon(card1_1);
		}
		if (openedCard1 != null && openedCard1 != selectedCard && openedCard2 == null)
		{
			openedCard2 = selectedCard;
			ImageIcon card2 = new ImageIcon(getClass().getResource(hashMap.get(openedCard2.getId())));
			Image card2Image = card2.getImage();
			Image newCard2Image = card2Image.getScaledInstance(openedCard1.getWidth(), openedCard1.getHeight(),java.awt.Image.SCALE_SMOOTH);
			ImageIcon card2_2 = new ImageIcon(newCard2Image);
			openedCard2.setIcon(card2_2);
			timer.start();
		}
	}

	/**
	 * Method to check if the two opened cards are matched or not and apply many actions based on this in different game modes.
	 * A more explicit elaboration is written for each check method in each mode.
	 */

	@Override
	public abstract void check();

	/**
	 * Method used to exchange Button_cards ids so changing their position on the grid.
	 * It will be used to make a special effect for the chocolate button card when it is not matched.
	 * 
	 * @param openedCard1 as explained before
	 * @param openedCard2 as explained before
	 */

	@Override
	public void exchange(Button_Cards openedCard1, Button_Cards openedCard2) {
		int i = openedCard1.getId();
		openedCard1.setId(openedCard2.getId());
		openedCard2.setId(i);
	}

	/**
	 * Checking whether the game is finished or not. If all Button_Cards are "matched", the game is finished.
	 * 
	 * @return true if every Button_Card is matched, false otherwise.
	 */

	@Override
	public boolean isFinished(){
		for(Button_Cards m: getCards())
		{
			if (m.isMatched() == false)
			{
				return false;
			}
		}
		return true;
	}

	//getters and setters for different variables 
	public static String[] getPics() {
		return pics;
	}
	public static int getScorePlayer1() {
		return scorePlayer1;
	}
	public void setScorePlayer1(int score) {
		PlayingFrame.scorePlayer1 = score;
	}

	public Button_Cards getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(Button_Cards selectedCard) {
		this.selectedCard = selectedCard;
	}

	public Button_Cards getOpenedCard1() {
		return openedCard1;
	}

	public void setOpenedCard1(Button_Cards openedCard1) {
		this.openedCard1 = openedCard1;
	}

	public Button_Cards getOpenedCard2() {
		return openedCard2;
	}

	public void setOpenedCard2(Button_Cards openedCard2) {
		this.openedCard2 = openedCard2;
	}

	public static ArrayList<Button_Cards> getCards() {
		return cards;
	}

	public ImageIcon getCardBack() {
		return cardBack;
	}

	public int getCardsMatched() {
		return cardsMatched;
	}

	public void setCardsMatched(int cardsMatched) {
		this.cardsMatched = cardsMatched;
	}

}
