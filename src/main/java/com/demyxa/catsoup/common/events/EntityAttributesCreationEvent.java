package com.demyxa.catsoup.common.events;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.entity.SoupedCat;
import com.demyxa.catsoup.core.init.EntityTypesInit;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CatSoup.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityAttributesCreationEvent {
	
	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(EntityTypesInit.SOUPED_CAT.get(), SoupedCat.setCustomAttributes().build());
	}

}
