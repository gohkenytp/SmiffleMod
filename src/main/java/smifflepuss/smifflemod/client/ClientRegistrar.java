package smifflepuss.smifflemod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.client.render.*;
import smifflepuss.smifflemod.registry.SmiffleModEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SmiffleModEntities.SHROOMLING.get(), ShroomlingRenderer::new);
        event.registerEntityRenderer(SmiffleModEntities.BIRCH_LEOPARD.get(), BirchLeopardRenderer::new);
        event.registerEntityRenderer(SmiffleModEntities.BONIFIED.get(), BonifiedRenderer::new);
        event.registerEntityRenderer(SmiffleModEntities.SEAHORSE.get(), SeahorseRenderer::new);
        event.registerEntityRenderer(SmiffleModEntities.SOUL.get(), SoulRenderer::new);
    }

    public static void setup(FMLCommonSetupEvent event) {
    }

}
