package other;

import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Ball {
	
	private static final int	max_size	= 16;
	private int					ID;
	private int					x;
	private int					y;
	private Color				color;
	private int					size;
	private Shape				circle;
	private Shape				outline;
	
	public Ball() {
		this.setSize(1);
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
		}
		this.setSize(this.getSize() + (5 * (3 + this.getSize())));
		this.setX(1024/2);
		this.setY(576/2);
		this.setCircle(new Circle(this.getX() + this.getSize() / 2, this.getY() + this.getSize() / 2, this.getSize() / 2));
		this.setOutline(new Circle(this.getX() + this.getSize() / 2, this.getY() + this.getSize() / 2, this.getSize() / 2 + 10));
	}
	
	public Ball(int ID) {
		Random r = new Random();
		this.setID(ID);
		this.setSize(r.nextInt(Ball.getMaxSize()) + 1);
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
		}
		this.setSize(this.getSize() + (5 * (3 + this.getSize())));
		this.setX(this.getSize() / 2 + r.nextInt(1024 - this.getSize() - (this.getSize() / 2)));
		this.setY(this.getSize() / 2 + r.nextInt(576 - this.getSize() - (this.getSize() / 2)));
		this.setY(0 - this.getSize() * 2);
		this.setY(10);
		this.setCircle(new Circle(this.getX() + this.getSize() / 2, this.getY() + this.getSize() / 2, this.getSize() / 2));
		this.setOutline(new Circle(this.getX() + this.getSize() / 2, this.getY() + this.getSize() / 2, this.getSize() / 2 + 10));
	}
	
	public int getID() {
		return ID;
	}
	
	private void setID(int iD) {
		ID = iD;
	}
	
	private int getX() {
		return x;
	}
	
	private void setX(int x) {
		this.x = x;
	}
	
	private int getY() {
		return y;
	}
	
	public boolean collides(Ball ball) {
		//System.out.println("check");
		Shape first = this.getOutline();
		Shape second = ball.getOutline();
		if (first.getMaxY() < second.getMinY()) return false;
		if (first.getMaxX() < second.getMinX()) return false;
		if (first.getMinY() > second.getMaxY()) return false;
		if (first.getMinX() > second.getMaxX()) return false;
		return true;
		/*
		if (first.intersects(second)) {
			return true;
		}
		if (first.contains(second)) {
			return true;
		}
		return false;
		*/
	}
	
	private void setY(int y) {
		this.y = y;
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
	
	private void setCircle(Shape circle) {
		this.circle = circle;
	}
	
	public Shape getOutline() {
		return outline;
	}
	
	private void setOutline(Shape outline) {
		this.outline = outline;
	}
	
	public void render(Graphics g) throws SlickException {
		//g.setColor(Color.black);
		//g.fill(this.outline);
		g.setColor(this.getColor());
		g.fill(this.circle);
	}
	
	public void moveDown() {
		this.setY(this.getY() + 1);
		this.getCircle().setCenterY(this.getY() + this.getSize());
		this.getOutline().setCenterY(this.getY() + this.getSize());
	}
	
	public static int getMaxSize() {
		return max_size;
	}
}
