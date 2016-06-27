package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import blueprints.Blueprints;
import main.Player;
import parts.Parts;

public class AssemblyTableScreen implements Screen {
	// Standard Instantiations
	boolean 				closed = true;
	Player 					player;
	Game 					game;
	Texture 				background;
	SpriteBatch 			batch;
	Stage 					stage;
	Skin 					skin;
	//Instantiating tables
	Table					mainTable;
	Table					selectionTable;
	Table					chassisViewTable;
	Table					rightChassisTable;
	Table					middleChassisTable;
	Table					leftChassisTable;
	//Instantiating table components
	//Labels
	Label					mainHeader;
	Label					currentBPLabel;
	Label					inventoryLabel;
	Label					chassisViewLabel;
	Label					powerLabel;
	Label					cpuLabel;
	Label					hddLabel;
	Label					motorLabel;
	Label					aiLabel;
	Label					sensorLabel;
	Label					enviroLabel;
	Label[]					optionalLabels;
	//Scroll Panes
	ScrollPane				inventoryPane;
	List<Parts>				inventoryList;
	ScrollPane				powerPane;
	ScrollPane				cpuPane;
	ScrollPane				hddPane;
	ScrollPane				motorPane;
	ScrollPane				aiPane;
	ScrollPane				sensorPane;
	ScrollPane				enviroPane;
	ScrollPane[]			optionalPanes;
	//Select Box (contains blueprints)
	SelectBox<Blueprints>	blueprintSelect;
	
	public AssemblyTableScreen (Game game, Player player)
	{
		this.player = player;
		this.game = game;
	}
	
	@Override
	public void show() 
	{
		//Standard declarations
		background			= new Texture("BlueprintConsoleBackground.png");
		skin 				= new Skin(Gdx.files.internal("uiskin.json"));
		batch 				= new SpriteBatch();
		stage 				= new Stage();
		
		//Label declarations
		mainHeader 			= new Label("Assembly Table", skin);
		currentBPLabel 		= new Label("Blueprints Owned:", skin);
		inventoryLabel		= new Label("Inventory:", skin);
		chassisViewLabel 	= new Label("Chassis View", skin);
		powerLabel			= new Label("Power Supply", skin);
		cpuLabel			= new Label("CPU", skin);
		hddLabel			= new Label("Hard Disk", skin);
		motorLabel			= new Label("Motor", skin);
		aiLabel				= new Label("AI", skin);
		sensorLabel			= new Label("Sensors", skin);
		enviroLabel			= new Label("Enviro Sensors", skin);
		optionalLabels		= new Label[4];
		
		//ScrollPane declarations
		inventoryPane		= new ScrollPane(null, skin);
		inventoryList		= new List<Parts>(skin);
		powerPane			= new ScrollPane(null, skin);
		cpuPane				= new ScrollPane(null, skin);
		hddPane				= new ScrollPane(null, skin);
		motorPane			= new ScrollPane(null, skin);
		aiPane				= new ScrollPane(null, skin);
		sensorPane			= new ScrollPane(null, skin);
		enviroPane			= new ScrollPane(null, skin);
		optionalPanes		= new ScrollPane[4];
		
		//Select Box declaration
		blueprintSelect		= new SelectBox<Blueprints>(skin);
		
		//Building the ChassisView table first
		
		
		
	}

	@Override
	public void render(float delta) 
	{
		
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
		
	}

}
