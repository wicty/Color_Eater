package other;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Player {
	
	private int		ID;
	private float	x;
	private float	y;
	private Color	color;
	private int		size;
	private Shape	circle;
	private boolean	alive;
	
	public Player(int ID) {
		this.setAlive(true);
		// ID
		this.setID(ID);
		// SIZE
		this.setSize(20);
		// COLOR
		this.setColor();
		// SIZE IN PX
		this.setSize(this.getSize() + (3 * (3 + this.getSize())));
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
	
	private void setColor() {
		switch (this.getSize() - 1) {
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
				this.setColor(50, 50, 50);
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
	
	public void move(String direction, int delta) {
		switch (direction) {
			case "right":
				this.getCircle().setCenterX((this.getCircle().getCenterX() + 0.2f * delta));
				break;
			case "left":
				this.getCircle().setCenterX((this.getCircle().getCenterX() - 0.2f * delta));
				break;
			case "up":
				this.getCircle().setCenterY((this.getCircle().getCenterY() - 0.2f * delta));
				break;
			case "down":
				this.getCircle().setCenterY((this.getCircle().getCenterY() + 0.2f * delta));
				break;
		}
	}
}
