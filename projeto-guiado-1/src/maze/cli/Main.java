package maze.cli;

import java.util.Scanner;
import maze.logic.Jogo;
import maze.logic.JogoDragaoAdormece;
import maze.logic.JogoMovimentoAleatorio;
import maze.logic.MazeBuilder;

public class Main {
	
	// Interface com a linha de comandos
	// Os numeros 8, 2, 4, 6 correspondem a cima, baixo, direita e esquerda respectivamente
	// qualquer outro input termina o programa
	public static void main(String[] args) {
		int move;
		Scanner s = new Scanner(System.in);
		
		MazeBuilder mb = new MazeBuilder();
		Jogo game = new Jogo(mb.buildMaze(7));
		
		System.out.println("Modos de jogo :");
		System.out.println("1 - Jogo normal");
		System.out.println("2 - Dragão c/ movimento aleatório");
		System.out.println("3 - Dragão pode adormecer:");
		System.out.print("Escolha o tipo de jogo > ");
		int gameMode = s.nextInt();
		System.out.println();
		System.out.print("Escolha a dimensão do labirinto (número ímpar) > ");
		int labSize = s.nextInt();
		System.out.println();
		
		switch(gameMode) {
		case 1:
			game = new Jogo(mb.buildMaze(labSize));
			break;
		case 2:
			game = new JogoMovimentoAleatorio(mb.buildMaze(labSize));
			break;
		case 3:
			game = new JogoDragaoAdormece(mb.buildMaze(labSize));
			break;
		default:
			System.out.println("That is not a valid game mode. Game exiting ...");
			game.setDone(true);
			break;
		}
		
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
				if (game.getDragon().isAlive()) {
					System.out.println("O dragão está a mover-se ... ");
					game.moveDragon();
					if (game instanceof JogoDragaoAdormece) {
						if (game.getDragon().hasJustFallenAsleep())
							System.out.println("O dragão adormeceu!");
						if (game.getDragon().hasJustWokenUp())
							System.out.println("O dragão acordou!");
					}
					System.out.println("Labirinto após movimento do dragão :");
					game.display();
				}
			}
		}
		s.close();
		game.printEndMessage();
	}

}
