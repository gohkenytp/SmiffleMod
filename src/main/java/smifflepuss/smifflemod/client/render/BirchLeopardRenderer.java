package smifflepuss.smifflemod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import smifflepuss.smifflemod.client.model.BirchLeopardModel;
import smifflepuss.smifflemod.entity.BirchLeopard;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BirchLeopardRenderer<E extends BirchLeopard> extends GeoEntityRenderer<E> {
    public BirchLeopardRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BirchLeopardModel());
    }

    @Override
    public void preRender(PoseStack poseStack, E animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
