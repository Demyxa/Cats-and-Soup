package com.demyxa.catsoup.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SoupedCatInvProvider implements INamedContainerProvider {

    private final IInventory inventory;
   
    

    public SoupedCatInvProvider(final IInventory inv) {
      super();
      this.inventory = inv;
     
      
    }

	@Override
	public Container createMenu(int id, PlayerInventory playerInv, PlayerEntity player) {
		 if (player.isSpectator()) {
		        return null;
		      } else {
		        return new SoupedCatInv(id, playerInv, inventory);
		      }
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent("Cat");
	}
	
	
}