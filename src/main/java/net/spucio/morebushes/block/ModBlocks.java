package net.spucio.morebushes.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spucio.morebushes.MoreBushes;
import net.spucio.morebushes.block.custom.*;
import net.spucio.morebushes.item.ModItems;


import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MoreBushes.MOD_ID);

    public static final RegistryObject<Block> Blue_BERRY_BUSH = BLOCKS.register("blue_berry_bush",
            () -> new BlueBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> LAVA_BERRY_BUSH = BLOCKS.register("lava_berry_bush",
            () -> new LavaBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> {
                int age = state.getValue(GoldenBerryBushBlock.AGE);
                if (age == 3) {
                    return 8;
                } else if (age == 2) {
                    return 5;
                } else {
                    return 0;
                }})));
    public static final RegistryObject<Block> GLOWSTONE_BERRY_BUSH = BLOCKS.register("glowstone_berry_bush",
            () -> new GlowstoneBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> {
                int age = state.getValue(GoldenBerryBushBlock.AGE);
                if (age == 3) {
                    return 12;
                } else if (age == 2) {
                    return 7;
                } else {
                    return 0;
                }})));
    public static final RegistryObject<Block> MINERS_BERRY_BUSH = BLOCKS.register("miners_berry_bush",
            () -> new MinersBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> {
                int age = state.getValue(GoldenBerryBushBlock.AGE);
                if (age == 3) {
                    return 4;
                } else if (age == 2) {
                    return 2;
                } else {
                    return 0;
                }})));
    public static final RegistryObject<Block> SHULKER_BERRY_BUSH = BLOCKS.register("shulker_berry_bush",
            () -> new ShulkerBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> CHORUS_BERRY_BUSH = BLOCKS.register("chorus_berry_bush",
            () -> new ChorusBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final RegistryObject<Block> GOLDEN_BERRY_BUSH = BLOCKS.register("golden_berry_bush",
            () -> new GoldenBerryBushBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> {
                int age = state.getValue(GoldenBerryBushBlock.AGE);
                if (age == 3) {
                    return 6;
                } else{
                    return 2;
                }})));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
