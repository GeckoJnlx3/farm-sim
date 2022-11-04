package MCO1Farm;


class TextLand {
    LandState landState;
    Crop crop;
    
    public TextLand () {
        landState = LandState.UNPLOWED;
        crop = new Crop("");
    }
}