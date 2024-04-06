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
import smifflepuss.smifflemod.entity.BirchLeopard;
import smifflepuss.smifflemod.entity.Bonified;
import smifflepuss.smifflemod.entity.Shroomling;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SmiffleMod.MODID);

    public static final RegistryObject<EntityType<Shroomling>> SHROOMLING = ENTITIES.register("shroomling", () -> EntityType.Builder.of(Shroomling::new, MobCategory.CREATURE).sized(0.5F, 0.5F).clientTrackingRange(10).build(prefix("shroomling")));
    public static final RegistryObject<EntityType<BirchLeopard>> BIRCH_LEOPARD = ENTITIES.register("birchleopard", () -> EntityType.Builder.of(BirchLeopard::new, MobCategory.CREATURE).sized(1.1F, 0.7F).clientTrackingRange(10).build(prefix("birchleopard")));
    public static final RegistryObject<EntityType<Bonified>> BONIFIED = ENTITIES.register("bonified", () -> EntityType.Builder.of(Bonified::new, MobCategory.MONSTER).sized(1.8F, 1.0F).clientTrackingRange(10).build(prefix("bonified")));

    private static String prefix(String path) {
        return SmiffleMod.MODID + "." + path;
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
        event.register(SHROOMLING.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Shroomling::checkShroomlingSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BIRCH_LEOPARD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BirchLeopard::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(BONIFIED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Bonified::checkBonifiedSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(SHROOMLING.get(), Shroomling.createAttributes().build());
        event.put(BIRCH_LEOPARD.get(), BirchLeopard.createAttributes().build());
        event.put(BONIFIED.get(), Bonified.createAttributes().build());
    }
}
