/*
NAME: Chang Sun
LOGIN: cs11sfk
ID: A53075169

NAME: Yaqing Wang
LOGIN: cs11sgi
ID: A53084051
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

/**
simple class Tetris, extend JFrame, implements ActionListener, ChangeListene
* @author Chang Sun, Yaqing Wang
* @version 6 June 2015
*/
public class Tetris extends JFrame implements ActionListener, ChangeListener
{
	public int rowComplete;	
	public boolean isGameStop = true;
	public static final int speedMin = 1;
        public static final int speedMax = 20;
        public static final int speedInit = 1;
	private GraphicsGrid graph;
	private ShapeMover mover;
	
	private JPanel northPanel;
	private JPanel northwestPanel;
	private JPanel northeastPanel;
	private JPanel southPanel;
	private JLabel scoreLabel;
	private JLabel highscoreLabel;
	private JLabel scoretxtLabel;
	private JLabel highscoretxtLabel;
	private JLabel gameoverLabel;
	private JButton newgameButton;
	private JButton resetButton;
	private JSlider speedSlider;
	private int score;
	private int highscore;
	private int tempscore;
	private TetrisGrid grid;
	private static final int PIXEL = 20;
	private static int pixel;
	/**
	 * Construct a JFrame for Tetris game
         */
	public Tetris()
	{
		
		super();
		grid = new TetrisGrid();
		graph = new GraphicsGrid(grid, pixel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	this.add(graph, BorderLayout.CENTER);
		
		northPanel = new JPanel();
		northwestPanel = new JPanel();
		northeastPanel = new JPanel();
		southPanel = new JPanel();
		scoretxtLabel = new JLabel("Score:");
		scoreLabel = new JLabel();
		highscoretxtLabel = new JLabel("High Score:");
		highscoreLabel = new JLabel();
		gameoverLabel = new JLabel();
		newgameButton = new JButton("New Game");
		resetButton = new JButton("Reset!");
		speedSlider = new JSlider(speedMin,speedMax,speedInit);

		scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
		highscoreLabel.setHorizontalAlignment(JLabel.RIGHT);		
		scoreLabel.setText(String.valueOf(score));
		highscoreLabel.setText(String.valueOf(highscore));
		

		northwestPanel.add(scoretxtLabel);
		northwestPanel.add(scoreLabel);
		northwestPanel.add(highscoretxtLabel);
                northwestPanel.add(highscoreLabel);
		northwestPanel.setLayout(new GridLayout(2,2));

		northeastPanel.add(gameoverLabel);

		northPanel.add(northwestPanel);
		northPanel.add(northeastPanel);
		northPanel.setLayout(new GridLayout(1,2));

		this.add(northPanel, BorderLayout.NORTH);
/*
		southPanel = new JPanel();
		newgameButton = new JButton("New Game");
                resetButton = new JButton("Reset!");
                speedSlider = new JSlider(speedMin,speedMax,speedInit);
*/

		southPanel.setFocusable(false);
		newgameButton.setFocusable(false);
		resetButton.setFocusable(false);
		speedSlider.setFocusable(false);

		newgameButton.addActionListener(this);
		resetButton.addActionListener(this);
		speedSlider.addChangeListener(this);
		//speedSlider.setPaintTicks(false);
		//speedSlider.setPaintLabels(false);

		southPanel.add(newgameButton);
		southPanel.add(resetButton);
		southPanel.add(speedSlider);
		
		this.add(southPanel, BorderLayout.SOUTH);

		this.pack();
    		this.validate();
    		this.setVisible(true);
		this.setFocusable(true);
		mover = new ShapeMover(this);
	    	this.addKeyListener(mover);
	    	new Thread(mover).start();


	}
	/** get graphicsgrid object
	 * @return graphicsgrid object
         */
	public GraphicsGrid getGraph()
	{
		return graph;
	}
	/** set score
	 * @param score tetrisgame score
         */
	public void setScore(int score)
	{
		this.score = score;
		scoreLabel.setText(String.valueOf(score));
	}
	/** set highest score
	 * @param highscore tetris game highest score
         */
	public void setHighscore(int highscore)
	{
		this.highscore= highscore;
		highscoreLabel.setText(String.valueOf(highscore));
	}
	/** set temporal score
	 * @param tempscore temporal score of tetris game
         */
	public void setTempScore(int tempscore)
	{
		this.tempscore = tempscore;
	}
	/** get score
	 * @return  score of tetris game
         */
	public int getScore()
	{
		return score;
	}
	/** get high score
	 * @return  high score of tetris game
         */
	public int getHighscore()
	{
		return highscore;
	}
	/** get temporal score
	 * @return  temporal score of game
         */ 
	public int getTempScore()
	{
		return tempscore;
	}
	/** add score to original score
	 * @param s  added score of tetris game
         */
	public void addScore(int s)
	{
		score += s;
		setScore(score);	
	}

	/** set gameoverlabe to display game over
	 * 
         */
	public void setGameover()
	{
		gameoverLabel.setText("GAME OVER");
	}
	/** set slider value
	 * @param speed  block's speed of moving down
         */
	public void setSlider(int speed)
	{
		speedSlider.setValue(speed);
	}
	

	/** change slider state 
	 * @param event changeEvent for speedSlider 
         */
	@Override
	public void stateChanged(ChangeEvent event)
	{
		
		int sliderVal = 0;
		String strSliderVal = "";
		JSlider sourceEvent = (JSlider) event.getSource();
			
		if(sourceEvent == speedSlider)
		{
			sliderVal = speedSlider.getValue();
			mover.setLevel(sliderVal - 1);
			setTempScore(score);
		}
		
	}
	/** actionperformed
	 * @param  event action event of button
         */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		
		JButton sourceEvent = (JButton) event.getSource();
		if(sourceEvent == newgameButton)
		{
			if (score > highscore)
			{
				highscore = score;
				highscoreLabel.setText(String.valueOf(highscore));
			}
			setScore(0);
			gameoverLabel.setText("");
			mover.setPause(false);
			grid.clear();
			grid.addNewShape();
			graph.repaint();
		}

		if(sourceEvent == resetButton)
		{
			setScore(0);
			setHighscore(0);
			highscoreLabel.setText(String.valueOf(highscore));
			scoreLabel.setText(String.valueOf(score));
			mover.setLevel(0);
			graph.repaint();
			gameoverLabel.setText("");
			mover.setPause(true);
			speedSlider.setValue(1);
		}
		
	}

		

	public static void main(String[] args)
	{
		try{
			if(args.length == 0){
				pixel = PIXEL;		
			}
			else if(args.length == 1){
				pixel = Integer.parseInt(args[0]);
			}else{
				throw new Exception("");
			}
			//detect exception
			if(pixel > 0);
			else{
				throw new Exception("");
			}
		}
		catch (Exception e){
                        System.out.println("Please input valide pixel");
			System.exit(0);
                }		


		Tetris tetris = new Tetris();
		try 
		{
			System.out.format("Hit Return to exit program");
			System.in.read();
		}
		catch (IOException e){}
		tetris.dispatchEvent(new WindowEvent(tetris, WindowEvent.WINDOW_CLOSING));
        tetris.dispose();	


	}
}
