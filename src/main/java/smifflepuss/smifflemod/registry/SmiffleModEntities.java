package smifflepuss.smifflemod.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.entity.Shroomling;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SmiffleMod.MODID);

    public static final RegistryObject<EntityType<Shroomling>> SHROOMLING = ENTITIES.register("shroomling", () -> EntityType.Builder.of(Shroomling::new, MobCategory.CREATURE).sized(0.5F, 0.5F).clientTrackingRange(10).build(prefix("shroomling")));

    private static String prefix(String path) {
        return SmiffleMod.MODID + "." + path;
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(SHROOMLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shroomling::checkShroomlingSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        }

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(SHROOMLING.get(), Shroomling.createAttributes().build());
    }
}
