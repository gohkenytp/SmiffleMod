package smifflepuss.smifflemod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import smifflepuss.smifflemod.config.SmiffleModConfig;
import smifflepuss.smifflemod.registry.SmiffleModItems;

import java.util.List;

public class ServerEvents {
    @SubscribeEvent
    public void onWandererTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        genericTrades.add(new BasicItemListing(1, new ItemStack(SmiffleModItems.BLUEBELL.get()), 12, 1, 0.05F));
    }

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        Level world = event.getEntity().level();
        BlockPos pos = event.getEntity().blockPosition();
        EntityType<?> entity = event.getEntity().getType();
        if ((SmiffleModConfig.ENABLE_POLAR_BEAR_MEAT.get() && entity.equals(EntityType.POLAR_BEAR))) {
            ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, world);
            itemEntity.setPos(pos.getX() + 0.5D, pos.getY() + 0.2D, pos.getZ() + 0.5D);
            itemEntity.setItem(SmiffleModItems.BEAR_MEAT.get().getDefaultInstance());
            event.getEntity().level().addFreshEntity(itemEntity);
        }
    }
}
