package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Jogo;
import maze.logic.JogoDragaoAdormece;
import maze.logic.JogoMovimentoAleatorio;

public class TestMazeWithMovingDragon {
	private char[][] board = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}				  
	};
	
	private boolean isAdjacent(int x1, int y1, int x2, int y2) {
		boolean adj = false;
		if (x1 == x2) {
			if (Math.abs(y1 - y2) == 1)
				adj = true;
		} else if (y1 == y2) {
			if (Math.abs(x1 - x2) == 1)
				adj = true;
		}
		return adj;
	}

	@Test(timeout=1000)
	public void testDragonMeetsUnarmedPlayer() {
		Jogo j = new JogoMovimentoAleatorio(board);
		assertEquals(true, j.getHero().isAlive());
		assertEquals(true, j.getDragon().get(0).isAlive());
		boolean dragonMetUnarmedPlayer = false;
		while (!dragonMetUnarmedPlayer) {
			j.moveDragon();
			if (isAdjacent(j.getHero().getPositionX(), j.getHero().getPositionY(), j.getDragon().get(0).getPositionX(), j.getDragon().get(0).getPositionY())) {
				assertEquals(false, j.getHero().isAlive());
				assertEquals(true, j.getDragon().get(0).isAlive());
				dragonMetUnarmedPlayer = true;
			}
		}
	}
	
	@Test(timeout=1000)
	public void testDragonMeetsArmedPlayer() {
		Jogo j = new JogoMovimentoAleatorio(board);
		j.getHero().setArmed(true);
		assertEquals(true, j.getHero().isArmed());
		assertEquals(true, j.getHero().isAlive());
		assertEquals(true, j.getDragon().get(0).isAlive());
		boolean dragonMetArmedPlayer = false;
		while (!dragonMetArmedPlayer) {
			j.moveDragon();
			if (isAdjacent(j.getHero().getPositionX(), j.getHero().getPositionY(), j.getDragon().get(0).getPositionX(), j.getDragon().get(0).getPositionY())) {
				assertEquals(true, j.getHero().isAlive());
				assertEquals(false, j.getDragon().get(0).isAlive());
				dragonMetArmedPlayer = true;
			}
		}
	}
	
	@Test(timeout=1000)
	public void testDragonFallsAsleep() {
		Jogo j = new JogoDragaoAdormece(board);
		assertEquals(false, j.getDragon().get(0).isSleeping());
		boolean dragonFellAsleep = false;
		while (!dragonFellAsleep) {
			j.moveDragon();
			if (j.getDragon().get(0).isSleeping()) {
				assertEquals(true, j.getDragon().get(0).isSleeping());
				assertEquals(true, j.getDragon().get(0).hasJustFallenAsleep());
				dragonFellAsleep = true;
			}
		}
	}
	
	@Test(timeout=1000)
	public void testDragonWakesUp() {
		Jogo j = new JogoDragaoAdormece(board);
		j.getDragon().get(0).setSleeping(true);
		assertEquals(true, j.getDragon().get(0).isSleeping());
		boolean dragonWokeUp = false;
		while (!dragonWokeUp) {
			j.moveDragon();
			if (!j.getDragon().get(0).isSleeping()) {
				assertEquals(false, j.getDragon().get(0).isSleeping());
				assertEquals(true, j.getDragon().get(0).hasJustWokenUp());
				dragonWokeUp = true;
			}
		}
	}
	
	@Test
	public void playerMeetsSleepingDragonUnarmed() {
		Jogo j = new JogoDragaoAdormece(board);
		j.getDragon().get(0).setSleeping(true);
		assertEquals(true, j.getDragon().get(0).isSleeping());
		assertEquals(true, j.getHero().isAlive());
		j.moveHeroDown();
		assertEquals(true, j.getHero().isAlive());
	}
	
	@Test(timeout=1000)
	public void dragonOverlapsSword() {
		Jogo j = new JogoMovimentoAleatorio(board);
		assertEquals(false, j.getDragon().get(0).isOverlapping());
		boolean dragonOverSword = false;
		while (!dragonOverSword) {
			j.moveDragon();
			if (j.getDragon().get(0).isOverlapping() && j.getDragon().get(0).isAlive()) {
				assertEquals(true, j.getDragon().get(0).isOverlapping());
				assertEquals('F', j.getLab().getCell(j.getDragon().get(0).getPositionX(), j.getDragon().get(0).getPositionY()));
				dragonOverSword = true;
			}
		}
	}

}
