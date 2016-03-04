package maze.logic;

public class Jogo {
	private Labirinto lab;
	private Heroi hero;
	private Dragao dragon;
	private Espada sword;
	
	private boolean done;
	public enum FinalState { VICTORY, DEFEAT, QUIT }
	private FinalState fs;
	
	public Jogo() {
		lab = new Labirinto();
		hero = new Heroi(1, 1);
		dragon = new Dragao(5, 1);
		sword = new Espada(6, 4);
		done = false;
		
		fs = FinalState.QUIT;
		
		lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive(), dragon.isOverlapping(), dragon.isSleeping());
		lab.updateBoardSword(sword.getPositionX(), sword.getPositionY(), sword.isAlive());
	}
	
	public void display() {
		lab.display();
		System.out.println();
	}

	public void moveHeroDown() {
		if (lab.getCell(hero.getPositionX(), hero.getPositionY() + 1) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveDown();
		}
	}
	
	public void moveHeroUp() {
		if (lab.getCell(hero.getPositionX(), hero.getPositionY() - 1) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveUp();
		}	
	}
	
	public void moveHeroLeft() {
		if (lab.getCell(hero.getPositionX() - 1, hero.getPositionY()) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveLeft();
		}	
	}
	
	public void moveHeroRight() {
		if (lab.getCell(hero.getPositionX() + 1, hero.getPositionY()) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveRight();
		}
	}
	
	public void updateGame() {
		updateInteractions();
		updateSword();
		updateDragon();
		updateHero();
	}
	
	protected void updateHero() {
		lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
	}
	
	protected void updateDragon() {
		lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive(), dragon.isOverlapping(), dragon.isSleeping());
	}
	
	protected void updateSword() {
		lab.updateBoardSword(sword.getPositionX(), sword.getPositionY(), sword.isAlive());
	}
	
	protected void updateInteractions() {
		if (dragon.inRange(hero.getPositionX(), hero.getPositionY())) {
			if (!hero.isArmed()) {
				hero.setAlive(false);
				fs = FinalState.DEFEAT;
				done = true;	
			} else {
				dragon.setAlive(false);
			}
		}
		if (sword.inRange(hero.getPositionX(), hero.getPositionY()) && sword.isAlive()) {
			hero.setArmed(true);
			sword.setAlive(false);
		}
		if (sword.inRange(dragon.getPositionX(), dragon.getPositionY()))
			dragon.setOverlapping(true);
		else
			dragon.setOverlapping(false);
		if (!dragon.isAlive() && lab.getCell(hero.getPositionX(), hero.getPositionY()) == 's') {
			fs = FinalState.VICTORY;
			done = true;
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
	
	public Dragao getDragon() {
		return dragon;
	}
	
	public Labirinto getLab() {
		return lab;
	}
	
	public Heroi getHero() {
		return hero;
	}
}