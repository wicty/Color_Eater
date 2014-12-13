package other;

import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Ball {
	
	private static final int	max_size			= 16;
	private static final int	max_number_of_balls	= 200;//200
	private static int			numberOfBalls		= 0;
	private static Random		r					= new Random();
	private int					ID;
	private float				x;
	private float				y;
	private Color				color;
	private int					size;
	private int					level;
	private Shape				circle;
	private Shape				collisionCircle;
	private static boolean		inProgress;
	private boolean				isChecking;
	private int					number_of_checks;
	
	public Ball(int ID) {
		numberOfBalls++;
		inProgress = true;
		isChecking = true;
		// ID
		this.setID(ID);
		// CHECK FOR COLLISION
		number_of_checks = 0;
		while (inProgress) {
			number_of_checks++;
			if (circleCanSpawnCheck()) {
				// IF NO COLLISION DETECTED SPAWN CIRCLE
				inProgress = false;
				isChecking = false;
				javagame.Play.spawnTimeDelay = 10;
				if (number_of_checks > 0) {
					//System.out.println("Number of balls: " + numberOfBalls + "    Number of spawn checks: " + number_of_checks);
				}
				this.newCircle(new Circle(this.getX() + this.getSize() / 2, this.getY() - 70 + this.getSize() / 2, this.getSize() / 2));
			}
			if (number_of_checks > 50000) {
				System.out.println("max number of checks exceeded");
				//inProgress = false;
				break;
			}
		}
	}
	
	private boolean circleCanSpawnCheck() {
		// SIZE
		this.setLevel(r.nextInt(Ball.getMaxSize()) + 1);
		// COLOR
		this.setColor();
		// SIZE IN PX
		this.setSize(this.getLevel() + (3 * (3 + this.getLevel())));
		// POSITION
		this.setX(this.getRandomX());
		//this.setY(getRandomY());
		this.setY(0);
		// CREATE COLLISION CIRCLE
		this.newCollisionCircle(new Circle(this.getX() + this.getSize() / 2, this.getY() + this.getSize() / 2, this.getSize() / 2));
		int i = 0;
		//if (javagame.Play.balls.size() > 1)
		{
			for (Ball ball : javagame.Play.balls) {
				if (this.collides(ball)) {
					i++;
				}
			}
			if (i > 0) {
				return false;
			}
		}
		return true;
	}
	
	/*private int getRandomY() {
		return r.nextInt(576 - this.getSize());
	}*/
	private int getRandomX() {
		/*this.getSize() / 2 +*/
		/*- (this.getSize() / 2)*/
		
		return r.nextInt(1024 - this.getSize());
	}
	
	private void setColor() {
		switch (this.getLevel() - 1) {
			case 0:
				this.setColor(255, 0, 0);
				break;
			case 1:
				this.setColor(255, 67, 0);
				break;
			case 2:
				this.setColor(255, 136, 0);
				break;
			case 3:
				this.setColor(255, 204, 0);
				break;
			case 4:
				this.setColor(238, 255, 0);
				break;
			case 5:
				this.setColor(170, 255, 0);
				break;
			case 6:
				this.setColor(101, 255, 0);
				break;
			case 7:
				this.setColor(34, 255, 0);
				break;
			case 8:
				this.setColor(0, 255, 33);
				break;
			case 9:
				this.setColor(0, 255, 101);
				break;
			case 10:
				this.setColor(0, 255, 169);
				break;
			case 11:
				this.setColor(0, 255, 237);
				break;
			case 12:
				this.setColor(0, 203, 255);
				break;
			case 13:
				this.setColor(0, 136, 255);
				break;
			case 14:
				this.setColor(0, 68, 255);
				break;
			case 15:
				this.setColor(0, 0, 255);
				break;
		}
	}
	
	public int getID() {
		return ID;
	}
	
	private void setID(int iD) {
		ID = iD;
	}
	
	private float getX() {
		return x;
	}
	
	private void setX(float x) {
		this.x = x;
	}
	
	private float getY() {
		return y;
	}
	
	public boolean collides(Ball ball) {
		Shape first = this.getCollisionCircle();
		Shape second = ball.getCollisionCircle();
		/*
		if (first.getMaxY() < second.getMinY()) return false;
		if (first.getMinY() > second.getMaxY()) return false;
		if (first.getMaxX() < second.getMinX()) return false;
		if (first.getMinX() > second.getMaxX()) return false;
		return true;
		*/
		/*
		if (first.getMaxX() > second.getMinX() & first.getMinX() < second.getMaxX()) return true;
		return false;
		*/
		if (first.intersects(second)) {
			return true;
		}
		if (first.contains(second)) {
			return true;
		}
		return false;
	}
	
	private void setY(float f) {
		this.y = f;
	}
	
	private Color getColor() {
		return color;
	}
	
	private void setColor(int red, int green, int blue) {
		this.color = new Color(red, green, blue);
	}
	
	private int getSize() {
		return size;
	}
	
	private void setSize(int size) {
		this.size = size;
	}
	
	public Shape getCircle() {
		return circle;
	}
	
	private void newCircle(Shape circle) {
		this.circle = circle;
	}
	
	public Shape getCollisionCircle() {
		return collisionCircle;
	}
	
	private void newCollisionCircle(Shape outline) {
		this.collisionCircle = outline;
	}
	
	public void render(Graphics g) throws SlickException {
		//g.setColor(Color.white);
		//g.fill(this.collisionCircle);
		g.setColor(this.getColor());
		g.fill(this.circle);
		g.setColor(Color.black);
		g.draw(this.circle);
	}
	
	public void moveDown(int delta) {
		/*
		if (!isChecking) {
			this.setY((float) (this.getY() + 0.1 * delta));
			this.getCircle().setCenterY(this.getY() + this.getSize());
			this.getCollisionCircle().setCenterY(this.getY() + this.getSize());
		}
		*/
		if (!isChecking) {
			this.getCircle().setCenterY((this.getCircle().getCenterY() + 0.05f * delta));
			this.getCollisionCircle().setCenterY((this.getCollisionCircle().getCenterY() + 0.05f * delta));
		}
	}
	
	public static int getMaxSize() {
		return max_size;
	}
	
	public static int getMaxNumberOfBalls() {
		return max_number_of_balls;
	}
	
	public static int getNumberOfBalls() {
		return numberOfBalls;
	}
	
	public static boolean isReady() {
		return !inProgress;
	}
	
	public static void setInProgress(boolean state){
		Ball.inProgress = state;
	}
	
	public static void setNumberBalls(int i) {
		Ball.numberOfBalls = i;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
