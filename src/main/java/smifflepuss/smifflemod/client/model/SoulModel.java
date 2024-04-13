package smifflepuss.smifflemod.client.model;

import net.minecraft.resources.ResourceLocation;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.Soul;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SoulModel<T extends Soul> extends DefaultedEntityGeoModel<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SmiffleMod.MODID, "textures/entity/soul/soul.png");

    public SoulModel() {
        super(SmiffleMod.modPrefix("soul"));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SmiffleMod.modPrefix("geo/soul.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SmiffleMod.modPrefix("animations/soul.animation.json");
    }
}