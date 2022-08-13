package com.demyxa.catsoup.common.armor;

import com.demyxa.catsoup.CatSoup;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CatArmorItem extends Item {

	private final int protection;
	private final ResourceLocation texture;


	
	public CatArmorItem(int pProtection, Item.Properties pProperties) {
		this(pProtection, new ResourceLocation(CatSoup.MOD_ID , "textures/entity/armor/felinum_cat_armor.png"),
				pProperties);
	}

	
	public CatArmorItem(int pProtection, ResourceLocation texture, Item.Properties pProperties) {
		super(pProperties);
		this.protection = pProtection;
		this.texture = texture;
	}
	
	
	   public ResourceLocation getTexture() {
	      return texture;
	   }

	   public int getProtection() {
	      return this.protection;
	   }

}
