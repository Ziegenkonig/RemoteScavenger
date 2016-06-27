package main;

import parts.CPU;
import parts.Microphone;
import parts.PersonalityChip;
import parts.PowerSupply;
import parts.Thermometer;

/**
 *	Player keeps track of the player's:
 *	- Resources
 *	- Inventory
 *	- Health
 *	- Skill Levels
 *	- Experience
 *	
 *	NOTES: Player will never have any subclasses, so you can edit the variables directly without having
 *			any getters/setters.
 */

public class Player {
	
	public Inventory 	inventory;
	public Resources 	resources;
	public int 			health;
	public int 			skill;
	public int 			experience;
	
	//Constructor
	public Player ()
	{
		inventory 	= new Inventory(100);
		resources 	= new Resources();
		health 		= 100;
		skill 		= 1;
		experience 	= 0;
	}
	
	//Fills the inventory with items.
	public void fillInventory()
	{
		inventory.storeItem(new Microphone());
		inventory.storeItem(new CPU());
		inventory.storeItem(new PersonalityChip(0));
		inventory.storeItem(new Thermometer());
		inventory.storeItem(new PowerSupply());
	}
	
}
