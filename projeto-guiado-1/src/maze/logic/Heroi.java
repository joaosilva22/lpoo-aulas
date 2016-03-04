package maze.logic;

public class Heroi extends Elemento {
	private boolean armed;
	
	public Heroi(Point pos) {
		super(pos);
		armed = false;
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public void setArmed(boolean a) {
		armed = a;
	}
}
