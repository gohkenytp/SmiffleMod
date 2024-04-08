package smifflepuss.smifflemod.registry;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import smifflepuss.smifflemod.SmiffleMod;
import smifflepuss.smifflemod.block.ModFlowerBlock;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SmiffleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmiffleModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SmiffleMod.MODID);
    public static final RegistryObject<Block> BLUEBELL = BLOCKS.register("bluebell", () -> new ModFlowerBlock(() -> MobEffects.REGENERATION, 8));
    public static final RegistryObject<Block> POTTED_BLUEBELL = BLOCKS.register("potted_bluebell", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUEBELL, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> SMOOTH_GLOWSTONE = register("smooth_glowstone", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel(value -> 15)));
    public static final RegistryObject<Block> GLOWSTONE_BRICKS = register("glowstone_bricks", () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel(value -> 15)));


    private static <T extends Block> RegistryObject<T> baseRegister(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        SmiffleModItems.ITEMS.register(name, item.apply(register));
        return register;
    }

    private static <T extends Block> RegistryObject<T> noItemRegister(String name, Supplier<? extends T> block) {
        RegistryObject<T> register = BLOCKS.register(name, block);
        return register;
    }

    private static <B extends Block> RegistryObject<B> register(String name, Supplier<? extends Block> block) {
        return (RegistryObject<B>) baseRegister(name, block, (object) -> SmiffleModBlocks.registerBlockItem(object));
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> {
                return new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
            };
        }
    }
