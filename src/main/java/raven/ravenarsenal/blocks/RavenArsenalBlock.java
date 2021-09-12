package raven.ravenarsenal.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.IContainerProvider;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import raven.ravenarsenal.containers.RavenContainer;
import raven.ravenarsenal.tiles.RavenTiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

final class RavenArsenalBlock extends Block {
    RavenArsenalBlock() {
        super(Properties.create(Material.IRON));
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos,
                                             @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
        if(worldIn.isRemote()) return ActionResultType.SUCCESS;
        if(!(player instanceof ServerPlayerEntity)) return ActionResultType.FAIL;

        ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
        //SimpleNamedContainerProvider provider = new SimpleNamedContainerProvider((IContainerProvider) RavenContainer.ARSENAL_PROVIDER)
        NetworkHooks.openGui(serverPlayer, RavenContainer.ARSENAL_PROVIDER);
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return RavenTiles.RAVEN_ARSENAL_TILE.get().create();
    }
}
