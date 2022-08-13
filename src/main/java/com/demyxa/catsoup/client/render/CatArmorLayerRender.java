package com.demyxa.catsoup.client.render;

import com.demyxa.catsoup.client.model.SoupedCatModel;
import com.demyxa.catsoup.common.armor.CatArmorItem;
import com.demyxa.catsoup.common.entity.SoupedCat;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.HorseRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.item.ItemStack;


public class CatArmorLayerRender extends LayerRenderer<SoupedCat, SoupedCatModel<SoupedCat>> {
	private final SoupedCatModel<SoupedCat> model = new SoupedCatModel<SoupedCat>();


	public CatArmorLayerRender(IEntityRenderer<SoupedCat, SoupedCatModel<SoupedCat>> p_i50926_1_) {
		super(p_i50926_1_);

	}
	//Note to self: LayerRenderer apparently doesn't need to be registered. 
	//Would have been awesome to know 3 days ago.
	//Keep in mind for when I inevitably copy and paste this code.
	@Override
	public void render(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, SoupedCat pLivingEntity,
			float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw,
			float pHeadPitch) {

		ItemStack itemstack = pLivingEntity.getArmor();
		if (itemstack.getItem() instanceof CatArmorItem) {

			CatArmorItem catArmor = (CatArmorItem) itemstack.getItem();
			this.getParentModel().copyPropertiesTo(this.model);

			this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
			this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

			IVertexBuilder ivertexbuilder = pBuffer.getBuffer(RenderType.entityCutoutNoCull(catArmor.getTexture()));
			this.model.renderToBuffer(pMatrixStack, ivertexbuilder, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F,
					1.0F, 1.0F);
			
			
			

		}

	}

}
