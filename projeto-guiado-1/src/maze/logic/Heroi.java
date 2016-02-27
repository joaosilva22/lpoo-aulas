package maze.logic;

public class Heroi extends Elemento {
	private boolean armed;
	
	public Heroi(int x, int y) {
		super(x, y);
		armed = false;
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public void setArmed(boolean a) {
		armed = a;
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
