package parts;

import com.badlogic.gdx.graphics.Texture;

public class Harddisk extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public Harddisk ()
	{
		setBuildRequirements(new int[]{5, 10, 10});
		setName("Hard Disk");
		setSprite(new Texture("HardDisk.png"));
	}
	
	public String toString ()
	{
		return name;
	}
}
