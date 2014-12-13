package other;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Player {
	
	private static int		pointsToNextLevel	= 1;
	private static float	speed				= .1f;
	private static float	speedModifier		= .05f;
	private int				ID;
	private float			x;
	private float			y;
	private Color			color;
	private int				size;
	private int				level;
	private Shape			circle;
	private boolean			alive;
	private int				points;
	
	public Player(int ID) {
		this.setAlive(true);
		// ID
		this.setID(ID);
		// SIZE
		this.setLevel(1);
		// COLOR
		this.changeColor();
		// SIZE IN PX
		this.setSize(this.getLevel() + (3 * (3 + this.getLevel())));
		// POSITION
		this.setX(500);
		//this.setY(getRandomY());
		this.setY(300);
		// CREATE  CIRCLE
		this.newCircle(new Circle(this.getX() + this.getSize() / 2, this.getY() - 70 + this.getSize() / 2, this.getSize() / 2));
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
	
	private void changeColor() {
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
			default:
				this.setColor(255, 255, 255);
				break;
		}
	}
	
	private void setColor(int red, int green, int blue) {
		this.color = new Color(red, green, blue);
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public Shape getCircle() {
		return circle;
	}
	
	public void newCircle(Shape circle) {
		this.circle = circle;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void render(Graphics g) throws SlickException {
		g.setColor(this.getColor());
		g.fill(this.circle);
		g.setColor(Color.black);
		g.draw(this.circle);
	}
	
	public void move(String direction, int delta, boolean slow, boolean fast) {
		float speed = Player.speed;
		if (slow) {
			speed -= Player.speedModifier;
		}
		else if (fast) {
			speed += Player.speedModifier;
		}
		switch (direction) {
			case "right":
				if (this.getCircle().getMaxX() < 1024) {
					this.getCircle().setCenterX((this.getCircle().getCenterX() + speed * delta));
				}
				break;
			case "left":
				if (this.getCircle().getMinX() > 0) {
					this.getCircle().setCenterX((this.getCircle().getCenterX() - speed * delta));
				}
				break;
			case "up":
				if (this.getCircle().getMinY() > 0) {
					this.getCircle().setCenterY((this.getCircle().getCenterY() - speed * delta));
				}
				break;
			case "down":
				if (this.getCircle().getMaxY() < 576) {
					this.getCircle().setCenterY((this.getCircle().getCenterY() + speed * delta));
				}
				break;
		}
	}
	
	public boolean collides(Ball ball) {
		Shape first = this.getCircle();
		Shape second = ball.getCircle();
		if (first.intersects(second)) {
			return true;
		}
		if (first.contains(second)) {
			return true;
		}
		return false;
	}
	
	public void checkCollisions() {
		for (int ID = 0; ID < javagame.Play.balls.size(); ID++) {
			Ball ball = javagame.Play.balls.get(ID);
			if (ball.getCircle() != null) {
				if (this.collides(ball)) {
					if (this.getLevel() == ball.getLevel()) {
						javagame.Play.balls.remove(ID);
						// ADD POINTS
						points++;
						if (this.getPoints() % Player.pointsToNextLevel == 0) {
							// INCREASE LEVEL
							this.setLevel(this.getLevel() + 1);
							// COLOR
							this.changeColor();
							// SIZE IN PX
							this.setSize(this.getLevel() + (3 * (3 + this.getLevel())));
							// CREATE  CIRCLE
							this.newCircle(new Circle(this.getCircle().getCenterX(), this.getCircle().getCenterY(), this.getSize() / 2));
						}
					}
					else if (this.getLevel() > ball.getLevel()) {
						// DESTROY BALL
						javagame.Play.balls.remove(ID);
						javagame.Play.pef.doEffect(ball.getCircle().getCenterX(), ball.getCircle().getCenterY());
					}
					else if (this.getLevel() < ball.getLevel()) {
						// TODO DEATH
						javagame.Play.balls.remove(ID);
						System.out.println("Death");
					}
				}
			}
		}
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void checkWin() {
		
		if (this.level > 16) {
			Ball.setInProgress(true);
			for (int ID = javagame.Play.balls.size()-1; ID >= 0; ID--) {
				//System.out.println("ID: "+ID+"   size: "+javagame.Play.balls.size());
				Ball ball = javagame.Play.balls.get(ID);
				javagame.Play.balls.remove(ID);
				javagame.Play.pef.doEffect(ball.getCircle().getCenterX(), ball.getCircle().getCenterY());
			}
			this.setLevel(16);
			// COLOR
			this.changeColor();
			// SIZE IN PX
			this.setSize(this.getLevel() + (3 * (3 + this.getLevel())));
			// CREATE  CIRCLE
			this.newCircle(new Circle(this.getCircle().getCenterX(), this.getCircle().getCenterY(), this.getSize() / 2));
			Ball.setInProgress(false);
		}
	}
}
