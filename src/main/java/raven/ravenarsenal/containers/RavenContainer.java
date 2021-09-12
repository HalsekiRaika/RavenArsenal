package raven.ravenarsenal.containers;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import java.util.function.Supplier;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

public final class RavenContainer {
    private RavenContainer() { throw new AssertionError(); }

    private static final DeferredRegister<ContainerType<?>> REGISTERER = DeferredRegister.create(ForgeRegistries.CONTAINERS, MOD_ID);

    public static final RegistryObject<ContainerType<ArsenalContainer>> RAVEN_ARSENAL_BLOCK_CONTAINER = REGISTERER.register("raven_arsenal_block", create(ArsenalContainer::createClientSide));

    public static final INamedContainerProvider ARSENAL_PROVIDER = new ArsenalContainerProvider();

    public static void register(@Nonnull IEventBus eventBus) {
        REGISTERER.register(eventBus);
        eventBus.addListener(RavenContainer::screenRegister);
    }

    private static void screenRegister(@Nonnull FMLClientSetupEvent clientSetup) {
        ScreenManager.registerFactory(RAVEN_ARSENAL_BLOCK_CONTAINER.get(), ArsenalScreen::new);
    }

    @Nonnull
    private static <T extends Container> Supplier<ContainerType<T>> create(@Nonnull IContainerFactory<T> clientContainerFactory) {
        return () -> IForgeContainerType.create(clientContainerFactory);
    }
}
