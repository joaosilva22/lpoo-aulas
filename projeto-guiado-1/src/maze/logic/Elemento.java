package maze.logic;

public class Elemento {
	private int x, y;
	private boolean alive;
	
	public Elemento(int posX, int posY) {
		x = posX;
		y = posY;
		alive = true;
	}
	
	public void setAlive(boolean a) {
		alive = a;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setPosition(int posX, int posY) {
		x = posX;
		y = posY;
	}
	
	public int getPositionX() {
		return x;
	}
	
	public int getPositionY() {
		return y;
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
