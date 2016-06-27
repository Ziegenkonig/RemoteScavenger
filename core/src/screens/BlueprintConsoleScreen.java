package screens;

import java.util.ArrayList;

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
import scavs.MouseDroid;
import scavs.Scav;
import scavs.TestPlayerAbomination;

public class BlueprintConsoleScreen implements Screen {
	// Standard Instantiations
	boolean 		closed = true;
	Player 			player;
	Game 			game;
	Texture 		background;
	SpriteBatch 	batch;
	Stage 			stage;
	Skin 			skin;
	
	// Table and objects within table
	Table 				table;
	Label 				title;
	Label 				chassisHeader;
	Label 				previewHeader;
	Label 				statsHeader;
	Label 				combatHeader;
	Label 				passiveHeader;
	ScrollPane 			chassisBox;
	ScrollPane 			combatBox;
	ScrollPane 			passiveBox;
	List<Scav> 			chassisList;
	List<String> 		combatList;
	List<String> 		passiveList;
	Image 				previewImage;
	Texture 			previewTexture = new MouseDroid().preview;
	TextButton 			backButton;
	TextButton 			createBlueprint;
	ArrayList<Object[]> stats;
	
	// Scavs that can have blueprints made
	MouseDroid 				mouse;
	TestPlayerAbomination 	scav;

	public BlueprintConsoleScreen(Game game, Player player) {
		this.game = game;
		this.player = player;
	}

	@Override
	public void show() {
		this.closed = false;
		//Standard declarations
		mouse 				= new MouseDroid();
		scav 				= new TestPlayerAbomination();
		skin 				= new Skin(Gdx.files.internal("uiskin.json"));
		batch 				= new SpriteBatch();
		stage 				= new Stage();
		table				= new Table();
		backButton 			= new TextButton("Back", skin);
		createBlueprint 	= new TextButton("Create Blueprint", skin);
		chassisList 		= new List<Scav>(skin);
		
		chassisList.setItems(mouse, scav);
		// ------------------------------------------------------------------------------
		
		// Image instantiation
		previewTexture 	= mouse.preview;
		previewImage 	= new Image(previewTexture);
		// Label instantiation
		title 			= new Label("BLUEPRINT CONSOLE", skin); title.setFontScale(2, 2);
		chassisHeader 	= new Label("Chassis Model", skin);
		previewHeader 	= new Label("Preview", skin);
		statsHeader 	= new Label("Stats", skin);
		combatHeader 	= new Label("Combat Components", skin);
		passiveHeader 	= new Label("Passive Components", skin);

		// Setting up the list of chassis
		chassisList = new List<Scav>(skin);
		chassisList.setItems(mouse, scav);
		chassisBox = new ScrollPane(chassisList, skin);
		Table chassisContainer = new Table(skin);
		chassisContainer.add(chassisBox).width(300f).height(128f);

		// Setting up the stats table
		Table statsContainer = new Table(skin);
		stats = chassisList.getSelected().getStats();
		Label tmp;
		for (int i = 0; i < stats.get(0).length; i += 2) {
			statsContainer.row().pad(5);
			tmp = new Label(stats.get(0)[i] + ": " + stats.get(1)[i], skin);
			statsContainer.add(tmp);
			tmp = new Label(stats.get(0)[i + 1] + ": " + stats.get(1)[i + 1], skin);
			statsContainer.add(tmp);
		}

		// Setting up the list of Combat components
		combatList = new List<String>(skin);
		combatList.setItems("Placeholder");
		combatBox = new ScrollPane(combatList, skin);
		Table combatContainer = new Table(skin);
		combatContainer.add(combatBox).width(300f).height(128f);
		
		// Setting up the list of Passive components
		passiveList = new List<String>(skin);
		passiveList.setItems("Placeholder");
		passiveBox = new ScrollPane(passiveList, skin);
		Table passiveContainer = new Table(skin);
		passiveContainer.add(passiveBox).width(300f).height(128f);

		table.setFillParent(true);
		// Row 1
		table.row().pad(5);
		table.add();
		table.add(title);
		table.add();
		//Row 2
		table.row().pad(5);
		table.add(chassisHeader);
		table.add(previewHeader);
		table.add(combatHeader);
		//Row 3
		table.row().pad(5);
		table.add(chassisContainer);
		table.add(previewImage).maxHeight(128).maxWidth(64);
		table.add(combatContainer);
		//Row 4
		table.row().pad(5);
		table.add();
		table.add(statsHeader);
		table.add(passiveHeader);
		//Row 5
		table.row().pad(5);
		table.add(createBlueprint);
		table.add(statsContainer);
		table.add(passiveContainer);
		//Row 6
		table.row().pad(5);
		table.add();
		table.add(backButton);
		table.add();

		// ------------------------------------------------------------------------------
		//Adding the table to the screen using the stage object
		stage.addActor(table);
		
		//Declaring the background for the screen
		background = new Texture("BlueprintConsoleBackground.png");
	}

	@Override
	public void render(float delta) {
		//Making sure the screen is correctly receiving input
		Gdx.input.setInputProcessor(stage);

		batch.begin();

		// Setting a transparent background
		Color c = new Color(Color.BLACK);
		c.a = 0.5f;
		batch.setColor(c);
		batch.draw(background, 25, 25, Gdx.graphics.getWidth() - 50, Gdx.graphics.getHeight() - 50);
		batch.setColor(Color.WHITE);

		batch.end();

		updateTable();
		
		stage.draw();
		stage.act();

		//Back button to close the screen
		if (backButton.isChecked())
			this.dispose();

		//This creates a blueprint, places it into the inventory, and closes the screen
		if (createBlueprint.isChecked()) {
			player.inventory.storeItem(chassisList.getSelected().createBlueprint());
			createBlueprint.setChecked(false);
		}

	}

	public void updateTable() {
		// Changes the preview picture to display the correct Scav
		Texture tmpTexture = chassisList.getSelected().preview;
		previewImage = (Image) table.getChildren().get(5);
		previewImage.setDrawable(new SpriteDrawable(new Sprite(tmpTexture)));

		// Changes the stats to display correctly for it's associated Scav
		Label tmpLabel;
		Table tmpTable = (Table) table.getChildren().get(10);
		stats = chassisList.getSelected().getStats();
		for (int i = 0; i < stats.get(0).length; i++) {
			tmpLabel = (Label) tmpTable.getChildren().get(i);
			tmpLabel.setText(stats.get(0)[i] + ": " + stats.get(1)[i]);
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
		closed = true;
	}

	@Override
	public void dispose() {
		closed = true;
		this.batch.dispose();
		this.stage.dispose();
		this.background.dispose();
	}
}
