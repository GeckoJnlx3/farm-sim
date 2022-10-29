package MyFarm;

public enum Title {
	FARMER(1), 
	REGISTERED_FARMER(2),
	DISTINGUISHED_FARMER(3),
	LEGENDARY_FARMER(4);
	
	private int constant;
	
	private Title(int constant) {
		this.constant = constant;
	}

	public int getConstant() {
		return constant;
	}

	public void setConstant(int constant) {
		this.constant = constant;
	}
	
	public Title setTitle(int constant) {
		switch (constant) {
		case 1:
			return FARMER;
		case 2: 
			return REGISTERED_FARMER;
		case 3:
			return DISTINGUISHED_FARMER;
		case 4:
			return LEGENDARY_FARMER;
		default:
			return FARMER;
		}
	}
	
	public void obtainTitleSuccessPrint(int input) {
		switch (input) {
		case 2:
			System.out.println("Congratulations! You are now a registered farmer!");
			break;
		case 3:
			System.out.println("Congratulations! You are now a distinguished farmer!");
			break;
		case 4:
			System.out.println("Congratulations! You are now a legendary farmer!");
			break;
		}
	}
}
