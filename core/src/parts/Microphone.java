package parts;

import com.badlogic.gdx.graphics.Texture;

public class Microphone extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public Microphone ()
	{
		setBuildRequirements(new int[]{5, 10, 5});
		setSprite(new Texture("Microphone.png"));
		setName("Microphone");
	}
	
	public String toString ()
	{
		return name;
	}
	
}
