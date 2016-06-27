package parts;

import com.badlogic.gdx.graphics.Texture;

public class Thermometer extends Parts {
	//Inherits:
	//String name
	//Texture sprite
	
	public Thermometer ()
	{
		setBuildRequirements(new int[]{10, 5, 5});
		setSprite(new Texture("Thermometer.png"));
		setName("Thermometer");
	}
	
	public String toString ()
	{
		return name;
	}
	
}
