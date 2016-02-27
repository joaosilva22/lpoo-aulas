package maze.cli;

import java.util.Scanner;
import maze.logic.Jogo;

public class Main {
	
	// Interface com a linha de comandos
	// 8, 2, 4, 6 correspondem a cima, baixo, direita e esquerda respectivamente
	public static void main(String[] args) {
		int move;
		Scanner s = new Scanner(System.in);
		
		Jogo game = new Jogo();
		game.display();
		while (!game.isDone()) {
			move = s.nextInt();
			switch (move) {
			case 8:
				game.moveHeroUp();
				break;
			case 2:
				game.moveHeroDown();
				break;
			case 4:
				game.moveHeroLeft();
				break;
			case 6:
				game.moveHeroRight();
				break;
			default:
				game.setDone(true);
				break;
			}
			game.updateGame();
			game.display();
		}
		s.close();
		game.printEndMessage();
	}

}
