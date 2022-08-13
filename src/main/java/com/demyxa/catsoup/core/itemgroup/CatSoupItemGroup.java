package com.demyxa.catsoup.core.itemgroup;

import com.demyxa.catsoup.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CatSoupItemGroup extends ItemGroup {
	
	public static final CatSoupItemGroup CATSOUP = new CatSoupItemGroup(ItemGroup.getGroupCountSafe(), "Cats and Soup");

	public CatSoupItemGroup(int index, String label) {
		super(index, label);
	
	}

	@Override
	public ItemStack makeIcon() {
		
		return new ItemStack(ItemInit.FELINUM_INGOT.get());
	}

	

}
