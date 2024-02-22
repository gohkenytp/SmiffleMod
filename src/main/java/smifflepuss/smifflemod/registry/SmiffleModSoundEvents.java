package smifflepuss.smifflemod.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smifflepuss.smifflemod.SmiffleMod;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "smifflemod");

    public static final RegistryObject<SoundEvent> ENTITY_SHROOMLING_DEATH = register("entity.shroomling.death");

    public static final RegistryObject<SoundEvent> ENTITY_SHROOMLING_HURT = register("entity.shroomling.hurt");

    public static final RegistryObject<SoundEvent> ENTITY_SHROOMLING_IDLE = register("entity.shroomling.idle");

    private static RegistryObject<SoundEvent> register(String name) {
        ResourceLocation id = new ResourceLocation("smifflemod", name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
