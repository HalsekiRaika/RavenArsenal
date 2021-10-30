package raven.ravenarsenal.api.material;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public final class RavenMaterial {
    private RavenMaterial() { throw new AssertionError(); }

    public static final MaterialLocation IRON = createProp("iron");
    public static final MaterialLocation GOLD = createProp("gold");
    public static final MaterialLocation NETHERITE = createProp("netherite");

    // #region MOD_INTEGRATION

    public static final MaterialLocation LEAD = createProp("lead");
    public static final MaterialLocation ZINC = createProp("zinc");
    public static final MaterialLocation BRASS = createProp("brass");
    public static final MaterialLocation COPPER = createProp("copper");
    public static final MaterialLocation SILVER = createProp("silver");

    // #endregion

    private static MaterialLocation createProp(String name) {
        return new MaterialLocation(MOD_ID, name);
    }
}
