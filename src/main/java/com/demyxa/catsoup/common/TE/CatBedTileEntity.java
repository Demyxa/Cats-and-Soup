package com.demyxa.catsoup.common.TE;

import com.demyxa.catsoup.core.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.sql.Connection;

public class CatBedTileEntity extends TileEntity {

    public boolean wasUsed;

    public CatBedTileEntity(TileEntityType<?> pType) {
        super(pType);
    }

    public CatBedTileEntity() {
        this(TileEntityInit.CAT_BED_TILE_ENTITY.get());
    }

    public void setChanged(){
        super.setChanged();

        BlockState state = this.level.getBlockState(this.worldPosition);
        this.level.sendBlockUpdated(this.worldPosition, state, state, 3);
        this.level.updateNeighborsAt(this.worldPosition, state.getBlock());
    }

    @Override
    public CompoundNBT save(CompoundNBT pCompound) {
        super.save(pCompound);
        pCompound.putBoolean("UsedState", this.wasUsed);

        return pCompound;
    }

    @Override
    public void load(BlockState pState, CompoundNBT pCompound) {
        super.load(pState, pCompound);
        this.wasUsed = pCompound.getBoolean("UsedState");


    }
    //Don't touch the 3, I don't know what it is or why it's here, just
    //don't fucking touch it.
    public SUpdateTileEntityPacket getUpdatePacket(){
        return new SUpdateTileEntityPacket(this.getBlockPos(), 3, getUpdateTag());
    }

    public CompoundNBT getUpdateTag(){
        CompoundNBT pCompound = new CompoundNBT();
        save(pCompound);
        return pCompound;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag){
        load(state, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){
        load(getBlockState(), pkt.getTag());
    }



}
