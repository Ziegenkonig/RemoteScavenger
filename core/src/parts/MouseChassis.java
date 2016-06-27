package parts;

import com.badlogic.gdx.graphics.Texture;

public class MouseChassis extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	String material;
	
	public MouseChassis (int type)
	{
		if (type == 1)
			material = "Duralloy";
		else if (type == 2)
			material = "Titanium";
		else if (type == 3)
			material = "Alien Alloy";
		else
			material = "Steel";
		
		setBuildRequirements(new int[]{10, 20, 20});
		setName(material + " Chassis(Mouse Model)");
		setSprite(new Texture("MouseChassis.png"));
	}
	
	public String toString ()
	{
		return name;
	}
}
