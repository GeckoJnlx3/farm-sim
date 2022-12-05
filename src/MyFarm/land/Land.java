package MyFarm.land;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import MyFarm.crop.Crop;

/**
 * Land plot that the player can interact with.
 * Contains an array of landstate and crops 
 * representing each plot of land. 
 */
public class Land {
    public LandState [][] landState = new LandState[5][10];
    public Crop crops [][] = new Crop[5][10];
    String [] rockCSVData = new String [31];
    String rockPath = "src/MyFarm/rock/scatter.csv";
    
    /**
     * Constructor for land. Creates the rocks and sets them.
     */
    public Land () {
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < 10; j++) {
                landState[i][j] = LandState.UNPLOWED;

                crops[i][j] = new Crop("");
            }
        }
        createRock();
        setRocks();
    }

    /**
     * sets the rocks on the landstate from the .csv file generated
     * by createRocks()
     */
    private void setRocks() {
    	BufferedReader br;
    	try {
            br = new BufferedReader (new FileReader(rockPath));
            String line = "";
            int i = 0;
            while ((line = br.readLine()) != null){
                rockCSVData[i] = line;
                i++;
            }
            String [][] rockList = new String [31][2];
            for (i = 1; rockCSVData[i] != null; i++) {
                rockList[i] = rockCSVData[i].split(",");

                int parseRow = Integer.parseInt(rockList[i][0]);
                int parseCol = Integer.parseInt(rockList[i][1]);

                landState[parseRow][parseCol] = LandState.BLOCKED;
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 

    }

    /**
     * writes a .csv file with the positions of each rock
     */
    private void createRock() {
        Random rand = new Random();
        int rockAmt = rand.nextInt(21) + 10;
        int i = 0;
        try {
        	PrintWriter pw = new PrintWriter(new File(rockPath));
            StringBuilder sb = new StringBuilder();
            ArrayList <Rock>rockGen = new ArrayList<Rock>();

            while (i < rockAmt) {
                Rock temp = new Rock();
                boolean found = false;

                for (int j = 0; j < rockGen.size() || !found; j++) {
                    if (temp.equals(rockGen.get(j)))
                        found = true;
                }

                if (!found){
                    rockGen.add(temp);
                    sb.append(rockGen.get(i).x + "," + rockGen.get(i).y);
                    if (i != rockAmt-1)
                        sb.append("\n");
                    i++;
                }
            }
            pw.write(sb.toString());
            pw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}