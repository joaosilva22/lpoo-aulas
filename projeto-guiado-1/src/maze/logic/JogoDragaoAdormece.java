package maze.logic;

import java.util.Random;

public class JogoDragaoAdormece extends JogoMovimentoAleatorio {
	private static final float WAKING_CHANCE = 0.2f;
	private static final float SLEEPING_CHANCE = 0.2f;
	private Random s;
	
	public JogoDragaoAdormece(char[][] board) {
		super(board);
		s = new Random();
	}
	
	public void moveDragon() {
		if (!getDragon().isSleeping()) {
			float sleepingRoll = s.nextFloat();
			if (sleepingRoll < SLEEPING_CHANCE) {
				getDragon().setSleeping(true);
			}
			else {
				boolean dragonMoved = false;
				while (!dragonMoved) {
					dragonMoved = moveDragonRandom();
				}
			}
		} else {
			float wakingRoll = s.nextFloat();
			if (wakingRoll < WAKING_CHANCE)
				getDragon().setSleeping(false);
			super.updateDragon();
		}
		updateGame();
	}
}
