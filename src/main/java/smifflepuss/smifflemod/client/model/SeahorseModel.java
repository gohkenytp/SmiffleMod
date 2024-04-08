package smifflepuss.smifflemod.client.model;

import net.minecraft.resources.ResourceLocation;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.Bonified;
import smifflepuss.smifflemod.entity.Seahorse;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class SeahorseModel<T extends Seahorse> extends DefaultedEntityGeoModel<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(SmiffleMod.MODID, "textures/entity/seahorse/seahorse.png");

    public SeahorseModel() {
        super(SmiffleMod.modPrefix("seahorse"));
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return SmiffleMod.modPrefix("geo/seahorse.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return SmiffleMod.modPrefix("animations/seahorse.animation.json");
    }
}