package MyFarm.land;

import java.util.Random;

/**
 * Rock class - contains an x and y coordinate for the position.
 */
public class Rock {
	int x;
	int y;
	
	/**
	 * Constructor for Rock class. Generates a random coordinate 
	 * for x and y
	 */
	Rock (){
		Random rand = new Random();
		this.x = rand.nextInt(5);
		this.y = rand.nextInt(10);
	}

	/**
	 * Compares current rock with another rock
	 * @param rock 	inputted rock
	 * @return	true if they are in the same position, false if not
	 */
	public boolean equals (Rock rock) {
		if (this.x == rock.x &&
			this.y == rock.y) 
			return true;
		else return false;
	}
	
}
