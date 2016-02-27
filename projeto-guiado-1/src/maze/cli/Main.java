package maze.cli;

import java.util.Scanner;
import maze.logic.Jogo;
import maze.logic.JogoMovimentoAleatorio;

public class Main {
	
	// Interface com a linha de comandos
	// Os numeros 8, 2, 4, 6 correspondem a cima, baixo, direita e esquerda respectivamente
	// qualquer outro numero termina o programa
	public static void main(String[] args) {
		int move;
		Scanner s = new Scanner(System.in);
		Jogo game = new JogoMovimentoAleatorio();
		
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
