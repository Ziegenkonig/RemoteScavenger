package parts;

import com.badlogic.gdx.graphics.Texture;

public class Accelerometer extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public Accelerometer()
	{
		setBuildRequirements(new int[]{10, 5, 5});
		setName("Accelerometer");
	}
	
	public String toString ()
	{
		return name;
	}
}
