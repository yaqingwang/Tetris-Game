/*
NAME: Chang Sun
LOGIN: cs11sfk
ID: A53075169

NAME: Yaqing Wang
LOGIN: cs11sgi
ID: A53084051
*/


/** define a 2D array of chars as a way to make ascii art.
 * can place and clear an arbitrary 2D array of chars in the grid
 * if asked-for array fits.
 * @author Chang Sun, Yaqing Wang
 * @version 6 June 2015
 */
public class TetrisGrid
{
	private char [][] grid;
	private static final char block ='#';  
	private static final char NEWLINE = '\n';
	private static final char space = '.';
	private static final int WIDTH = 10;
	private static final int HEIGHT = 20; 
	private static final int INITIAL_ROW = 0;
	private static final int INITIAL_COLUMN = 5;
	private static final Coord  LEFT = new Coord(-1, 0);
	private static final Coord RIGHT = new Coord(1, 0);
	private static final Coord  DOWN = new Coord(0, 1); 

	private int rows;
	private int cols;
	private int numOfComplete;
	private int currRow;
	private int currCol;
	private boolean [][] shapeOccupied;
	private boolean [] completeRows = new boolean[HEIGHT];;
	private TetrisShape tetrisShape;
	private Coord[] shape;

	/** Constructor 
	 */
	public TetrisGrid()
	{
		
		grid = new char[HEIGHT][WIDTH];
		shapeOccupied = new boolean[HEIGHT][WIDTH];
		this.rows = HEIGHT;
		this.cols = WIDTH;
		int i;
		int j;
		for(i = 0; i < rows; i++)
		{
			for(j = 0; j < cols; j++)
			{
				grid[i][j] = space;
				shapeOccupied[i][j] = false; 
			}
		}
		currRow = INITIAL_ROW;
		currCol = INITIAL_COLUMN;
		tetrisShape = new TetrisShape();
		shape = tetrisShape.getCoordArray();
	}

	/**
	 * Get the reference of the shape. 
	 * @return reference of the shape
	 */
	public Coord[] getShape()
	{

		return shape;
	}

	/**
	 * Judge if the move of shape need to be stopped.
	 * @return true if the Teris shape would interact with other block after downstairs
     	 */
	public boolean isStop()
	{
		
		for(int i = 0; i < shape.length; i++)
		{
			int x = shape[i].r + currRow + 1;
			int y = shape[i].c + currCol;
			if(x < 0 ) continue;
			if(x >= rows) return true;
			if(shapeOccupied[x][y]) return true;
		}
		
		return false;	
	}	

	/**
	 * Judge if the shape can be valid in the grid.
	 * @return true if the shpe can be valid in the grid.
	 */
	public boolean isValid()
	{
		if(!isEmpty(shape) || !isFit(shape)) return false;
		return true;

	}

	/**
	 * Move the shape to the left.
	 */
	public void moveLeft()
	{
		currCol--;
		if(!isEmpty(shape) || !isFit(shape)) currCol++;
	}
	
	/**
	 * Move the shape to the right.
	 */
        public void moveRight()
	{
		currCol++;
		if(!isEmpty(shape) || !isFit(shape)) currCol--;
	}

	/**
	 * Move the shape downward.
	 */
	public void moveDown()
	{
		currRow++;
	}

	/**
	 * Rotate the shape clockwise.
	 */
	public void clock()
	{
		tetrisShape.rotateClock();
		shape = tetrisShape.getCoordArray();
		if(!isEmpty(shape) || !isFit(shape))
		{
			tetrisShape.reverseClock();
			shape = tetrisShape.getCoordArray();
		}
	}

	/**
	 * Rotate the shape counterclockwise.
	 */
	public void counterClock()
	{
		tetrisShape.rotateCounterclock();
		shape = tetrisShape.getCoordArray();
		if(!isEmpty(shape) || !isFit(shape))
		{
			tetrisShape.reverseCounterclock();
			shape = tetrisShape.getCoordArray();
		}
	}



	/**
	 * Add new shape to the grid.
	 */
	public void addNewShape()
	{
		tetrisShape = new TetrisShape();
		shape = tetrisShape.getCoordArray();
		currRow = INITIAL_ROW;
		currCol = INITIAL_COLUMN;
	}

    	/**
	 * Judge if the game need to be stopped.
 	 * @return true if the game need to be stopped.
	 */   
	public boolean isGameStop()
	{
		
		for(int i = 0; i < shape.length; i++)
		{		
                        int x = shape[i].r + INITIAL_ROW + 1;
                        int y = shape[i].c + INITIAL_COLUMN;
                        if(x < 0) continue;
                        if(shapeOccupied[x][y]) return true;
                }
                return false;
	}

