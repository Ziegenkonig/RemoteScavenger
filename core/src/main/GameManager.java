package main;

import com.badlogic.gdx.Game;

import screens.MainMenu;

//Game manager handles a lot of things, such as inventory, screens, variables such as health and experience
//This particular GameManager handles:
// - Screens for the game
//

public class GameManager extends Game {
	Game game;
	Player player;
	
	public GameManager () 
	{
		game = this;
	}
	
	@Override
	public void create () 
	{
		player = new Player();
		player.fillInventory();
		game.setScreen(new MainMenu(game, player));
	}

	@Override
	public void render () 
	{
		super.render();
	}
	
}
