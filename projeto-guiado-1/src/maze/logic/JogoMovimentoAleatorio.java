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
		if (!getDragon().isSleeping()) {
			boolean dragonMoved = false;
			while (!dragonMoved) {
				dragonMoved = moveDragonRandom();
			}
		}
		updateGame();
	}
	
	protected boolean moveDragonRandom() {
		int direction = r.nextInt(4);
		boolean success = false;
		if (direction == 0) {
			if (getLab().getCell(getDragon().getPositionX(), getDragon().getPositionY() - 1) != 'x'
				&& getLab().getCell(getDragon().getPositionX(), getDragon().getPositionY() - 1) != 's') {
				success = true;
				getLab().cleanCell(getDragon().getPositionX(), getDragon().getPositionY());
				getDragon().moveUp();
			}
		} else if (direction == 1) {
			if (getLab().getCell(getDragon().getPositionX(), getDragon().getPositionY() + 1) != 'x'
				&& getLab().getCell(getDragon().getPositionX(), getDragon().getPositionY() + 1) != 's') {
				success = true;
				getLab().cleanCell(getDragon().getPositionX(), getDragon().getPositionY());
				getDragon().moveDown();
			}
		} else if (direction == 2) {
			if (getLab().getCell(getDragon().getPositionX() - 1, getDragon().getPositionY()) != 'x'
				&& getLab().getCell(getDragon().getPositionX() - 1, getDragon().getPositionY()) != 's') {
				success = true;
				getLab().cleanCell(getDragon().getPositionX(), getDragon().getPositionY());
				getDragon().moveLeft();
			}
		} else if (direction == 3) {
			if (getLab().getCell(getDragon().getPositionX() + 1, getDragon().getPositionY()) != 'x'
				&& getLab().getCell(getDragon().getPositionX() + 1, getDragon().getPositionY()) != 's') {
				success = true;
				getLab().cleanCell(getDragon().getPositionX(), getDragon().getPositionY());
				getDragon().moveRight();
			}
		}
		return success;
	}
}
