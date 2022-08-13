package com.demyxa.catsoup.client.render;

import com.demyxa.catsoup.client.model.SoupedCatModel;
import com.demyxa.catsoup.common.entity.SoupedCat;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LeatherHorseArmorLayer;
import net.minecraft.client.renderer.entity.model.FoxModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class CatWeaponLayerRender extends LayerRenderer<SoupedCat, SoupedCatModel<SoupedCat>> {


    public CatWeaponLayerRender(IEntityRenderer<SoupedCat, SoupedCatModel<SoupedCat>> p_i50938_1_) {
        super(p_i50938_1_);
    }

    @Override
    public void render(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, SoupedCat pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        pMatrixStack.pushPose();

        pMatrixStack.scale(0.80F, 0.80F, 0.80F);

        pMatrixStack.translate((double)((this.getParentModel()).snout.x / 16.0F), (double)((this.getParentModel()).snout.y / 16.0F), (double)((this.getParentModel()).snout.z / 16.0F));
        pMatrixStack.translate((double)0.20F, (double)1.23F, -0.85D);
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));

        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(pNetHeadYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(pHeadPitch));
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(-35.0F));




        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlotType.MAINHAND);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(pLivingEntity, itemstack, ItemCameraTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight);

        pMatrixStack.popPose();
    }
}