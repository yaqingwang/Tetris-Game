/*
NAME: Chang Sun
LOGIN: cs11sfk
ID: A53075169

NAME: Yaqing Wang
LOGIN: cs11sgi
ID: A53084051
*/

import java.util.Random;


/** A class that can create random one of seven kinds of
 * Tetris shapes. And can rotate the shape clockwise or
 * counterclockwise.
 * @author Chang Sun, Yaqing Wang
 * @version June, 6, 2015
 */
public class TetrisShape 
{
	private Random randGen = new Random();
	private Coord[] shape = new Coord[4];

	/**
	 * Create a shaped randomly from seven kinds of
	 * Tetris shapes. The instance includes a 4 size
	 * Coord array.
	 */
	public TetrisShape()	
	{
		int num = randGen.nextInt(7) + 1;
		switch(num)
		{
			case 1: // O - shape
				shape[0] = new Coord(0, 0);
				shape[1] = new Coord(0, 1);
				shape[2] = new Coord(1, 0);
				shape[3] = new Coord(1, 1);
				break;
			case 2: // I - shape
				shape[0] = new Coord(-1, 0);
				shape[1] = new Coord(0, 0);
				shape[2] = new Coord(1, 0);
				shape[3] = new Coord(2, 0);
				break;
			case 3: // S - shape
				shape[0] = new Coord(0, -1);
				shape[1] = new Coord(0, 0);
				shape[2] = new Coord(-1, 0);
				shape[3] = new Coord(-1, 1);
				break;
			case 4: // Z - shape 
				shape[0] = new Coord(-1, -1);
				shape[1] = new Coord(-1, 0);
				shape[2] = new Coord(0, 0);
				shape[3] = new Coord(0, 1);
				break;
			case 5: // L - shape
				shape[0] = new Coord(-2, 0);
                                shape[1] = new Coord(-1, 0);
                                shape[2] = new Coord(0, 0);
                                shape[3] = new Coord(0, 1);
                                break;
			case 6: // J - shape
				shape[0] = new Coord(-2, 0);
                                shape[1] = new Coord(-1, 0);
                                shape[2] = new Coord(0, 0);
                                shape[3] = new Coord(0, -1);
                                break;
			case 7: // T -shape
				shape[0] = new Coord(0, -1);
                                shape[1] = new Coord(0, 0);
                                shape[2] = new Coord(1, 0);
                                shape[3] = new Coord(0, 1);
                                
		}		
		
	}

	/**
	 * Get the reference of Coord array
	 * @return reference of the Coord array
	 */
	public Coord[] getCoordArray()
	{
		return shape;	
	}
		
	/**
	 * Get the reference of offset Coord array
	 * @param offset the given offset
	 * @return reference of the offset Coord array
	 */
	public Coord[] getOffsetCoordArray(Coord offset)
	{
		Coord[] offsetshape = new Coord[4];	
		int i = 0;
		for(i = 0; i < offsetshape.length; i++)
		{
			offsetshape[i]= new Coord(shape[i]);
			offsetshape[i].move(offset.r,offset.c);
		}
		return offsetshape;
	}

	/**
	 * Rotate the shape clockwise
	 */
	public void rotateClock()
	{
		
		int store = 0; 	//store initial r of shape
		for(int i = 0; i < shape.length; i++)
		{
			store = shape[i].r;
			shape[i].r = shape[i].c;
                        shape[i].c = store * (-1);
		}
		
	}
	
	/**
	 * Undo the rotate clockwise action
	 */
	public void reverseClock()
	{
		int store = 0; 	//store initial r of shape
		for(int i = 0; i < shape.length; i++)
		{
			store = -shape[i].c;
			shape[i].c = shape[i].r;
                        shape[i].r = store;
		}


	}

	/**
         * Rotate the shape clockwise
         */
	public void rotateCounterclock()
	{
                int store = 0; 
                for(int i = 0; i < shape.length; i++)
                {
                        store = shape[i].r;
			shape[i].r = shape[i].c * (-1);
			shape[i].c = store;
                }
		
		
	}

	/**
         * Undo the rotate clockwise action
         */
	public void reverseCounterclock()
	{
		int store = 0; 
                for(int i = 0; i < shape.length; i++)
                {
                        store = -shape[i].r;
			shape[i].r = shape[i].c;
			shape[i].c = store;
                }
	}

}
