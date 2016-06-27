package blueprints;

import java.util.ArrayList;

import main.Inventory;
import parts.Parts;

public class Blueprints extends Parts {
	ArrayList<Parts> recipe;
	
	
	//Constructor for blueprint objects
	public Blueprints ()
	{
		
	}
	
	//This returns the recipe that the blueprint represents
	public ArrayList<Parts> getRecipe ()
	{
		return this.recipe;
	}
	
	public void setRecipe (ArrayList<Parts> parts)
	{
		recipe = parts;
	}
	//Checks whether or not the recipe is currently buildable based on the inventory argument
	//This method should: 
	// 1) Take the ith item in recipe, and compare it against the items in inventory
	// 2) If the item can not find a match by the time the last item of inventory is reached; return false
	// 3) If the item's match is found, immediately move on to the next item in recipe to reduce runtime
	public boolean isBuildable (Inventory inventory)
	{
		for (int i = 0; i < recipe.size(); i++) {
			
			for (int j = 0; j < inventory.capacity; j++) {
				
				if ( recipe.get(i).equals(inventory.contents.get(j)) )
					j = inventory.capacity;
				else if ( j == inventory.capacity-1)
					return false;
				
			}
		}
		
		return true;
	}

}
