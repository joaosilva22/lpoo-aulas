package maze.logic;

import java.util.Random;

public class JogoMovimentoAleatorio extends Jogo {
	private Random r;
	
	public JogoMovimentoAleatorio(char[][] board) {
		super(board);
		r = new Random();
	}
	
	public void updateGame() {
		updateSword();
		updateDragon();
		updateHero();
	}
	
	public void moveDragon() {
		for (Dragao dragon : getDragon()) {
			if (!dragon.isSleeping()) {
				boolean dragonMoved = false;
				while (!dragonMoved) {
					dragonMoved = moveDragonRandom(dragon);
				}
			}
			updateGame();
		}
	}
	
	protected boolean moveDragonRandom(Dragao dragon) {
		int direction = r.nextInt(4);
		boolean success = false;
		if (direction == 0) {
			if (getLab().getCell(dragon.getPositionX(), dragon.getPositionY() - 1) != 'X'
				&& getLab().getCell(dragon.getPositionX(), dragon.getPositionY() - 1) != 'S') {
				success = true;
				getLab().cleanCell(dragon.getPositionX(), dragon.getPositionY());
				dragon.moveUp();
			}
		} else if (direction == 1) {
			if (getLab().getCell(dragon.getPositionX(), dragon.getPositionY() + 1) != 'X'
				&& getLab().getCell(dragon.getPositionX(), dragon.getPositionY() + 1) != 'S') {
				success = true;
				getLab().cleanCell(dragon.getPositionX(), dragon.getPositionY());
				dragon.moveDown();
			}
		} else if (direction == 2) {
			if (getLab().getCell(dragon.getPositionX() - 1, dragon.getPositionY()) != 'X'
				&& getLab().getCell(dragon.getPositionX() - 1, dragon.getPositionY()) != 'S') {
				success = true;
				getLab().cleanCell(dragon.getPositionX(), dragon.getPositionY());
				dragon.moveLeft();
			}
		} else if (direction == 3) {
			if (getLab().getCell(dragon.getPositionX() + 1, dragon.getPositionY()) != 'X'
				&& getLab().getCell(dragon.getPositionX() + 1, dragon.getPositionY()) != 'S') {
				success = true;
				getLab().cleanCell(dragon.getPositionX(), dragon.getPositionY());
				dragon.moveRight();
			}
		}
		return success;
	}
}
