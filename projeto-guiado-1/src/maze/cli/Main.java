package maze.cli;

import java.util.Scanner;
import maze.logic.Jogo;
import maze.logic.JogoDragaoAdormece;
import maze.logic.JogoMovimentoAleatorio;
import maze.logic.MazeBuilder;

public class Main {
	
	// Interface com a linha de comandos
	// Os numeros 8, 2, 4, 6 correspondem a cima, baixo, direita e esquerda respectivamente
	// qualquer outro numero termina o programa
	public static void main(String[] args) {
		int move;
		Scanner s = new Scanner(System.in);
		
		MazeBuilder mb = new MazeBuilder();
		char[][] board = mb.buildMaze(9);
		Jogo game = new JogoDragaoAdormece(board);
		
		System.out.println("Labirinto inicial :");
		game.display();
		while (!game.isDone()) {
			
			boolean playerHasMoved = false;
			do {
				System.out.print("Movimento do herói > ");
				move = s.nextInt();
				switch (move) {
				case 8:
					playerHasMoved = game.moveHeroUp();
					break;
				case 2:
					playerHasMoved = game.moveHeroDown();
					break;
				case 4:
					playerHasMoved = game.moveHeroLeft();
					break;
				case 6:
					playerHasMoved = game.moveHeroRight();
					break;
				default:
					game.setDone(true);
					playerHasMoved = true;
					break;
				}
				
				if (!playerHasMoved && !game.isDone()) {
					System.out.println("Movimento ilegal! Tente de novo ...");
					game.display();
				}
			} while (!playerHasMoved);
			System.out.println("Labirinto após movimento do herói :");
			game.display();
			if ((game instanceof JogoMovimentoAleatorio) || (game instanceof JogoDragaoAdormece)) {
				System.out.println("O dragão está a mover-se ... ");
				System.out.println("Labirinto após movimento do dragão :");
				game.moveDragon();
				game.display();
			}
		}
		s.close();
		game.printEndMessage();
	}

}
