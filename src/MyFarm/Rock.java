package MyFarm;

import java.util.Random;

public class Rock {
	int x;
	int y;
	
	Rock (){
		Random rand = new Random();
		this.x = rand.nextInt(5);
		this.y = rand.nextInt(10);
	}
	
	Rock(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public String toString(){
		return "Rock:" + x + ", " + y;
	}
	public boolean equals (Rock rock) {
		if (this.x == rock.x &&
			this.y == rock.y) 
			return true;
		else return false;
	}
	
}
