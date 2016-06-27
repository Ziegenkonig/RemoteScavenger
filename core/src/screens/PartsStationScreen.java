package screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import main.Player;
import parts.*;

public class PartsStationScreen implements Screen {
	//General declarations
	boolean 		closed = true;
	Player 			player;
	Game 			game;
	SpriteBatch 	batch;
	Texture 		background;
	Skin 			skin;
	Stage 			stage;
	
	//Table declarations
	
	//Tables
	Table			mainTable;
	Table			previewTable;
	//Images
	SpriteDrawable 	itemThumbnailTexture;
	Image 			itemThumbnail;
	//Labels
	Label 			title;
	Label			partsHeader;
	Label			inventoryHeader;
	Label			itemHeader;
	Label			error;
	Label			resourceInfo;
	Label			partInfo;
	//Buttons
	TextButton		back;
	TextButton		create;
	//Scroll Panes
	ScrollPane		partsPane;
	ScrollPane		inventoryPane;
	//Lists
	List<Parts>		partsList;
	List<Parts>		inventoryList;
	
	//Temporary object declarations
	Parts tempPart;
	boolean clicked;
	
	
	public PartsStationScreen (Game game, Player player)
	{
		this.player = player;
		this.game = game;
	}

	@Override
	public void show() 
	{
		this.closed = false;
		//General Instantiations
		batch 		= new SpriteBatch();
		background 	= new Texture("BlueprintConsoleBackground.png");
		skin		= new Skin(Gdx.files.internal("uiskin.json"));
		stage 		= new Stage();
		
		//Temporary instantiations
		tempPart = new Parts();
		clicked = false;
		
		//Table instantiations
		mainTable 		= new Table();
		previewTable 	= new Table();
		
		mainTable.setFillParent(true);
		
		//Objects inside table: Instantiation and building
		
		//Images
		itemThumbnailTexture 	= new SpriteDrawable(new Sprite(new Texture("badlogic.jpg")));
		itemThumbnail 			= new Image();
		
		itemThumbnail.setDrawable(itemThumbnailTexture);
		
		//Labels
		title 				= new Label("Parts Station", skin);
		partsHeader 		= new Label("Parts", skin);
		inventoryHeader 	= new Label("Inventory", skin);
		itemHeader			= new Label("", skin);
		error				= new Label("", skin);
		resourceInfo		= new Label("", skin);
		partInfo			= new Label("", skin);
		
		//Buttons
		back 	= new TextButton("Back", skin);
		create	= new TextButton("Create", skin);
		
		//Lists
		
		partsList = new List<Parts>(skin);
		
		//Filling the parts list
		partsList.setItems(	new Accelerometer(), 
							new AISuite(),
							new Camera(),
							new CPU(),
							new Harddisk(),
							new Microphone(),
							new MouseChassis(0),
							new PersonalityChip(0),
							new PowerSupply(),
							new Thermometer(),
							new TractionMotor());
							
		inventoryList 	= new List<Parts>(skin);
		
		//Scroll Panes
		partsPane 		= new ScrollPane(partsList, skin);
		inventoryPane 	= new ScrollPane(inventoryList, skin);
		
		//Building previewTable first
		//Row 1
		previewTable.row();
		previewTable.add(itemHeader);
		//Row 2
		previewTable.row();
		previewTable.add(itemThumbnail);
		//Row 3
		previewTable.row();
		previewTable.add(partInfo);
		
		//Building mainTable
		//Row 1
		mainTable.row();
		mainTable.add();
		mainTable.add(title);
		mainTable.add();
		//Row 2
		mainTable.row();
		mainTable.add(partsHeader);
		mainTable.add(create);
		mainTable.add(inventoryHeader);
		//Row 3
		mainTable.row();
		mainTable.add(partsPane).fillY();
		mainTable.add(previewTable);
		mainTable.add(inventoryPane).fillY();
		//Row 4
		mainTable.row();
		mainTable.add();
		mainTable.add(back);
		mainTable.add(resourceInfo);
		//Row 5
		mainTable.row();
		mainTable.add(error);
		
		//Add mainTable to stage
		stage.addActor(mainTable);
	}

	@Override
	public void render(float delta) 
	{
		//Set input listener
		Gdx.input.setInputProcessor(stage);
		
		batch.begin();
		
		//Setting a transparent background
		Color c = new Color(Color.BLACK);
		c.a = 0.5f;
		batch.setColor(c);
		batch.draw(background, 125, 25, Gdx.graphics.getWidth()-250, Gdx.graphics.getHeight()-50);
		batch.setColor(Color.WHITE);
		
		batch.end();
		
		//If back button is clicked close screen
		if (back.isChecked())
			this.dispose();
		
		//If create button is clicked, add the selected part to the inventory
		if (create.isChecked()) {
			player.inventory.storeItem(partsList.getSelected());
			create.setChecked(false);
		}
		
		//Making sure table is up to date
		updateTable();
		
		//Making sure table is actually drawn to screen
		stage.draw();
		stage.act();
	}

	public void updateTable()
	{
		//Updating inventoryList's contents to be identical to inventory's contents
		inventoryList.setItems(player.inventory.getItems());
		
		//Updating the itemThumbnail to match the currently selected Parts in partsList
		tempPart = partsList.getSelected();
		itemThumbnailTexture.setSprite(new Sprite(tempPart.getSprite()));
		itemThumbnailTexture.setMinHeight(128);
		itemThumbnailTexture.setMinWidth(128);
		itemThumbnail.setDrawable(itemThumbnailTexture);
		
		//Updating the itemHeader's text to match what is currently selected inside partsList
		itemHeader.setText(partsList.getSelected().toString());
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
	public void dispose() 
	{
		closed = true;
		this.batch.dispose();
		this.stage.dispose();
		this.background.dispose();
	}
}
