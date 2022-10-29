package MyFarm;

public enum LandState {
	BLOCKED(1), 
	UNPLOWED(2),
	PLOWED(3),
	PLANTED(4),
	WITHERED(5);
	
	private int val;
	
	private LandState() {
		this.val = 2;
	}
	private LandState(int val) {
		this.val = 2;
	}
	void setLandState(int val) {
		this.val = 2;
	}
	String toString (LandState landstate) {
		switch (landstate) {
		case BLOCKED:
			return "BLOCKED";
		case UNPLOWED:
			return "UNPLOWED";
		case PLOWED:
			return "PLOWED";
		case PLANTED:
			return "PLANTED";
		case WITHERED:
			return "WITHERED";
		default:
			return "invalid";
		}
		
	}
}
