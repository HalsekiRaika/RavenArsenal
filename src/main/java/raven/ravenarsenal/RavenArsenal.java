package raven.ravenarsenal;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import raven.ravenarsenal.api.ArsenalApi;
import raven.ravenarsenal.blocks.RavenBlocks;
import raven.ravenarsenal.containers.RavenContainer;
import raven.ravenarsenal.items.RavenItems;
import raven.ravenarsenal.tiles.RavenTiles;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

@Mod(MOD_ID)
public class RavenArsenal {
    public static final String MOD_ID = "raven_arsenal";
    private static final Logger LOGGER = LogManager.getLogger("RavenArsenal");

    private static RavenArsenal INSTANCE;

    public RavenArsenal() {
        INSTANCE = this;
        IEventBus fmlEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        RavenBlocks.register(fmlEventBus);
        RavenItems.register(fmlEventBus);
        RavenTiles.register(fmlEventBus);
        RavenContainer.register(fmlEventBus);

        ArsenalApi api = ArsenalApi.getInstance();
        Addons.onLoad(api);
    }

    public static RavenArsenal getInstance() {
        return INSTANCE;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
