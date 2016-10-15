import java.lang.Math;
import javax.swing.*;

public class Minesweeper {

	public static final int SIZE = 9;
	public static final int BOMBS = 10;
	public static final int UNCLICKED = 0;
	public static final int CLICKED = -1;
	public static final int MINE = -2;
	public static final int FLAG = -3;
	public static final int QUESTION_MARK = -4;
	public static int [][] hiddenGrid = new int[SIZE][SIZE];
	public static int [][] visibleGrid = new int[SIZE][SIZE];
	int score = 0;
	
	public static void main(String []args) {
		
		hiddenGrid = bombGenerator(hiddenGrid);
		hiddenGrid = numbersGenerator(hiddenGrid);
		showGrid(hiddenGrid);
	}
	
	public static int[][] bombGenerator(int [][] hiddenGrid) {
		
		int bombNumber=0; 
		while (bombNumber<BOMBS) {
			
			int rowIndex = (int) (Math.random()*SIZE);
			int columnIndex = (int) (Math.random()*SIZE);
			//System.out.println("Row index: " + rowIndex + "    Column index: " + columnIndex);
			if (hiddenGrid [rowIndex][columnIndex] != MINE) {
				
				hiddenGrid [rowIndex][columnIndex] = MINE;
				bombNumber++;
			}
		}
		
		return hiddenGrid;
	}
	
/*	public static int[][] bombGenerator(int [][] hiddenGrid) {
		
		hiddenGrid [0][3] = MINE;
		hiddenGrid [1][4] = MINE;
		hiddenGrid [2][2] = MINE;
		hiddenGrid [4][4] = MINE;
	
		return hiddenGrid;
	}
*/	
	public static int[][] numbersGenerator(int [][] hiddenGrid) {
		
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				int bombs = 0;
				if (hiddenGrid[row][column] != MINE) {
					bombs = numberOfBombsAround(hiddenGrid, row, column);
					hiddenGrid[row][column] = bombs;
				}
			}
		}
		
		return hiddenGrid;
	}
	
	public static int numberOfBombsAround(int [][] hiddenGrid, int centreRow, int centreColumn) {
	
		int bombCounter = 0;
		
		System.out.println("centre crap : " + centreRow + centreColumn);
		for (int row = centreRow-1; row < centreRow+2; row++) {
			for (int column = centreColumn - 1; column < centreColumn+2 ; column++) {
				System.out.println("Row: " + row + "    column: " + column + "   " );
				boolean rowInBounds = (row >= 0) && (row < SIZE);
				boolean columnInBounds = (column >= 0) && (column < SIZE);
				boolean notCentreElement = (row != centreRow) || (column != centreColumn) ;
				
				if (notCentreElement && rowInBounds && columnInBounds) {
					System.out.println("element checked");
					if (hiddenGrid[row][column] == MINE) {
						System.out.println("Bomb added");
						bombCounter++;
					}
				}
			}
		}
		
		return bombCounter;
	}
	
	
	
	public static void showGrid(int [][] gridToPrint)
	{
		for (int row = 0; row < SIZE; row++) {
			for (int column = 0; column < SIZE; column++) {
				System.out.print(gridToPrint [row][column] + "\t");
			}
			System.out.println();
		}
	}
	
}
