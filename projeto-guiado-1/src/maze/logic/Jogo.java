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
		lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive());
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
			//lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		}
	}
	
	public void moveHeroUp() {
		if (lab.getCell(hero.getPositionX(), hero.getPositionY() - 1) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveUp();
			//lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		}	
	}
	
	public void moveHeroLeft() {
		if (lab.getCell(hero.getPositionX() - 1, hero.getPositionY()) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveLeft();
			//lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		}	
	}
	
	public void moveHeroRight() {
		if (lab.getCell(hero.getPositionX() + 1, hero.getPositionY()) != 'x') {
			lab.cleanCell(hero.getPositionX(), hero.getPositionY());
			hero.moveRight();
			//lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
		}
	}
	
	public void updateGame() {
		if (dragon.inRange(hero.getPositionX(), hero.getPositionY())) {
			if (hero.isArmed()) {
				dragon.setAlive(false);
			} else {
				hero.setAlive(false);
				fs = FinalState.DEFEAT;
				done = true;
			}
			lab.updateBoardDragon(dragon.getPositionX(), dragon.getPositionY(), dragon.isAlive());
		}
		
		if (sword.inRange(hero.getPositionX(), hero.getPositionY())) {
			hero.setArmed(true);
			lab.updateBoardSword(sword.getPositionX(), sword.getPositionY(), sword.isAlive());
		}
		
		if (!dragon.isAlive() && lab.getCell(hero.getPositionX(), hero.getPositionY()) == 's') {
			fs = FinalState.VICTORY;
			done = true;
		}
			
		lab.updateBoardHero(hero.getPositionX(), hero.getPositionY(), hero.isAlive(), hero.isArmed());
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
}