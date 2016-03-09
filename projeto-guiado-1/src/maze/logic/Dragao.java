package maze.logic;

import java.lang.Math;

public class Dragao extends Elemento {
	private boolean overlapping = false;
	private boolean sleeping = false;
	private boolean justWokeUp = false, justFellAsleep = false;
	
	public Dragao(Point pos) {
		super(pos);
	}
	
	// Verifica se o elemento com as coordenadas x, y esta numa celula adjacente ao dragao
	public boolean inRange(int x, int y) {
		if ( Math.abs(getPositionX() - x) <= 1 && Math.abs(getPositionY() - y) <= 1 && (getPositionX() == x || getPositionY() == y))
			return true;
		else
			return false;
	}
	
	public boolean isOverlapping() {
		return overlapping;
	}
	
	public void setOverlapping(boolean b) {
		overlapping = b;
	}
	
	public boolean isSleeping() {
		return sleeping;
	}
	
	public void setSleeping(boolean b) {
		sleeping = b;
	}
	
	public void setJustWokeUp(boolean b) {
		justWokeUp = b;
	}
	
	public void setJustFellAsleep(boolean b) {
		justFellAsleep = b;
	}
	
	public boolean hasJustWokenUp() {
		return justWokeUp;
	}
	
	public boolean hasJustFallenAsleep() {
		return justFellAsleep;
	}
}
