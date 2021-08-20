package raven.ravenarsenal.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import raven.ravenarsenal.blocks.RavenBlocks;
import raven.ravenarsenal.itemgroups.RAItemGroup;

import javax.annotation.Nonnull;

import java.util.function.Supplier;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public class RavenItems {
    private RavenItems() {
        throw new AssertionError();
    }

    private static final DeferredRegister<Item> REGISTERER = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static RegistryObject<Item> DEBUG_ITEM = REGISTERER.register("debug_item", DebugItem::new);
    public static RegistryObject<Item> RAVEN_ARSENAL
            = REGISTERER.register("raven_arsenal_block", blockItemSupplier(RavenBlocks.RAVEN_ARSENAL_BLOCK));

    public static void register(@Nonnull IEventBus bus) {
        REGISTERER.register(bus);
    }

    private static Supplier<BlockItem> blockItemSupplier(Supplier<Block> blockSupplier) {
        return () -> new BlockItem(blockSupplier.get(), new Item.Properties().group(RAItemGroup.RAVEN_MAIN));
    }
}
