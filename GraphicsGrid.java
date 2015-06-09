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
 * A simple class that creates grid which extends JPanel. 
 * @author Chang Sun, Yaqing Wang
 * @version 6 June 2015
 */
class GraphicsGrid extends JPanel {
	private ArrayList<Point> fillCells;
	private final int width = 10; 
	private final int height = 20;
	private int pixel;
	private TetrisGrid tetrisGrid;
	
	/**
	 * Construct a GraphicsGrid instance with default pixel.
         * @param tetrisGrid A TetrisGrid object
         */
	public GraphicsGrid(TetrisGrid tetrisGrid) 
	{
		final int PIXEL = 20;
                this.pixel = PIXEL; 
                this.tetrisGrid = tetrisGrid;
                fillCells = new ArrayList<Point>();
     }
	
	/**
	 * Construct a GraphicsGrid instance with given pixel.
	 * @param tetrisGrid A TetrisGrid object
	 * @param p the pixel of each square box
	 */
	public GraphicsGrid(TetrisGrid tetrisGrid, int p) 
	{
		pixel = p;
		this.tetrisGrid = tetrisGrid;
		fillCells = new ArrayList<Point>();
	}
	
	/**
	 * Get the reference of TetrisGrid object.
	 * @return the reference of TetrisGrid object
	 */
	public TetrisGrid getTetrisGrid()
	{
		return tetrisGrid;
	}

	/** 
	 * Get the size of window
         * @return the value of dimension
         */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(width * pixel + 2, height * pixel + 2);
	}

	/** 
	 * Pass graphics object g to paint grid and blue square on it
         * @param g Graphics object 
         */
	@Override
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		final int OFFSET = width * pixel / 2;
		boolean [][] shapeOccupied = tetrisGrid.getOccupied();
                for(int i = 0; i < height; i++)
				{
                        for(int j = 0; j < width; j++)
						{
                                if(shapeOccupied[i][j])
								{
                                        fillCells.add(new Point(j ,i));
                                }
                        }
                }

		if (fillCells == null) return;
		for (Point fillCell : fillCells) 
		{
			int cellX = (fillCell.x * pixel) + OFFSET;
			int cellY = (fillCell.y * pixel)  ;
			g.setColor(Color.BLUE);
			g.fillRect(cellX, cellY, pixel, pixel);
		}
		g.setColor(Color.BLACK);
		g.drawRect(OFFSET, 0, width * pixel , height * pixel);

		for (int i = 0; i < width; i += 1) 
		{
			g.drawLine(i * pixel + OFFSET , 0, i * pixel+ OFFSET, height * pixel);
		}

		for (int i = 0; i < height; i += 1) 
		{
			g.drawLine(OFFSET, i * pixel, width * pixel + OFFSET, i * pixel);
		}
		clearCell();
	}
	
	/** 
	 * Add new point to arraylist and repaint it.
         * @param x xcoordinate of point
         * @param y ycoordinate of point
         */
	public void fillCell(int x, int y) 
	{
		fillCells.add(new Point(x, y));
		repaint();
	}
		
	/** 
	 * Remove point from arraylist and repaint it.
         */
	public void clearCell()
	{
		fillCells.clear();
		
	}

}

// vim: ts=4:sw=4:tw=7
