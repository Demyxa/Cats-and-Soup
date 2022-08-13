package com.demyxa.catsoup.core.init;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.armor.CatArmorItem;
import com.demyxa.catsoup.core.itemgroup.CatSoupItemGroup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ArmorInit {

	public static final DeferredRegister<Item> ARMOR = DeferredRegister.create(ForgeRegistries.ITEMS, CatSoup.MOD_ID);

	//This is a terrible way of doing this, as it doesn't support multiple types of armor (Leather, Felinum, etc.)
	public static final RegistryObject<CatArmorItem> FELINUM_CAT_ARMOR = ARMOR.register("felinum_cat_armor",
			() -> new CatArmorItem(15, new Item.Properties().tab(CatSoupItemGroup.CATSOUP).stacksTo(1)));

}
