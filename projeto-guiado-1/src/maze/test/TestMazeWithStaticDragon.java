package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestMazeWithStaticDragon {
	private char[][] board1 = {
			{'x', 'x', 'x', 'x', 'x'},
			{'x', ' ', ' ', 'H', 's'},
			{'x', ' ', 'x', ' ', 'x'},
			{'x', 'E', ' ', 'D', 'x'},
			{'x', 'x', 'x', 'x', 'x'}				  
	};
	
	private char[][] board2 = {
			{'x', 'x', 'x', 'x', 'x'},
			{'x', ' ', 'E', 'H', 's'},
			{'x', ' ', 'x', ' ', 'x'},
			{'x', ' ', ' ', 'D', 'x'},
			{'x', 'x', 'x', 'x', 'x'}				  
	};

	@Test
	public void testMoveHeroToFreeCell() {
		Jogo j = new Jogo(board1);
		assertEquals(new Point(3, 1), j.getHero().getPosition());
		j.moveHeroLeft();
		assertEquals(new Point(2, 1), j.getHero().getPosition());
	}
	
	@Test
	public void testMoveHeroAgainsWall() {
		Jogo j = new Jogo(board1);
		assertEquals(new Point(3, 1), j.getHero().getPosition());
		j.moveHeroUp();
		assertEquals(new Point(3, 1), j.getHero().getPosition());
	}
	
	@Test
	public void testHeroPickUpSword() {
		Jogo j = new Jogo(board2);
		assertEquals(false, j.getHero().isArmed());
		j.moveHeroLeft();
		j.updateGame();
		assertEquals(true, j.getHero().isArmed());
	}
		
	@Test
	public void testHeroDies() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.getHero().isArmed());
		j.moveHeroDown();
		j.updateGame();
		assertEquals(false, j.getHero().isAlive());
	}
	
	/*@Test
	public void testDragonDies() {
		Jogo j = new Jogo(board2);
		assertEquals(true, j.getDragon().isAlive());
		j.getHero().setArmed(true);
		j.moveHeroDown();
		j.updateGame();
		assertEquals(false, j.getDragon().isAlive());
	}*/
	
	/*@Test
	public void testHeroWins() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.isDone());
		j.getDragon().setAlive(false);
		j.getHero().setArmed(true);
		j.moveHeroRight();
		j.updateGame();
		assertEquals(true, j.isDone());
	}*/
	
	@Test
	public void testFailExitWithoutSword() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.getHero().isArmed());
		assertEquals(false, j.isDone());
		j.moveHeroRight();
		j.updateGame();
		assertEquals(false, j.isDone());
	}
	
	@Test
	public void testFailWithoutKillingDragon() {
		Jogo j = new Jogo(board1);
		j.getHero().setArmed(true);
		assertEquals(false, j.isDone());
		assertEquals(true, j.getHero().isArmed());
		j.moveHeroRight();
		j.updateGame();
		assertEquals(false, j.isDone());
	}
}
