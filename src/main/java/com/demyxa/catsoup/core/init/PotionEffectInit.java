package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.core.util.Util;

import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;

@EventBusSubscriber(modid = CatSoup.MOD_ID, bus = Bus.FORGE)

public class PotionEffectInit extends Effect {

	protected PotionEffectInit(EffectType type, int color) {
		super(type, color);
		// TODO Auto-generated constructor stub
	}

	public static final DeferredRegister<Effect> REGISTER = DeferredRegister.create(Effect.class, CatSoup.MOD_ID);

	public static final RegistryObject<Effect> FELINE_VIGOR = REGISTER.register("feline_vigor",
			() -> new PotionEffectInit(EffectType.BENEFICIAL, 16313402).addAttributeModifier(Attributes.MOVEMENT_SPEED,
					Util.generateNewUUID().toString(), 0.5, Operation.MULTIPLY_TOTAL).addAttributeModifier(
							Attributes.ATTACK_SPEED, Util.generateNewUUID().toString(), 0.5, Operation.MULTIPLY_BASE));

	public static final RegistryObject<Effect> FELINE_FEROCITY = REGISTER.register("feline_ferocity",
			() -> new PotionEffectInit(EffectType.BENEFICIAL, 16313402).addAttributeModifier(Attributes.ATTACK_DAMAGE,
					Util.generateNewUUID().toString(), 2, Operation.MULTIPLY_TOTAL));

}
