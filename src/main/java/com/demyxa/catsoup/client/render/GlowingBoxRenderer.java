package com.demyxa.catsoup.client.render;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.client.model.GlowingBoxModel;
import com.demyxa.catsoup.common.entity.GlowingBox;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class GlowingBoxRenderer<T extends GlowingBox> extends EntityRenderer<T>{
	protected final GlowingBoxModel<GlowingBox> model = new GlowingBoxModel<>();
	
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(CatSoup.MOD_ID, "textures/entity/glowingbox.png");
	

	
	public GlowingBoxRenderer(EntityRendererManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
		
		
	}
	
	
	
	
	@Override
	public void render(T pEntity, float pEntityYaw, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight) {
		
		//I don't know why I'm keeping this. It works without it, but better safe than sorry!
		//this.model.renderToBuffer(pMatrixStack, pBuffer.getBuffer(this.model.renderType(TEXTURE)), pPackedLight , OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		
		this.model.render(pMatrixStack, pBuffer.getBuffer(this.model.renderType(TEXTURE)), pPackedLight , OverlayTexture.NO_OVERLAY, 0, 0, 0, 0);
		
	      super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);   
	      
	}
	
	public ResourceLocation getTextureLocation(GlowingBox pEntity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}
	
}

