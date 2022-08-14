package com.demyxa.catsoup.common.goal;

import com.demyxa.catsoup.common.TE.CatBedTileEntity;
import com.demyxa.catsoup.common.block.CatBedBlock;
import com.demyxa.catsoup.common.entity.SoupedCat;
import com.demyxa.catsoup.core.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.util.math.BlockPos;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;




public class AttemptRestGoal extends MoveToBlockGoal {

    private final SoupedCat entity;

    public AttemptRestGoal(SoupedCat pEntity, double pDouble) {
        super(pEntity, pDouble, 8, 2);
        this.entity = pEntity;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.entity.getHealth() < (this.entity.getMaxHealth() / 1.5) && this.findNearestBlock();
    }

    @Override
    public boolean canContinueToUse() {
        return this.entity.getHealth() < (this.entity.getMaxHealth() / 1.5) && this.isValidTarget(this.entity.level, this.blockPos);
    }

    @Override
    public void start() {
        super.start();
    }

    public void tick() {
        super.tick();
        if (super.isReachedTarget() && !this.entity.isResting) {
            this.entity.startResting();
            CatBedTileEntity catbedTE = (CatBedTileEntity) this.entity.level.getBlockEntity(entity.getNavigation().getTargetPos().below());
            catbedTE.wasUsed = true;
        }

    }
    @Override
    public double acceptedDistance() {
        return 0.5D;
    }

    @Override
    protected void moveMobToBlock() {
        this.mob.getNavigation().moveTo((double)((float)this.blockPos.getX() + 0.5D), (double)(this.blockPos.getY()), (double)((float)this.blockPos.getZ() +0.5D) , this.speedModifier);
    }



    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected boolean isValidTarget(IWorldReader pLevel, BlockPos pPos) {

        if (pLevel.getBlockState(pPos.above()).is(BlockInit.CAT_BED_BLOCK.get()) && pLevel.isEmptyBlock(pPos.above().above())) {
            return true;
        }
        else {
            return false;
        }
    }


}
