package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import main.Player;

public class OptionsScreen  implements Screen{
	Game 		game;
	Player 		player;
	Stage 		stage;
	Table 		table;
	Label 		info;
	Skin 		skin;
	TextButton 	back;
	
	public OptionsScreen(Game game, Player player) 
	{
		this.player = player;
		this.game = game;
	}
	
	@Override
	public void show() 
	{
		//Declaring all objects
		skin 	= new Skin(Gdx.files.internal("uiskin.json"));
		table 	= new Table();
		info 	= new Label("This is a work in progress!", skin);
		stage 	= new Stage();
		back 	= new TextButton("Back to Main Menu", skin);
		
		//Adding info and back into table
		table.add(info);
		table.row();
		table.add(back);
		table.setFillParent(true);
		
		//Adding table to the stage's actors
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
		
		if (back.isChecked()) {
			game.setScreen(new MainMenu(game, player));
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
		skin.dispose();
		stage.dispose();
	}

}
