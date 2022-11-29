package MyFarm;

public enum LandState {
    BLOCKED,
    UNPLOWED,
    PLOWED,
    PLANTED,
    WITHERED,
    HARVESTABLE;

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
            case HARVESTABLE:
                return "HARVESTABLE";
            default:
                return "invalid";
        }

    }
}