package maze.logic;

public class Labirinto {
	private char[][] board = {
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 's' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', 'x', ' ', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }
	};
	
	public void cleanCell(int x, int y) {
		board[y][x] = ' ';
	}
	
	public void updateBoardHero(int x, int y, boolean alive, boolean armed) {
		if (alive) {
			if (armed)
				board[y][x] = 'A';
			else 
				board[y][x] = 'H';
		} else {
			cleanCell(x, y);
		}
	}
	
	public void updateBoardSword(int x, int y, boolean alive) {
		if (alive)
			board[y][x] = 'E';
		else
			cleanCell(x, y);
	}
	
	public void updateBoardDragon(int x, int y, boolean alive) {
		if (alive)
			board[y][x] = 'D';
		else
			cleanCell(x, y);
	}
	
	public void display() {
		for (char[] line : board) {
			for (char cell : line) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}
	
	public char getCell(int x, int y) {
		return board[y][x];
	}
}
