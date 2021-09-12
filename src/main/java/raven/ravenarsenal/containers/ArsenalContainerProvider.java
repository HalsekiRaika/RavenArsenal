package raven.ravenarsenal.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

final class ArsenalContainerProvider implements INamedContainerProvider {
    private final ITextComponent displayName;

    ArsenalContainerProvider() {
        this.displayName = new TranslationTextComponent(MOD_ID + ".container.raven_arsenal_block");
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return displayName;
    }

    @Override
    @Nonnull
    public Container createMenu(int windowID, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity playerEntity) {
        return ArsenalContainer.createServerSide(windowID, playerInventory);
    }
}
