package parts;

import com.badlogic.gdx.graphics.Texture;

public class CPU extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	
	public CPU ()
	{
		setBuildRequirements(new int[]{10, 5, 5});
		setSprite(new Texture("CPUSprite.png"));
		setName("Computer Proccessing Unit");
	}
	
	public String toString ()
	{
		return name;
	}
	
}
