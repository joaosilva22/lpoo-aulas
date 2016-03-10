package maze.logic;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder {
	private static final int DRAGON_NUMBER = 2;
	
	private char[][] maze;
	private char[][] visited;
	private Stack<Point> history;
	private Random r;
	
	@Override
	public char[][] buildMaze(int size) throws IllegalArgumentException {
		if (size % 2 == 0 || size < 4) throw new IllegalArgumentException();
		
		maze = new char[size][size];
		visited = new char[(size-1)/2][(size-1)/2];
		history = new Stack<Point>();
		r = new Random();
		
		initMaze();
		
		while (!history.empty()) {
			int currentLine = history.peek().getX(), currentCol = history.peek().getY();
			int newLine = 0, newCol = 0;
			if (hasUnvisitedNeighbours(currentLine, currentCol)) {
				do {
					int rand = r.nextInt(4);
					switch (rand) {
					case 0:
						newLine = currentLine + 2;
						newCol = currentCol;
						break;
					case 1:
						newLine = currentLine - 2;
						newCol = currentCol;
						break;
					case 2:
						newLine = currentLine;
						newCol = currentCol + 2;
						break;
					case 3:
						newLine = currentLine;
						newCol = currentCol - 2;
						break;
					}
				} while (isVisited(newLine, newCol));
				maze[Math.abs(currentLine + newLine) / 2][Math.abs(currentCol + newCol) / 2] = ' ';
				markVisited(newLine, newCol);
				history.push(new Point(newLine, newCol));
			} else {
				history.pop();
			}
		}
		
		int heroX, heroY, dragonX, dragonY, swordX, swordY;
		do {
			heroX = r.nextInt(maze.length);
			heroY = r.nextInt(maze.length);
		} while (maze[heroX][heroY] != ' ');
		maze[heroX][heroY] = 'H';
		
		for (int i = 0; i < DRAGON_NUMBER; i++) {
			do {
				dragonX = r.nextInt(maze.length);
				dragonY = r.nextInt(maze.length);
			} while (maze[dragonX][dragonY] != ' ' || isAdjacent(heroX, heroY, dragonX, dragonY));
			maze[dragonX][dragonY] = 'D';
		}
		
		do {
			swordX = r.nextInt(maze.length);
			swordY = r.nextInt(maze.length);
		} while (maze[swordX][swordY] != ' ');
		maze[swordX][swordY] = 'E';
		
		return maze;
	}
	
	private void initMaze() {
		for (char[] line : maze)
			Arrays.fill(line, 'x');
		
		for (char[] line : visited)
			Arrays.fill(line, '.');
		
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
	
	private boolean hasUnvisitedNeighbours(int line, int col) {
		int lowerLine = line - 2, lowerCol = col - 2, upperLine = line + 2, upperCol = col + 2;
		boolean unvis = false;
		if ((!isVisited(line, lowerCol)) || (!isVisited(line, upperCol)) || (!isVisited(lowerLine, col)) || (!isVisited(upperLine, col)))
			unvis = true;
		return unvis;
	}
	
	private boolean isVisited(int line, int col) {
		boolean vis = false;
		int visLine = (line - 1) / 2, visCol = (col - 1) / 2;
		if (visLine >= 0 && visLine < visited.length && visCol >= 0 && visCol < visited.length) {
			if (visited[visLine][visCol] == '+')
				vis = true;
		} else {
			vis = true;
		}
		return vis;
	}
	
	private void markVisited(int line, int col) {
		visited[(line - 1)/2][(col - 1)/2] = '+';
	}
	
	private boolean isAdjacent(int x1, int y1, int x2, int y2) {
		boolean adj = false;
		if (x1 == x2) {
			if (Math.abs(y1 - y2) == 1)
				adj = true;
		} else if (y1 == y2) {
			if (Math.abs(x1 - x2) == 1)
				adj = true;
		}
		return adj;
	}
}
