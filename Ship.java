/**
 * This class is used to define the ships in the battleship game. It will define what ship is being used and how many spots that ship takes up.
 *
 */
public class Ship {

	private static int length = 0;
	private static String ship = "";
	
	private static String carrier = "carrier";
	private static String battleship = "battleship";
	private static String cruiser = "cruiser";
	private static String submarine = "submarine";
	private static String destroyer = "destroyer";
	
	/**
	 * preconditions: The player must be enter valid coordinates and valid directions to place the ship
	 * postconditions: This will return a String for the name of the ship
	 * 
	 * @param s This is name of the ship
	 * @return This will return the type of ship that is being placed
	 */
	public static String shipSelected(String s)
	{
		if(s.equals("carrier"))
		{
			ship = carrier;
		}
		if(s.equals("battleship"))
		{
			ship = battleship;
		}
		if(s.equals("cruiser"))
		{
			ship = cruiser;
		}
		if(s.equals("submarine"))
		{
			ship = submarine;
		}
		if(s.equals("destroyer"))
		{
			ship = destroyer;
		}
		return ship;
	}
	
	/**
	 * preconditions: Only one ship can be selected at a time
	 * postconditions: This will return the length of the ship (1, 2, 3, 4, or 5 depending on which ship is selected)
	 * 
	 * @param ship This is the name of the ship that was selected
	 * @return This will return an integer between 1 to 5 to represent the number of spots the ship selected will take up on the board
	 */
	public static int lengthOfShip(String ship)
	{
		ship = Ship.shipSelected(ship);
		switch(ship)
		{
			case "carrier":
				length = 5;
				break;
			case "battleship":
				length = 4;
				break;
			case "cruiser":
				length = 3;
				break;
			case "submarine":
				length = 3;
				break;
			case "destroyer":
				length = 2;
				break;
		}
		return length;
	}
	
}
