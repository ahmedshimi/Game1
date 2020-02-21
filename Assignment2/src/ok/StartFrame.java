package ok;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;

/**
 * @author AhmedShemy
 */

/**
 * The StartFrame class is the Frame appearing when the game is started allowing the user access to rules and high scores. 
 * It also allows the user to specify grid dimensions, choose difficulty, whether to play against another human or computer.
 * Access to exit the game or start it after choosing the desired settings is accessible as well.
 * 
 * @param difficultyGroup ButtonGroup holding three radio buttons of difficulty.
 * @param opponentsGroup ButtonGroup holding two radio buttons of choosing opponent.
 * @param rules JButton used to access rules frame.
 * @param highScores JButton used to access high scores sheet frame.
 * @param start JButton used to start the game.
 * @param exit JButton used to exit the game.
 * @param spinnerOne JSpinner used to choose the number of rows.
 * @param spinnerTwo JSpinner used to choose the number of columns.
 * @param rb1 JRadioButton used to select easy as a difficulty.
 * @param rb2 JRadioButton used to select medium as a difficulty.
 * @param rb3 JRadioButton used to select hard as a difficulty.
 * @param rb4 JRadioButton used to select another human as opponent.
 * @param rb5 JRadioButton used to select computer as opponent.
 * @param model1 SpinnerModel used as a model for rows JSpinner(spinnerOne) of easy difficulty.
 * @param model2 SpinnerModel used as a model for columns JSpinner(spinnerTwo) of easy difficulty.
 * @param model3 SpinnerModel used as a model for rows JSpinner(spinnerOne) of medium difficulty.
 * @param model4 SpinnerModel used as a model for columns JSpinner(spinnerTwo) of medium difficulty.
 * @param model5 SpinnerModel used as a model for rows JSpinner(spinnerOne) of hard difficulty.
 * @param model6 SpinnerModel used as a model for columns JSpinner(spinnerTwo) of hard difficulty.
 * @param rowsLabel JLabel for the string "Rows: "
 * @param columnsLabel JLabel for the string "Columns: "
 * @param difficultyLabel JLabel for the string "Difficulty: "
 * @param opponentLabel JLabel for the string "Select your opponent: "
 * @param p1 JPanel used for holding the rules button.
 * @param p2 JPanel used for holding the difficultyLabel and the 3 radio buttons for difficulty(rb1,rb2,rb3).
 * @param p3 JPanel used for holding the opponentLabel and the 2 radio buttons for opponents(rb4,rb5).
 * @param p4 JPanel used for holding the rowsLabel, columnsLabel and their spinners(spinnerOne, spinnerTwo).
 * @param p5 JPanel used for holding the highScores button, start button and exit button.
 */

