package smifflepuss.smifflemod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import smifflepuss.smifflemod.client.ClientRegistrar;
import smifflepuss.smifflemod.config.SmiffleModConfig;
import smifflepuss.smifflemod.event.ServerEvents;
import smifflepuss.smifflemod.registry.SmiffleModBlocks;
import smifflepuss.smifflemod.registry.SmiffleModEntities;
import smifflepuss.smifflemod.registry.SmiffleModItems;
import smifflepuss.smifflemod.registry.SmiffleModSoundEvents;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SmiffleMod.MODID)
public class SmiffleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "smifflemod";
    public static final Logger LOGGER = LogManager.getLogger();

    public SmiffleMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        modBus.addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SmiffleModConfig.CLIENT);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SmiffleModConfig.COMMON);

        SmiffleModBlocks.BLOCKS.register(modBus);
        SmiffleModEntities.ENTITIES.register(modBus);
        SmiffleModItems.ITEMS.register(modBus);
        SmiffleModSoundEvents.SOUND_EVENTS.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ServerEvents());

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientRegistrar::setup));
    }

    public void setup(final FMLCommonSetupEvent event)
    {
        SmiffleModItems.composterInit();
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(net.minecraftforge.registries.ForgeRegistries.BLOCKS.getKey(SmiffleModBlocks.BLUEBELL.get()), SmiffleModBlocks.POTTED_BLUEBELL);
    }

    public static ResourceLocation modPrefix(String path) {
        return new ResourceLocation(SmiffleMod.MODID, path);
    }

}
