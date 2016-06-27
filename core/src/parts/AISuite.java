package parts;

import com.badlogic.gdx.graphics.Texture;

//Still needs a sprite

public class AISuite extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public AISuite ()
	{
		setBuildRequirements(new int[]{15, 10, 5});
		setName("Artificial Intelligence Suite");
		setSprite(new Texture("AISuite.png"));
	}
	
	public String toString ()
	{
		return name;
	}
}
