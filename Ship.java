/**
 * Ship.java  5/17/2017
 *
 * Rahul Kumar
 * AP Computer Science
 * Mrs. Hitch 3rd Period
 *
 *
 */



public interface Ship
{
	public boolean hasSank(); //  returns if the <type> Ship has all of its positions hit.
	public void resetShip(); //sets amounts of hits back to 0
	public int[][] init(int[][] board); //initilizes the board with a certain ship 
	public void incrementHits(); //increments number of hits
	public int[][] goRightfromPos(int row, int col, int[][] board); //checks if the random position selected can fit X amounts of slots right(X depends on the ship size, each has a unique one
	public int[][] goLeftfromPos(int row, int col, int[][] board); //checks if the random position selected can fit X amounts of slots left(X depends on the ship size, each has a unique one
	public int[][] goUpfromPos(int row, int col, int[][] board); //checks if the random position selected can fit X amounts of slots up(X depends on the ship size, each has a unique one
	public int[][] goDownfromPos(int row, int col, int[][] board);  //checks if the random position selected can fit X amounts of slots down(X depends on the ship size, each has a unique one
}