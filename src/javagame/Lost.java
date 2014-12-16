package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.*;
import other.Ball;

public class Lost extends BasicGameState {
	
	//  ____   ____  _       _______     _____       _       ______   _____     ________   ______   
	// |_  _| |_  _|/ \     |_   __ \   |_   _|     / \     |_   _ \ |_   _|   |_   __  |.' ____ \  
	//   \ \   / / / _ \      | |__) |    | |      / _ \      | |_) |  | |       | |_ \_|| (___ \_| 
	//    \ \ / / / ___ \     |  __ /     | |     / ___ \     |  __'.  | |   _   |  _| _  _.____`.  
	//     \ ' /_/ /   \ \_  _| |  \ \_  _| |_  _/ /   \ \_  _| |__) |_| |__/ | _| |__/ || \____) | 
	//      \_/|____| |____||____| |___||_____||____| |____||_______/|________||________| \______.' 
	//                                                                                                                                          
	public int	ID	= 0;
	Image		background_image;
	
	public Lost(int state) {
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
		background_image = new Image("res/background/lost.png");
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
		if (input.isKeyPressed(Input.KEY_ENTER)){
			Ball.setInProgress(true);
			for (int ID = javagame.Play.balls.size() - 1; ID >= 0; ID--) {
				//System.out.println("ID: "+ID+"   size: "+javagame.Play.balls.size());
				Ball ball = javagame.Play.balls.get(ID);
				javagame.Play.balls.remove(ID);
				javagame.Play.pef.doEffect(ball.getCircle().getCenterX(), ball.getCircle().getCenterY());
			}

			Ball.setInProgress(false);
			javagame.Play.lost = false;
			sbg.enterState(2);
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
		background_image.draw(0, 0);
		//g.drawString("ID: " + this.getID(), 20, 20);
	}
	
	public int getID() {
		return this.ID;
	}
}
