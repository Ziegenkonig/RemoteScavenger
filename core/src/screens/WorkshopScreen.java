package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import main.Player;
import scavs.MouseDroid;

public class WorkshopScreen implements Screen {
	//Various variables
	float stateTime;
	boolean right = true;
	int frameCounter = 0;
	//Default instantiations
	Player 					player;
	Game 					game;
	Stage 					stage;
	Skin 					skin;
	SpriteBatch 			batch;
	TextureRegion[] 		frames;
	TextureRegion			currentFrame;
	//Workshop
	Animation 				workshopBackground; //3 frames
	Texture 				workshopBackgroundSheet;
	//Sunbeams
	Animation 				sunbeams; //7 frames
	Texture					sunbeamSpriteSheet;
	//Blueprint Console
	Animation				blueprintConsoleAnimation; //4 frames
	Texture					blueprintConsoleSheet;
	Rectangle				blueprintConsole;
	//Parts Station
	Texture 				partsStationSheet;
	Rectangle 				partsStation;
	//Assembly Station
	Texture 				assemblyTableSheet;
	Rectangle				assemblyTable;
	//Mouse robot
	MouseDroid 				mouse;
	//Screens that this screen leads to
	BlueprintConsoleScreen 	bpconsole;
	InventoryScreen 		inventoryScreen;
	PartsStationScreen		partsStationScreen;
	
	public WorkshopScreen(Game game, Player player) 
	{
		this.game = game;
		this.player = player;
	}
	
	@Override
	public void show() 
	{	
		bpconsole 			= new BlueprintConsoleScreen(game, player);
		inventoryScreen 	= new InventoryScreen(game, player);
		partsStationScreen 	= new PartsStationScreen(game, player);
		
		workshopBackgroundSheet = new Texture("WrkshpScrnBckgrndSheet.png");
		sunbeamSpriteSheet		= new Texture("SunbeamSpriteSheetSmall.png");
		blueprintConsoleSheet 	= new Texture("BlueprintConsoleSheet.png");
		partsStationSheet 		= new Texture("PartsStation.png");
		assemblyTableSheet		= new Texture("AssemblyTable.png");
		skin 					= new Skin(Gdx.files.internal("uiskin.json"));
		
		stateTime = 0f;
		//Begin Animation code
		//----------------------------------------------------------------------------------------
		//I decided that to save some time and space, I would only declare one Texture for a character
		//that had multiple animations, and declare each animation separately
		workshopBackground 			= animate(workshopBackgroundSheet, 3, 0.33f);
		sunbeams 					= animate(sunbeamSpriteSheet, 7, 0.8f);
		blueprintConsoleAnimation 	= animate(blueprintConsoleSheet, 4, 0.5f);
		
		batch = new SpriteBatch();
		stage = new Stage();
	
		//Establishing mouse size
		mouse 	= new MouseDroid();
		mouse.x = 50;
		mouse.y = 16;
		//Establishing blueprint console size
		blueprintConsole = new Rectangle((int)(Gdx.graphics.getWidth()*0.2),
										 (int)(Gdx.graphics.getHeight()*0.03), 
										 (int)(Gdx.graphics.getWidth()*0.09), 
										 (int)(Gdx.graphics.getHeight()*0.37));
		//Establishing parts station size
		partsStation = new Rectangle((int)(Gdx.graphics.getWidth()*0.32), 
									 (int)(Gdx.graphics.getHeight()*0.03), 
									 (int)(Gdx.graphics.getWidth()*0.44), 
									 (int)(Gdx.graphics.getHeight()*0.44));
		//Establishing assembly table size
		assemblyTable = new Rectangle((int)(Gdx.graphics.getWidth()*0.78), 
									  (int)(Gdx.graphics.getHeight()*0.03), 
									  (int)(Gdx.graphics.getWidth()*0.17), 
									  (int)(Gdx.graphics.getHeight()*0.34));
	}

