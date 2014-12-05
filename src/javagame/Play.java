package javagame;

import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import other.Ball;

public class Play extends BasicGameState {
	
	//  ____   ____  _       _______     _____       _       ______   _____     ________   ______   
	// |_  _| |_  _|/ \     |_   __ \   |_   _|     / \     |_   _ \ |_   _|   |_   __  |.' ____ \  
	//   \ \   / / / _ \      | |__) |    | |      / _ \      | |_) |  | |       | |_ \_|| (___ \_| 
	//    \ \ / / / ___ \     |  __ /     | |     / ___ \     |  __'.  | |   _   |  _| _  _.____`.  
	//     \ ' /_/ /   \ \_  _| |  \ \_  _| |_  _/ /   \ \_  _| |__) |_| |__/ | _| |__/ || \____) | 
	//      \_/|____| |____||____| |___||_____||____| |____||_______/|________||________| \______.' 
	//                                                                                                                                          
	public int				ID	= 0;
	Image					background_image;
	public CopyOnWriteArrayList<Ball>	balls;
	//public ArrayList<Ball>	balls;
	private Ball			player;
	private Ball			temp;
	private static int		spawn_interval;
	
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
		balls = new CopyOnWriteArrayList<Ball>();
		//balls = new ArrayList<Ball>();
		//balls.add(new Ball(balls.size()));
		player = new Ball();
		temp = new Ball(0);
		balls.add(temp);
		temp = new Ball(0);
		balls.add(temp);
		spawn_interval = 0;
	}
	
	//  _____  _____  _______  ______        _     _________  ________  
	// |_   _||_   _||_   __ \|_   _ `.     / \   |  _   _  ||_   __  | 
	//   | |    | |    | |__) | | | `. \   / _ \  |_/ | | \_|  | |_ \_| 
	//   | '    ' |    |  ___/  | |  | |  / ___ \     | |      |  _| _  
	//    \ \__/ /    _| |_    _| |_.' /_/ /   \ \_  _| |_    _| |__/ | 
	//     `.__.'    |_____|  |______.'|____| |____||_____|  |________| 
	//
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		for (int i = 1; i < javagame.Game.ID; i++) {
			if (input.isKeyPressed(i + 1)) {
				sbg.enterState(i);
			}
		}
		spawn_interval++;
		if (spawn_interval == 10) {
			spawn_interval = 0;
			this.addBall();
			/*
			balls.add(new Ball(balls.size()));
			for (int i = 0; i < balls.size() - 1; i++) {
				if (balls.get(i).collides(balls.get(balls.size() - 1))) {
					balls.remove(i);
				}
				if (balls.get(i).collides(player)) {
					balls.remove(i);
				}
			}*/
		}
		/*
		for (Ball first : balls){
			for(Ball second : balls)
			{
				if (first.getID()!=second.getID()&&first.collides(second)) {
					balls.remove(second.getID());
				}
			}
		}
		
		
		// test comment
		
		if (spawn_interval % 1== 0) {
			for (Ball ball : balls) {
				ball.moveDown();
			}
		}*/
	}
	
	private void addBall() {
		temp = new Ball(0);
		for (Ball ball : balls) {
			if (!ball.collides(temp)) {
				System.out.println("ADDING");
				balls.add(temp);
			} else {
				this.addBall();
			}
		}
		{
			//System.out.println("adding");
			//temp = null;
		}
	}
	
	//  _______     ________  ____  _____  ______   ________  _______     
	// |_   __ \   |_   __  ||_   \|_   _||_   _ `.|_   __  ||_   __ \    
	//   | |__) |    | |_ \_|  |   \ | |    | | `. \ | |_ \_|  | |__) |   
	//   |  __ /     |  _| _   | |\ \| |    | |  | | |  _| _   |  __ /    
	//  _| |  \ \_  _| |__/ | _| |_\   |_  _| |_.' /_| |__/ | _| |  \ \_  
	// |____| |___||________||_____|\____||______.'|________||____| |___| 
	//                                                                    
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//background_image.draw(0, 0);
		g.setColor(Color.black);
		g.drawString("ID: " + this.getID(), 20, 20);
		if (balls.size() > 0) {
			for (Ball ball : balls) {
				ball.render(gc.getGraphics());
			}
		}
		player.render(gc.getGraphics());
		//temp.render(gc.getGraphics());
	}
	
	public int getID() {
		return this.ID;
	}
}
