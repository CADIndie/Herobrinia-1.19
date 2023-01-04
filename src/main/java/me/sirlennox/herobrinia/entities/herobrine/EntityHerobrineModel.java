/*
 * Copyright (c) 2023. This software is owned, managed, maintained or developed by Digital Genesis Studios and has been marked as using the license below. Please contact opensource@digital-genesis.org for more information or any inquiries you have.
 */

package me.sirlennox.herobrinia.entities.herobrine;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class EntityHerobrineModel extends BipedEntityModel<EntityHerobrine> {
	private final ModelPart steve;
	public EntityHerobrineModel(ModelPart root) {
		super(root, RenderLayer::getEntityTranslucent);
		this.steve = root.getChild("steve");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData steve = modelPartData.addChild("steve", ModelPartBuilder.create().uv(0, 0).cuboid(4.0F, -23.5F, -12.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.0F, -15.5F, -10.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -15.5F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(12.0F, -15.5F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.0F, -3.5F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(8.0F, -3.5F, -10.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.5F, -24.0F, -12.5F, 9.0F, 9.0F, 9.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(4.0F, -16.03F, -10.5F, 8.0F, 13.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.49F, -16.0F, -10.5F, 4.0F, 13.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(11.5F, -16.0F, -10.5F, 5.0F, 13.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(3.5F, -4.0F, -10.5F, 5.0F, 13.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(7.5F, -4.0F, -10.5F, 5.0F, 13.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.0F, 16.0F, 8.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}

	@Override
	public void setAngles(EntityHerobrine livingEntity, float f, float g, float h, float i, float j) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		steve.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}