	@Override
	public void render(float delta) 
	{
		//Making sure we have input in case we want a pop a menu or something.
//		Gdx.input.setInputProcessor(stage);
		
		//setting stateTime so it keeps track of frames every pass of render()
		stateTime += Gdx.graphics.getDeltaTime();
		
		//----------------------------------------------------------------------------------------
		
		//Deals with everything the batch needs to draw.
		batch.begin();
		
		//Drawing background animation
		currentFrame = workshopBackground.getKeyFrame(stateTime, true);
		batch.draw(currentFrame,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//Drawing animation for blueprint console
		currentFrame = blueprintConsoleAnimation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, blueprintConsole.x, blueprintConsole.y, blueprintConsole.getWidth(), blueprintConsole.getHeight());
		
		//Drawing image for parts station
		batch.draw(partsStationSheet, partsStation.x, partsStation.y, partsStation.getWidth(), partsStation.getHeight());
		
		//Drawing image for assembly table
		batch.draw(assemblyTableSheet, assemblyTable.x, assemblyTable.y, assemblyTable.getWidth(), assemblyTable.getHeight());
		
		//----------------------------------------------------------------------------------------
		
		//Handles moving the mouse character left and right as well as displaying the correct
		//animation. Also handles the mouse standing still if not moving.
		
		//Moving left
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
			batch.draw(mouse.move(0, stateTime), mouse.x, mouse.y, mouse.getWidth(), mouse.getHeight());
		
		//Moving right
		else if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
			batch.draw(mouse.move(1,  stateTime), mouse.x, mouse.y, mouse.getWidth(), mouse.getHeight());
		
		//Standing still
		else
			batch.draw(mouse.move(-1, stateTime), mouse.x, mouse.y, mouse.getWidth(), mouse.getHeight());
		
		//----------------------------------------------------------------------------------------
		
		//This next section deals with making a texture/texture region transparent.
		//Set a color, make it white or in other words, clear, to allow original color to show
		Color c = new Color(Color.WHITE);
		c.a = 0.3f; //This sets the colors alpha level, 1 is the highest, 0 is completely transparent
		//Set batch color to the transparent color, and now everything we draw will be translucent
		batch.setColor(c);
		//Animation code for sunbeams
		currentFrame = sunbeams.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Don't forget to set the color back to default, or else everything else we draw will be transparent.
		batch.setColor(Color.WHITE);
		
		//----------------------------------------------------------------------------------------
		
		batch.end();
		
		//----------------------------------------------------------------------------------------
		
		//Next bit of code handles various keyboard events
		
		//Handling opening the blueprint console screen
		if (Gdx.input.isKeyJustPressed(Keys.E) && screensClosed()) {
			if (mouse.overlaps(blueprintConsole))
				bpconsole.show();
		}
		if (!bpconsole.closed)
			bpconsole.render(stateTime);
		
		//Handling opening the parts station screen
		if (Gdx.input.isKeyJustPressed(Keys.E) && screensClosed()) {
			if (mouse.overlaps(partsStation))
				partsStationScreen.show();
		}
		if (!partsStationScreen.closed)
			partsStationScreen.render(stateTime);
		
		//Handling opening the inventory screen
		if (Gdx.input.isKeyJustPressed(Keys.I) && screensClosed())
				inventoryScreen.show();
		if (!inventoryScreen.closed)
			inventoryScreen.render(stateTime);
		
		//If player hits escape, go back to main menu
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
			game.setScreen(new MainMenu(game, player));
		
		//----------------------------------------------------------------------------------------
		
		//Checks for mouse going out of bounds
		if (mouse.x < 30)
			mouse.x = 30;
		if (mouse.x > Gdx.graphics.getWidth()-30-mouse.getWidth())
			mouse.x = Gdx.graphics.getWidth()-30-mouse.getWidth();
		
//		stage.draw();
//		stage.act();
	}

	//Animate takes a sprite sheet and the number of frames within said sheet and prepares a 
	//TextureRegion array that can be used in the animation constructor
	public Animation animate (Texture sheet, int frameCount,  float frameTime)
	{
		TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/frameCount, sheet.getHeight());
		frames = new TextureRegion[frameCount];
		for (int i = 0; i < frameCount; i++)
			frames[i] = tmp[0][i];
		return new Animation(frameTime, frames);
	}
	
	//This method checks to see if all other screens are closed
	public boolean screensClosed ()
	{
		if (inventoryScreen.closed && bpconsole.closed && partsStationScreen.closed)
			return true;
		
		return false;
	}
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
