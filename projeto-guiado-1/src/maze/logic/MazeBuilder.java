package maze.logic;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder {
	private char[][] maze;
	private char[][] visited;
	private Stack history;
	private Random r;
	
	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		if (size % 2 == 0) throw new IllegalArgumentException();
		
		maze = new char[size][size];
		visited = new char[(size-1)/2][(size-1)/2];
		history = new Stack<Point>();
		r = new Random();
		
		initMaze();
		
		while (!history.empty()) {

		}
		
		return maze;
	}
	
	private void initMaze() {
		for (char[] line : maze)
			Arrays.fill(line, 'x');
		
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[i].length; j++)
				if ((i % 2 != 0) && (j % 2 != 0))
					maze[i][j] = ' ';
		
		markInitialCell();
	}
	
	private void markInitialCell() {		
		Point p = new Point(-1,-1);
		do {
			p.set(r.nextInt((maze.length-1)/2) * 2 + 1, r.nextInt((maze.length-1)/2) * 2 + 1);
		} while (p.getX() != 1 && p.getY() != 1 && p.getX() != maze.length - 2 && p.getY() != maze.length - 2);
		// maze[p.getX()][p.getY()] = '+';
		if (p.getX() == 1)
			maze[p.getX() - 1][p.getY()] = 's';
		else if (p.getX() == maze.length - 2) 
			maze[p.getX() + 1][p.getY()] = 's';
		else if (p.getY() == 1) 
			maze[p.getX()][p.getY() - 1] = 's';
		else 
			maze[p.getX()][p.getY() + 1] = 's';
		history.push(p);
		visited[(p.getX()-1)/2][(p.getY()-1)/2] = '+';
	}
}
