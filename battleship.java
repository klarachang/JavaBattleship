import java.util.Scanner;
import java.util.Arrays;

public class battleship {
	
	private static String[][] board1 = new String[11][11];
	private static String[][] board2 = new String[11][11];
	private static String[][] board3 = new String[11][11];
	private static String[][] board4 = new String[11][11];
	private static String player = " ";
	
	private static String coordinate = "";
	private static String ship = "";
	private static int col = 0;
	private static int row = 0;
	private static String direction = "";
	private static int length = 0;
	
	private static int countOfX1 = 0;
	private static int countOfX2 = 0;
	private static boolean sunk = false;
	private static boolean winner = false;
	
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		int turn = 1;
		String attack = "";
		int sunkenShips1 = 0;
		int sunkenShips2 = 0;	
		
		// this is to label each coordinate on the board
		for(int row = 1; row < 11; row++)
		{
			for(int col = 1; col < 11; col++)
			{
				board1[row][col] = "" + (char) (65 + col - 1) + row;
				board2[row][col] = "" + (char) (65 + col - 1) + row;
				board3[row][col] = "" + (char) (65 + col - 1) + row;
				board4[row][col] = "" + (char) (65 + col - 1) + row;
			}
		}
		// this is the code for playing the game
		for(int i = 1; i <= 2; i++)
		{
			if(i%2 == 1)
			{
				player = "1";
			}
			else
			{
				player = "2";
			}
			
			if(player.equals("1"))
			{
				printBoard1(board1); 
				
				placeCarrier1();
					
				placeBattleship1();
				
				placeCruiser1();
				
				placeSubmarine1();
				
				placeDestroyer1();
			}
			else if(player.equals("2"))
			{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\n\n\n\n\n\n\n");
				System.out.println("\n\n\n\n\n\n\n");
				System.out.println("\n\n\n\n\n\n\n");
				System.out.println("\n\n\n\n\n\n\n");
				
				printBoard2(board2);
				
				placeCarrier2();
					
				placeBattleship2();
				
				placeCruiser2();
				
				placeSubmarine2();
				
				placeDestroyer2();
				
				System.out.println();
				System.out.println();
				System.out.println();
			}
		}
			
