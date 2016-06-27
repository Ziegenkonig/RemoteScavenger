package scavs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import blueprints.MouseScavBlueprint;

//MouseDrois is a type of rectangle, that normally has a standardized height and width.
//The height and width is allowed to be changed, however it must always be a perfect square.

public class MouseDroid extends Scav {
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
	
	public MouseDroid () 
	{
		//Setting preview image
		preview = new Texture("MouseStandingLeftSheet.png");
		//Settnig States
		facingRight = true;
		
		//Setting variables
		speed = 5;
		health = 20;
		armor = 0;
		shields = 0;
		inventorySize = 0;
		damage = 1;
		
		//Setting size
		this.setHeight(64);
		this.setWidth(64);
		
		//Instantiating animations
		sheet = new Texture("MouseStandingRightSheet.png");
		standRight = animate(sheet, 4, 0.25f);
		
		sheet = new Texture("MouseStandingLeftSheet.png");
		standLeft = animate(sheet, 1, 1f);
		
		sheet = new Texture("MouseRunRightSheet.png");
		runRight = animate(sheet, 6, 0.17f);
		
		sheet = new Texture("MouseRunLeftSheet.png");
		runLeft = animate(sheet, 6, 0.17f);
		
		//Setting which blueprint this scav is associated with
		setBlueprint(new MouseScavBlueprint());
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
	
	//Returns string that is displayed when object is called in string form.
	public String toString ()
	{
		return "Mouse Scav";
	}
}
