package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.container.SoupedCatInv;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypesInit {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS,
			CatSoup.MOD_ID);

	public static final RegistryObject<ContainerType<SoupedCatInv>> SOUPED_CAT_INV = CONTAINER_TYPES.register("souped_cat_inv",
			() -> IForgeContainerType.create(SoupedCatInv::new));
	
	
}

