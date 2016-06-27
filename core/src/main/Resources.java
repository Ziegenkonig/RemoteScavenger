package main;

//	Resources keep track of how many resources (used to build components) the player has.
// 	Resources are not technically a part of the inventory, although the count of each resource
// 	does appear on the inventory screen.
//	Resources:
//		- Precious Metals
//		- Plastics
//		- Metals

public class Resources {
	
	public int preciousMetals;
	public int plastics;
	public int metals;
	public int[] amounts;
	
	public Resources () 
	{
		preciousMetals = 0;
		plastics = 0;
		metals = 0;
		amounts = new int[]{preciousMetals, plastics, metals};
	}
	
	//Displays readable description of class attributes when called
	public String toString ()
	{
		return 	"Precious Metals: " + amounts[0]		+ "\n" +
				"Plastics: " 		+ amounts[1]		+ "\n" +
				"Metals: " 			+ amounts[2]			;	
	}
	
	//Method that adds value to the precious metals attribute
	public void addPreciousMetals (int amount)
	{
		preciousMetals = preciousMetals + amount;
		amounts[0] = preciousMetals;
	}
	
	//Method that adds value to the plastics attribute
	public void addPlastics (int amount)
	{
		plastics = plastics + amount;
		amounts[1] = plastics;
	}
	
	//Method that adds value to the metals attribute
	public void addMetals (int amount)
	{
		metals = metals + amount;
		amounts[2] = metals;
	}
}
