/**
 * BattleshipManager.java  5/17/2017
 *
 * Rahul Kumar
 * AP Computer Science
 * Mrs. Hitch 3rd Period
 *
 *
 */
 
//creates Battleship Game using other classes

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class BattleshipManager extends JFrame implements ActionListener
{
	//private instance variables
	private ArrayList<Ship> playergrid;
	private ArrayList<Ship> AIGrid;
	private int[][] placement;
	private int[][] AIboard;
	private JLabel banner;
	private JLabel background;
	private Container contents;
	private JTextArea textArea;
	private JScrollPane scrollpane;
	private JButton randomizebutton;
	private JButton resetButton;
	private JPanel AIpanel;
	private JPanel Playerpanel;
	private ImageIcon HitBattleship = new ImageIcon("HitBattleship.png");
    private ImageIcon HitCarrier = new ImageIcon("HitCarrier.png");
    private ImageIcon HitCruiser = new ImageIcon("HitCruiser.png");
    private ImageIcon HitSub = new ImageIcon("HitSub.png");
    private ImageIcon HitDestroyer = new ImageIcon("HitDestroyer.png");
    private ImageIcon Splash = new ImageIcon("redX.png");
    private ImageIcon water = new ImageIcon("water.gif");

	private JButton[][] squares;
	private JButton[][] enemysquares;

	//Ships
	private Ship PlayerBattleship;
	private Ship PlayerCarrier;
	private Ship PlayerCruiser;
	private Ship PlayerDestroyer;
	private Ship PlayerSubmarine;

	private Ship AIBattleship;
	private Ship AICarrier;
	private Ship AICruiser;
	private Ship AIDestroyer;
	private Ship AISubmarine;

	//Image Labels
	private JLabel EnemyCarrierLabel;
	private JLabel EnemyDestroyerLabel;
	private JLabel EnemyCruiserLabel;
	private JLabel EnemySubmarineLabel;
	private JLabel EnemyBattleshipLabel;

	private JLabel PlayerCarrierLabel;
	private JLabel PlayerDestroyerLabel;
	private JLabel PlayerCruiserLabel;
	private JLabel PlayerSubmarineLabel;
	private JLabel PlayerBattleshipLabel;
	
	private JLabel HasSubmarine;
    private JLabel HasCarrier;
    private JLabel HasCruiser;
    private JLabel HasBattleship;
    private JLabel HasDestroyer;
    
    private JLabel AIHasSubmarine;
    private JLabel AIHasCarrier;
    private JLabel AIHasCruiser;
    private JLabel AIHasBattleship;
    private JLabel AIHasDestroyer;

	private boolean gameover = false;

	//constructer
	public BattleshipManager()
	{
		playergrid = new ArrayList<Ship>(); // initilizes grids and boards
		AIGrid = new ArrayList<Ship>();
		placement = new int[8][8];
		AIboard = new int[8][8];

		//Player ships initilized
		PlayerBattleship = new Battleship();
		playergrid.add(PlayerBattleship);

		PlayerCarrier = new Carrier();
		playergrid.add(PlayerCarrier);

		PlayerCruiser = new Cruiser();
		playergrid.add(PlayerCruiser);

		PlayerDestroyer = new Destroyer();
		playergrid.add(PlayerDestroyer);

		PlayerSubmarine = new Submarine();
		playergrid.add(PlayerSubmarine);


		//sets board to a default of -1
		for(int row = 0; row < placement.length; row++){
			for(int col = 0; col < placement[row].length; col++)
			{
				placement[row][col] = -1;
			}
		}

		//Goes through every Ship in the playergrid, and initlizes them on the playerboard(Polymorphism)
		for(Ship e: playergrid)
		{
			e.init(placement);
		}

		//Prints board in console(Was used for debugging)
		for(int row = 0; row < placement.length; row++){
			for(int col = 0; col < placement[row].length; col++)
			{
				System.out.print(placement[row][col] + " ");
			}
			System.out.println();
		}




		//AI Ships initilized
		AIBattleship = new Battleship();
		AIGrid.add(AIBattleship);

		AICarrier = new Carrier();
		AIGrid.add(AICarrier);

		AICruiser = new Cruiser();
		AIGrid.add(AICruiser);

		AIDestroyer = new Destroyer();
		AIGrid.add(AIDestroyer);

		AISubmarine = new Submarine();
		AIGrid.add(AISubmarine);



		//sets AIboard to a default of -1
		for(int row = 0; row < AIboard.length; row++){
			for(int col = 0; col < AIboard[row].length; col++)
			{
				AIboard[row][col] = -1;
			}
		}

		for(Ship e: AIGrid) //Goes through every Ship in the playergrid, and initlizes them on the AIboard(Polymorphism)
		{
			e.init(AIboard);
		}


	  //Creating the title image
	  ImageIcon image = new ImageIcon("battleship-banner.jpg");
	  banner = new JLabel(image);
	  banner.setLocation(-12,0);
      banner.setSize(710, 125);
      getContentPane().add(banner);

      //GUI Frame
      setLayout(null);
      setSize(715, 700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Playerpanel = new JPanel();
      Playerpanel.setLayout(new GridLayout(8,8));
      Playerpanel.setLocation(25,150);
      Playerpanel.setSize(300,300);
      add(Playerpanel);
      
      //initlizes the 2dmatrix of buttons for both the player and the AI
      squares = new JButton[8][8];
	  enemysquares = new JButton[8][8];

      ButtonHandler buttonHandler = new ButtonHandler();

	//filling the button array with buttons with the default image of "water"
		 for(int i=0; i <8; i++){
            for(int j=0; j < 8; j++){
                JButton button = new JButton();
                button.setSize(5,5);
                button.setIcon(water);
                squares[i][j] = button;
                Playerpanel.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);
            }
        }

      AIpanel = new JPanel();
      AIpanel.setLayout(new GridLayout(8,8));
      AIpanel.setLocation(372,150);
      AIpanel.setSize(300,300);
      add(AIpanel);

		 for(int i=0; i <8; i++){
            for(int j=0; j < 8; j++){
                JButton button = new JButton();
                button.setSize(5,5);
                button.setIcon(water);
                enemysquares[i][j] = button;
                AIpanel.add(enemysquares[i][j]);

            }
        }
	//Setting icons for each ship on the player side so they can see which ships are on their side
      for(int i=0; i <8; i++){
            for(int j=0; j < 8; j++){
            	if(placement[i][j] == 2)
                  enemysquares[i][j].setIcon(HitDestroyer);
               if(placement[i][j] == 3)
                 	enemysquares[i][j].setIcon(HitCruiser);
               if(placement[i][j] == 4)
                  enemysquares[i][j].setIcon(HitBattleship);
               if(placement[i][j] == 5)
                  enemysquares[i][j].setIcon(HitCarrier);
               if(placement[i][j] == 6)
                  enemysquares[i][j].setIcon(HitSub);
            }
      }

	  //Static images
      ImageIcon bg = new ImageIcon("background.jpg");
	  background = new JLabel(bg);
	  background.setLocation(-100,2);
      background.setSize(900, 785);
      getContentPane().setBackground(Color.black);
      //background.setVisible(false);
      
      HasSubmarine = new JLabel(HitSub);
	  HasSubmarine.setLocation(25,463);
	  HasSubmarine.setSize(35,35);
	  getContentPane().add(HasSubmarine);
	  
	  HasBattleship = new JLabel(HitBattleship);
	  HasBattleship.setLocation(75,463);
	  HasBattleship.setSize(35,35);
	  getContentPane().add(HasBattleship);
	  
	  HasDestroyer = new JLabel(HitDestroyer);
	  HasDestroyer.setLocation(125,463);
	  HasDestroyer.setSize(35,35);
	  getContentPane().add(HasDestroyer);
	  
	  HasCruiser = new JLabel(HitCruiser);
	  HasCruiser.setLocation(175,463);
	  HasCruiser.setSize(35,35);
	  getContentPane().add(HasCruiser);
	  
	  HasCarrier = new JLabel(HitCarrier);
	  HasCarrier.setLocation(225,463);
	  HasCarrier.setSize(35,35);
	  getContentPane().add(HasCarrier);
	  
	  AIHasSubmarine = new JLabel(HitSub);
	  AIHasSubmarine.setLocation(437,463);
	  AIHasSubmarine.setSize(35,35);
	  getContentPane().add(AIHasSubmarine);
	  
	  AIHasBattleship = new JLabel(HitBattleship);
	  AIHasBattleship.setLocation(487,463);
	  AIHasBattleship.setSize(35,35);
	  getContentPane().add(AIHasBattleship);
	  
	  AIHasDestroyer = new JLabel(HitDestroyer);
	  AIHasDestroyer.setLocation(537,463);
	  AIHasDestroyer.setSize(35,35);
	  getContentPane().add(AIHasDestroyer);
	  
	  AIHasCruiser = new JLabel(HitCruiser);
	  AIHasCruiser.setLocation(587,463);
	  AIHasCruiser.setSize(35,35);
	  getContentPane().add(AIHasCruiser);
	  
	  AIHasCarrier = new JLabel(HitCarrier);
	  AIHasCarrier.setLocation(637,463);
	  AIHasCarrier.setSize(35,35);
	  getContentPane().add(AIHasCarrier);
	  
	  
      //Setting text area with the Documentation or default text
      textArea = new JTextArea();
      textArea.setFont(new Font("consolas", Font.PLAIN, 16));
      textArea.setEditable(false);   // cannot type text into textArea
      textArea.setLineWrap(true);   // wrap text when reach right border
      textArea.setText("Welcome To Battleship!\n---------------------\n" +
       "Battleship is a game between the Player(You) and the AI. Your board\n" + 
       "is on the right, and the enemy's board is on the left. Both you and\n" +
       "the Computer will start off with 5 ships:\n\nList of Ships\n----------------\n" +
       "1 Battleship – length of 4 grid places and is represented by a box\n" +
       "with a blue outline and black dot.\n" +
       "1 Carrier – length of 5 grid places and is represented by a box\n" +
       "with a red outline and black dot.\n"+
       "1 Submarine – length of 3 grid places and is represented by a box\n" +
       "with a yellow outline and black dot.\n" +
       "1 Cruiser – length of 3 grid places and is represented by a box\n" +
       "with a green outline and black dot.\n" +
       "1 Destroyer – length of 2 grid places and is represented by a box\n" +
       "with a orange outline and black dot.\n\n" +
       	"The Icons you see under the grid is the ships left to sink. If a ship\n" +
       	"such as a submarine sinks, the icon associated with the submarine\n" +
       	"will disappear. You and the enemy have this.\n\n" +
       "Randomize Board(button) -  This button will randomize the placement\n" +
       "of your 5 ships. The moment you click to fire your projectile on the\n" +
       "enemy board,you will no longer have access to this button.\n\n" +
       "Rules- You start off. You may click anywhere to fire your projectile on the enemy board. After you fire your projectile, the computer will"
       	+ "fire a projectile on your board. If you hit a ship, the Icon\n" +
       	"associated with that ship will pop up(Example: If you hit an enemy \n" +
       	"Submarine, a yellow outlined box with a black dot in the middle will appear at that position). If the computer hits one of your ship, a\n" +
       	"“explosion” icon will appear at that position. Whoever sinks all the opposing sides ships, wins.\n\n" +
       	"CONSOLE \n---------\n\n");


	  //buttons
      randomizebutton = new JButton("Randomize Board"); // randomizes ships positions on the playerboard
	  	randomizebutton.setLocation(272,465);
	  	randomizebutton.setSize(150,30);

	  	resetButton = new JButton("Reset Game"); // resets the game
	  	resetButton.setLocation(272,465);
	  	resetButton.setSize(150,30);

	   getContentPane().add(randomizebutton); //adds buttons to panel
	   getContentPane().add(resetButton);

	   randomizebutton.addActionListener(this);
	   randomizebutton.setVisible(false);

	   resetButton.addActionListener(this);
	   resetButton.setVisible(false);
		
	  //sets everything on the Panel
      scrollpane = new JScrollPane(textArea);

      scrollpane.setLocation(23,510);
      scrollpane.setSize(650, 125);
      scrollpane.setBorder(BorderFactory.createLineBorder (Color.black, 2));

      getContentPane().add(scrollpane);
      getContentPane().add(background);
	   background.setVisible(false);

	   scrollpane.setVisible(true);
	   randomizebutton.setVisible(true);
	   background.setVisible(true);
      setVisible(true);
	}

	//returns if the projectile fired had a ship
	public boolean isHit(int row, int col)
	{
		if(AIboard[row][col] != -1)
			return true; //had a ship
		else
			return false; // did not have a ship
	}

	//returns which kind of ship on the position
	public int typeOfHit(int row, int col)
	{
		if(AIboard[row][col] == 2)
			return 2;
		if(AIboard[row][col] == 3)
			return 3;
		if(AIboard[row][col] == 4)
			return 4;
		if(AIboard[row][col] == 5)
			return 5;
		if(AIboard[row][col] == 6)
			return 6;
		else
			return 0;

	}

	//checks if all ships return true to the method call "hasSunk"
	public boolean checkIfEitherSideWon(ArrayList<Ship> parameter)
	{
		int numberofsunkships = 0;
		for(int i = 0; i < parameter.size(); i++)
		{
			if(parameter.get(i).hasSank())
				numberofsunkships++;
		}

		if(numberofsunkships == 5)
			return true;
		else
			return false;
	}


	//finds a random position to fire at to the player board and loops till the position has not been shot at
	public void AIAttack()
	{

		  int randomcol = 0;
		  int randomrow = 0;
		  boolean condition = false;

		  ImageIcon enemyHit = new ImageIcon("EnemyHit.jpg");

		  while(!(condition || gameover)) // if the game is not over or the conditon(Attack was not executed before) is not met
		  {
				randomcol = (int)(Math.random() * AIboard[0].length);
				randomrow = (int)(Math.random() * AIboard.length);

				//If AI Missed
				if(placement[randomrow][randomcol] == -1 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(Splash);
					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI missed\n\n");

					condition = true;
				}
				//if AI hit a Submarine
				else if(placement[randomrow][randomcol] == 6 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(enemyHit);

					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI hit a Submarine!\n\n");

					PlayerSubmarine.incrementHits();

					if(PlayerSubmarine.hasSank()) //if the hit executed sank the player's Submarine
					{
						textArea.append("AI sunk your Submarine!\n");
						AIHasSubmarine.setVisible(false);

						if(checkIfEitherSideWon(playergrid)) // if the game is won
						{
							gameover = true;
				         textArea.append("GAME OVER. AI Won!");
				         resetButton.setVisible(true);
						}
					}

					condition = true;
				}
				//if AI hit a Carrier
				else if(placement[randomrow][randomcol] == 5 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(enemyHit);
					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI hit a Carrier!\n\n");


					PlayerCarrier.incrementHits();

					if(PlayerCarrier.hasSank()) //if the hit executed sank the player's Carrier
					{
						textArea.append("AI sunk your Carrier!\n");
						AIHasCarrier.setVisible(false);

					   if(checkIfEitherSideWon(playergrid))
						{
							gameover = true;
							textArea.append("GAME OVER. AI Won!");
							resetButton.setVisible(true);
						}
					}

					condition = true;
				}
				//if AI hits a battleship
				else if(placement[randomrow][randomcol] == 4 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(enemyHit);
					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI hit a Battleship!\n\n");

					PlayerBattleship.incrementHits();

					if(PlayerBattleship.hasSank()) //if the hit executed sank the player's Battleship
					{
						textArea.append("AI sunk your Battleship!\n");
						AIHasBattleship.setVisible(false);

					   if(checkIfEitherSideWon(playergrid))
						{
							gameover = true;
							textArea.append("GAME OVER. AI Won!");
							resetButton.setVisible(true);
						}
					}

					condition = true;
				}
				//if AI hits a Cruiser
				else if(placement[randomrow][randomcol] == 3 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(enemyHit);
					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI hit a Cruiser!\n\n");


					PlayerCruiser.incrementHits();

					if(PlayerCruiser.hasSank()) //if the hit executed sank the player's Cruiser
					{
						textArea.append("AI sunk your Cruiser!\n");
						AIHasCruiser.setVisible(false);

					   if(checkIfEitherSideWon(playergrid))
						{
							gameover = true;
                   	textArea.append("GAME OVER. AI Won!");
                   	resetButton.setVisible(true);
						}
					}

					condition = true;
				} //if AI Hits a Destroyer
				else if(placement[randomrow][randomcol] == 2 && enemysquares[randomrow][randomcol] != null)
				{
					enemysquares[randomrow][randomcol].setIcon(enemyHit);
					textArea.append("AI fired a projectile at (" + randomrow + "," + randomcol+ ")" + "\t\tAI hit a Destroyer!\n\n");


					PlayerDestroyer.incrementHits();

					if(PlayerDestroyer.hasSank())  //if the hit executed sank the player's Destroyer
					{
						textArea.append("AI sunk your Destroyer!\n");
						AIHasDestroyer.setVisible(false);

					   if(checkIfEitherSideWon(playergrid))
						{
							gameover = true;
							textArea.append("GAME OVER. AI Won!");
							resetButton.setVisible(true);

						}
					}

					condition = true;
				}
			}
			enemysquares[randomrow][randomcol] = null;

			textArea.setCaretPosition(textArea.getDocument().getLength());


	}

	public void actionPerformed(ActionEvent event)
    {
		if(event.getSource() == randomizebutton) //if the button press associates to the "randomizebutton"
	        {
				//sets the board to -1
			  	for(int row = 0; row < placement.length; row++){
				   for(int col = 0; col < placement[row].length; col++)
						{
							placement[row][col] = -1;
						}
					}
				//reints
				for(Ship e: playergrid)
				{
					e.init(placement);
				}
				
				//sets all to the default icon "water"
				for(int i=0; i <8; i++){
	            	for(int j=0; j < 8; j++){
	                	enemysquares[i][j].setIcon(water);
	            	}
        		}

				//sets the icon assoicated with each Ship with the new, reintilized board
        		for(int i=0; i <8; i++){
            	for(int j=0; j < 8; j++){
            		if(placement[i][j] == 2)
                  	enemysquares[i][j].setIcon(HitDestroyer);
               	if(placement[i][j] == 3)
                 		enemysquares[i][j].setIcon(HitCruiser);
               	if(placement[i][j] == 4)
                  	enemysquares[i][j].setIcon(HitBattleship);
               	if(placement[i][j] == 5)
                 		enemysquares[i][j].setIcon(HitCarrier);
                  if(placement[i][j] == 6)
                  	enemysquares[i][j].setIcon(HitSub);
            }
      	}

    	}
    	if(event.getSource() == resetButton) //if button press associates with "resetButton"
    	{
    		gameover = false;

			//sets text to the default text
    		textArea.setText("");
    		textArea.setText("Welcome To Battleship!\n---------------------\n" +
		       "Battleship is a game between the Player(You) and the AI. Your board\n" + 
		       "is on the right, and the enemy's board is on the left. Both you and\n" +
		       "the Computer will start off with 5 ships:\n\nList of Ships\n----------------\n" +
		       "1 Battleship – length of 4 grid places and is represented by a box\n" +
		       "with a blue outline and black dot.\n" +
		       "1 Carrier – length of 5 grid places and is represented by a box\n" +
		       "with a red outline and black dot.\n"+
		       "1 Submarine – length of 3 grid places and is represented by a box\n" +
		       "with a yellow outline and black dot.\n" +
		       "1 Cruiser – length of 3 grid places and is represented by a box\n" +
		       "with a green outline and black dot.\n" +
		       "1 Destroyer – length of 2 grid places and is represented by a box\n" +
		       "with a orange outline and black dot.\n\n" +
		       	"The Icons you see under the grid is the ships left to sink. If a ship\n" +
		       	"such as a submarine sinks, the icon associated with the submarine\n" +
		       	"will disappear. You and the enemy have this.\n\n" +
		       "Randomize Board(button) -  This button will randomize the placement\n" +
		       "of your 5 ships. The moment you click to fire your projectile on the\n" +
		       "enemy board,you will no longer have access to this button.\n\n" +
		       "Rules- You start off. You may click anywhere to fire your projectile on the enemy board. After you fire your projectile, the computer will"
		       	+ "fire a projectile on your board. If you hit a ship, the Icon\n" +
		       	"associated with that ship will pop up(Example: If you hit an enemy \n" +
		       	"Submarine, a yellow outlined box with a black dot in the middle will appear at that position). If the computer hits one of your ship, a\n" +
		       	"“explosion” icon will appear at that position. Whoever sinks all the opposing sides ships, wins.\n\n" +
		       	"CONSOLE \n---------\n\n");

				//sets the board to -1
    			for(int row = 0; row < placement.length; row++){
				   for(int col = 0; col < placement[row].length; col++)
						{
							placement[row][col] = -1;
						}
					}
				
				//resets the enemy;s board to -1	
				for(int row = 0; row < AIboard.length; row++){
				   for(int col = 0; col < AIboard[row].length; col++)
						{
							AIboard[row][col] = -1;
						}
					}
				
				//reinits both grids and ships
				for(Ship e: playergrid)
				{
					e.init(placement);
				}
				for(Ship e: AIGrid)
				{
					e.init(AIboard);
				}
				//calls the resetShip method for all ships
				for(Ship e: playergrid)
				{
					e.resetShip();
				}
				for(Ship e: AIGrid)
				{
					e.resetShip();
				}
				//creates new panels and resets them
				  remove(background);
				  
				  remove(Playerpanel);
				  Playerpanel = new JPanel();
			      Playerpanel.setLayout(new GridLayout(8,8));
			      Playerpanel.setLocation(25,150);
			      Playerpanel.setSize(300,300);
			      add(Playerpanel);
			      Playerpanel.setVisible(false);
			      
			      //reinits the 2d matrixes
			      squares = new JButton[8][8];
				  enemysquares = new JButton[8][8];
			
				  ButtonHandler buttonHandler = new ButtonHandler();
			
					 for(int i=0; i <8; i++){
			            for(int j=0; j < 8; j++){
			                JButton button = new JButton();
			                button.setSize(5,5);
			                button.setIcon(water);
			                squares[i][j] = button;
			                Playerpanel.add(squares[i][j]);
			                squares[i][j].addActionListener(buttonHandler);
			            }
			        }
			
				  remove(AIpanel);
			      AIpanel = new JPanel();
			      AIpanel.setLayout(new GridLayout(8,8));
			      AIpanel.setLocation(372,150);
			      AIpanel.setSize(300,300);
			      add(AIpanel);
			      AIpanel.setVisible(false);
			      add(background);
			
					 for(int i=0; i <8; i++){
			            for(int j=0; j < 8; j++){
			                JButton button = new JButton();
			                button.setSize(5,5);
			                button.setIcon(water);
			                enemysquares[i][j] = button;
			                AIpanel.add(enemysquares[i][j]);
			
			            }
			        }
			
			      for(int i=0; i <8; i++){
			            for(int j=0; j < 8; j++){
			            	if(placement[i][j] == 2)
			                  enemysquares[i][j].setIcon(HitDestroyer);
			               if(placement[i][j] == 3)
			                 	enemysquares[i][j].setIcon(HitCruiser);
			               if(placement[i][j] == 4)
			                  enemysquares[i][j].setIcon(HitBattleship);
			               if(placement[i][j] == 5)
			                  enemysquares[i][j].setIcon(HitCarrier);
			               if(placement[i][j] == 6)
			                  enemysquares[i][j].setIcon(HitSub);
			            }
			      }
			      
			      //shows all the labels needed for restarting the game
			      resetButton.setVisible(false); // hides resetButton
			      randomizebutton.setVisible(true);
			      Playerpanel.setVisible(true);
			      AIpanel.setVisible(true);
			      HasSubmarine.setVisible(true);
			      HasDestroyer.setVisible(true);
			      HasBattleship.setVisible(true);
			      HasCarrier.setVisible(true);
			      HasCruiser.setVisible(true);
			      
			      AIHasSubmarine.setVisible(true);
			      AIHasDestroyer.setVisible(true);
			      AIHasBattleship.setVisible(true);
			      AIHasCarrier.setVisible(true);
			      AIHasCruiser.setVisible(true);
			      background.setVisible(true);
			      
			      setVisible(true);


    	}

    }

	//if a button on the Panel has been pressed
	 public class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Object source = e.getSource();
				randomizebutton.setVisible(false);

		//if the game is not over
		if(!gameover)
		{
            for(int i=0; i < 8; i++){
                for(int j=0; j < 8; j++){
                    if( source == squares[i][j]){
                        if(isHit(i,j) && squares[i][j] != null && !gameover) //if position assoicated with the button contains a ship
                        {
                        	int hit = typeOfHit(i,j);
                        		if(hit == 2)
                        		{
                        			squares[i][j].setIcon(HitDestroyer);

                        			textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou hit a Destroyer!\n");

											AIDestroyer.incrementHits();

											if(AIDestroyer.hasSank()) // if player sank the enemy's Destroyer
											{
												textArea.append("You sunk the AI Destroyer!\n");
												HasDestroyer.setVisible(false);

												if(checkIfEitherSideWon(AIGrid)) //if game is won
													{
														  gameover = true;
												        textArea.append("GAME OVER. You Won!");
														  resetButton.setVisible(true);
													}
											}
                        		}
                        		if(hit == 3)
                        		{
                        			squares[i][j].setIcon(HitCruiser);

                        			textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou hit a Cruiser!\n");


											AICruiser.incrementHits();

											if(AICruiser.hasSank()) // if player sank the enemy's Cruiser
											{
												textArea.append("You sunk the AI Cruiser!\n");
												HasCruiser.setVisible(false);

											   if(checkIfEitherSideWon(AIGrid)) //if game is won
													{
														 gameover = true;
												       textArea.append("GAME OVER. You Won!");
												       resetButton.setVisible(true);
													}

											}
                        		}
                        		if(hit == 4)
                        		{
                        			squares[i][j].setIcon(HitBattleship);

                        			textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou hit a Battleship!\n");


											AIBattleship.incrementHits();

											if(AIBattleship.hasSank()) // if player sank the enemy's Battleship
											{
												textArea.append("You sunk the AI Battleship!\n");
												HasBattleship.setVisible(false);

											   if(checkIfEitherSideWon(AIGrid)) //if game is won
													{
													 gameover = true;
											       textArea.append("GAME OVER. You Won!");
											       resetButton.setVisible(true);
													}
											}
                        		}
                        		if(hit == 5)
                        		{
                        			squares[i][j].setIcon(HitCarrier);

                        			textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou hit a Carrier!\n");

											AICarrier.incrementHits();

											if(AICarrier.hasSank()) // if player sank the enemy's Carrier
											{
												textArea.append("You sunk the AI Carrier!\n");
												HasCarrier.setVisible(false);

											   if(checkIfEitherSideWon(AIGrid)) //if game is won
													{
													 gameover = true;
											       textArea.append("GAME OVER. You Won!");
											       resetButton.setVisible(true);
													}
											}
                        		}
                        		if(hit == 6)
                        		{
                        			squares[i][j].setIcon(HitSub);

                        			textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou hit a Submarine!\n");


											AISubmarine.incrementHits();

											if(AISubmarine.hasSank())
											{
												textArea.append("You sunk the AI Submarine!\n"); // if player sank the enemy's Submarine
												HasSubmarine.setVisible(false);


											   if(checkIfEitherSideWon(AIGrid)) //if game is won
													{
													  gameover = true;
											        textArea.append("GAME OVER. You Won!");
											        resetButton.setVisible(true);
													}
											}
                        		}
	                        	try
	                        	{
                        			AIAttack(); // shoots a projectile on the player board
	                        	}
	                        	catch(NullPointerException m) //the AiAttack sometimes return null for the button it tries, but it doesn't matter
	                        	{
	                        		System.out.println("null, but system handled it");
	                        	}
                        	squares[i][j] = null;
                        }
                        else if(squares[i][j] != null && !gameover) //if the button or gird has a -1 and does not contain a ship
                        {
	                        	squares[i][j].setIcon(Splash);
	                        	textArea.append("You fired a projectile at (" + i + "," + j + ")" + "\t\tYou Missed\n");

                        		try
	                        	{
                        			AIAttack(); // shoots a projectile on the player board
	                        	}
	                        	catch(NullPointerException m) //the AiAttack sometimes return null for the button it tries, but it doesn't matter
	                        	{
	                        		System.out.println("null, but system handled it");
	                        	}
                        	squares[i][j] = null;
                        }

                        return;

                    }

                }
            }
        }
        }
    }

	public static void main(String[] args) //main
	{
		BattleshipManager app = new BattleshipManager(); //creates a "BattleshipManager" object
	}
}
