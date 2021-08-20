package raven.ravenarsenal.capabilities;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public final class WeaponModifierCapability {
    private WeaponModifierCapability() {
        throw new AssertionError();
    }

    private final IItemHandler itemHandler = new ItemStackHandler();

    public static void register() {
        //CapabilityManager.INSTANCE.register();
    }

    public static class ModifierHandler {

    }
}
