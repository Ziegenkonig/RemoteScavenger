package blueprints;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

import parts.AISuite;
import parts.Accelerometer;
import parts.CPU;
import parts.Camera;
import parts.Harddisk;
import parts.Microphone;
import parts.MouseChassis;
import parts.Parts;
import parts.PersonalityChip;
import parts.PowerSupply;
import parts.Thermometer;
import parts.TractionMotor;

public class MouseScavBlueprint extends Blueprints {
	//Inherits:
	//String name
	//Texture sprite
	Texture sprite;
	String name;
	
	public MouseScavBlueprint () 
	{
		setSprite(new Texture("badlogic.jpg"));
		
		ArrayList<Parts> recipe = new ArrayList<Parts>();
		recipe.add(new Microphone());
		recipe.add(new Camera());
		recipe.add(new Accelerometer());
		recipe.add(new CPU());
		recipe.add(new TractionMotor());
		recipe.add(new PersonalityChip(0));
		recipe.add(new Thermometer());
		recipe.add(new Harddisk());
		recipe.add(new AISuite());
		recipe.add(new PowerSupply());
		recipe.add(new MouseChassis(0));
		setRecipe(recipe);
		
		setName("Mouse Scav Blueprint");
	}
}
