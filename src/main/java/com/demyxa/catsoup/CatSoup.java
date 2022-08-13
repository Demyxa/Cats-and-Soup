package com.demyxa.catsoup;

import com.demyxa.catsoup.core.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demyxa.catsoup.client.render.GlowingBoxRenderer;
import com.demyxa.catsoup.client.render.SoupedCatRenderer;
import com.demyxa.catsoup.client.screen.SoupedCatInvScreen;
import com.demyxa.catsoup.common.events.PlayerEvents;
import com.demyxa.catsoup.core.itemgroup.CatSoupItemGroup;
import com.demyxa.catsoup.core.util.BetterBrewingRecipe;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CatSoup.MOD_ID)
@Mod.EventBusSubscriber(modid = CatSoup.MOD_ID, bus = Bus.MOD)
public class CatSoup {
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "catsoup";

	public CatSoup() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		EntityTypesInit.ENTITY_TYPES.register(bus);
		BlockInit.BLOCKS.register(bus);

		ItemInit.ITEMS.register(bus);
		PotionEffectInit.REGISTER.register(bus);
		PotionsInit.POTIONS.register(bus);
		ContainerTypesInit.CONTAINER_TYPES.register(bus);
		ArmorInit.ARMOR.register(bus);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doSetupStuff);

		MinecraftForge.EVENT_BUS.register(PlayerEvents.class);
		MinecraftForge.EVENT_BUS.register(this);
	}
	

	private void doClientStuff(final FMLClientSetupEvent event) {

		RenderTypeLookup.setRenderLayer(BlockInit.CATNIP_FLOWER.get(), RenderType.cutout());
		ScreenManager.register(ContainerTypesInit.SOUPED_CAT_INV.get(), SoupedCatInvScreen::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.GLOWING_BOX.get(), GlowingBoxRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypesInit.SOUPED_CAT.get(), SoupedCatRenderer::new);
	}
	
	

	private void doSetupStuff(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {

			BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ItemInit.CATNIP.get(),
					PotionsInit.FELINE_VIGOR_POTION.get()));

		});
	}

	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			event.getRegistry().register(new BlockItem(block, new Item.Properties().tab(CatSoupItemGroup.CATSOUP))
					.setRegistryName(block.getRegistryName()));

		});

	}

}
