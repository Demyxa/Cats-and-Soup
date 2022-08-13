package com.demyxa.catsoup.common.events;

import java.util.stream.Stream;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.entity.GlowingBox;
import com.demyxa.catsoup.common.entity.SoupedCat;
import com.demyxa.catsoup.core.init.EntityTypesInit;
import com.demyxa.catsoup.core.init.ItemInit;
import com.demyxa.catsoup.core.init.PotionEffectInit;

import net.minecraft.block.AbstractBlock.AbstractBlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = CatSoup.MOD_ID, bus = Bus.FORGE)

public class PlayerEvents {

	@SubscribeEvent
	 public static void onInteract(EntityInteractSpecific event) {
		if (!event.getSide().isClient() && event.getTarget().getType().equals(EntityType.CAT)) {

			CatEntity oldCat = (CatEntity) event.getTarget();

			if (event.getPlayer().getMainHandItem().getItem().equals(ItemInit.TRANSFORM_WAND.get())) {

				if (oldCat.isTame()) {

					SoupedCat.ascendCat(oldCat);
					
				}
			}
		}
	}

	

	@SubscribeEvent
	public static void onFelinumSwordAbility(RightClickItem event) {

		PlayerEntity player = event.getPlayer();

		if (event.getItemStack().getItem().equals(ItemInit.FELINUM_SWORD.get())) {
			if (player.isOnGround()) {
				if (!player.getActiveEffectsMap().containsKey(PotionEffectInit.FELINE_FEROCITY.get())) {

					double yaw = ((player.getRotationVector().y + 90) * Math.PI) / 180;
					double pitch = ((player.getRotationVector().x + 90) * Math.PI) / 180;

					double x = Math.sin(pitch) * Math.cos(yaw);
					double y = Math.sin(pitch) * Math.sin(yaw);

					player.setDeltaMovement(new Vector3d(x, 0.5, y).multiply(1.5, 1, 1.5));
					player.addEffect(new EffectInstance(PotionEffectInit.FELINE_FEROCITY.get(), 60, 0, true, true));
					player.playSound(SoundEvents.CAT_PURREOW, 10, (float) 1.5);
					player.playSound(SoundEvents.ARMOR_EQUIP_LEATHER, 10, (float) 1.5);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {

		PlayerEntity player = event.getPlayer();
		if (player.getMainHandItem().getItem().equals(ItemInit.FELINUM_PICKAXE.get())) {

			IWorld world = event.getWorld();
			BlockPos pos = event.getPos();
			AbstractBlockState blockBroken = event.getState();

			if (Blocks.ORES.contains(blockBroken.getBlock())) {

				Stream<BlockPos> blockPosList = BlockPos.betweenClosedStream(new AxisAlignedBB(pos).inflate(10));

				blockPosList.forEach(blockPos -> {

					if (world.getBlockState(blockPos).getBlock().equals(blockBroken.getBlock())) {
						if (!blockPos.equals(pos)) {

							GlowingBox boxEntity = new GlowingBox(EntityTypesInit.GLOWING_BOX.get(), (World) world);
							world.addFreshEntity(boxEntity);
							boxEntity.setGlowing(true);
							boxEntity.setInvisible(true);
							boxEntity.teleportTo(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5);

						}

					}
				});
			}
		}
	}

}
