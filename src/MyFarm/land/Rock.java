package MyFarm.land;

import java.util.Random;
/**
 * Rock class - contains the position of the rock
 */
public class Rock {
	int x;
	int y;
	
	/**
	 * Initializes a randomized value of x and y
	 */
	Rock (){
		Random rand = new Random();
		this.x = rand.nextInt(5);
		this.y = rand.nextInt(10);
	}
	/**
	 * Initializes a set value of x and y
	 */
	Rock(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * puts the rock in a string format of
	 * "Rock: x, y"
	 */
	public String toString(){
		return "Rock:" + x + ", " + y;
	}

	/**
	 * compares the values of the rocks together
	 * @param rock that is compared with this. rock
	 * @return true if equal, false if not
	 */
	public boolean equals (Rock rock) {
		if (this.x == rock.x &&
			this.y == rock.y) 
			return true;
		else return false;
	}
	
}
