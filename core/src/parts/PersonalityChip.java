package parts;

import com.badlogic.gdx.graphics.Texture;

public class PersonalityChip extends Parts{
	//Inherits:
	//String name
	//Texture sprite
	String personality;
	
	public PersonalityChip (int type)
	{
		switch(type) {
			case 1: personality = "Funny";
					break;
			case 2: personality = "Spunky";
					break;
			case 3: personality = "Tsundere";
					break;
			case 4: personality = "Ragebot";
					break;
			case 5: personality = "Depressed";
					break;
			case 6: personality = "Smart";
					break;
			case 7: personality = "Arrogant";
					break;
			default: personality = "Default";
					break;
		}
		
		setBuildRequirements(new int[]{10, 10, 5});
		setName(personality + " Personality Algorithm Chip");
		setSprite(new Texture("PersonalityChip.png"));
	}
	
	public String toString ()
	{
		return name;
	}
	
}
