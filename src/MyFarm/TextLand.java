package MyFarm;

class TextLand {
    LandState landState;
    Crop crop;
    String [] rockCSVData = new String [30];
    public TextLand () {
        landState = LandState.UNPLOWED;
        crop = new Crop("");
    }
}