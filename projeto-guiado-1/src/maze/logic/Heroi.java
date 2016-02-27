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
}
