package net.spucio.morebushes.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.spucio.morebushes.MoreBushes;
import net.spucio.morebushes.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_BERRY_BUSH_KEY = registerKey("blue_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDEN_BERRY_BUSH_KEY = registerKey("golden_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_BERRY_BUSH_KEY = registerKey("lava_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWSTONE_BERRY_BUSH_KEY = registerKey("glowstone_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MINERS_BERRY_BUSH_KEY = registerKey("miners_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHULKER_BERRY_BUSH_KEY = registerKey("shulker_berry_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHORUS_BERRY_BUSH_KEY = registerKey("chorus_berry_bush");

    public static void bootstap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        // Złote jagody - POJEDYNCZE (używamy registerSimple)
        registerSimple(context, GOLDEN_BERRY_BUSH_KEY, ModBlocks.GOLDEN_BERRY_BUSH.get(),
                List.of(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.NETHERRACK, Blocks.END_STONE,
                        Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM));

        // Reszta - GRUPKI (używamy registerPatch)
        registerPatch(context, BLUE_BERRY_BUSH_KEY, ModBlocks.Blue_BERRY_BUSH.get(), List.of(Blocks.GRASS_BLOCK));
        registerPatch(context, LAVA_BERRY_BUSH_KEY, ModBlocks.LAVA_BERRY_BUSH.get(), List.of(Blocks.CRIMSON_NYLIUM));
        registerPatch(context, GLOWSTONE_BERRY_BUSH_KEY, ModBlocks.GLOWSTONE_BERRY_BUSH.get(), List.of(Blocks.WARPED_NYLIUM));
        registerPatch(context, MINERS_BERRY_BUSH_KEY, ModBlocks.MINERS_BERRY_BUSH.get(), List.of(Blocks.STONE, Blocks.GRANITE, Blocks.ANDESITE, Blocks.TUFF, Blocks.DIORITE));
        registerPatch(context, SHULKER_BERRY_BUSH_KEY, ModBlocks.SHULKER_BERRY_BUSH.get(), List.of(Blocks.END_STONE));
        registerPatch(context, CHORUS_BERRY_BUSH_KEY, ModBlocks.CHORUS_BERRY_BUSH.get(), List.of(Blocks.END_STONE));
    }

    // Dla pojedynczego krzaka (brak patcha)
    private static void registerSimple(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Block bushBlock, List<Block> targetBlocks) {
        FeatureUtils.register(context, key, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(bushBlock.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))));
    }

    private static void registerPatch(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Block bushBlock, List<Block> targetBlocks) {

        WeightedStateProvider weightedProvider = new WeightedStateProvider(
                SimpleWeightedRandomList.<net.minecraft.world.level.block.state.BlockState>builder()
                        .add(bushBlock.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 2), 65)
                        .add(bushBlock.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3), 35)
                        .build()
        );

        FeatureUtils.register(context, key, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(weightedProvider),
                        targetBlocks));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MoreBushes.MOD_ID, name));
    }
}