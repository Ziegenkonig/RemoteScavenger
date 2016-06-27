package parts;

import com.badlogic.gdx.graphics.Texture;

public class PowerSupply extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	int watts;
	
	public PowerSupply ()
	{
		watts = 1000;
		
		setBuildRequirements(new int[]{5, 10, 15});
		setSprite(new Texture("Power Supply.png"));
		setName(watts + "W Power Supply");
	}
	
	public String toString ()
	{
		return name;
	}
	
}
