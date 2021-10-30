package raven.ravenarsenal.api.modifier;


import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IModifier<T> extends IForgeRegistryEntry<T> {

    default int getSlotUse() { return 1; }
}