public class StartFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	ButtonGroup difficultyGroup = new ButtonGroup();
	ButtonGroup opponentsGroup = new ButtonGroup();

	JButton rules = new JButton ("Rules Code");
	JButton highScores = new JButton ("High Scores");
	JButton start = new JButton ("Start");
	JButton exit = new JButton ("Exit");

	static JSpinner spinnerOne = new JSpinner();
	static JSpinner spinnerTwo = new JSpinner();

	static JRadioButton rb1 = new JRadioButton("Easy");
	static JRadioButton rb2 = new JRadioButton("Medium");
	static JRadioButton rb3 = new JRadioButton("Hard");
	static JRadioButton rb4 = new JRadioButton("Another Player");
	static JRadioButton rb5 = new JRadioButton("Computer");

	static SpinnerModel model1 = new SpinnerNumberModel(2,1,8,1);
	static SpinnerModel model2 = new SpinnerNumberModel(2,1,8,1);
	static SpinnerModel model3 = new SpinnerNumberModel(4,2,8,1);
	static SpinnerModel model4 = new SpinnerNumberModel(4,4,8,1);
	static SpinnerModel model5 = new SpinnerNumberModel(4,4,8,1);
	static SpinnerModel model6 = new SpinnerNumberModel(4,4,8,1);

	JLabel rowsLabel = new JLabel("Rows: ", JLabel.RIGHT);
	JLabel columnsLabel = new JLabel("Columns: ", JLabel.RIGHT);
	JLabel difficultyLabel = new JLabel("Difficulty: ", JLabel.RIGHT);
	JLabel opponentLabel = new JLabel("Select your opponent: ", JLabel.RIGHT);

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();

	/**
	 * Constructor. Adding every item to the designated panel and then adding the five panels to the StartFrame.
	 */

	public StartFrame(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout (5,1));

		p1.add(rules);
		rules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createRulesFrame();
			}
		});
		p1.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		add(p1);

		p2.setLayout(new GridLayout (1,4));
		p2.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		difficultyGroup.add(rb1);
		difficultyGroup.add(rb2);
		difficultyGroup.add(rb3);
		p2.add(difficultyLabel);
		p2.add(rb1);
		p2.add(rb2);
		p2.add(rb3);
		add(p2);

		p3.setLayout(new GridLayout (1,3));
		p3.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		opponentsGroup.add(rb4);
		opponentsGroup.add(rb5);
		p3.add(opponentLabel);
		p3.add(rb4);
		p3.add(rb5);
		add(p3);

		p4.add(rowsLabel);
		p4.add(spinnerOne);
		p4.add(columnsLabel);
		p4.add(spinnerTwo);
		p4.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		p4.setLayout(new GridLayout (1,4));
		makeModel();
		add(p4);

		p5.add(highScores);
		highScores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createHighScoresFrame();
			}
		});
		p5.add(start);
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startGame();
			}
		});
		p5.add(exit);
		exit.addActionListener(e -> this.dispose());
		p5.setLayout(new GridLayout (1,3));
		p5.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		add(p5);
		pack();
		setVisible(true);
	}

	/**
	 * Method used to create high scores frame by reading the high scores file and displaying its numbers in a frame.
	 * When HighScores button is clicked, it will be displayed.
	 */

	public void createHighScoresFrame(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new JFrame();
				frame.setLayout(new GridLayout(0,1));
				JLabel highScoresHeader = new JLabel("HighScores: ");
				highScoresHeader.setHorizontalAlignment(JLabel.LEFT);
				highScoresHeader.setVerticalAlignment(JLabel.NORTH);
				frame.add(highScoresHeader);
				highScoresHeader.setBounds(10,10, 10,10);
				try {
					BufferedReader in = new BufferedReader(new FileReader(HighScores.getFile()));
					String line;
					while ((line = in.readLine()) != null) {
						JButton scoreField = new JButton(line);
						scoreField.setHorizontalAlignment(JLabel.CENTER);
						scoreField.setBounds(100,100, 100,100);
						frame.add(scoreField);
					}
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(new Dimension(500, 500));
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Method used to create rules frame. When rules button is clicked, it will be displayed.
	 */

	public static void createRulesFrame(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new JFrame();
				frame.setLayout(new GridLayout(0,1));
				JLabel rulesLab = new JLabel("Rules:");
				rulesLab.setHorizontalAlignment(JLabel.CENTER);
				frame.add(rulesLab);
				rulesLab.setBounds(50,100, 100,30);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JLabel rule1 = new JLabel("1- Flip two cards of the same fruit or chocolate to get a match.");
				rule1.setHorizontalAlignment(JLabel.CENTER);
				rule1.setBounds(50,100, 100,30);
				frame.add(rule1);
				JLabel rule2 = new JLabel("2- Remember what was on each card you saw and where it was.");
				rule2.setHorizontalAlignment(JLabel.CENTER);
				rule2.setBounds(50,100, 100,30);
				frame.add(rule2);
				JLabel rule3 = new JLabel("3- Use the lowest number of flips as possible.");
				rule3.setHorizontalAlignment(JLabel.CENTER);
				rule3.setBounds(50,100, 100,30);
				frame.add(rule3);
				JLabel rule4 = new JLabel("4- Watch and remember during the other player's turn.");
				rule4.setHorizontalAlignment(JLabel.CENTER);
				rule4.setBounds(50,100, 100,30);
				frame.add(rule4);
				JLabel rule5 = new JLabel("5- Enjoy!");
				rule5.setHorizontalAlignment(JLabel.CENTER);
				rule5.setBounds(50,100, 100,30);
				frame.add(rule5);
				frame.setSize(new Dimension(500, 500));
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Method calculating the number of Button_cards needed depending on the user input for the grid dimensions.
	 * 
	 * @return grid Integer representing the number of Button_cards to be created.
	 */

	public static int calculateGrid() {
		int grid = (Integer)spinnerOne.getValue() * (Integer)spinnerTwo.getValue();
		return grid;
	}

	/**
	 * Method to set the model of each spinner depending on the difficulty chosen by the user.
	 */

	public static void makeModel(){
		rb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerOne.setModel(model1);
				spinnerTwo.setModel(model2);
			}
		});

		rb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerOne.setModel(model3);
				spinnerTwo.setModel(model4);
			}
		});

		rb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerOne.setModel(model5);
				spinnerTwo.setModel(model6);
			}
		});
	}

	/**
	 * Method to start the game according to the user's input when start button is clicked.
	 */

	public void startGame(){
		if(rb1.isSelected() | rb2.isSelected() | rb3.isSelected()) {
			if(rb4.isSelected() | rb5.isSelected()){
				if (calculateGrid() %2 == 0){
					//MultiPlayerFrame
					if(rb4.isSelected()) {                
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								try {
									new MultiPlayerFrame();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}

					//PlayerVsMachineFrame
					if(rb5.isSelected()) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								try {
									new PlayerVsMachineFrame();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}else {
					JOptionPane.showMessageDialog(null, "Enter Rows and Columns that lead to an even number for the grid!");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Choose opponent!");	
			}
		}else {
			JOptionPane.showMessageDialog(null, "Choose difficulty!");	
		}
	}

	/**
	 * Main Method to run the game so the starting panel will appear.
	 */

	public static void main (String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					new StartFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
