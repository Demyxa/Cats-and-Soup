package com.demyxa.catsoup.common.container;

import com.demyxa.catsoup.common.armor.CatArmorItem;
import com.demyxa.catsoup.core.init.ContainerTypesInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class SoupedCatInv extends Container {

	public final IInventory catInventory;

	public SoupedCatInv(final int windowId, final PlayerInventory pInv, final IInventory inv) {
		super(ContainerTypesInit.SOUPED_CAT_INV.get(), windowId);
		this.catInventory = inv;

		inv.startOpen(pInv.player);

		// Cat Armor Slot
		this.addSlot(new Slot(catInventory, 0, 8, 18) {

			public boolean mayPlace(ItemStack pStack) {
				return pStack.getItem() instanceof CatArmorItem;
			}
		});

		// Cat Weapon Slot
		this.addSlot(new Slot(catInventory, 1, 8, 36));

		// Player Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlot(new Slot(pInv, j + i * 9 + 9, 8 + j * 18, 102 + i * 18 + -18));
			}
		}

		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlot(new Slot(pInv, i, 8 + i * 18, 142));
		}

	}

	public SoupedCatInv(final int windowId, final PlayerInventory pInv, final PacketBuffer data) {

		this(windowId, pInv, new Inventory(2));
	}

	@Override
	public boolean stillValid(PlayerEntity pPlayer) {

		return this.catInventory.stillValid(pPlayer);
	}

	@Override
	public ItemStack quickMoveStack(final PlayerEntity player, final int slotIndex) {

		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);

		if (slot != null && slot.hasItem()) {
			ItemStack slotStack = slot.getItem();
			stack = slotStack.copy();
			if (slotIndex < 2 && !this.moveItemStackTo(slotStack, 2, 37, true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(slotStack, 0, 2, false)) {
				return ItemStack.EMPTY;
			}
			if (slotStack.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
			slot.onTake(player, slotStack);
		}

		return stack;
	}

}
