package raven.ravenarsenal.containers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.SlotItemHandler;
import raven.ravenarsenal.containers.libs.SlotPositionHoldingContainerScreen;
import raven.ravenarsenal.containers.libs.geometry.IntPoint2d;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static raven.ravenarsenal.RavenArsenal.MOD_ID;

final class ArsenalScreen extends SlotPositionHoldingContainerScreen<ArsenalContainer> {
    ArsenalScreen(ArsenalContainer container, PlayerInventory pInv, ITextComponent textComponent) {
        super(container, pInv, textComponent);
    }

    private static final ResourceLocation rLocation = new ResourceLocation(MOD_ID, "textures/gui/arsenal.png");

    @Override
    protected void init() {
        this.xSize = 217;
        this.ySize = 244;
        super.init();
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.blendColor(1.0f, 1.0f,  1.0f, 1.0f);
        this.getMinecraft().getTextureManager().bindTexture(rLocation);

        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);
    }

    @Nonnull
    @Override
    protected Map<Slot, IntPoint2d> calculateSlotPosition(@Nonnull ArsenalContainer container) {
        final int[][] CONTAINER_SLOT_POS_ARRAY = new int[][]{
                {57, 24}, {143, 24}, {57, 110}, {143, 110}, // OuterSlots {A, B, C, D}
                {100, 33}, {133, 67}, {100, 100}, {67, 67}, // InnerSlots {E, F, G, H}
                {100, 67} // Center Slot {I}
        };
        // player
        final int
                PLAYER_MAIN_INV_START_POS_X = 28,
                PLAYER_MAIN_INV_START_POS_Y = 150,
                PLAYER_HOTBAR_INV_START_POS_X = 28,
                PLAYER_HOTBAR_INV_START_POS_Y = 216;

        final int SLOT_SIZE_X_LIM = 18;
        final int SLOT_SIZE_Y_LIM = 18;

        final int PLAYER_MAIN_INV_COL_LIM = 3;
        final int PLAYER_MAIN_INV_ROW_LIM = 9;

        Map<Slot, IntPoint2d> slotMap = new HashMap<>();

        List<SlotItemHandler> pMainInvSlots = container.playerInventorySlots();
        for (int x = 0; x < PLAYER_MAIN_INV_ROW_LIM; x++) {
            for (int y = 0; y < PLAYER_MAIN_INV_COL_LIM; y++) {
                SlotItemHandler slot = pMainInvSlots.get(x + y * PLAYER_MAIN_INV_ROW_LIM);
                slotMap.put(slot, new IntPoint2d(
                        PLAYER_MAIN_INV_START_POS_X + x * SLOT_SIZE_X_LIM,
                        PLAYER_MAIN_INV_START_POS_Y + y * SLOT_SIZE_Y_LIM));
            }
        }

        List<SlotItemHandler> pHotBarInvSlots = container.hotbarSlots();
        for (int i = 0; i < pHotBarInvSlots.size(); i++) {
            SlotItemHandler slot = pHotBarInvSlots.get(i);
            slotMap.put(slot, new IntPoint2d(PLAYER_HOTBAR_INV_START_POS_X + i * SLOT_SIZE_X_LIM, PLAYER_HOTBAR_INV_START_POS_Y));
        }

        List<SlotItemHandler> containerInvSlots = container.containerInventorySlots();
        for (int i = 0; i < containerInvSlots.size(); i++) {
            SlotItemHandler slot = containerInvSlots.get(i);
            slotMap.put(slot, new IntPoint2d(CONTAINER_SLOT_POS_ARRAY[i][0], CONTAINER_SLOT_POS_ARRAY[i][1]));
        }

        return slotMap;
    }
}
