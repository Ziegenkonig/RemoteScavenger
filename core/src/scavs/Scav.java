package scavs;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import blueprints.Blueprints;
import main.Tuple;

public class Scav extends Rectangle{
	private static final long serialVersionUID = 1L;
	//Preview image for Scav
	public Texture preview;
	Blueprints blueprint;
	
	//Coordinates for where parts are located on the chassis view
	//during assembly
	LinkedList<Tuple> partCoords;
	LinkedList<Integer> partSize;
	
	//Stats
	int speed; // Pixels/Frame
	int health; // Healthpoints until critical damage has been reached
	int armor; // The amount of damage that gets reduced per hit
	int shields; // The amount of damage that can be taken before armor and hitpoints come into play
	int inventorySize; //The amount of items the droid can carry
	int damage; //The amount of damage a basic attack will do
	
	ArrayList<Object[]> stats = new ArrayList<Object[]>(); //Two arrays, first is string, second is int; represents stat names and values
	
	public Scav()
	{
//		preview = new Texture("badlogic.jpg");
		speed = 5;
		health = 20;
		armor = 0;
		shields = 0;
		inventorySize = 0;
		damage = 1;
	}
	
	//getStats produces an array list that holds two arrays.
	//The first array holds the names of the stats
	//The second array holds the values of the stats
	//This allows for loops to be run on the arrays for easy retrieval of all the stats at once.
	public ArrayList<Object[]> getStats()
	{
		//Instantiating the two object arrays
		Object[] statNames = {"Health", "Damage", "Armor", "Shields", "Speed", "Inventory Capacity"};
		Object[] statValues = {health, damage, armor, shields, speed, inventorySize};
		//Adding the two arrays to the list
		stats.add(statNames);
		stats.add(statValues);
		
		return stats;
	}
	
	//Handles the dirty work of animating so that you only need to call one line of code to make
	//an entire animation; only works for sprite sheets with one row
	public Animation animate (Texture sheet, int frameCount,  float frameTime)
	{
		TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/frameCount, sheet.getHeight());
		TextureRegion[] frames = new TextureRegion[frameCount];
		for (int i = 0; i < frameCount; i++)
			frames[i] = tmp[0][i];
		return new Animation(frameTime, frames);
	}
	
	public void setBlueprint (Blueprints blueprint)
	{
		this.blueprint = blueprint;
	}
	
	public Blueprints createBlueprint ()
	{
		return blueprint;
	}
	
}
