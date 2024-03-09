package smifflepuss.smifflemod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.config.SmiffleModConfig;
import smifflepuss.smifflemod.registry.SmiffleModItems;

import java.util.List;

public class ServerEvents {
    @SubscribeEvent
    public void onWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        genericTrades.add(new BasicItemListing(1, new ItemStack(SmiffleModItems.BLUEBELL.get()), 12, 1, 0.05F));
    }
}
