package smifflepuss.smifflemod.registry;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.block.ModFlowerBlock;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SmiffleMod.MODID);
    public static final RegistryObject<Block> BLUEBELL = BLOCKS.register("bluebell", () -> new ModFlowerBlock(() -> MobEffects.REGENERATION, 8));
    public static final RegistryObject<Block> POTTED_BLUEBELL = BLOCKS.register("potted_bluebell", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUEBELL, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
}
