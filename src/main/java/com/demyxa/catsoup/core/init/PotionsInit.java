package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionsInit {

	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES,
			CatSoup.MOD_ID);

	public static final RegistryObject<Potion> FELINE_VIGOR_POTION = POTIONS.register("feline_vigor_potion",
			() -> new Potion(new EffectInstance(PotionEffectInit.FELINE_VIGOR.get(), 6000, 0, true, true, true)));
	
	
	
	

}
