package raven.ravenarsenal.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public class RavenBlocks {
    private RavenBlocks() {
        throw new AssertionError();
    }

    private static final DeferredRegister<Block> REGISTERER = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> RAVEN_ARSENAL_BLOCK = REGISTERER.register("raven_arsenal_block", RavenArsenalBlock::new);

    public static void register(@Nonnull IEventBus eventBus) {
        REGISTERER.register(eventBus);
    }
}
