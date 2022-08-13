package com.demyxa.catsoup.client.screen;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.common.container.SoupedCatInv;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoupedCatInvScreen extends ContainerScreen<SoupedCatInv> {

	private static final ResourceLocation SOUPED_CAT_GUI = new ResourceLocation(CatSoup.MOD_ID,
			"textures/gui/souped_cat.png");
	

	public SoupedCatInvScreen(SoupedCatInv container, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
		super(container, pPlayerInventory, pTitle);
		this.topPos = 0;
		this.leftPos = 0;
		this.height = 201;
		this.width = 175;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      this.minecraft.getTextureManager().bind(SOUPED_CAT_GUI);
	      int i = (this.width - this.imageWidth) / 2;
	      int j = (this.height - this.imageHeight) / 2;
	      this.blit(pMatrixStack, i, j,0, 0, this.getXSize(), this.getYSize());
	}

	@Override
	public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
		this.renderBackground(pMatrixStack);
		super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
		
		this.renderTooltip(pMatrixStack, pMouseX, pMouseY);

	}

}
