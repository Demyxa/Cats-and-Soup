package com.demyxa.catsoup.client.model;

import com.demyxa.catsoup.common.entity.SoupedCat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports

public class SoupedCatModel<T extends SoupedCat> extends QuadrupedModel<T> {

	public final ModelRenderer snout;
	private final ModelRenderer neck;
	private final ModelRenderer ears;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer tail;
	private final ModelRenderer tailstart;
	private final ModelRenderer tailmid;
	private final ModelRenderer tailend;
	private final ModelRenderer legs;
	private final ModelRenderer body_r1;

	public SoupedCatModel() {

		super(0, 0.0F, false, 0.0F, 0.0F, 2.0F, 2.0F, 0);
		texWidth = 32;
		texHeight = 32;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 20.0F, -5.0F);
		setRotationAngle(head, 1.5708F, 0, 0);
		head.texOffs(0, 13).addBox(-1.5F, -3.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		

		snout = new ModelRenderer(this);
		snout.setPos(0.0F, 4.0F, 5.0F);
		
		head.addChild(snout);
		snout.texOffs(11, 18).addBox(-1.0F, -5.25F, -8.25F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		snout.texOffs(18, 13).addBox(-0.5F, -5.5F, -8.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		neck = new ModelRenderer(this);
		neck.setPos(0.0F, 4.0F, 5.0F);
		
		head.addChild(neck);
		neck.texOffs(16, 8).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		ears = new ModelRenderer(this);
		ears.setPos(0.0F, 0.0F, 0.0F);
		
		head.addChild(ears);

		left = new ModelRenderer(this);
		left.setPos(0.0F, 4.0F, 5.0F);
		ears.addChild(left);
		left.texOffs(9, 13).addBox(0.25F, -7.5F, -6.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setPos(0.0F, 4.0F, 5.0F);
		ears.addChild(right);
		right.texOffs(6, 5).addBox(-1.25F, -7.5F, -6.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		
		body_r1 = new ModelRenderer(this);
		body_r1.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(body_r1);
		setRotationAngle(body_r1, -1.5708F, 0.0F, 0.0F);
		body_r1.texOffs(0, 0).addBox(-1.5F, -5.0F, -5.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);
		
		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 24.0F, 0.0F);
		

		tailstart = new ModelRenderer(this);
		tailstart.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(tailstart);
		tailstart.texOffs(6, 0).addBox(-0.5F, -5.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		tailmid = new ModelRenderer(this);
		tailmid.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(tailmid);
		tailmid.texOffs(17, 18).addBox(-0.5F, -4.0F, 5.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		tailend = new ModelRenderer(this);
		tailend.setPos(0.0F, 0.0F, 0.0F);
		tail.addChild(tailend);
		tailend.texOffs(16, 5).addBox(-0.5F, -2.0F, 6.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		legs = new ModelRenderer(this);
		legs.setPos(0.0F, 24.0F, 0.0F);
		

		leg0 = new ModelRenderer(this);
		leg0.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg0);
		leg0.texOffs(16, 0).addBox(0.05F, -3.0F, -4.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg2);
		leg2.texOffs(12, 13).addBox(0.05F, -3.0F, 2.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg1);
		leg1.texOffs(0, 5).addBox(-2.05F, -3.0F, -4.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg3);
		leg3.texOffs(0, 0).addBox(-2.05F, -3.0F, 2.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legs.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
		
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return Iterables.concat(super.bodyParts(), ImmutableList.of(body));
	}

	@Override
	protected Iterable<ModelRenderer> headParts() {
		return Iterables.concat(super.bodyParts(), ImmutableList.of(head));

	}

}