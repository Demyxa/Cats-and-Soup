 package com.demyxa.catsoup.common.material;

import java.util.function.Supplier;

import com.demyxa.catsoup.core.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum FelinumMaterial implements IItemTier {
	
	FELIUM_TOOL(4, 2500 , 15f, 4f, 17, () -> Ingredient.of(ItemInit.FELINUM_INGOT.get()));
	
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantibility;
	private final Ingredient repairMaterial ;
	
	FelinumMaterial(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantibility, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantibility = enchantibility;
		this.repairMaterial = repairMaterial.get();
	}
	
	
	@Override
	public int getUses() {
		
		return this.maxUses;
	}
	@Override
	public float getSpeed() {
		
		return this.efficiency;
	}
	@Override
	public float getAttackDamageBonus() {
		
		return this.attackDamage;
	}
	@Override
	public int getLevel() {
		
		return this.harvestLevel;
	}
	@Override
	public int getEnchantmentValue() {
		
		return this.enchantibility;
	}
	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
		return this.repairMaterial;
	}
	

}
