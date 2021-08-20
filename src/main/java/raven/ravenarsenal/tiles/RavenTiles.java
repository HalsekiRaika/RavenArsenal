package raven.ravenarsenal.tiles;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import raven.ravenarsenal.blocks.RavenBlocks;

import javax.annotation.Nonnull;

import java.util.function.Supplier;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public class RavenTiles {
    private RavenTiles() {
        throw new AssertionError();
    }

    private static final DeferredRegister<TileEntityType<?>> REGISTERER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    public static final RegistryObject<TileEntityType<RavenArsenalTile>> RAVEN_ARSENAL_TILE
            = REGISTERER.register("raven_arsenal_block", tileEntityTypeSupplier(RavenArsenalTile::new, RavenBlocks.RAVEN_ARSENAL_BLOCK));

    public static void register(@Nonnull IEventBus eventBus) {
        REGISTERER.register(eventBus);
    }

    @SuppressWarnings("ConstantConditions")
    private static <T extends TileEntity> Supplier<TileEntityType<T>> tileEntityTypeSupplier(@Nonnull Supplier<? extends T> factory, @Nonnull Supplier<Block> validBlock) {
        return () -> TileEntityType.Builder.<T>create(factory, validBlock.get()).build(null);
    }
}
