package com.demyxa.catsoup.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class GlowingBox extends Entity {

	public GlowingBox(EntityType<? extends Entity> pType, World pLevel) {
		super(pType, pLevel);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		if (this.tickCount > 60) 
			this.kill();
	}

	@Override
	protected void defineSynchedData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT pCompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT pCompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
			
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
