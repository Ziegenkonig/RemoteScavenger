package parts;

import com.badlogic.gdx.graphics.Texture;

import main.Resources;

//The basic class for all parts, the building blocks of a Scav.
//TODO:
//	- Write in a short description for all parts.

public class Parts implements Comparable<Parts>{
	Texture sprite; //The image that a part is seen as
	String 	name; //This is used to display the name of the object in a toString method
	String 	info; //This is a short description about the part in question
	int[] 	buildRequirements; //How many resources it takes to build one of the parts in question
	
	//Constructor
	public Parts ()
	{
		sprite = new Texture("badlogic.jpg");
		buildRequirements = new int[]{5, 5, 5};
	}
	
	//Getter/Setter for String name
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	//Getter/Setter for Texture sprite
	public void setSprite(Texture sprite)
	{
		this.sprite = sprite;
	}
	public Texture getSprite ()
	{
		return sprite;
	}

	//Getter/setter for int[] buildRequirements
	public void setBuildRequirements (int[] buildRequirements)
	{
		this.buildRequirements = buildRequirements;
	}
	public int[] getBuildRequirements ()
	{
		return buildRequirements;
	}
	
	@Override
	public int compareTo(Parts part) {
		return (this.name).compareTo(part.name);
	}
	
	public String toString ()
	{
		return name;
	}
	
	public String displayInfo ()
	{
		return 	"Required: \n" 	+
				buildRequirements[0] + " Precious Metal \n" +
				buildRequirements[1] + " Plastics \n" +
				buildRequirements[2] + " Metals";
	}
	
	//isBuildable runs through the buildRequirements array and compares it to the resource count that the
	//player currently holds.
	// - If buildRequirements[i] != resources[i] or less than, false
	// - If the loop finishes to completion, true
	public boolean isBuildable (Resources resources) 
	{
		for (int i = 0; i < buildRequirements.length; i++) {
			
			if (buildRequirements[i] != resources.amounts[i] || buildRequirements[i] < resources.amounts[i])
				return false;
			
		}
		
		return true;
	}
}
