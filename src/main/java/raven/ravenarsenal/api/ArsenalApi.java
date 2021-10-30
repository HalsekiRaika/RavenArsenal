package raven.ravenarsenal.api;

public final class ArsenalApi implements IRAApi {
    private static final ArsenalApi INSTANCE = new ArsenalApi();



    public static ArsenalApi getInstance() {
        return INSTANCE;
    }
}
