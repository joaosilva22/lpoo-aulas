package maze.logic;

public class Labirinto {
	private char[][] board;
	
	public Labirinto(char[][] maze) {
		board = maze;
	}
	
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
	
	public void updateBoardDragon(int x, int y, boolean alive, boolean overlapping, boolean sleeping) {
		if (alive) {
			if (sleeping) {
				if (overlapping)
					board[y][x] = 'f';
				else
					board[y][x] = 'd';
			} else {
				if (overlapping)
					board[y][x] = 'F';
				else
					board[y][x] = 'D';
			}
		} else {
			cleanCell(x, y);
		}
	}
	
	public void display() {
		System.out.printf("  ");
		for (int k = 0; k < board.length; k++)
			System.out.printf("%2d", k);
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			System.out.printf("%2d ", i);
			for (int j = 0; j < board[i].length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}
	
	public char getCell(int x, int y) {
		return board[y][x];
	}
	
	public Point findHeroPosition()
	{
		for(int i = 1; i < board.length - 1; i++)
			for(int j = 1; j < board[i].length - 1; j++)
				if(board[i][j] == 'H')
				{
					Point p = new Point (j,i);
					return p;
				}
		Point p2 = new Point(-1, -1);
		return p2;
	}
	
	public Point findDragonPosition()
	{
		for(int i = 1; i < board.length - 1; i++)
			for(int j = 1; j < board[i].length - 1; j++)
				if(board[i][j] == 'D')
				{
					Point p = new Point (j,i);
					return p;
				}
		Point p2 = new Point(-1, -1);
		return p2;
	}
	
	public Point findSwordPosition()
	{
		for(int i = 1; i < board.length - 1; i++)
			for(int j = 1; j < board[i].length - 1; j++)
				if(board[i][j] == 'E')
				{
					Point p = new Point (j,i);
					return p;
				}
		Point p2 = new Point(-1, -1);
		return p2;
	}
}
