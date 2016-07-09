/**
 * Tuple is a type of data that can be used to represent a point on a plane
 * Using two values, X and Y.
 */

package main;

public class Tuple <X, Y> 
{
	public X x;
	public Y y;
	
	//Constructor
	public Tuple (X x, Y y)
	{
		this.x = x;
		this.y = y;
	}
	
	//setX resets the x value that the tuple was instantiated with
	public void setX (X x)
	{
		this.x = x;
	}
	//setY resets the y value that the tuple was instantiated with
	public void setY (Y y)
	{
		this.y = y;
	}
	
	public String toString ()
	{
		return "(" + x + ", " + y + ")";
	}
}
