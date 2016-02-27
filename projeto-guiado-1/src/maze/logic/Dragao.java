package maze.logic;

import java.lang.Math;

public class Dragao extends Elemento {
	public Dragao(int x, int y) {
		super(x, y);
	}
	
	// Verifica se o elemento com as coordenadas x, y esta numa celula adjacente ao dragao
	public boolean inRange(int x, int y) {
		if ( Math.abs(getPositionX() - x) <= 1 && Math.abs(getPositionY() - y) <= 1 && (getPositionX() == x || getPositionY() == y))
			return true;
		else
			return false;
	}
}
