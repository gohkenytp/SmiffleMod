package smifflepuss.smifflemod.client.model;

import net.minecraft.resources.ResourceLocation;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.Bonified;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class BonifiedModel<T extends Bonified> extends DefaultedEntityGeoModel<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SmiffleMod.MODID, "textures/entity/bonified/bonified.png");

    public BonifiedModel() {
        super(SmiffleMod.modPrefix("bonified"));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SmiffleMod.modPrefix("geo/bonified.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SmiffleMod.modPrefix("animations/bonified.animation.json");
    }
}