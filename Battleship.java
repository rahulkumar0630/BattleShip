/**
 * Battleship.java  5/17/2017
 *
 * Rahul Kumar
 * AP Computer Science
 * Mrs. Hitch 3rd Period
 *
 *
 */


//Each method does what the "Ship" interface, but goes to 4 positions for the "X"

public class Battleship implements Ship
{
	//private instance variables
	private int numofhits;

	//constructer
	public Battleship()
	{
		numofhits = 0;
	}

	public int[][] init(int[][] board) 
	{

		boolean condition = false;

		while(!condition) //repeats till Battleship fits somewhere
		{
			int randfordir = (int)(Math.random() * 3); //chooses if board should be inited up,down,left ,right

			System.out.println(randfordir);

			int randomcol = (int)(Math.random() * board[0].length); //gets a random col pos
			int randomrow = (int)(Math.random() * board.length); //gets a random row pos

			if((randfordir == 0) && (goRightfromPos(randomrow, randomcol, board) != null))
			{
				board = goRightfromPos(randomrow, randomcol, board); //inits Battleship to the right
				condition = true;

			}
			else if(randfordir == 1  && (goLeftfromPos(randomrow, randomcol , board) != null))
			{
				board = goLeftfromPos(randomrow, randomcol, board); //inits Battleship to the left
				System.out.println("Left");
				condition = true;
			}

			else if(randfordir == 2 && (goUpfromPos(randomrow, randomcol, board) != null))
			{
				board = goUpfromPos(randomrow, randomcol, board); //inits Battleship Up
				condition = true;
			}
			else if(randfordir == 3 && (goDownfromPos(randomrow, randomcol, board) != null))
			{
				board = goDownfromPos(randomrow, randomcol, board); //inits Battleship Down
					condition = true;
			}
		}

		return board;
	}

	
	public int[][] goRightfromPos(int row, int col, int[][] paraboard)
	{

		int amountRight = paraboard[0].length - col; //finds how many spots are the right of the pos

		if(amountRight >= 4) //if its enough for a Battleship
		{
			int count = 0;
			for(int i = col; i < col + 4; i++) //if there is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 4)
			{
				for(int i = col; i < col + 4; i++)
				{
					paraboard[row][i] = 4; //setting the positions "4" for Battleship
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isnt free space
		}
		else
		{
			return null; //not enough for right
		}

	}

	public int[][] goLeftfromPos(int row, int col, int[][] paraboard)
	{


		if(col >= 4) //if there is enough spots to the left of the pos
		{
			int count = 0;
			for(int i = col; i > col - 4; i--) //if there is free space
			{
				if(paraboard[row][i] == -1)
					count++;
			}

			if(count == 4)
			{
				for(int i = col; i > col - 4; i--)
				{
					paraboard[row][i] = 4;  //setting the positions "4" for Battleship
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isnt free space
		}
		else
			return null;//not enough for left

	}

	public int[][] goUpfromPos(int row, int col, int[][] paraboard)
	{


		if(row >= 4) //if there is enough spots up from the pos
		{
			int count = 0;
			for(int i = row; i > row - 4; i--) //if there is free space
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 4)
			{
				for(int i = row; i > row - 4; i--)
				{
					paraboard[i][col] = 4; //setting the positions "4" for Battleship
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isnt free space
		}
		else
			return null; //not enough for up

	}

	public int[][] goDownfromPos(int row, int col, int[][] paraboard)
	{

		int amountDown = paraboard.length - row; //finds how manyspots are down from pos

		if(amountDown >= 4) //if there is enough spots down from the pos
		{
			int count = 0;
			for(int i = row; i < row + 4; i++)
			{
				if(paraboard[i][col] == -1)
					count++;
			}

			if(count == 4)
			{
				for(int i = row; i < row + 4; i++)
				{
					paraboard[i][col] = 4;  //setting the positions "4" for Battleship
				}
			return paraboard; //returns new board
			}
			return null; //returns null if there isnt free space
		}
		else
			return null; //not enough for down

	}

	public void incrementHits()
	{
		numofhits++;
	}


	public boolean hasSank()
	{
		if(numofhits == 4)
			return true;
		else
			return false;
	}
	
	public void resetShip()
	{
		numofhits = 0;
	}

}