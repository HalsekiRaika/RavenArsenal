package raven.ravenarsenal;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import raven.ravenarsenal.blocks.RavenBlocks;
import raven.ravenarsenal.items.RavenItems;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

@Mod(MOD_ID)
public class RavenArsenal {
    public static final String MOD_ID = "raven_arsenal";

    private static RavenArsenal INSTANCE;

    public RavenArsenal() {
        INSTANCE = this;
        IEventBus fmlEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RavenBlocks.register(fmlEventBus);
        RavenItems.register(fmlEventBus);
    }

}
