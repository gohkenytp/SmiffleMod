package smifflepuss.smifflemod.client.model;

import net.minecraft.resources.ResourceLocation;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.Shroomling;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class ShroomlingModel <T extends Shroomling> extends DefaultedEntityGeoModel<T> {
    private static final ResourceLocation[] TEXTURES = new ResourceLocation[]{
            new ResourceLocation(SmiffleMod.MODID, "textures/entity/shroomling/shroomling_red.png"),
            new ResourceLocation(SmiffleMod.MODID, "textures/entity/shroomling/shroomling_brown.png")};
    public ShroomlingModel() {
        super(SmiffleMod.modPrefix("shroomling"));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SmiffleMod.modPrefix("geo/shroomling.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return TEXTURES[object.getVariant()];
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SmiffleMod.modPrefix("animations/shroomling.animation.json");
    }
}