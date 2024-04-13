package smifflepuss.smifflemod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import smifflepuss.smifflemod.client.model.SoulModel;
import smifflepuss.smifflemod.entity.Soul;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SoulRenderer<E extends Soul> extends GeoEntityRenderer<E> {
    public SoulRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SoulModel());
    }

    @Override
    public void preRender(PoseStack poseStack, E animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
