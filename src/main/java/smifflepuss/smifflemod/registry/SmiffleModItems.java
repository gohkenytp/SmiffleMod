package smifflepuss.smifflemod.registry;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.item.ItemModFishBucket;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SmiffleMod.MODID);

    public static final RegistryObject<Item> SHROOMLING_SPAWN_EGG = ITEMS.register("shroomling_spawn_egg", () -> new ForgeSpawnEggItem(SmiffleModEntities.SHROOMLING, 0xFF5555, 0x964B00, (new Item.Properties())));
    public static final RegistryObject<Item> BIRCH_LEOPARD_SPAWN_EGG = ITEMS.register("birchleopard_spawn_egg", () -> new ForgeSpawnEggItem(SmiffleModEntities.BIRCH_LEOPARD, 0xA39898, 0x93C47D, (new Item.Properties())));
    public static final RegistryObject<Item> BONIFIED_SPAWN_EGG = ITEMS.register("bonified_spawn_egg", () -> new ForgeSpawnEggItem(SmiffleModEntities.BONIFIED, 0x766D6D, 0x567B96, (new Item.Properties())));
    public static final RegistryObject<Item> SEAHORSE_SPAWN_EGG = ITEMS.register("seahorse_spawn_egg", () -> new ForgeSpawnEggItem(SmiffleModEntities.SEAHORSE, 0xFFD966, 0xFFF02D, (new Item.Properties())));
    public static final RegistryObject<Item> BLUEBELL = ITEMS.register("bluebell", () -> new BlockItem(SmiffleModBlocks.BLUEBELL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BEAR_MEAT = ITEMS.register("bear_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).meat().build())));
    public static final RegistryObject<Item> COOKED_BEAR_MEAT = ITEMS.register("cooked_bear_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.85F).meat().build())));
    public static final RegistryObject<Item> BONIFIED_FUR = ITEMS.register("bonified_fur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket", () -> new ItemModFishBucket(SmiffleModEntities.SEAHORSE, Fluids.WATER, new Item.Properties()));
    public static final RegistryObject<Item> SEAHORSE_ITEM = ITEMS.register("seahorse_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).meat().build())));
    public static final RegistryObject<Item> COOKED_SEAHORSE = ITEMS.register("cooked_seahorse", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.65F).meat().build())));

    @SubscribeEvent
    public static void registerCreativeTabsItem(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.getEntries().putAfter(new ItemStack(Items.BEE_SPAWN_EGG), new ItemStack(BIRCH_LEOPARD_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(SmiffleModItems.BIRCH_LEOPARD_SPAWN_EGG.get()), new ItemStack(BONIFIED_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(Items.SALMON_SPAWN_EGG), new ItemStack(SEAHORSE_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(Items.SHEEP_SPAWN_EGG), new ItemStack(SHROOMLING_SPAWN_EGG.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
             event.getEntries().putAfter(new ItemStack(Items.GLOWSTONE), new ItemStack(SmiffleModBlocks.SMOOTH_GLOWSTONE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(SmiffleModBlocks.SMOOTH_GLOWSTONE.get()), new ItemStack(SmiffleModBlocks.GLOWSTONE_BRICKS.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(Items.LILY_OF_THE_VALLEY), new ItemStack(SmiffleModBlocks.BLUEBELL.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.getEntries().putAfter(new ItemStack(Items.COOKED_RABBIT), new ItemStack(BEAR_MEAT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(BEAR_MEAT.get()), new ItemStack(COOKED_BEAR_MEAT.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(Items.COOKED_SALMON), new ItemStack(SEAHORSE_ITEM.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(new ItemStack(SEAHORSE_ITEM.get()), new ItemStack(COOKED_SEAHORSE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putBefore(new ItemStack(Items.RABBIT_FOOT), new ItemStack(BONIFIED_FUR.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putBefore(new ItemStack(Items.TROPICAL_FISH_BUCKET), new ItemStack(SEAHORSE_BUCKET.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    public static void composterInit() {
        ComposterBlock.COMPOSTABLES.put(SmiffleModBlocks.BLUEBELL.get(), 0.1F);
    }

}
