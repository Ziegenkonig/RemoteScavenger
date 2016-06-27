package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import main.Player;

public class MainMenu implements Screen{
	Player		player;
	Game 		game;
	Table 		table;
	Stage 		stage;
	TextButton 	newGame;
	TextButton 	options;
	TextButton 	about;
	TextButton 	exit;
	Label 		title;
	Skin 		skin;
	
	public MainMenu(Game game, Player player)
	{
		this.game = game;
		this.player = player;
	}
	
	@Override
	public void show() 
	{
		//General instantiations
		stage 	= new Stage();
		skin 	= new Skin(Gdx.files.internal("uiskin.json"));
		
		//Table instantiations
		table 	= new Table();
		title 	= new Label("REMOTE SCAVENGER", skin);
		title.setFontScale(2,2);
		
		// Text buttons for the table instantiations
		newGame = new TextButton("New Game", skin);
		options = new TextButton("Options", skin);
		about 	= new TextButton("About", skin);
		exit 	= new TextButton("Exit", skin);
		
		//Making sure the table fits the background of the screen
		table.setFillParent(true);
		
		//Structure for the main menu
		//--------------
		//-----TITLE----
		//----NEWGAME---
		//----OPTIONS---
		//-----ABOUT----
		//-----EXIT-----
		table.row().fillX();
		table.add(new Label("", skin));
		table.add(title);
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		table.add(newGame);
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		table.add(options);
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		table.add(about);
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		//
		table.row().fillX();
		table.add(new Label("", skin));
		table.add(exit);
		table.add(new Label("", skin));
		
		//Adding table to stage
		stage.addActor(table);
	}

	@Override
	public void render(float delta) 
	{
		Gdx.input.setInputProcessor(stage);
		
		//Setting background of screen
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act();
		
		//If escape is pressed, close program
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
			dispose();
		}
		
		if (newGame.isChecked()) {
			game.setScreen(new WorkshopScreen(game, player));
			dispose();
		}
		
		if (options.isChecked()) {
			game.setScreen(new OptionsScreen(game, player));
			dispose();
		}
		
		if (about.isChecked()) {
			game.setScreen(new AboutScreen(game, player));
			dispose();
		}
		
		if (exit.isChecked()) {
			Gdx.app.exit();
			dispose();
		}
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
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}
	
}
