package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.material.FelinumMaterial;
import com.demyxa.catsoup.core.itemgroup.CatSoupItemGroup;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CatSoup.MOD_ID);

	public static final RegistryObject<Item> HANDHELDCAT = ITEMS.register("handheldcat",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_INGOT = ITEMS.register("felinum_ingot",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_PICKAXE = ITEMS.register("felinum_pickaxe",
			() -> new PickaxeItem(FelinumMaterial.FELIUM_TOOL, 4, 1f,
					new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_AXE = ITEMS.register("felinum_axe",
			() -> new AxeItem(FelinumMaterial.FELIUM_TOOL, 8, 1f, new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_SHOVEL = ITEMS.register("felinum_shovel",
			() -> new ShovelItem(FelinumMaterial.FELIUM_TOOL, 4, 1f,
					new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_HOE = ITEMS.register("felinum_hoe",
			() -> new HoeItem(FelinumMaterial.FELIUM_TOOL, 3, 1f, new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> FELINUM_SWORD = ITEMS.register("felinum_sword",
			() -> new SwordItem(FelinumMaterial.FELIUM_TOOL, 6, 1f,
					new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> CATNIP = ITEMS.register("catnip",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> TRANSFORM_WAND = ITEMS.register("transform_wand",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> HAIR_BRUSH = ITEMS.register("hair_brush",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> CAT_FUR = ITEMS.register("cat_fur",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

	public static final RegistryObject<Item> CAT_FUR_LINING = ITEMS.register("cat_fur_lining",
			() -> new Item(new Item.Properties().tab(CatSoupItemGroup.CATSOUP)));

}
