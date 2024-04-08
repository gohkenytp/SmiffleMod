package smifflepuss.smifflemod.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import smifflepuss.smifflemod.entity.BirchLeopard;
import smifflepuss.smifflemod.misc.tags.SmiffleModItemTags;
import smifflepuss.smifflemod.registry.SmiffleModItems;

import java.util.List;

public class ServerEvents {
    @SubscribeEvent
    public void onWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        genericTrades.add(new BasicItemListing(1, new ItemStack(SmiffleModItems.BLUEBELL.get()), 12, 1, 0.05F));
    }

    @SubscribeEvent
    public void onLootLevelEvent(LootingLevelEvent event) {
        DamageSource src = event.getDamageSource();
        if (src != null) {
            if (src.getEntity() instanceof BirchLeopard) {
                event.setLootingLevel(event.getLootingLevel() + 2);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDropEvent(LivingDropsEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Entity killer = event.getSource().getEntity();
        if (livingEntity instanceof Player) return;

        if (killer instanceof BirchLeopard) {
            event.getDrops().removeIf(itemEntity -> itemEntity.getItem().is(SmiffleModItemTags.BIRCH_LEOPARD_CONSUMABLES));
        }
    }
}