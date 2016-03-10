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
		for (Dragao dragon : getDragon()) {
			dragon.setJustWokeUp(false);
			dragon.setJustFellAsleep(false);
			if (!dragon.isSleeping()) {
				float sleepingRoll = s.nextFloat();
				if (sleepingRoll < SLEEPING_CHANCE) {
					dragon.setSleeping(true);
					dragon.setJustFellAsleep(true);
				}
				else {
					boolean dragonMoved = false;
					while (!dragonMoved) {
						dragonMoved = moveDragonRandom(dragon);
					}
				}
			} else {
				float wakingRoll = s.nextFloat();
				if (wakingRoll < WAKING_CHANCE) {
					dragon.setSleeping(false);
					dragon.setJustWokeUp(true);
				}
			}
			updateGame();
		}
	}
}
