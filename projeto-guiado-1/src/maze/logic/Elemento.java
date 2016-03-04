package maze.logic;

public class Elemento {
	private Point position;
	private boolean alive;
	
	public Elemento(Point pos) {
		position = pos;
		alive = true;
	}
	
	public void setAlive(boolean a) {
		alive = a;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setPosition(int x, int y) {
		position.set(x, y);
	}
	
	public int getPositionX() {
		return position.getX();
	}
	
	public int getPositionY() {
		return position.getY();
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void moveUp() {
		setPosition(getPositionX(), getPositionY() - 1);
	}
	
	public void moveDown() {
		setPosition(getPositionX(), getPositionY() + 1);
	}
	
	public void moveLeft() {
		setPosition(getPositionX() - 1, getPositionY());
	}
	
	public void moveRight() {
		setPosition(getPositionX() + 1, getPositionY());
	}
}
