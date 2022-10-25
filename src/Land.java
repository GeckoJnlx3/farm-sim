package MyFarm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Land {
	LandState [][] landState = new LandState[5][10];
	//Crop crop [][]; 	
	String [] rockCSVData = new String [12];
	public Land () {
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 10; j++) {
				landState[i][j] = LandState.UNPLOWED;
			}
		}
	}
	
	void setRocks(String path) {
		try {
			BufferedReader br = new BufferedReader (new FileReader(path));
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null){
				rockCSVData [i] = line;
				i++;
			}
			String [][] rockList = new String [20][];
			for (i = 0; i < rockCSVData.length; i++) {
				rockList[i] = rockCSVData[i].split(",");
				landState[Integer.parseInt(rockList[i][0])][Integer.parseInt(rockList[i][1])] = LandState.BLOCKED;
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
