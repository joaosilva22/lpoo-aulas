package maze.logic;

public class Espada extends Elemento {
	public Espada(int x, int y) {
		super(x, y);
	}
	
	// Verifica se o elemento com coordenadas x, y esta na mesma posicao da espada
	public boolean inRange(int x, int y) {
		if (getPositionX() == x && getPositionY() == y)
			return true;
		else
			return false;
	}
}
