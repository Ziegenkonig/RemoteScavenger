package parts;

import com.badlogic.gdx.graphics.Texture;

public class Camera extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public Camera ()
	{
		setBuildRequirements(new int[]{10, 10, 5});
		setSprite(new Texture("Camera.png"));
		setName("Camera");
	}
	
	public String toString ()
	{
		return name;
	}
}
