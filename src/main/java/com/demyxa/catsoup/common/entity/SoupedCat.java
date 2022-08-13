package com.demyxa.catsoup.common.entity;

import com.demyxa.catsoup.common.armor.CatArmorItem;
import com.demyxa.catsoup.common.container.SoupedCatInvProvider;
import com.demyxa.catsoup.common.goal.AttemptRestGoal;
import com.demyxa.catsoup.core.init.EntityTypesInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IEquipable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import org.spongepowered.asm.mixin.injection.At;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SoupedCat extends TameableEntity implements IInventoryChangedListener, IEquipable {

	protected Inventory inventory;
	private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("046E1665-8B10-40C8-8F9D-CF9B1667F295");
	private static final DataParameter<Byte> DATA_ID_FLAGS = EntityDataManager.defineId(SoupedCat.class, DataSerializers.BYTE);
	private static final DataParameter<Optional<UUID>> DATA_ID_OWNER_UUID = EntityDataManager.defineId(SoupedCat.class, DataSerializers.OPTIONAL_UUID);

	private int restedTicks = 0;
	public boolean isResting = false;
	public SoupedCat(EntityType<? extends TameableEntity> pType, World pLevel) {
		super(pType, pLevel);
		this.createInventory();

	}

	@Override
	public void tick() {
		super.tick();
		if (this.isResting) {
			this.restedTicks++;
			if (Math.floorMod(restedTicks, 20) == 0) {
				this.level.addParticle(ParticleTypes.HEART, this.getX(), this.getY(), this.getZ(), 1.0, 2.0, 1.0);

			}
			if (this.restedTicks >= this.getTicksToRest() || this.getHealth() == this.getMaxHealth()) {
				this.stopResting();
			}
		}
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return TameableEntity.createLivingAttributes().add(net.minecraft.entity.ai.attributes.Attributes.MAX_HEALTH, 40)
				.add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.FOLLOW_RANGE, 8)
				.add(Attributes.ARMOR, 1.0F).add(Attributes.ATTACK_KNOCKBACK, 0.3).add(Attributes.ATTACK_SPEED, 3.0);

	}

	protected void registerGoals() {

		this.goalSelector.addGoal(1, new SwimGoal(this));

		this.goalSelector.addGoal(6, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(8, new AttemptRestGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new FollowOwnerGoal(this, 1.0D, 4.0F, 2.0F, false));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));


	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_ID_FLAGS, (byte) 0);
		this.entityData.define(DATA_ID_OWNER_UUID, Optional.empty());

	}

	@Override
	protected net.minecraft.util.SoundEvent getAmbientSound() {
		return net.minecraft.util.SoundEvents.CAT_AMBIENT;
	}

	@Override
	protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundEvents.CAT_HURT;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockState) {
		this.playSound(SoundEvents.WOLF_STEP, 0.2F, 0.5F);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld pServerLevel, AgeableEntity pMate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSaddleable() {
		// TODO Auto-generated method stub
		return false;
	}


	public static void ascendCat(CatEntity oldCat) {

		SoupedCat newCat = new SoupedCat(EntityTypesInit.SOUPED_CAT.get(), oldCat.level);

		//TODO Multiple Cat Variants, keep the one that is ascended
		UUID uuidOld = oldCat.getOwnerUUID();
		newCat.setTame(true);
		newCat.setOwnerUUID(uuidOld);
		UUID uuidNew = newCat.getOwnerUUID();
		newCat.setCustomName(oldCat.getCustomName());
		newCat.teleportTo(oldCat.getX(), oldCat.getY(), oldCat.getZ());
		oldCat.remove();
		oldCat.level.addFreshEntity(newCat);
	}

	@Nullable
	public UUID getOwnerUUID() {

		return this.entityData.get(DATA_ID_OWNER_UUID).orElse((UUID) null);
	}

	public void setOwnerUUID(@Nullable UUID pUuid) {
		this.entityData.set(DATA_ID_OWNER_UUID, Optional.ofNullable(pUuid));
	}


	@Override
	public void containerChanged(IInventory pInvBasic) {
		ItemStack itemstack = this.getArmor();

		if (this.tickCount > 20 && itemstack.getItem() instanceof CatArmorItem) {
			this.playSound(SoundEvents.HORSE_ARMOR, 0.25F, 2.0F);
		}

		this.updateContainerEquipment();

	}

	// I literally have no idea what this does, but it looks important so I will
	// keep it.
	private net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> capability,
			@Nullable net.minecraft.util.Direction facing) {
		if (this.isAlive() && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
				&& itemHandler != null)
			return itemHandler.cast();
		return super.getCapability(capability, facing);
	}

	protected int getInventorySize() {
		return 2;
	}

	public SoupedCat getEntity() {
		return this;
	}

	public ItemStack getArmor() {

		return this.getItemBySlot(EquipmentSlotType.CHEST);
	}


	public void addAdditionalSaveData(CompoundNBT pCompound) {
		super.addAdditionalSaveData(pCompound);
		if (!this.inventory.getItem(0).isEmpty()) {
			pCompound.put("ArmorItem", this.inventory.getItem(0).save(new CompoundNBT()));
		}
		if (!this.inventory.getItem(1).isEmpty()) {
			pCompound.put("WeaponItem", this.inventory.getItem(1).save(new CompoundNBT()));
		}

	}

	public void readAdditionalSaveData(CompoundNBT pCompound) {
		super.readAdditionalSaveData(pCompound);
		if (pCompound.contains("ArmorItem", 10)) {
			ItemStack itemstack = ItemStack.of(pCompound.getCompound("ArmorItem"));
			if (!itemstack.isEmpty() && itemstack.getItem() instanceof CatArmorItem) {
				this.inventory.setItem(0, itemstack);
			}
		}
		if (pCompound.contains("WeaponItem", 10)) {
			ItemStack itemstack = ItemStack.of(pCompound.getCompound("WeaponItem"));
			if (!itemstack.isEmpty()) {
				this.inventory.setItem(1, itemstack);
			}
		}
		this.updateContainerEquipment();
	}

	private float getTicksToRest() {
		return Math.round(((this.getMaxHealth() - this.getHealth()) * 20 )* 3);
	}

	public void startResting() {

			this.isResting = true;

	}
	public void stopResting() {

			this.getOwner().sendMessage(new StringTextComponent("<" + this.getDisplayName().getString() + ">" + " I am back in Busi-nyas!"), null);
			this.setHealth(this.getMaxHealth());
			this.restedTicks = 0;
			this.isResting = false;


		}



	protected void createInventory() {
		Inventory inventory = this.inventory;
		this.inventory = new Inventory(this.getInventorySize());
		if (inventory != null) {
			inventory.removeListener(this);
			int i = Math.min(inventory.getContainerSize(), this.inventory.getContainerSize());

			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = inventory.getItem(j);
				if (!itemstack.isEmpty()) {
					this.inventory.setItem(j, itemstack.copy());
				}

			}
		}
		this.inventory.addListener(this);
		this.updateContainerEquipment();
		this.itemHandler = net.minecraftforge.common.util.LazyOptional
				.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this.inventory));
	}

	private void setArmor(ItemStack pStack) {
		this.setItemSlot(EquipmentSlotType.CHEST, pStack);
		this.setDropChance(EquipmentSlotType.CHEST, 1.0F);
	}

	private void setWeapon(ItemStack pStack) {
		this.setItemSlot(EquipmentSlotType.MAINHAND, pStack);
		this.setDropChance(EquipmentSlotType.MAINHAND, 1.0F);

	}

	protected void updateContainerEquipment() {
		if (!this.level.isClientSide) {
			this.setArmorEquipment(this.inventory.getItem(0));
			this.setWeapon(this.inventory.getItem(1));

		}

	}


	private void setArmorEquipment(ItemStack pStack) {
		this.setArmor(pStack);
		if (!this.level.isClientSide) {
			this.getAttribute(Attributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
			if (pStack.getItem() instanceof CatArmorItem) {
				int i = ((CatArmorItem) pStack.getItem()).getProtection();
				if (i != 0) {
					this.getAttribute(Attributes.ARMOR).addTransientModifier(new AttributeModifier(ARMOR_MODIFIER_UUID,
							"Cat Armor bonus", (double) i, AttributeModifier.Operation.ADDITION));

				}
			}
		}

	}


	@Override
	public ActionResultType mobInteract(PlayerEntity pPlayer, Hand pHand) {

		if (pPlayer.getItemInHand(pHand).isEmpty()) { 														
			if (pPlayer instanceof ServerPlayerEntity) {

				NetworkHooks.openGui((ServerPlayerEntity) pPlayer, new SoupedCatInvProvider(this.inventory));
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.SUCCESS;
	}


	@Override
	public void equipSaddle(SoundCategory pSource) {

	}

	@Override
	public boolean isSaddled() {

		return false;
	}

}
