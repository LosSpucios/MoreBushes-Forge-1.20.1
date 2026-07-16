package net.spucio.morebushes.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.spucio.morebushes.MoreBushes;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MoreBushes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // 1. Rejestracja Loot Table
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK))));


        // 2. Rejestracja Block State
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // 3. Rejestracja WorldGen (Configured Features)
        generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
    }
}