/**
 * Submarine.java  5/17/2017
 *
 * Rahul Kumar
 * AP Computer Science
 * Mrs. Hitch 3rd Period
 *
 *
 */

//Each method does what the "Ship" interface, but goes to 3 positions for the "X"

public class Submarine implements Ship
{
	private int numofhits;

	public Submarine()
	{
		numofhits = 0;
	}

	public int[][] init(int[][] board)
	{

		boolean condition = false;

		while(!condition) //repeats till Submarine fits somewhere
		{
			int randfordir = (int)(Math.random() * 3); //chooses if board should be nited up, down, left, right

			System.out.println(randfordir);

			int randomcol = (int)(Math.random() * board[0].length); //gets a random col pos
			int randomrow = (int)(Math.random() * board.length); //gets a random row pos

			if((randfordir == 0) && (goRightfromPos(randomrow, randomcol, board) != null))
			{
				board = goRightfromPos(randomrow, randomcol, board); //inits Sub to the right
				condition = true;

			}
			else if(randfordir == 1  && (goLeftfromPos(randomrow, randomcol , board) != null))
			{
				board = goLeftfromPos(randomrow, randomcol, board); //inits Sub to the left
				System.out.println("Left");
				condition = true;
			}

			else if(randfordir == 2 && (goUpfromPos(randomrow, randomcol, board) != null))
			{
				board = goUpfromPos(randomrow, randomcol, board); //inits Sub Up
				condition = true;
			}
			else if(randfordir == 3 && (goDownfromPos(randomrow, randomcol, board) != null))
			{
				board = goDownfromPos(randomrow, randomcol, board); //inits Sub Down
					condition = true;
			}
		}

		return board;
	}


	public int[][] goRightfromPos(int row, int col, int[][] paraboard)
	{

		int amountRight = paraboard[0].length - col; //finds how many spots are to the right of the pos

		if(amountRight >= 3) //if its enough for a Sub
		{
			int count = 0;
			for(int i = col; i < col + 3; i++) //if there is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 3)
			{
				for(int i = col; i < col + 3; i++)
				{
					paraboard[row][i] = 6; //setting the positions "6" for Sub
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isn't enough free space
		}
		else
		{
			return null; //not enough pos to the right
		}

	}

	public int[][] goLeftfromPos(int row, int col, int[][] paraboard)
	{


		if(col >= 2) //if there is enough space to the left of the pos
		{
			int count = 0;
			for(int i = col; i > col - 3; i--) //if there is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 3)
			{
				for(int i = col; i > col - 3; i--)
				{
					paraboard[row][i] = 6; //setting the positions "6" for Sub
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isn't enough free space
		}
		else
			return null; //not enough pos to the left

	}

	public int[][] goUpfromPos(int row, int col, int[][] paraboard)
	{


		if(row >= 2) //if there is enough space up from the pos
		{
			int count = 0;
			for(int i = row; i > row - 3; i--) //if there is free space
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 3)
			{
				for(int i = row; i > row - 3; i--)
				{
					paraboard[i][col] = 6; //setting the positions "6" for Sub
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isn't enough free space
		}
		else
			return null; //not enough pos Up

	}

	public int[][] goDownfromPos(int row, int col, int[][] paraboard)
	{

		int amountDown = paraboard.length - row; //finds how many spots are down from the pos

		if(amountDown >= 3)
		{
			int count = 0;
			for(int i = row; i < row + 3; i++) //if there is free space
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 3)
			{
				for(int i = row; i < row + 3; i++)
				{
					paraboard[i][col] = 6; //setting the positions "6" for Sub
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isn't enough free space
		}
		else
			return null; //not enough pos Down

	}

	public void incrementHits()
	{
		numofhits++;
	}

	public boolean hasSank()
	{
		if(numofhits == 3)
			return true;
		else
			return false;
	}
	
	public void resetShip()
	{
		numofhits = 0;
	}

}