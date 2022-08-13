package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.entity.GlowingBox;
import com.demyxa.catsoup.common.entity.SoupedCat;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypesInit {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			CatSoup.MOD_ID);

	public static final RegistryObject<EntityType<GlowingBox>> GLOWING_BOX = ENTITY_TYPES.register("glowing_box",
			() -> EntityType.Builder.of(GlowingBox::new, EntityClassification.AMBIENT).sized(1, 1)
					.build(new ResourceLocation(CatSoup.MOD_ID, "glowing_box").toString()));

	
	public static final RegistryObject<EntityType<SoupedCat>> SOUPED_CAT = ENTITY_TYPES.register("souped_cat",
			() -> EntityType.Builder.of(SoupedCat::new, EntityClassification.CREATURE).sized(0.5f, 0.5f)
					.build(new ResourceLocation(CatSoup.MOD_ID, "souped_cat").toString()));
}

