package parts;

import com.badlogic.gdx.graphics.Texture;

public class TractionMotor extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	
	public TractionMotor ()
	{
		setBuildRequirements(new int[]{5, 10, 20});
		setName("Traction Motor");
		setSprite(new Texture("Traction Motor.png"));
	}
	
	public String toString ()
	{
		return name;
	}
}
