package maze.test;

import static org.junit.Assert.*;
import org.junit.Test;
import maze.logic.*;

public class TestMazeWithStaticDragon {
	private char[][] board1 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', ' ', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', 'E', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}				  
	};
	
	private char[][] board2 = {
			{'X', 'X', 'X', 'X', 'X'},
			{'X', ' ', 'E', 'H', 'S'},
			{'X', ' ', 'X', ' ', 'X'},
			{'X', ' ', ' ', 'D', 'X'},
			{'X', 'X', 'X', 'X', 'X'}				  
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
		assertEquals(true, j.getHero().isArmed());
	}
		
	@Test
	public void testHeroDies() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.getHero().isArmed());
		j.moveHeroDown();
		assertEquals(false, j.getHero().isAlive());
	}
	
	@Test
	public void testDragonDies() {
		Jogo j = new Jogo(board2);
		assertEquals(true, j.getDragon().get(0).isAlive());
		j.getHero().setArmed(true);
		j.moveHeroDown();
		assertEquals(false, j.getDragon().get(0).isAlive());
	}
	
	@Test
	public void testHeroWins() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.isDone());
		j.getDragon().get(0).setAlive(false);
		j.getHero().setArmed(true);
		j.moveHeroRight();
		assertEquals(true, j.isDone());
	}
	
	@Test
	public void testFailExitWithoutSword() {
		Jogo j = new Jogo(board1);
		assertEquals(false, j.getHero().isArmed());
		assertEquals(false, j.isDone());
		j.moveHeroRight();
		assertEquals(false, j.isDone());
	}
	
	@Test
	public void testFailWithoutKillingDragon() {
		Jogo j = new Jogo(board1);
		j.getHero().setArmed(true);
		assertEquals(false, j.isDone());
		assertEquals(true, j.getHero().isArmed());
		j.moveHeroRight();
		assertEquals(false, j.isDone());
	}
}
