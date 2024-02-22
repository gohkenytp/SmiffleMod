package smifflepuss.smifflemod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import smifflepuss.smifflemod.SmiffleMod;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID)
public class SmiffleModConfig {
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_POLAR_BEAR_MEAT;

    public static final ForgeConfigSpec CLIENT;
    public static final ForgeConfigSpec COMMON;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Experimental Entity Modifications").push("experimental_entity_modifications");
        ENABLE_POLAR_BEAR_MEAT = COMMON_BUILDER.comment("Enables polar bears dropping their meat when slain").define("Enable polar bears dropping their meat when slain", true);
        COMMON_BUILDER.pop();

        CLIENT = CLIENT_BUILDER.build();
        COMMON = COMMON_BUILDER.build();
    }
}