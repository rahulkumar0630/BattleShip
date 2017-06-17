/**
 * Destroyer.java  5/17/2017
 *
 * Rahul Kumar
 * AP Computer Science
 * Mrs. Hitch 3rd Period
 *
 *
 */

//Each method does what the "Ship" interface, but goes to 2 positions for the "X"

public class Destroyer implements Ship
{

	private int numofhits;


	public Destroyer()
	{
		numofhits = 0;
	}

	public int[][] init(int[][] board)
	{

		boolean condition = false;

		while(!condition) //repeats till Destroyer fits somewhere
		{
			int randfordir = (int)(Math.random() * 3); //chooses if board should be inited up,down,left,right

			System.out.println(randfordir);

			int randomcol = (int)(Math.random() * board[0].length); //gets a random col pos
			int randomrow = (int)(Math.random() * board.length); //gets a random row pos

			if((randfordir == 0) && (goRightfromPos(randomrow, randomcol, board) != null))
			{
				board = goRightfromPos(randomrow, randomcol, board); //inits Destroyer to the right
				condition = true;

			}
			else if(randfordir == 1  && (goLeftfromPos(randomrow, randomcol , board) != null))
			{
				board = goLeftfromPos(randomrow, randomcol, board); //inits Destroyer to the left
				System.out.println("Left");
				condition = true;
			}

			else if(randfordir == 2 && (goUpfromPos(randomrow, randomcol, board) != null))
			{
				board = goUpfromPos(randomrow, randomcol, board); //inits Destroyer up
				condition = true;
			}
			else if(randfordir == 3 && (goDownfromPos(randomrow, randomcol, board) != null))
			{
				board = goDownfromPos(randomrow, randomcol, board); ////inits Destroyer down
					condition = true;
			}
		}

		return board;
	}


	public int[][] goRightfromPos(int row, int col, int[][] paraboard)
	{

		int amountRight = paraboard[0].length - col; //finds how many spots are the right of the pos

		if(amountRight >= 2) //if its enough for a Destroyer
		{
			int count = 0;
			for(int i = col; i < col + 2; i++) //if thre is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 2)
			{
				for(int i = col; i < col + 2; i++)
				{
					paraboard[row][i] = 2; //setting the positions "2" for Destroyer
				}
			return paraboard; //returns new board
			}
			return null; //returns null of there isnt enough free space
		}
		else
		{
			return null; //not enough pos to the right
		}

	}

	public int[][] goLeftfromPos(int row, int col, int[][] paraboard)
	{


		if(col >= 1) //if there is enough spots to the left 
		{
			int count = 0;
			for(int i = col; i > col - 2; i--) //if there is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 2)
			{
				for(int i = col; i > col - 2; i--)
				{
					paraboard[row][i] = 2; //setting the positions "2" for Destroyer
				}
			return paraboard; //returns new board
			}
			return null; //returns null of there isnt enough free space
		}
		else
			return null; //not enough pos to the left

	}

	public int[][] goUpfromPos(int row, int col, int[][] paraboard)
	{


		if(row >= 1) //if there is enough spots Up
		{
			int count = 0;
			for(int i = row; i > row - 2; i--) //if there is free space
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 2)
			{
				for(int i = row; i > row - 2; i--)
				{
					paraboard[i][col] = 2; //setting the positions "2" for Destroyer
				}
			return paraboard; //returns new board
			}
			return null; //returns null of there isnt enough free space
		}
		else
			return null; //not enough pos Up

	}

	public int[][] goDownfromPos(int row, int col, int[][] paraboard)
	{

		int amountDown = paraboard.length - row; //finds how many spots are the right of the pos

		if(amountDown >= 2) //if there is enough spots down
		{
			int count = 0;
			for(int i = row; i < row + 2; i++) //if there is free space
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 2)
			{
				for(int i = row; i < row + 2; i++)
				{
					paraboard[i][col] = 2; //setting the positions "2" for Destroyer
				}
			return paraboard; //returns new board
			}
			return null; //returns null of there isnt enough free space
		}
		else
			return null; //not enough pos down

	}

	public void incrementHits()
	{
		numofhits++;
	}

	public boolean hasSank()
	{
		if(numofhits == 2)
			return true;
		else
			return false;
	}
	
	public void resetShip()
	{
		numofhits = 0;
	}

}