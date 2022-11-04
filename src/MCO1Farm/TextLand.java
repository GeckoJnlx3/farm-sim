package MCO1Farm;

/**
 * Land plot that the player can interact with.
 * Where Crops can be planted, grown and harvested.
 */

class TextLand {
    LandState landState;
    Crop crop;

    /**
     * Constructor for the single land plot.
     * Initialized with no crop, and in an
     * UNPLOWED state.
     */
    public TextLand () {
        landState = LandState.UNPLOWED;
        crop = new Crop("");
    }
}