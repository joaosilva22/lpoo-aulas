package maze.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

import maze.logic.Dragao;
import maze.logic.Jogo;
import maze.logic.JogoDragaoAdormece;
import maze.logic.JogoMovimentoAleatorio;
import maze.logic.MazeBuilder;

public class Main {
	
	// Interface com a linha de comandos
	// Os numeros 8, 2, 4, 6 correspondem a cima, baixo, direita e esquerda respectivamente
	// qualquer outro input termina o programa
	public static void main(String[] args) {
		char[][] maze = {};
		MazeBuilder mb = new MazeBuilder();
		Scanner s = new Scanner(System.in);
		
		Jogo game = new Jogo(mb.buildMaze(5));
		
		System.out.println("Modos de jogo :");
		System.out.println("1 - Jogo normal");
		System.out.println("2 - Dragão c/ movimento aleatório");
		System.out.println("3 - Dragão pode adormecer:");
		
		System.out.println();
		
		boolean validGameMode = true;
		int gameMode = 1;
		do {
			validGameMode = true;
			System.out.print("Escolha o tipo de jogo > ");
			try {
				gameMode = s.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Deve introduzir um número! Por favor tente de novo ...");
				System.out.println();
				s.next();
				validGameMode = false;
			}
			
			if (gameMode < 1 || gameMode > 3) {
				System.out.println("Escolha um modo de jogo válido [1, 3] !");
				System.out.println();
				validGameMode = false;
			}
		} while (!validGameMode);
		
		System.out.println();
		
		boolean numberOfDragonsChosen = true;
		int numberOfDragons = 1;
		do {
			boolean validInput = true;
			do {
				validInput = true;
				System.out.print("Escolha o número de dragões [1, 4] > ");
				try {
					numberOfDragons = s.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Deve introduzir um número! Por favor tente de novo ...");
					System.out.println();
					s.next();
					validInput = false;
				}
			} while (!validInput);
			
			numberOfDragonsChosen = true;
			try {
				mb = new MazeBuilder(numberOfDragons);
			} catch (IllegalArgumentException e) {
				System.out.println("Número ilegal de dragões, por favor tente de novo ...");
				System.out.println();
				numberOfDragonsChosen = false;
			}
		} while (!numberOfDragonsChosen);
		
		System.out.println();
		
		boolean labSizeChosen = true;
		int labSize = 5;
		do {
			boolean validInput = true;
			do {
				validInput = true;
				System.out.print("Escolha a dimensão do labirinto (número ímpar) > ");
				try {
					labSize = s.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Deve introduzir um número! Por favor tente de novo ...");
					System.out.println();
					s.next();
					validInput = false;
				}
			} while (!validInput);
			
			labSizeChosen = true;
			try {
				maze = mb.buildMaze(labSize);
			} catch (IllegalArgumentException e) {
				System.out.println("Tamanho inválido, por favor tente de novo ...");
				System.out.println();
				labSizeChosen = false;
			}
		} while (!labSizeChosen);
		
		System.out.println();
		
		switch(gameMode) {
		case 1:
			game = new Jogo(maze);
			break;
		case 2:
			game = new JogoMovimentoAleatorio(maze);
			break;
		case 3:
			game = new JogoDragaoAdormece(maze);
			break;
		}
		
		System.out.println("Labirinto inicial ( " + labSize + "x" + labSize + ", " + numberOfDragons + " dragões ) :");
		System.out.println("[ CONTROLOS : w - cima, a - esquerda, s - baixo, d - direita, exit - sair ]");
		game.display();
		while (!game.isDone()) {
			
			boolean playerHasMoved = false;
			String move;
			boolean inputDoesntMatch = false;
			do {
				System.out.print("Movimento do herói > ");
				move = s.next();
				inputDoesntMatch = false;
				
				switch (move) {
					case "w":
						playerHasMoved = game.moveHeroUp();
						break;
					case "s":
						playerHasMoved = game.moveHeroDown();
						break;
					case "a":
						playerHasMoved = game.moveHeroLeft();
						break;
					case "d":
						playerHasMoved = game.moveHeroRight();
						break;
					case "exit":
						game.setDone(true);
						playerHasMoved = true;
						break;
					default:
						inputDoesntMatch = true;
						playerHasMoved = false;
						break;
				}
				
				if (!playerHasMoved && !game.isDone()) {
					if (inputDoesntMatch) {
						System.out.println("Comando inválido. Tente de novo ...");
						System.out.println("[ CONTROLOS : w - cima, a - esquerda, s - baixo, d - direita, exit - sair ]");
						game.display();
					}
					else {
						System.out.println("Movimento ilegal! Tente de novo ...");
						game.display();
					}
				}
			} while (!playerHasMoved);
			
			if (!game.isDone()) {
				System.out.println("Labirinto após movimento do herói :");
				game.display();
				if ((game instanceof JogoMovimentoAleatorio) || (game instanceof JogoDragaoAdormece)) {
					if (game.isAnyDragonAlive()) {
						if (game.getNumberOfAliveDragons() == 1)
							System.out.println("O dragão está a mover-se ... ");
						else
							System.out.println("Os dragões estão a mover-se ... ");
						game.moveDragon();
						
						for (Dragao dragon : game.getDragon()) {
							if (game instanceof JogoDragaoAdormece) {
								if (dragon.hasJustFallenAsleep())
									if (game.getNumberOfAliveDragons() == 1)
										System.out.println("O dragão adormeceu!");
									else
										System.out.println("Um dragão adormeceu!");
								if (dragon.hasJustWokenUp())
									if (game.getNumberOfAliveDragons() == 1)
										System.out.println("O dragão acordou!");
									else
										System.out.println("Um dragão acordou!");
							}
						}
						
						System.out.println("Labirinto após movimento do dragão :");
						game.display();
					}
				}
			}
		}
		s.close();
		game.printEndMessage();
	}

}
