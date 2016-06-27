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
import parts.Parts;

public class InventoryScreen implements Screen {
	boolean 		closed = true;
	Player 			player;
	Game 			game;
	SpriteBatch 	batch;
	Texture 		background;
	Skin 			skin;
	Stage 			stage;
	//Table instantiations
	Table 			mainTable;
	Table 			contentTable;
	SpriteDrawable 	itemThumbnailImage;
	Image 			itemThumbnail;
	TextButton 		backButton;
	TextButton 		sortButton;
	Label 			screenHeader;
	ScrollPane 		inventoryPanel;
	ScrollPane 		previewPanel;
	List<Object> 	inventoryList;
	
	
	public InventoryScreen(Game game, Player player) 
	{
		this.game = game;
		this.player = player;
	}
	
	@Override
	public void show() {
		this.closed 	= false;
		//General declarations
		stage 			= new Stage();
		batch 			= new SpriteBatch();
		background 		= new Texture("BlueprintConsoleBackground.png");
		skin 			= new Skin(Gdx.files.internal("uiskin.json"));
		
		//Table declarations
		mainTable 		= new Table();
		contentTable 	= new Table();
		
		//Image needs to be updated every frame to reflect which item in the list is selected
		//So to easier to this in updateTable(), I instantiated the SpriteDrawable below so that
		//it can easily be changed.
		itemThumbnailImage 	= new SpriteDrawable(new Sprite());
		itemThumbnail 		= new Image();
		itemThumbnail.setDrawable(itemThumbnailImage);
		
		previewPanel 	= new ScrollPane(itemThumbnail, skin);
		
		//Extra stuff for the table
		backButton 		= new TextButton("Back", skin);
		sortButton 		= new TextButton("Sort", skin);
		screenHeader 	= new Label("INVENTORY", skin);
		inventoryList 	= new List<Object>(skin);
		inventoryPanel 	= new ScrollPane(inventoryList, skin);
		
		//Making the table
		stage.addActor(makeTable());
	}

	@Override
	public void render(float delta) 
	{
		Gdx.input.setInputProcessor(stage);
		
		batch.begin();
		
		//Setting a transparent background
		Color c = new Color(Color.BLACK);
		c.a = 0.5f;
		batch.setColor(c);
		batch.draw(background, 225, 25, Gdx.graphics.getWidth()-450, Gdx.graphics.getHeight()-50);
		batch.setColor(Color.WHITE);
		
		batch.end();
		
		//If back button is clicked close screen
		if (backButton.isChecked())
			this.dispose();
		//If sort button is clicked, sort inventory items in list
		if (sortButton.isChecked())
			player.inventory.sortName();
		
		//Make sure table is constantly up to date
		updateTable();
		
		//Making sure table is actually drawn to screen
		stage.draw();
		stage.act();
	}

	public Table makeTable()
	{
		//Main Table
		mainTable.row().pad(8);
		mainTable.add(screenHeader);
		
		contentTable.row().padLeft(20);
		contentTable.add(sortButton).left();
		contentTable.row().pad(20);
		contentTable.add(inventoryPanel).expandY().fillY(); //Filling the list with objects is handled in updateTable()
		contentTable.add(previewPanel); //Updating the image is also handled in updateTable()
		
		mainTable.row().pad(8).expandY();
		mainTable.add(contentTable).fillY();
		
		mainTable.row().pad(8);
		mainTable.add(backButton);
		
		mainTable.setSize(500, Gdx.graphics.getHeight()-50);
		mainTable.setPosition((Gdx.graphics.getWidth()/2)-(mainTable.getWidth()/2), 25);
//		mainTable.setDebug(true);
//		contentTable.setDebug(true);
		
		return mainTable;
	}
	
	public void updateTable()
	{
		//Setting inventoryList's contents to be identical to inventory's contents
		inventoryList.setItems(player.inventory.getItems());
		
		//Updating the image
		Parts tempObject = (Parts) inventoryList.getSelected();
		itemThumbnailImage.setSprite(new Sprite(tempObject.getSprite()));
		itemThumbnailImage.setMinHeight(128);
		itemThumbnailImage.setMinWidth(128);
		itemThumbnail.setDrawable(itemThumbnailImage);
		
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
