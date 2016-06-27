package scavs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestPlayerAbomination extends Scav {
	private static final long serialVersionUID = 1L;

	//Default variables/objects
	TextureRegion currentFrame;
	
	//Animations
	Animation standRight;
	Animation standLeft;
	Animation runRight;
	Animation runLeft;
	Texture sheet;
	
	//States
	boolean facingRight;
	
	public TestPlayerAbomination ()
	{
		//Setting preview image
		preview = new Texture("TestingCharacterSpriteRight.png");
		//Settnig States
		facingRight = true;
		
		//Setting variables
		speed = 2;
		health = 50;
		armor = 2;
		shields = 0;
		inventorySize = 0;
		damage = 3;
		
		//Setting size
		this.setHeight(64);
		this.setWidth(64);
		
		//Instantiating animations
		sheet = new Texture("TestingCharacterSpriteRight.png");
		standRight = animate(sheet, 4, 0.25f);
		
		sheet = new Texture("TestingCharacterSpriteLeft.png");
		standLeft = animate(sheet, 1, 1f);
		
		sheet = new Texture("TestingCharacterSheetRight.png");
		runRight = animate(sheet, 6, 0.17f);
		
		sheet = new Texture("TestingCharacterSheetLeft.png");
		runLeft = animate(sheet, 6, 0.17f);
	}
	
	//move() returns a texture that is the current frame of one of the animation objects
	//direction = 0  : moving left
	//direction = 1  : moving right
	//direction = -1 : standing still
	public TextureRegion move(int direction, float stateTime) {
		
		if (direction == 1) {
			currentFrame = runRight.getKeyFrame(stateTime, true);
			this.x += 5;
			facingRight = true;
		} else if (direction == 0) {
			currentFrame = runLeft.getKeyFrame(stateTime, true);
			this.x -= 5;
			facingRight = false;
		} else if (direction == -1) {
			if (facingRight)
				currentFrame = standRight.getKeyFrame(stateTime, true);
			else
				currentFrame = standLeft.getKeyFrame(stateTime, true);
		}
		return currentFrame;
	}
	
	public String toString ()
	{
		return "Test Player Abomination";
	}
	
}
