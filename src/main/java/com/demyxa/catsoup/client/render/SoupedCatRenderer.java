package com.demyxa.catsoup.client.render;

import com.demyxa.catsoup.CatSoup;
import com.demyxa.catsoup.client.model.SoupedCatModel;
import com.demyxa.catsoup.common.entity.SoupedCat;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SoupedCatRenderer extends MobRenderer<SoupedCat, SoupedCatModel<SoupedCat>> {
	
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(CatSoup.MOD_ID, "textures/entity/cole.png");

	public SoupedCatRenderer(EntityRendererManager p_i50973_1_) {
		super(p_i50973_1_, new SoupedCatModel<>(), 0.3F);
		this.addLayer(new CatArmorLayerRender(this));
		this.addLayer(new CatWeaponLayerRender(this));
	}
	
	

	
	
	@Override
	public ResourceLocation getTextureLocation(SoupedCat pEntity) {
		// TODO Auto-generated method stub
		return TEXTURE;
	}


}
