package ok;

import java.awt.Color;
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
 * The EastPanel class is the panel appearing on the right side of the playing frame displaying whose turn, 
 * score of the player, access to high scores sheet and an exit button.
 * 
 * @param scoreLabel JLabel for the string "Score: ".
 * @param scoreField JTextField used for displaying player's score that has the turn.
 * @param turnLabel JLabel for the string "Player Turn".
 * @param turnField JTextField used for displaying which player's turn.
 * @param highScores JButton used to access high scores sheet frame.
 * @param exit JButton used to exit the game.
 * @param p1 JPanel used for holding the scoreLabel and scoreField.
 * @param p2 JPanel used for holding the turnLabel and turnField.
 * @param p3 JPanel used for holding the high scores button
 * @param p4 JPanel used for holding the exit button
 */

public class EastPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	JLabel scoreLabel = new JLabel("Score: ");	
	static JTextField scoreField = new JTextField ("");

	JLabel turnLabel = new JLabel("Player Turn");
	static JTextField turnField = new JTextField ("Player 1");

	JButton highScores = new JButton("High Scores");
	JButton exit = new JButton ("Exit");

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();

	/**
	 * Constructor. Adding every item to the designated panel and then adding the four panels to the EastPanel.
	 */

	public EastPanel() {
		p1.setLayout(new GridLayout(2,1));
		p1.setBackground(Color.BLACK);
		p1.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		scoreLabel.setForeground(Color.WHITE);
		p1.add(scoreLabel);
		p1.add(scoreField);

		p2.add(turnLabel);
		turnLabel.setForeground(Color.WHITE);
		p2.add(turnField);
		p2.setBackground(Color.BLACK);
		p2.setLayout(new GridLayout (2,1));
		p2.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

		p3.add(highScores);
		p3.setBackground(Color.BLACK);
		p3.setLayout(new GridLayout (1,1));
		p3.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
		highScores.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createHighScoresFrame();
			}
		});

		p4.add(exit);
		p4.setBackground(Color.BLACK);
		exit.addActionListener(e -> System.exit(0));
		p4.setLayout(new GridLayout (1,1));
		p4.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

		add(p1);
		add(p2);
		add(p3);
		add(p4);
		setLayout(new GridLayout(0,1));
		setBackground(Color.BLACK);
		setVisible(true);
	}


	/**
	 * Method used to set the score field to the player that holds the turn now.
	 */

	public static void setScoreField() {
		if(StartFrame.rb5.isSelected()) 
		{
			if(turnField.getText().equals("Player 1"))
			{
				scoreField.setText(Integer.toString(PlayerVsMachineFrame.getScorePlayer1()));
			}else if(turnField.getText().equals("Computer"))
			{
				scoreField.setText(Integer.toString(PlayerVsMachineFrame.getScoreComputer()));
			}
		}

		if(StartFrame.rb4.isSelected()) 
		{
			if(turnField.getText().equals("Player 1"))
			{
				scoreField.setText(Integer.toString(MultiPlayerFrame.getScorePlayer1()));

			}else if(turnField.getText().equals("Player 2"))
			{
				scoreField.setText(Integer.toString(MultiPlayerFrame.getScorePlayer2()));
			}
		}
	}

	public static JTextField getTurnField() {
		return turnField;
	}

	/**
	 * Method used to set the turn field to the player that holds the turn now.
	 */

	public static void setTurnField(){
		if(StartFrame.rb5.isSelected())
		{
			if(turnField.getText().equals("Player 1"))
			{
				turnField.setText("Computer");

			}else if(turnField.getText().equals("Computer"))
			{
				turnField.setText("Player 1");
			}
		}

		if(StartFrame.rb4.isSelected()) 
		{
			if(turnField.getText().equals("Player 1"))
			{
				turnField.setText("Player 2");

			}else if(turnField.getText().equals("Player 2"))
			{
				turnField.setText("Player 1");
			}
		}
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

}

