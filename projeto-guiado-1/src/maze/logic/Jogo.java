package maze.logic;

import java.util.ArrayList;

public class Jogo {
	private Labirinto lab;
	private Heroi hero;
	private Espada sword;
	
	private ArrayList<Dragao> dragons;
	
	private boolean done;
	public enum FinalState { VICTORY, DEFEAT, QUIT }
	private FinalState fs;
	
	public Jogo(char[][] board) {
		lab = new Labirinto(board);
		hero = new Heroi(lab.findHeroPosition());
		dragons = new ArrayList<Dragao>();
		ArrayList<Point> dragonPoints = lab.findDragonPosition();
		for (int i = 0; i < dragonPoints.size(); i++) {
			dragons.add(new Dragao(dragonPoints.get(i)));
		}
		sword = new Espada(lab.findSwordPosition());
		done = false;
		
		fs = FinalState.QUIT;
		
		lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		for (Dragao dragon : dragons)
			lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive(), dragon.isOverlapping(), dragon.isSleeping());
		lab.updateBoardSword(sword.getPositionX(), sword.getPositionY(), sword.isAlive());
	}
	
	public void display() {
		lab.display();
		System.out.println();
	}

	public boolean moveHeroDown() {
		if (lab.getCell(hero.getPositionX(), hero.getPositionY() + 1) == ' ' || lab.getCell(hero.getPositionX(), hero.getPositionY() + 1) == 'E' || (lab.getCell(hero.getPositionX(), hero.getPositionY() + 1) == 's' && !isAnyDragonAlive())) {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveDown();
			updateGame();
			return true;
		}
		return false;
	}
	
	public boolean moveHeroUp() {
		if (lab.getCell(hero.getPositionX(), hero.getPositionY() - 1) == ' ' || lab.getCell(hero.getPositionX(), hero.getPositionY() - 1) == 'E' || (lab.getCell(hero.getPositionX(), hero.getPositionY() - 1) == 's' && !isAnyDragonAlive())) {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveUp();
			updateGame();
			return true;
		}
		return false;
	}
	
	public boolean moveHeroLeft() {
		if (lab.getCell(hero.getPositionX() - 1, hero.getPositionY()) == ' ' || lab.getCell(hero.getPositionX() - 1, hero.getPositionY()) == 'E' || (lab.getCell(hero.getPositionX()  - 1, hero.getPositionY()) == 's' && !isAnyDragonAlive())) {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveLeft();
			updateGame();
			return true;
		}
		return false;
	}
	
	public boolean moveHeroRight() {
		if (lab.getCell(hero.getPositionX() + 1, hero.getPositionY()) == ' ' || lab.getCell(hero.getPositionX() + 1, hero.getPositionY()) == 'E' || (lab.getCell(hero.getPositionX() + 1, hero.getPositionY()) == 's' && !isAnyDragonAlive())) {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveRight();
			updateGame();
			return true;
		}
		return false;
	}
	
	public void moveDragon() {}
	
	public void updateGame() {
		updateSword();
		updateDragon();
		updateHero();
	}
	
	protected void updateHero() {
		updateInteractions();
		lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
	}
	
	protected void updateDragon() {
		for (Dragao dragon : dragons) {
			updateInteractions();
			lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive(), dragon.isOverlapping(), dragon.isSleeping());
		}
	}
	
	protected void updateSword() {
		updateInteractions();
		lab.updateBoardSword(sword.getPositionX(), sword.getPositionY(), sword.isAlive());
	}
	
	protected void updateInteractions() {
		if (sword.inRange(hero.getPositionX(), hero.getPositionY()) && sword.isAlive()) {
			hero.setArmed(true);
			sword.setAlive(false);
		}
		for (Dragao dragon : dragons) {
			if (dragon.inRange(hero.getPositionX(), hero.getPositionY())) {
				if (!hero.isArmed()) {
					if (!dragon.isSleeping()) {
						hero.setAlive(false);
						fs = FinalState.DEFEAT;
						done = true;
					}
				} else {
					dragon.setAlive(false);
				}
			}
			if (sword.inRange(dragon.getPositionX(), dragon.getPositionY()))
				dragon.setOverlapping(true);
			else
				dragon.setOverlapping(false);
			if (!dragon.isAlive() && lab.getCell(hero.getPositionX(), hero.getPositionY()) == 's' && hero.isArmed()) {
				fs = FinalState.VICTORY;
				done = true;
			}
		}
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean d) {
		done = d;
	}
	
	public void printEndMessage() {
		if (fs == FinalState.DEFEAT) System.out.println("Derrota, o dragao venceu...");
		if (fs == FinalState.VICTORY) System.out.println("Vitoria, o heroi venceu!");
		if (fs == FinalState.QUIT) System.out.println("Jogo terminado pelo utilizador.");
	}
	
	public ArrayList<Dragao> getDragon() {
		return dragons;
	}
	
	public Labirinto getLab() {
		return lab;
	}
	
	public Heroi getHero() {
		return hero;
	}
	
	public boolean isAnyDragonAlive() {
		boolean answer = false;
		for (Dragao dragon : dragons)
			if (dragon.isAlive())
				answer = true;
		return answer;
	}
	
	public int getNumberOfAliveDragons() {
		int count = 0;
		for (Dragao dragon : dragons)
			if (dragon.isAlive())
				count++;
		return count;
	}
}