package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
	
	public static final String	gamename	= "Color Eater";
	private static final int	frameRate	= 60;				// 120, 60
	public static final int		speed		= 240 / frameRate;	// 2  , 4
	public static int			ID			= 1;
	
	public Game(String gamename) {
		super(gamename);
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new Menu(ID++)); 		// ID: 1
		this.addState(new Play(ID++)); 		// ID: 2
		this.addState(new Options(ID++)); 	// ID: 3
		this.addState(new Win(ID++)); 		// ID: 4
		this.addState(new Lost(ID++)); 		// ID: 5
		this.enterState(2);
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(1024, 576, false); // fullscreen = true
			//appgc.setTargetFrameRate(frameRate);
			//appgc.setShowFPS(false);
			//appgc.setAlwaysRender(true);
			//appgc.setMouseGrabbed(true); // hide mouse cursor
			appgc.start();
		}
		catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
