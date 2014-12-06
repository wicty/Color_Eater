package javagame;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import other.Ball;
import other.Player;

public class Play extends BasicGameState {
	
	//  ____   ____  _       _______     _____       _       ______   _____     ________   ______   
	// |_  _| |_  _|/ \     |_   __ \   |_   _|     / \     |_   _ \ |_   _|   |_   __  |.' ____ \  
	//   \ \   / / / _ \      | |__) |    | |      / _ \      | |_) |  | |       | |_ \_|| (___ \_| 
	//    \ \ / / / ___ \     |  __ /     | |     / ___ \     |  __'.  | |   _   |  _| _  _.____`.  
	//     \ ' /_/ /   \ \_  _| |  \ \_  _| |_  _/ /   \ \_  _| |__) |_| |__/ | _| |__/ || \____) | 
	//      \_/|____| |____||____| |___||_____||____| |____||_______/|________||________| \______.' 
	//                                                                                                                                          
	public int						ID	= 0;
	Image							background_image;
	//public CopyOnWriteArrayList<Ball>	balls;
	public static ArrayList<Ball>	balls;
	public static ArrayList<Player>	players;
	public static int				spawnInterval;
	
	public Play(int state) {
		ID = state;
	}
	
	//  _____  ____  _____  _____  _________  
	// |_   _||_   \|_   _||_   _||  _   _  | 
	//   | |    |   \ | |    | |  |_/ | | \_| 
	//   | |    | |\ \| |    | |      | |     
	//  _| |_  _| |_\   |_  _| |_    _| |_    
	// |_____||_____|\____||_____|  |_____|   
	//                                                         
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background_image = new Image("res/background/colorful.png");
		//balls = new CopyOnWriteArrayList<Ball>();
		spawnInterval = 0;
		balls = new ArrayList<Ball>();
		players = new ArrayList<Player>();
		players.add(new Player(players.size()));
	}
	
	//  _____  _____  _______  ______        _     _________  ________  
	// |_   _||_   _||_   __ \|_   _ `.     / \   |  _   _  ||_   __  | 
	//   | |    | |    | |__) | | | `. \   / _ \  |_/ | | \_|  | |_ \_| 
	//   | '    ' |    |  ___/  | |  | |  / ___ \     | |      |  _| _  
	//    \ \__/ /    _| |_    _| |_.' /_/ /   \ \_  _| |_    _| |__/ | 
	//     `.__.'    |_____|  |______.'|____| |____||_____|  |________| 
	//
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// ############################################################ //	
		Input input = gc.getInput();
		for (int i = 1; i < javagame.Game.ID; i++) {
			if (input.isKeyPressed(i + 1)) {
				sbg.enterState(i);
			}
		}
		spawnInterval--;
		// ############################################################ //
		if (!input.isKeyDown(Input.KEY_SPACE)) {
			if (Ball.isReady() & spawnInterval < 0) {
				if (Ball.getNumberOfBalls() >= Ball.getMaxNumberOfBalls()) {
					balls.remove(0);
					Ball.setNumberBalls(Ball.getNumberOfBalls() - 1);
				}
				balls.add(new Ball(balls.size()));
			}
			for (Ball ball : balls) {
				if (ball.getCircle() != null) {
					ball.moveDown(delta);
				}
			}
		}
		// ############################################################ //
		if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) { // move left
			players.get(0).move("left", delta);
		}
		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) { // move right
			;
			players.get(0).move("right", delta);
		}
		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP)) { // move right
			players.get(0).move("up", delta);
		}
		if (input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN)) { // move right
			players.get(0).move("down", delta);
		}
		// ############################################################ //
		/*
		if (spawn_interval % 1== 0) {
			for (Ball ball : balls) {
				ball.moveDown();
			}
		}
		*/
		// ############################################################ //
	}
	
	//  _______     ________  ____  _____  ______   ________  _______     
	// |_   __ \   |_   __  ||_   \|_   _||_   _ `.|_   __  ||_   __ \    
	//   | |__) |    | |_ \_|  |   \ | |    | | `. \ | |_ \_|  | |__) |   
	//   |  __ /     |  _| _   | |\ \| |    | |  | | |  _| _   |  __ /    
	//  _| |  \ \_  _| |__/ | _| |_\   |_  _| |_.' /_| |__/ | _| |  \ \_  
	// |____| |___||________||_____|\____||______.'|________||____| |___| 
	//                                                                    
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//background_image.setImageColor(.75f, .75f, .75f);
		background_image.draw(0, 0);
		g.setColor(Color.black);
		g.drawString("ID: " + this.getID(), 20, 20);
		if (balls.size() > 0) {
			for (Ball ball : balls) {
				if (ball.getCircle() != null) {
					ball.render(gc.getGraphics());
				}
			}
		}
		// ############################################################ //
		if (players.size() > 0) {
			for (Player player : players) {
				if (player.isAlive()) {
					player.render(gc.getGraphics());
				}
			}
		}
	}
	
	public int getID() {
		return this.ID;
	}
}
