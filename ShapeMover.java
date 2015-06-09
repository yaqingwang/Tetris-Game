/*
NAME: Chang Sun
LOGIN: cs11sfk
ID: A53075169

NAME: Yaqing Wang
LOGIN: cs11sgi
ID: A53084051
*/


import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
/** 
 * @author Chang Sun, Yaqing Wang
 * @version 6 June 2015
 *
 * A simple class that let block move and move left, right, rotate under user 
 * constrol which implements Runnable interface
 */
class ShapeMover implements Runnable, KeyListener
{
	private TetrisGrid grid;
	private GraphicsGrid graph;
	private Tetris tetris;
	private int score;
	private int preScore;
	private boolean pause;
	private int rowComplete;
	private int level;
	private int count;
	private final static int MILLISECONDS = 50;
	private final static int LEVELUP = 2000;
	private final static int MAXLEVEL = 20;
	private final static int BONUS = 10;
	/** Constructor
      *  @param tetris tetris instance
      */
	public ShapeMover(Tetris tetris)
	{
		this.tetris = tetris;
		this.graph = tetris.getGraph();
		this.grid = tetris.getGraph().getTetrisGrid();
	}

	/** Handle the key typed event from the text field. 
	  * @param e key type event
      */
 	@Override
    public void keyTyped(KeyEvent e) {}
    
    /** Handle the key pressed event from the text field. 
	  * @param e key pressed event
      */
    @Override    
	public synchronized void keyPressed(KeyEvent e)
	{
		
	 if(!pause && !grid.isGameStop())
		{ 
				
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_H: grid.moveLeft();
				tetris.addScore(BONUS);
				graph.repaint();
				break;
			case KeyEvent.VK_L: grid.moveRight();
				tetris.addScore(BONUS);
				graph.repaint();
				break;
			case KeyEvent.VK_J: grid.counterClock();
				tetris.addScore(BONUS);
				graph.repaint();
				break;
			case KeyEvent.VK_K: grid.clock();
				tetris.addScore(BONUS);
				graph.repaint();
				break;
			case KeyEvent.VK_SPACE: 
				while(!grid.isStop())
				{
					grid.moveDown();
					tetris.addScore(BONUS);
				}
				grid.placeShape();
				rowComplete = grid.getNumOfComplete();
				grid.clearCompleteRows();
				switch(rowComplete)
				{
				case 0: break;
				case 1: tetris.addScore(100);
						break;
				case 2: tetris.addScore(400);
						break;
				case 3: tetris.addScore(800);
						break;
				case 4: tetris.addScore(1600);
						break;
				}
				
				if(grid.isGameStop())
				{
					tetris.setGameover();
					if (tetris.getScore() > tetris.getHighscore()) {
							tetris.setHighscore(tetris.getScore());
					}
				}else{	
					
					graph.repaint();
					grid.addNewShape();
				}
				break;
		}	
	 }

	}
    
   /** Handle the key released event from the text field. 
	  * @param e key released event
      */
    	@Override
   	 public void keyReleased(KeyEvent e) {}
	
	/** creat a  thread and cause run to make block move down  
	 *   in a separately executing thread
      */
	public void run()
	{
		
		while(true)
		{
			graph.repaint();
			score = tetris.getScore();
		
			if(pause || grid.isGameStop()) continue;
			if(level < MAXLEVEL -1 && (score - tetris.getTempScore()) > 2000)	
			{
				tetris.setTempScore(score);
				level++;
			}

			count = MAXLEVEL -level;
		


			//System.out.println("count:"+count);
			//System.out.println("tempScore:" + tetris.getTempScore());
			//System.out.println("score:" + tetris.getScore());

			for(int i = 0; i < count; i++)
			{
				try { TimeUnit.MILLISECONDS.sleep(MILLISECONDS);}
                	catch (InterruptedException e){};

			}
			if(pause) continue;
		 				grid.moveDown();
						tetris.addScore(BONUS);
						//System.out.println(tetris.getScore()); 	 	
				
			if(grid.isStop())
			{
				grid.placeShape();
				rowComplete = grid.getNumOfComplete();

				switch(rowComplete){
				case 0: break;
				case 1: tetris.addScore(100);
						break;
				case 2: tetris.addScore(400);
						break;
				case 3: tetris.addScore(800);
						break;
				case 4: tetris.addScore(1600);
						break;
				}
			
				grid.clearCompleteRows();
				tetris.isGameStop = grid.isGameStop();
				grid.addNewShape();
				
			}	
			
			if(grid.isStop())
				System.out.println("stop");
			if(grid.isGameStop())
			{
				tetris.setGameover();
				if (tetris.getScore() > tetris.getHighscore()) 
				{
							tetris.setHighscore(tetris.getScore());
				}
			}	
        }
	}
	/** set pause state
	 * @param p  state of pause
     */
	public void setPause(boolean p)
	{
		pause = p;
	}
	/** set speed level
	 * @param l value of level
     */
	public void setLevel(int l)
	{
		level = l;
		count = MAXLEVEL -l;

	}

}	

// vim: ts=4:sw=4:tw=7
