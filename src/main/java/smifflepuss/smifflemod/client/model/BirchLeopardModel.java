package smifflepuss.smifflemod.client.model;

import net.minecraft.resources.ResourceLocation;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.BirchLeopard;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BirchLeopardModel<T extends BirchLeopard> extends DefaultedEntityGeoModel<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SmiffleMod.MODID, "textures/entity/birchleopard/birchleopard.png");

    public BirchLeopardModel() {
        super(SmiffleMod.modPrefix("birchleopard"));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SmiffleMod.modPrefix("geo/birchleopard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SmiffleMod.modPrefix("animations/birchleopard.animation.json");
    }
}