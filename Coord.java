/*
NAME: Chang Sun
LOGIN: cs11sfk
ID: A53075169

NAME: Yaqing Wang
LOGIN: cs11sgi
ID: A53084051
*/

/**
 * A help class where an instance of the class is
 * an integer pair(row, column).
 * @author Chang Sun, Yaqing Wang
 * @version 6 June 2015
 */
public class Coord 
{
	public int r;
	public int c;

	/**
	 * Create an instance based on given (r, c)
	 * @param r set rows
	 * @param c set columns
	 */	
	public Coord(int r, int c)
	{
		this.r = r;
		this.c = c;
	}	
	
	/**
	 * Create an instance based on given Coord
	 * instance
	 * @param initial set initial Coord instance
	 */
	public Coord(Coord initial)
	{
		this.r = initial.r;
		this.c = initial.c;
	}
	
	/**
	 * Get the r of instance
	 * @return row of instance
	 */
	public int getR()
	{
		return r;
	}	

	/**
         * Get the c of instance
         * @return column of instance
         */
	public int getC()
	{
		return c;
	}

	/**
	 * Move the Coord instance by adding given (r, c)
	 * @param offsetr set the number of rows that need to be moved
	 * @param offsetc set the number of columns that need to be moved
	 */
	public void move(int offsetr, int offsetc)
	{
		r = r + offsetr;
		c = c + offsetc;
	}
}
