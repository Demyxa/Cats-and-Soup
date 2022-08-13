package com.demyxa.catsoup.common.block;

import com.demyxa.catsoup.core.init.ItemInit;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class CatBedBlock extends Block {

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(4, 0, 2, 13, 3, 3),
            Block.box(4, 0, 13, 13, 3, 14),
            Block.box(2, 0, 2, 5, 3, 3),
            Block.box(11, 0, 2, 14, 3, 3),
            Block.box(2, 0, 12, 14, 3, 13),
            Block.box(5, 0, 2, 11, 2, 3),
            Block.box(3, 0.75, 3, 13, 1.75, 12)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(4, 0, 2, 13, 3, 3),
            Block.box(4, 0, 13, 13, 3, 14),
            Block.box(13, 0, 2, 14, 3, 5),
            Block.box(13, 0, 11, 14, 3, 14),
            Block.box(3, 0, 2, 4, 3, 14),
            Block.box(13, 0, 5, 14, 2, 11),
            Block.box(4, 0.75, 3, 13, 1.75, 13)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();


    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(13, 0, 4, 14, 3, 13),
            Block.box(2, 0, 4, 3, 3, 13),
            Block.box(11, 0, 13, 14, 3, 14),
            Block.box(2, 0, 13, 5, 3, 14),
            Block.box(2, 0, 3, 14, 3, 4),
            Block.box(5, 0, 13, 11, 2, 14),
            Block.box(3, 0.75, 4, 13, 1.75, 13)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();


    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(3, 0, 13, 12, 3, 14),
            Block.box(3, 0, 2, 12, 3, 3),
            Block.box(2, 0, 11, 3, 3, 14),
            Block.box(2, 0, 2, 3, 3, 5),
            Block.box(12, 0, 2, 13, 3, 14),
            Block.box(2, 0, 5, 3, 2, 11),
            Block.box(3, 0.75, 3, 12, 1.75, 13)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();


    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    //public static final BooleanProperty USED = BlockStateProperties.CONDITIONAL;


    public CatBedBlock() {
        super(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.COLOR_RED).strength(0.2f).sound(SoundType.WOOL).harvestLevel(0));

        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
        //this.registerDefaultState(this.defaultBlockState().setValue(USED, false));


    }
    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        switch((Direction)pState.getValue(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }


    @Override
    public BlockState mirror (BlockState state, Mirror pMirror) {
        return state.rotate(pMirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
        //pBuilder.add(USED);
    }

   /* @Override
    *public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
    *    if (pState.getValue(USED)) {
    *        if (pPlayer.getItemInHand(pHand).equals(ItemInit.HAIR_BRUSH.get())) {
    *            pPlayer.inventory.add(new ItemStack(ItemInit.CAT_FUR.get(), 1));
    *            pState.setValue(USED, false);
    *            return ActionResultType.SUCCESS;
    *        }
    *    } else {
    *        return ActionResultType.FAIL;
    *    }
    *    return ActionResultType.PASS;
    }*/
}