		do
		{
			if(turn%2 == 1)
			{
				player = "1";	
			}
			else
			{
				player = "2";
			}
			
			if(player.equals("1"))
			{
				// this is to make a delay of 2 seconds in showing the next player's board, so the players will not see each other's boards
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println();
				System.out.println();
				printBoard1(board1);
				System.out.println();
				printBoard3(board3);
				do
				{
					System.out.print("Player " + player + ", enter a coordinate: ");
					attack = keyboard.nextLine().toUpperCase();
					if(attackIsValid(attack))
					{
						if(!isRepeated1(attack))
						{
							break;
						}
					}
				}while(true);
				row = Integer.parseInt(attack.substring(1,attack.length()));
				col = (int) attack.charAt(0);
				
				// this is to check whether a ship was hit and/or sunk
				if(board2[row][col - 64].equals("© ") || board2[row][col - 64].equals(" © ") || board2[row][col - 64].equals("∆ ") ||
						board2[row][col - 64].equals(" ∆ ") || board2[row][col - 64].equals("* ") || board2[row][col - 64].equals(" * ") ||
						board2[row][col - 64].equals("# ") || board2[row][col - 64].equals(" # ") || board2[row][col - 64].equals("$ ") ||
						board2[row][col - 64].equals(" $ "))
				{
					board2[row][col - 64] = "X ";
					if((row) == 10)
					{
						board2[row][col - 64] = " X ";
					}
					
					board3[row][col - 64] = "X ";
					if((row) == 10)
					{
						board3[row][col - 64] = " X ";
					}
					countOfX1++;
					System.out.println("Ship is HIT!");
					if(carrierIsSunk2(board2) == false && battleshipIsSunk2(board2) == false && 
							cruiserIsSunk2(board2) == false && submarineIsSunk2(board2) == false && 
							destroyerIsSunk2(board2) == false)
					{
						System.out.println("None of the ships have been sunk.");
					}
					else
					{
						System.out.println("These are the ships you have sunk: ");
					}
					if(carrierIsSunk2(board2) == true)
					{
						System.out.println("Carrier");
					}
					if(battleshipIsSunk2(board2) == true)
					{
						System.out.println("Battleship");
					}
					if(cruiserIsSunk2(board2) == true)
					{
						System.out.println("Cruiser");
					}
					if(submarineIsSunk2(board2) == true)
					{
						System.out.println("Submarine");
					}
					if(destroyerIsSunk2(board2) == true)
					{
						System.out.println("Destroyer");
					}	
				}
				// this is to check if a ship was missed
				
				else if(isBlank2(row,col))
				{
					board3[row][col - 64] = "/ ";
					if((row) == 10)
					{
						board3[row][col - 64] = " / ";
						
					}
					System.out.println("Ship is MISSED!");
					if(carrierIsSunk2(board2) == false && battleshipIsSunk2(board2) == false && 
							cruiserIsSunk2(board2) == false && submarineIsSunk2(board2) == false && 
							destroyerIsSunk2(board2) == false)
					{
						System.out.println("None of the ships have been sunk.");
					}
					else
					{
						System.out.println("These are the ships you have sunk: ");
					}
					
					if(carrierIsSunk2(board2) == true)
					{
						System.out.println("Carrier");
					}
					if(battleshipIsSunk2(board2) == true)
					{
						System.out.println("Battleship");
					}
					if(cruiserIsSunk2(board2) == true)
					{
						System.out.println("Cruiser");
					}
					if(submarineIsSunk2(board2) == true)
					{
						System.out.println("Submarine");
					}
					if(destroyerIsSunk2(board2) == true)
					{
						System.out.println("Destroyer");
					}
				}
				
				
			}
			
			else if(player.equals("2"))
			{
				// this is to make a delay of 2 seconds in showing the next player's board, so the players will not see each other's boards
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				System.out.println();
				System.out.println();
				printBoard2(board2);
				System.out.println();
				printBoard4(board4);
				
				do
				{
					System.out.print("Player " + player + ", enter a coordinate: ");
					attack = keyboard.nextLine().toUpperCase();
					
					if(attackIsValid(attack))	
					{
						if(!isRepeated2(attack))
						{
							break;
						}
					}
				}while(true);
				row = Integer.parseInt(attack.substring(1,attack.length()));
				col = (int) attack.charAt(0);
				
				// this is to check whether a ship was hit and/or sunk
				if(board1[row][col - 64].equals("© ") || board1[row][col - 64].equals(" © ") || board1[row][col - 64].equals("∆ ") ||
						board1[row][col - 64].equals(" ∆ ") || board1[row][col - 64].equals("* ") || board1[row][col - 64].equals(" * ") ||
						board1[row][col - 64].equals("# ") || board1[row][col - 64].equals(" # ") || board1[row][col - 64].equals("$ ") ||
						board1[row][col - 64].equals(" $ "))
				{
					board1[row][col - 64] = "X ";
					if((row) == 10)
					{
						board1[row][col - 64] = " X ";
					}
					
					board4[row][col - 64] = "X ";
					if((row) == 10)
					{
						board4[row][col - 64] = " X ";
					}
					countOfX2++;
					
					System.out.println("Ship is HIT!");
					
					if(carrierIsSunk1(board1) == false && battleshipIsSunk1(board1) == false && 
							cruiserIsSunk1(board1) == false && submarineIsSunk1(board1) == false && 
							destroyerIsSunk1(board1) == false)
					{
						System.out.println("None of the ships have been sunk.");
					}
					else
					{
						System.out.println("These are the ships you have sunk: ");
					}
					
					if(carrierIsSunk1(board1) == true)
					{
						System.out.println("Carrier");
					}
					if(battleshipIsSunk1(board1) == true)
					{
						System.out.println("Battleship");
					}
					if(cruiserIsSunk1(board1) == true)
					{
						System.out.println("Cruiser");
					}
					if(submarineIsSunk1(board1) == true)
					{
						System.out.println("Submarine");
					}
					if(destroyerIsSunk1(board1) == true)
					{
						System.out.println("Destroyer");
					}
					
				}
				// this is to check if a ship was missed
				else if(isBlank1(row,col))
					{
					board4[row][col - 64] = "/ ";
					if((row) == 10)
					{
						board4[row][col - 64] = " / ";
						
					}
					System.out.println("Ship is MISSED!");
					if(carrierIsSunk1(board1) == false && battleshipIsSunk1(board1) == false && 
							cruiserIsSunk1(board1) == false && submarineIsSunk1(board1) == false && 
							destroyerIsSunk1(board1) == false)
					{
						System.out.println("None of the ships have been sunk.");
					}
					else
					{
						System.out.println("These are the ships you have sunk: ");
					}
					if(carrierIsSunk1(board1) == true)
					{
						System.out.println("Carrier");
					}
					if(battleshipIsSunk1(board1) == true)
					{
						System.out.println("Battleship");
					}
					if(cruiserIsSunk1(board1) == true)
					{
						System.out.println("Cruiser");
					}
					if(submarineIsSunk1(board1) == true)
					{
						System.out.println("Submarine");
					}
					if(destroyerIsSunk1(board1) == true)
					{
						System.out.println("Destroyer");
					}
				}
				
				
			}
			
			// this is to check for a winner
			if(hasWinner(board3, countOfX1))
			{
				winner = true;
				System.out.println("Congratulations Player 1, you sunk all their ships. You won!");
			}
			else if(hasWinner(board4, countOfX2))
			{
				winner = true;
				System.out.println("Congratulations Player 2, you sunk all their ships. You won!");
			}
			
			turn++;
		}while(!winner);
		
		
	}
	
	/**
	 * preconditions: Board 1 must be declared
	 * postconditions: Player 1's board to place ships will be printed out
	 * 
	 * @param board1 This is Player 1's board to place their ships
	 */
	public static void printBoard1(String[][] board1)

	{
		board1[0][0] = " ";
		
		for(int row = 1; row < board1.length; row++)
		{
			board1[row][0] = "" + row;
		}
		
		for(int col = 1; col < board1.length; col++)
		{
			board1[0][col] = "" + (char) (65 + col - 1) + " ";
		}
		
		
		for(int row = 0; row < board1.length - 1; row++)
		{
			System.out.println(board1[row][0] + " |  " + board1[row][1] + " |  " + board1[row][2] + " |  " + board1[row][3] + " |  " + board1[row][4] + " |  " + board1[row][5] + " |  " + board1[row][6] + " |  " + board1[row][7] + " |  " + board1[row][8] + " |  " + board1[row][9] + " |  " + board1[row][10]);
			if(row != (board1.length - 1))
			{
				System.out.println("- +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -");
			}
		} 
		System.out.println(board1[10][0] + "| " + board1[10][1] + " | " + board1[10][2] + " | " + board1[10][3] + " | " + board1[10][4] + " | " + board1[10][5] + " | " + board1[10][6] + " | " + board1[10][7] + " | " + board1[10][8] + " | " + board1[10][9] + " | " + board1[10][10]);
	}

	/**
	 * preconditions: Board 1 must be declared
	 * postconditions: Player 1's board to mark guesses will be printed out
	 * 
	 * @param board3 This is Player 1's board to mark their guesses in trying to hit the opponent's ships
	 */
	public static void printBoard3(String[][] board3)

	{
		board3[0][0] = " ";
		
		for(int row = 1; row < board3.length; row++)
		{
			board3[row][0] = "" + row;
		}
		
		for(int col = 1; col < board3.length; col++)
		{
			board3[0][col] = "" + (char) (65 + col - 1) + " ";
		}
		
		
		for(int row = 0; row < board3.length - 1; row++)
		{
			System.out.println(board3[row][0] + " |  " + board3[row][1] + " |  " + board3[row][2] + " |  " + board3[row][3] + " |  " + board3[row][4] + " |  " + board3[row][5] + " |  " + board3[row][6] + " |  " + board3[row][7] + " |  " + board3[row][8] + " |  " + board3[row][9] + " |  " + board3[row][10]);
			if(row != (board3.length - 1))
			{
				System.out.println("- +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -");
			}
		} 
		System.out.println(board3[10][0] + "| " + board3[10][1] + " | " + board3[10][2] + " | " + board3[10][3] + " | " + board3[10][4] + " | " + board3[10][5] + " | " + board3[10][6] + " | " + board3[10][7] + " | " + board3[10][8] + " | " + board3[10][9] + " | " + board3[10][10]);
	}
	
	/**
	 * preconditions: Board 2 must be declared
	 * postconditions: Player 2's board to place ships will be printed out
	 * 
	 * @param board2 This is Player 2's board to place their ships
	 */
	public static void printBoard2(String[][] board2)
	{
		{
			board2[0][0] = " ";
			
			for(int row = 1; row < board2.length; row++)
			{
				board2[row][0] = "" + row;
			}
			
			for(int col = 1; col < board2.length; col++)
			{
				board2[0][col] = "" + (char) (65 + col - 1) + " ";
			}
			
			
			for(int row = 0; row < board2.length - 1; row++)
			{
				System.out.println(board2[row][0] + " |  " + board2[row][1] + " |  " + board2[row][2] + " |  " + board2[row][3] + " |  " + board2[row][4] + " |  " + board2[row][5] + " |  " + board2[row][6] + " |  " + board2[row][7] + " |  " + board2[row][8] + " |  " + board2[row][9] + " |  " + board2[row][10]);
				if(row != board2.length - 1)
				{
					System.out.println("- +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -");
				}
			} 
			System.out.println(board2[10][0] + "| " + board2[10][1] + " | " + board2[10][2] + " | " + board2[10][3] + " | " + board2[10][4] + " | " + board2[10][5] + " | " + board2[10][6] + " | " + board2[10][7] + " | " + board2[10][8] + " | " + board2[10][9] + " | " + board2[10][10]);
		}
	}
	
	
	/**
	 * preconditions: Board 2 must be declared
	 * postconditions: Player 2's board to mark guesses will be printed out
	 * 
	 * @param board4 This is Player 2's board to mark their guesses in trying to hit the opponent's ships
	 */
	public static void printBoard4(String[][] board4)

	{
		board4[0][0] = " ";
		
		for(int row = 1; row < board4.length; row++)
		{
			board4[row][0] = "" + row;
		}
		
		for(int col = 1; col < board4.length; col++)
		{
			board4[0][col] = "" + (char) (65 + col - 1) + " ";
		}
		
		
		for(int row = 0; row < board4.length - 1; row++)
		{
			System.out.println(board4[row][0] + " |  " + board4[row][1] + " |  " + board4[row][2] + " |  " + board4[row][3] + " |  " + board4[row][4] + " |  " + board4[row][5] + " |  " + board4[row][6] + " |  " + board4[row][7] + " |  " + board4[row][8] + " |  " + board4[row][9] + " |  " + board4[row][10]);
			if(row != board4.length - 1)
			{
				System.out.println("- +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -  +  -");
			}
		} 
		System.out.println(board4[10][0] + "| " + board4[10][1] + " | " + board4[10][2] + " | " + board4[10][3] + " | " + board4[10][4] + " | " + board4[10][5] + " | " + board4[10][6] + " | " + board4[10][7] + " | " + board4[10][8] + " | " + board4[10][9] + " | " + board4[10][10]);
	}

	
	/**
	 * preconditions: Board 1 must be printed out
	 * postconditions: Player 1's Carrier will be placed on their board
	 */
	public static void placeCarrier1()
	{
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Carrier (5 spaces): ");
					
				ship = "carrier";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
	
			if(!offOfBoard(row, col, direction, length,ship))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
		}
			
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 5; i++)
			{
				board1[row][col - 64 + i] = "© ";
				if((row) == 10)
				{
					board1[row][col - 64 + i] = " © ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 5; i++)
			{
				board1[row + i][col - 64] = "© ";
				if((row + i) == 10)
				
				{
					board1[row + i][col - 64] = " © ";
				}
			}
			
		
		}
	}
	
	
	/**
	 * preconditions: Board 2 must be printed out
	 * postconditions: Player 2's Carrier will be placed on their board
	 */
	public static void placeCarrier2()
	{
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Carrier (5 spaces): ");
					
				ship = "carrier";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
				
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
	
			if(!offOfBoard(row, col, direction, length,ship))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
		}
			
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 5; i++)
			{
				board2[row][col - 64 + i] = "© ";
				if((row) == 10)
				{
					board1[row][col - 64 + i] = " © ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 5; i++)
			{
				board2[row + i][col - 64] = "© ";
				if((row + i) == 10)
				
				{
					board2[row + i][col - 64] = " © ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: Board 1 must be printed out
	 * postconditions: Player 1's Battleship will be placed on their board
	 */
	public static void placeBattleship1()
	{
		printBoard1(board1);
		
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Battleship (4 spaces): ");
					
				ship = "battleship";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			
			if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping1(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping1(row,col,direction,length,ship))
			{
				System.out.println("You are not allowed to overlap your ships.");
			}
		}
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 4; i++)
			{
				board1[row][col - 64 + i] = "∆ ";
				
				if((row) == 10)
				{
					board1[row][col - 64 + i] = " ∆ ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 4; i++)
			{
				board1[row + i][col - 64] = "∆ ";
				
				if((row + i) == 10)
				{
					board1[row + i][col - 64] = " ∆ ";
				}
			}
		}
		
	}
	
	
	/**
	 * preconditions: Board 2 must be printed out
	 * postconditions: Player 2's Battleship will be placed on their board
	 */
	public static void placeBattleship2()
	{
		printBoard2(board2);
		
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Battleship (4 spaces): ");
					
				ship = "battleship";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			
			if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping2(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping2(row,col,direction,length,ship))
			{
				System.out.println("You are not allowed to overlap your ships.");
			}
		}
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 4; i++)
			{
				board2[row][col - 64 + i] = "∆ ";
				
				if((row) == 10)
				{
					board2[row][col - 64 + i] = " ∆ ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 4; i++)
			{
				board2[row + i][col - 64] = "∆ ";
				
				if((row + i) == 10)
				{
					board2[row + i][col - 64] = " ∆ ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: Board 1 must be printed out
	 * postconditions: Player 1's Cruiser will be placed on their board
	 */
	public static void placeCruiser1()
	{
		printBoard1(board1);
		while(true)
		{
			
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Cruiser (3 spaces): ");
					
				ship = "cruiser";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			if(!offOfBoard(row, col, direction,length,ship) && !(isOverlapping1(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping1(row,col,direction,length,ship))
			{
				
				System.out.println("You are not allowed to overlap your ships.");
			}
		}
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 3; i++)
			{
				board1[row][col - 64 + i] = "* ";
				if((row) == 10)
				{
					board1[row][col - 64 + i] = " * ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 3; i++)
			{
				board1[row + i][col - 64] = "* ";
				if((row + i) == 10)
				{
					board1[row + i][col - 64] = " * ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: Board 2 must be printed out
	 * postconditions: Player 2's Cruiser will be placed on their board
	 */
	public static void placeCruiser2()
	{
		printBoard2(board2);
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Cruiser (3 spaces): ");
					
				ship = "cruiser";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			if(!offOfBoard(row, col, direction,length,ship) && !(isOverlapping2(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping2(row,col,direction,length,ship))
			{
				
				System.out.println("You are not allowed to overlap your ships.");
			}
			
		}
		
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 3; i++)
			{
				board2[row][col - 64 + i] = "* ";
				if((row) == 10)
				{
					board2[row][col - 64 + i] = " * ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 3; i++)
			{
				board2[row + i][col - 64] = "* ";
				if((row + i) == 10)
				{
					board2[row + i][col - 64] = " * ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: Board 1 must be printed out
	 * postconditions: Player 1's Submarine will be placed on their board
	 */
	public static void placeSubmarine1()
	{
			printBoard1(board1);
			while(true)
			{
				do
				{
					System.out.print("Player " + player + " please enter a coordinate to place the front of your Submarine (3 spaces): ");
						
					ship = "submarine";
					coordinate = keyboard.nextLine().toUpperCase();
					if(coordinateIsValid(coordinate))
					{
						break;
					}
				}while(true);
			
				row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
				col = (int) coordinate.charAt(0);
				
				do
				{
					System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
					direction = keyboard.nextLine().toLowerCase();
					if(direction.equals("horizontally") || direction.equals("vertically"))
					{
						break;
					}
				}while(true);
				if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping1(row,col,direction,length,ship)))
				{
					break;
				}
				else if(offOfBoard(row, col, direction,length,ship))
				{
					System.out.println("The ship does not fit on the board.");
					continue;
				}
				else if(isOverlapping1(row,col,direction,length,ship))
				{
					
					System.out.println("You are not allowed to overlap your ships.");
				}
			}
			if(direction.equals("horizontally"))
			{
				for(int i = 0; i < 3; i++)
				{
					board1[row][col - 64 + i] = "# ";
					if((row) == 10)
					{
						board1[row][col - 64 + i] = " # ";
					}
				}
			}
			if(direction.equals("vertically"))
			{
				for(int i = 0; i < 3; i++)
				{
					board1[row + i][col - 64] = "# ";
					if((row + i) == 10)
					{
						board1[row + i][col - 64] = " # ";
					}
				}
			}
	}
	
	
	/**
	 * preconditions: Board 2 must be printed out
	 * postconditions: Player 2's Submarine will be placed on their board
	 */
	public static void placeSubmarine2()
	{
			printBoard2(board2);
			while(true)
			{
				do
				{
					System.out.print("Player " + player + " please enter a coordinate to place the front of your Submarine (3 spaces): ");
						
					ship = "submarine";
					coordinate = keyboard.nextLine().toUpperCase();
					if(coordinateIsValid(coordinate))
					{
						break;
					}
				}while(true);
			
				row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
				col = (int) coordinate.charAt(0);
				
				do
				{
					System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
					direction = keyboard.nextLine().toLowerCase();
					if(direction.equals("horizontally") || direction.equals("vertically"))
					{
						break;
					}
				}while(true);
				if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping2(row,col,direction,length,ship)))
				{
					break;
				}
				else if(offOfBoard(row, col, direction,length,ship))
				{
					System.out.println("The ship does not fit on the board.");
					continue;
				}
				else if(isOverlapping2(row,col,direction,length,ship))
				{
					
					System.out.println("You are not allowed to overlap your ships.");
				}
			}
			if(direction.equals("horizontally"))
			{
				for(int i = 0; i < 3; i++)
				{
					board2[row][col - 64 + i] = "# ";
					if((row) == 10)
					{
						board2[row][col - 64 + i] = " # ";
					}
				}
			}
			if(direction.equals("vertically"))
			{
				for(int i = 0; i < 3; i++)
				{
					board2[row + i][col - 64] = "# ";
					if((row + i) == 10)
					{
						board2[row + i][col - 64] = " # ";
					}
				}
			}
	}
	
	
	/**
	 * preconditions: Board 1 must be printed out
	 * postconditions: Player 1's Destroyer will be placed on their board
	 */
	public static void placeDestroyer1()
	{	
		printBoard1(board1);
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Destroyer (2 spaces): ");
					
				ship = "destroyer";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
		
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping1(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping1(row,col,direction,length,ship))
			{
				
				System.out.println("You are not allowed to overlap your ships.");
			}
		}
		
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 2; i++)
			{
				board1[row][col - 64 + i] = "$ ";
				if((row) == 10)
				{
					board1[row][col - 64 + i] = " $ ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 2; i++)
			{
				board1[row + i][col - 64] = "$ ";
				if((row + i) == 10)
				{
					board1[row + i][col - 64] = " $ ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: Board 2 must be printed out
	 * postconditions: Player 2's Destroyer will be placed on their board
	 */
	public static void placeDestroyer2()
	{	
		printBoard2(board2);
		while(true)
		{
			do
			{
				System.out.print("Player " + player + " please enter a coordinate to place the front of your Destroyer (2 spaces): ");
					
				ship = "destroyer";
				coordinate = keyboard.nextLine().toUpperCase();
				if(coordinateIsValid(coordinate))
				{
					break;
				}
			}while(true);
			
			row = Integer.parseInt(coordinate.substring(1,coordinate.length()));
			col = (int) coordinate.charAt(0);
			
			do
			{
				System.out.print("Enter whether you want to place your ship vertically or horizontally: ");
				direction = keyboard.nextLine().toLowerCase();
				if(direction.equals("horizontally") || direction.equals("vertically"))
				{
					break;
				}
			}while(true);
			
			if(!offOfBoard(row, col, direction, length,ship) && !(isOverlapping2(row,col,direction,length,ship)))
			{
				break;
			}
			else if(offOfBoard(row, col, direction,length,ship))
			{
				System.out.println("The ship does not fit on the board.");
				continue;
			}
			else if(isOverlapping2(row,col,direction,length,ship))
			{
				
				System.out.println("You are not allowed to overlap your ships.");
			}
		}
		if(direction.equals("horizontally"))
		{
			for(int i = 0; i < 2; i++)
			{
				board2[row][col - 64 + i] = "$ ";
				if((row) == 10)
				{
					board2[row][col - 64 + i] = " $ ";
				}
			}
		}
		if(direction.equals("vertically"))
		{
			for(int i = 0; i < 2; i++)
			{
				board2[row + i][col - 64] = "$ ";
				if((row + i) == 10)
				{
					board2[row + i][col - 64] = " $ ";
				}
			}
		}
	}
	
	
	/**
	 * preconditions: The user must enter in a coordinate
	 * postconditions: This will return true or false
	 * 
	 * @param coordinate This is the coordinate that the user typed in
	 * @return This will return true if the coordinate is valid and false if it is not
	 */
	public static boolean coordinateIsValid(String coordinate)
	{
		if(coordinate.length() >= 2 && (coordinate.equals("A1") || coordinate.equals("A2") || coordinate.equals("A3") || coordinate.equals("A4") || coordinate.equals("A5") || 
				coordinate.equals("A6") || coordinate.equals("A7") || coordinate.equals("A8") || coordinate.equals("A9") || coordinate.equals("A10") || 
				coordinate.equals("B1") || coordinate.equals("B2") || coordinate.equals("B3") || coordinate.equals("B4") || coordinate.equals("B5") || 
				coordinate.equals("B6") || coordinate.equals("B7") || coordinate.equals("B8") || coordinate.equals("B9") || coordinate.equals("B10") ||
				coordinate.equals("C1") || coordinate.equals("C2") || coordinate.equals("C3") || coordinate.equals("C4") || coordinate.equals("C5") || 
				coordinate.equals("C6") || coordinate.equals("C7") || coordinate.equals("C8") || coordinate.equals("C9") || coordinate.equals("C10") ||
				coordinate.equals("D1") || coordinate.equals("D2") || coordinate.equals("D3") || coordinate.equals("D4") || coordinate.equals("D5") || 
				coordinate.equals("D6") || coordinate.equals("D7") || coordinate.equals("D8") || coordinate.equals("D9") || coordinate.equals("D10") ||
				coordinate.equals("E1") || coordinate.equals("E2") || coordinate.equals("E3") || coordinate.equals("E4") || coordinate.equals("E5") || 
				coordinate.equals("E6") || coordinate.equals("E7") || coordinate.equals("E8") || coordinate.equals("E9") || coordinate.equals("E10") ||
				coordinate.equals("F1") || coordinate.equals("F2") || coordinate.equals("F3") || coordinate.equals("F4") || coordinate.equals("F5") || 
				coordinate.equals("F6") || coordinate.equals("F7") || coordinate.equals("F8") || coordinate.equals("F9") || coordinate.equals("F10") ||
				coordinate.equals("G1") || coordinate.equals("G2") || coordinate.equals("G3") || coordinate.equals("G4") || coordinate.equals("G5") || 
				coordinate.equals("G6") || coordinate.equals("G7") || coordinate.equals("G8") || coordinate.equals("G9") || coordinate.equals("G10") ||
				coordinate.equals("H1") || coordinate.equals("H2") || coordinate.equals("H3") || coordinate.equals("H4") || coordinate.equals("H5") || 
				coordinate.equals("H6") || coordinate.equals("H7") || coordinate.equals("H8") || coordinate.equals("H9") || coordinate.equals("H10") ||
				coordinate.equals("I1") || coordinate.equals("I2") || coordinate.equals("I3") || coordinate.equals("I4") || coordinate.equals("I5") || 
				coordinate.equals("I6") || coordinate.equals("I7") || coordinate.equals("I8") || coordinate.equals("I9") || coordinate.equals("I10") ||
				coordinate.equals("J1") || coordinate.equals("J2") || coordinate.equals("J3") || coordinate.equals("J4") || coordinate.equals("J5") || 
				coordinate.equals("J6") || coordinate.equals("J7") || coordinate.equals("J8") || coordinate.equals("J9") || coordinate.equals("J10")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * preconditions: The user must enter in an attack coordinate
	 * postconditions: This will return true or false
	 * 
	 * @param attack This is the attack coordinate that the user typed in
	 * @return This will return true if the attack coordinate is valid and false if it is not
	 */
	public static boolean attackIsValid(String attack)
	{
		if(attack.length() >= 2 && (attack.equals("A1") || attack.equals("A2") || attack.equals("A3") || attack.equals("A4") || attack.equals("A5") || 
				attack.equals("A6") || attack.equals("A7") || attack.equals("A8") || attack.equals("A9") || attack.equals("A10") || 
				attack.equals("B1") || attack.equals("B2") || attack.equals("B3") || attack.equals("B4") || attack.equals("B5") || 
				attack.equals("B6") || attack.equals("B7") || attack.equals("B8") || attack.equals("B9") || attack.equals("B10") ||
				attack.equals("C1") || attack.equals("C2") || attack.equals("C3") || attack.equals("C4") || attack.equals("C5") || 
				attack.equals("C6") || attack.equals("C7") || attack.equals("C8") || attack.equals("C9") || attack.equals("C10") ||
				attack.equals("D1") || attack.equals("D2") || attack.equals("D3") || attack.equals("D4") || attack.equals("D5") || 
				attack.equals("D6") || attack.equals("D7") || attack.equals("D8") || attack.equals("D9") || attack.equals("D10") ||
				attack.equals("E1") || attack.equals("E2") || attack.equals("E3") || attack.equals("E4") || attack.equals("E5") || 
				attack.equals("E6") || attack.equals("E7") || attack.equals("E8") || attack.equals("E9") || attack.equals("E10") ||
				attack.equals("F1") || attack.equals("F2") || attack.equals("F3") || attack.equals("F4") || attack.equals("F5") || 
				attack.equals("F6") || attack.equals("F7") || attack.equals("F8") || attack.equals("F9") || attack.equals("F10") ||
				attack.equals("G1") || attack.equals("G2") || attack.equals("G3") || attack.equals("G4") || attack.equals("G5") || 
				attack.equals("G6") || attack.equals("G7") || attack.equals("G8") || attack.equals("G9") || attack.equals("G10") ||
				attack.equals("H1") || attack.equals("H2") || attack.equals("H3") || attack.equals("H4") || attack.equals("H5") || 
				attack.equals("H6") || attack.equals("H7") || attack.equals("H8") || attack.equals("H9") || attack.equals("H10") ||
				attack.equals("I1") || attack.equals("I2") || attack.equals("I3") || attack.equals("I4") || attack.equals("I5") || 
				attack.equals("I6") || attack.equals("I7") || attack.equals("I8") || attack.equals("I9") || attack.equals("I10") ||
				attack.equals("J1") || attack.equals("J2") || attack.equals("J3") || attack.equals("J4") || attack.equals("J5") || 
				attack.equals("J6") || attack.equals("J7") || attack.equals("J8") || attack.equals("J9") || attack.equals("J10")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * preconditions: The user must enter in a valid coordinate and a valid direction
	 * postconditions: This will return true or false
	 * 
	 * @param row This is the row taken from the user's input
	 * @param col This is the column taken from the user's input
	 * @param direction This is the direction taken from the user's input
	 * @param length This is the length of the ship that is being placed
	 * @param s This is the name of the ship that is being placed
	 * @return This will return true if the ship being placed is going to be off the board and false if 
	 * 			it will not go off the board 
	 */ 
	public static boolean offOfBoard(int row, int col, String direction, int length, String s)
	{
		ship = Ship.shipSelected(s);
		length = Ship.lengthOfShip(s);
		if(direction.equals("horizontally"))
		{
			int check = length + (col - 64) - 1;
			if(check > 10)
			{
				return true;
			}
		}
		if(direction.equals("vertically"))
		{
			int check = length + row - 1;
			if(check > 10)
			{
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * preconditions: The user must enter in a valid coordinate and a valid direction
	 * postconditions: This will return true or false
	 * 
	 * @param row This is the row taken from the user's input
	 * @param col This is the column taken from the user's input
	 * @param direction This is the direction taken from the user's input
	 * @param length This is the length of the ship that is being placed
	 * @param s This is the name of the ship that is being placed
	 * @return This will return true is the ship being placed for Player 1 is going to overlap any other ship on Board 1, and false if
	 * 			it is not going to overlap  
	 */
	public static boolean isOverlapping1(int row, int col, String direction, int length, String s)
	{
		ship = Ship.shipSelected(s);
		length = Ship.lengthOfShip(s);
		if(ship.equals("battleship"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank1(row,col) || !isBlank1(row,col + 1) || !isBlank1(row,col + 2) || !isBlank1(row,col + 3))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank1(row,col) || !isBlank1(row + 1,col) || !isBlank1(row + 2,col) || !isBlank1(row + 3,col))
				{
					return true;
				}
			}
		}
		if(ship.equals("cruiser") || ship.equals("submarine"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank1(row,col) || !isBlank1(row,col + 1) || !isBlank1(row,col + 2))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank1(row,col) || !isBlank1(row + 1,col) || !isBlank1(row + 2,col))
				{
					return true;
				}
			}
		}
		if(ship.equals("destroyer"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank1(row,col) || !isBlank1(row,col + 1))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank1(row,col) || !isBlank1(row + 1,col))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * preconditions: The user must enter in a valid coordinate and a valid direction
	 * postconditions: This will return true or false
	 * 
	 * @param row This is the row taken from the user's input
	 * @param col This is the column taken from the user's input
	 * @param direction This is the direction taken from the user's input
	 * @param length This is the length of the ship that is being placed
	 * @param s This is the name of the ship that is being placed
	 * @return This will return true is the ship being placed for Player 2 is going to overlap any other ship on Board 2, and false if
	 * 			it is not going to overlap 
	 */
	public static boolean isOverlapping2(int row, int col, String direction, int length, String s)
	{
		ship = Ship.shipSelected(s);
		length = Ship.lengthOfShip(s);
		
		if(ship.equals("battleship"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank2(row,col) || !isBlank2(row,col + 1) || !isBlank2(row,col + 2) || !isBlank2(row,col + 3))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank2(row,col) || !isBlank2(row + 1,col) || !isBlank2(row + 2,col) || !isBlank2(row + 3,col))
				{
					return true; 
				}
			}
		}
		if(ship.equals("cruiser") || ship.equals("submarine"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank2(row,col) || !isBlank2(row,col + 1) || !isBlank2(row,col + 2))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank2(row,col) || !isBlank2(row + 1,col) || !isBlank2(row + 2,col))
				{
					return true;
				}
			}
		}
		if(ship.equals("destroyer"))
		{
			if(direction.equals("horizontally"))
			{
				if(!isBlank2(row,col) || !isBlank2(row,col + 1))
				{
					return true;
				}
			}
			if(direction.equals("vertically"))
			{
				if(!isBlank2(row,col) || !isBlank2(row + 1,col))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * preconditions: The user must enter in a valid coordinate 
	 * postconditions: This will return true or false
	 * 
	 * @param row This is the row taken from the user's input
	 * @param col This is the column taken from the user's input
	 * @return TThis will return true is the coordinate Player 2 guessed is not occupied by a ship on Board 1
	 */
	public static boolean isBlank1(int row, int col)
	{
		if(!(board1[row][col - 64].equals("© ") || board1[row][col - 64].equals(" © ") || board1[row][col - 64].equals("∆ ") ||
				board1[row][col - 64].equals(" ∆ ") || board1[row][col - 64].equals("* ") || board1[row][col - 64].equals(" * ") ||
				board1[row][col - 64].equals("# ") || board1[row][col - 64].equals(" # ") || board1[row][col - 64].equals("$ ") ||
				board1[row][col - 64].equals(" $ ") || board1[row][col - 64].equals("X ") || board1[row][col - 64].equals(" X ")))
		{
			return true;
		}
		return false;
	}
	
	
	/**
	 * preconditions: The user must enter in a valid coordinate 
	 * postconditions: This will return true or false
	 * 
	 * @param row This is the row taken from the user's input
	 * @param col This is the column taken from the user's input
	 * @return TThis will return true is the coordinate Player 1 guessed is not occupied by a ship on Board 2
	 */
	public static boolean isBlank2(int row, int col)
	{
		if(!(board2[row][col - 64].equals("© ") || board2[row][col - 64].equals(" © ") || board2[row][col - 64].equals("∆ ") ||
				board2[row][col - 64].equals(" ∆ ") || board2[row][col - 64].equals("* ") || board2[row][col - 64].equals(" * ") ||
				board2[row][col - 64].equals("# ") || board2[row][col - 64].equals(" # ") || board2[row][col - 64].equals("$ ") ||
				board2[row][col - 64].equals(" $ ") || board2[row][col - 64].equals("X ") || board2[row][col - 64].equals(" X ")))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * preconditions: The user must enter an attack coordinate
	 * postconditions: This will return true or false
	 * 
	 * @param attack this is the coordinate from user input
	 * @return This will return true if Player 1's coordinate has been guessed already and false if it has not
	 */
	public static boolean isRepeated1(String attack)
	{
		row = Integer.parseInt(attack.substring(1,attack.length()));
		col = (int) attack.charAt(0);
		if(board3[row][col - 64].equals("X ") || board3[row][col - 64].equals(" X "))
		{
			System.out.println("You have already guessed this spot.");
			return true;
		}
		return false;
	}
	
	/**
	 * preconditions: The user must enter an attack coordinate
	 * postconditions: This will return true or false
	 * 
	 * @param attack this is the coordinate from user input
	 * @return This will return true if Player 2's coordinate has been guessed already and false if it has not
	 */
	public static boolean isRepeated2(String attack)
	{
		row = Integer.parseInt(attack.substring(1,attack.length()));
		col = (int) attack.charAt(0);
		if(board4[row][col - 64].equals("X ") || board4[row][col - 64].equals(" X "))
		{
			System.out.println("You have already guessed this spot.");
			return true;
		}
		return false;
	}
	
	/**
	 * preconditions: All of the ships on the board being checked must be sunk
	 * postconditions: This will return true or false
	 * 
	 * @param board This is the board that is being checked for a winner
	 * @param count This is the number of hits (Xs) that are on the board
	 * @return This will return true if there are 17 Xs on the board and false if there are not 17 Xs
	 */
	public static boolean hasWinner(String[][]board, int count)
	{
		if(count == 17)
		{
			return true;
		}
		return false;
	}

	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board1 This is the board that is being checked
	 * @return This will return true if Player 1's Carrier has been sunk and false if it has not been sunk
	 */
	public static boolean carrierIsSunk1(String[][] board1)
	{
		outer: for(int row = 1; row < board1.length; row++)
		{
			for(int col = 1; col < board1.length; col++)
			{
				if(board1[row][col].equals("© ") || board1[row][col].equals(" © "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
	
	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board2 This is the board that is being checked
	 * @return This will return true if Player 2's Carrier has been sunk and false if it has not been sunk
	 */
	public static boolean carrierIsSunk2(String[][] board2)
	{
		outer: for(int row = 1; row < board2.length; row++)
		{
			for(int col = 1; col < board2.length; col++)
			{
				if(board2[row][col].equals("© ") || board2[row][col].equals(" © "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}

	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board1 This is the board that is being checked
	 * @return This will return true if Player 1's Battleship has been sunk and false if it has not been sunk
	 */
	public static boolean battleshipIsSunk1(String[][] board1)
	{
		outer: for(int row = 1; row < board1.length; row++)
		{
			for(int col = 1; col < board1.length; col++)
			{
				if(board1[row][col].equals("∆ ") || board1[row][col].equals(" ∆ "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
	
	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board2 This is the board that is being checked
	 * @return This will return true if Player 2's Battleship has been sunk and false if it has not been sunk
	 */
	public static boolean battleshipIsSunk2(String[][] board2)
	{
		outer: for(int row = 1; row < board2.length; row++)
		{
			for(int col = 1; col < board2.length; col++)
			{
				if(board2[row][col].equals("∆ ") || board2[row][col].equals(" ∆ "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}

	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board1 This is the board that is being checked
	 * @return This will return true if Player 1's Cruiser has been sunk and false if it has not been sunk
	 */
	public static boolean cruiserIsSunk1(String[][] board1)
	{
		outer: for(int row = 1; row < board1.length; row++)
		{
			for(int col = 1; col < board1.length; col++)
			{
				if(board1[row][col].equals("* ") || board1[row][col].equals(" * "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
	
	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board2 This is the board that is being checked
	 * @return This will return true if Player 2's Cruiser has been sunk and false if it has not been sunk
	 */
	public static boolean cruiserIsSunk2(String[][] board2)
	{
		outer: for(int row = 1; row < board2.length; row++)
		{
			for(int col = 1; col < board2.length; col++)
			{
				if(board2[row][col].equals("* ") || board2[row][col].equals(" * "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}

	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board1 This is the board that is being checked
	 * @return This will return true if Player 1's Submarine has been sunk and false if it has not been sunk
	 */
	public static boolean submarineIsSunk1(String[][] board1)
	{
		outer: for(int row = 1; row < board1.length; row++)
		{
			for(int col = 1; col < board1.length; col++)
			{
				if(board1[row][col].equals("# ") || board1[row][col].equals(" # "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
	
	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board2 This is the board that is being checked
	 * @return This will return true if Player 2's Submarine has been sunk and false if it has not been sunk
	 */
	public static boolean submarineIsSunk2(String[][] board2)
	{
		outer: for(int row = 1; row < board2.length; row++)
		{
			for(int col = 1; col < board2.length; col++)
			{
				if(board2[row][col].equals("# ") || board2[row][col].equals(" # "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}

	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board1 This is the board that is being checked
	 * @return This will return true if Player 1's Destroyer has been sunk and false if it has not been sunk
	 */
	public static boolean destroyerIsSunk1(String[][] board1)
	{
		outer: for(int row = 1; row < board1.length; row++)
		{
			for(int col = 1; col < board1.length; col++)
			{
				if(board1[row][col].equals("$ ") || board1[row][col].equals(" $ "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
	
	
	/**
	 * preconditions: The player's ship must have been placed on the board
	 * postconditions: This will return true or false
	 * 
	 * @param board2 This is the board that is being checked
	 * @return This will return true if Player 2's Destroyer has been sunk and false if it has not been sunk
	 */
	public static boolean destroyerIsSunk2(String[][] board2)
	{
		outer: for(int row = 1; row < board2.length; row++)
		{
			for(int col = 1; col < board2.length; col++)
			{
				if(board2[row][col].equals("$ ") || board2[row][col].equals(" $ "))
				{
					sunk = false;
					break outer;
				}
				else
				{
					sunk = true;
				}
			}
		}
		return sunk;
	}
}


