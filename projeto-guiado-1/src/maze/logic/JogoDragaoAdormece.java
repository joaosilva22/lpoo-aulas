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
	
	public void updateGame() {
		updateInteractions();
		updateSword();
		updateDragon();
		updateHero();
	}
	
	protected void updateDragon() {
		if (!getDragon().isSleeping()) {
			float sleepingRoll = s.nextFloat();
			if (sleepingRoll < SLEEPING_CHANCE) {
				getDragon().setSleeping(true);
				getLab().updateBoardDragon(getDragon().getPositionX(), getDragon().getPositionY(),getDragon().isAlive(), getDragon().isOverlapping(), getDragon().isSleeping());
			} else {
				super.updateDragon();
			}
		} else {
			float wakingRoll = s.nextFloat();
			if (wakingRoll < WAKING_CHANCE)
				getDragon().setSleeping(false);
			getLab().updateBoardDragon(getDragon().getPositionX(), getDragon().getPositionY(),getDragon().isAlive(), getDragon().isOverlapping(), getDragon().isSleeping());
		}
	}
}