	/**
	 * Judge if the positions that in the grid have been
	 * occupied.
	 * @return return an boolean 2D array, each element in the
	 * array is true when the position has been occupied.
	 */
	public boolean[][] getOccupied()
	{
		boolean [][] temp = new boolean[HEIGHT][WIDTH];
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				if(shapeOccupied[i][j]) temp[i][j] = true;
			}
		} 
		for(int k = 0; k < shape.length; k++)
		{
			int x = shape[k].r + currRow;
			int y = shape[k].c + currCol;
			if (x < 0) continue;
			temp[x][y] = true;
		}
		return temp;
	}

	/**
	 * Judge if the shape is in grid.
	 * @param shapeCoord the reference of the shape
	 * @return true if shape is in grid, false otherwise
     	 */
	private boolean isFit(Coord[] shapeCoord)
	{
		for(int i = 0; i < shapeCoord.length; i++)
		{
			int x = shapeCoord[i].r + currRow;
			int y = shapeCoord[i].c + currCol;
			if(x >= rows) return false;
			if(y < 0|| y >= cols) return false;
		}
		return true;
	}

 	/**
	 * Judge if the place that shape need to be placed is empty.
	 * @param shapeCoord the reference of the shape
     	 * @return true if shape does not intersect with occupied block
     	 */
	private boolean isEmpty(Coord[] shapeCoord)
	{
		for(int i = 0; i < shapeCoord.length; i++)
		{
			int x = shapeCoord[i].r + currRow;
			int y = shapeCoord[i].c + currCol;
			if(x < 0) continue;
			if(x >= rows) return false;
			if(y < 0 || y >= cols) return false;
			if(shapeOccupied[x][y]) return false;
		}
		return true;
	}

	/** 
	 * place the 2D shape in the grid at location (r,c) 
	 * only if every row of the shape fits in the grid and
	 * doesn't intersect any non-empty spaces. Otherwise don't
	 * place the shape
	 */
	public void placeShape()
	{
		for(int i = 0; i < shape.length; i++)
		{
			int x = shape[i].r + currRow;
			int y = shape[i].c + currCol;
			if(x < 0) continue;	
			grid[x][y] = block;
			shapeOccupied[x][y] = true;
		}
	}
	
	/** 
	 * Clear the elements in the grid defined by the 2D shape. 
	 * Starting at location (r,c) in the grid. The contents of the 
	 * shape are irrelevant only the dimensions of each row are used.
	 * Clear only if every row of the shape fits in the grid. 
	 * Cleared elements in the grid are set to the dot char.
	 */
	public void clearShape()
	{	
		int i;
		int j;
		boolean isFit = isFit(shape);
		if(isFit)
		{		
			for(i = 0; i < shape.length; i++)
			{
				int x = shape[i].r + currRow;
				int y = shape[i].c + currCol;
				if(x < 0) continue;
				grid[x][y] = space;
				shapeOccupied[x][y] = false;
			}
		}
	}

	/**
	 * Clear the whole grid
	 */
	public void clear()
	{
		int i;
		int j;
		for(i = 0; i < rows; i++)
		{
			for(j = 0; j < cols; j++)
			{
				grid[i][j] = space;
				shapeOccupied[i][j] = false;			
			}
		}
	}
	
	/**
	 * Judge if the whole row is occupied by shapes.
     	 * @return true if the row is complete 
     	 */
	public boolean isComplete(int x)
	{
		for(int i = 0; i < cols; i ++)
		{
			if(!shapeOccupied[x][i]) return false;
		}
		return true;
	}

	/** 
	 * Get the width and height of the grid 
	 * @return array where index=0 is nrows, index=1 nrows 
	 */
	public int [] getSize()
	{
		int[] size = new int[2];
		size[0] = rows;
		size[1]	= cols;
		return size; 
	}

	/**
	 * Get the number of occupied rows
     	 * @return the number of complete rows
     	 */
	public int getNumOfComplete()
	{
		setCompleteRows();
		return numOfComplete;
	}	
	
    	/**
	  * Set an array that record if rows are complete
	  * and set an integer to record the number of complete
	  * rows
	  */
	public void setCompleteRows()
	{
		numOfComplete = 0;
		for(int j = 0; j < rows; j++)
			completeRows[j] = false;
		for(int i = 0; i < rows; i++)
		{
			if(isComplete(i))
			{
				completeRows[i] = true;
				numOfComplete++;
			}
		}
	}

	/**
	 * Clear the complete rows
	 */
	public void clearCompleteRows()
	{
		setCompleteRows();
		int k = rows - 1;
		boolean [][] tempOccupied = new boolean[HEIGHT][WIDTH];
		for(int i = rows - 1; i >= 0; i--)
		{
			if(completeRows[i]) i--;
			if(i < 0) break;
			while(!completeRows[i])
			{
				for(int m = 0; m < cols; m++)
					tempOccupied[k][m] = shapeOccupied[i][m]; 
				k--;
				i--;
				if(i < 0) break;
			}		
		}
		clear();
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				shapeOccupied[i][j] = tempOccupied[i][j];
	}




	/** create a nice, printable representation of the grid and
	 * filled coordinates
	 */
	@Override
	public String toString()
	{
		String output ="";
		int i, j;
		char[][] printGrid = new char[rows][cols];
		for( i = 0; i < rows; i++)
			for(j = 0; j < cols; j++ )
				printGrid[i][j] = grid[i][j];
		for (i = 0; i < rows; i++ )
				output += new String(printGrid[i]) + NEWLINE;
		return output;
	}
	

}
// vim: ts=4:sw=4:tw=78
