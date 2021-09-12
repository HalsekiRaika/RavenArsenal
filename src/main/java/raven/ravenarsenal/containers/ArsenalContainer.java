package raven.ravenarsenal.containers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.PlayerMainInvWrapper;
import raven.ravenarsenal.containers.libs.SlotPositionLessContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ArsenalContainer extends SlotPositionLessContainer {
    public static ArsenalContainer createServerSide(int windowId, PlayerInventory pInv) {
        return new ArsenalContainer(windowId, pInv);
    }

    public static ArsenalContainer createClientSide(int windowId, PlayerInventory pInv, PacketBuffer packet) {
        return new ArsenalContainer(windowId, pInv);
    }

    private static final int CONTAINER_INV_SIZE = 9;
    private static final int PLAYER_MAIN_INV_SIZE = 27;
    private static final int PLAYER_HOTBAR_INV_SIZE = 9;

    private final IItemHandler containerHandler;
    private final PlayerMainInvWrapper pInvHandler;

    private final List<SlotItemHandler> containerInv;
    private final List<SlotItemHandler> playerMainInv;
    private final List<SlotItemHandler> playerHotBarInv;

    private ArsenalContainer(int windowId, PlayerInventory pInv) {
        super(RavenContainer.RAVEN_ARSENAL_BLOCK_CONTAINER.get(), windowId);
        this.containerHandler = new ItemStackHandler(CONTAINER_INV_SIZE);
        this.pInvHandler = new PlayerMainInvWrapper(pInv);
        this.containerInv = new ArrayList<>();
        this.playerMainInv = new ArrayList<>();
        this.playerHotBarInv = new ArrayList<>();

        for (int slotNum = 0; slotNum < CONTAINER_INV_SIZE; slotNum++) {
            SlotItemHandler handler = posLessSlot(containerHandler, slotNum);
            this.addSlot(handler);
            this.containerInv.add(handler);
        }

        for (int slotNum = 0; slotNum < PLAYER_MAIN_INV_SIZE; slotNum++) {
            SlotItemHandler handler = posLessSlot(pInvHandler, slotNum + PLAYER_HOTBAR_INV_SIZE);
            this.addSlot(handler);
            this.playerMainInv.add(handler);
        }

        for (int slotNum = 0; slotNum < PLAYER_HOTBAR_INV_SIZE; slotNum++) {
            SlotItemHandler handler = posLessSlot(pInvHandler, slotNum);
            this.addSlot(handler);
            this.playerHotBarInv.add(handler);
        }
    }


    @Nonnull
    @Override
    public ItemStack transferStackInSlot(@Nonnull PlayerEntity playerIn, int index) {
        Slot sourceSlot = inventorySlots.get(index);
        if(sourceSlot == null || !sourceSlot.getHasStack()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getStack();
        ItemStack copy = sourceStack.copy();

        if(isPlayerInventory(index))
            if(!mergeItemStack(sourceStack, 36, 45, false))
                return ItemStack.EMPTY;

        if(isContainerSlot(index))
            if(!mergeItemStack(sourceStack, 0, 35, false))
                return ItemStack.EMPTY;

        if(sourceStack.getCount() == 0) {
            sourceSlot.putStack(ItemStack.EMPTY);
        } else {
            sourceSlot.onSlotChanged();
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copy;
    }

    private boolean isPlayerInventory(int index) {
        return 0 <= index && index <= 35;
    }

    private boolean isContainerSlot(int index) {
        return index == 36 || index == 45;
    }

    public List<SlotItemHandler> hotbarSlots() {
        return Collections.unmodifiableList(playerHotBarInv);
    }

    public List<SlotItemHandler> playerInventorySlots() {
        return Collections.unmodifiableList(playerMainInv);
    }

    public List<SlotItemHandler> containerInventorySlots() {
        return Collections.unmodifiableList(containerInv);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity playerIn) {
        return true;
    }

    private static SlotItemHandler posLessSlot(IItemHandler itemHandler, int index) {
        return new SlotItemHandler(itemHandler, index, 0, 0);
    }
}
