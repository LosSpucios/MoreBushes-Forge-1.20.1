package net.spucio.morebushes.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.spucio.morebushes.MoreBushes;
import net.spucio.morebushes.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MoreBushes.MOD_ID);

    public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberries",
            () -> new ItemNameBlockItem(ModBlocks.Blue_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.BLUEBERRIES)));
    public static final RegistryObject<Item> GOLDENBERRIES = ITEMS.register("golden_berries",
            () -> new ItemNameBlockItem(ModBlocks.GOLDEN_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.GOLDENBERRIES)));
    public static final RegistryObject<Item> LAVABERRIES = ITEMS.register("lava_berries",
            () -> new ItemNameBlockItem(ModBlocks.LAVA_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.LAVABERRIES)));
    public static final RegistryObject<Item> GLOWSTONEBERRIES = ITEMS.register("glowstone_berries",
            () -> new ItemNameBlockItem(ModBlocks.GLOWSTONE_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.GLOWSTONEBERRIES)));
    public static final RegistryObject<Item> MINERSBERRIES = ITEMS.register("miners_berries",
            () -> new ItemNameBlockItem(ModBlocks.MINERS_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.MINERSBERRIES)));
    public static final RegistryObject<Item> SHULKERBERRIES = ITEMS.register("shulker_berries",
            () -> new ItemNameBlockItem(ModBlocks.SHULKER_BERRY_BUSH.get(), new Item.Properties().food(ModFoodProperties.SHULKERBERRIES)));
    public static final RegistryObject<Item> CHORUSBERRIES = ITEMS.register("chorus_berries",
            () -> new ItemNameBlockItem(ModBlocks.CHORUS_BERRY_BUSH.get(),
                    new Item.Properties().food(ModFoodProperties.CHORUSBERRIES)) {
                @Override
                public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
                    ItemStack itemStack = super.finishUsingItem(stack, level, entity);
                    if (!level.isClientSide) {
                        // Logika teleportacji (teleportowanie gracza)
                        double d0 = entity.getX();
                        double d1 = entity.getY();
                        double d2 = entity.getZ();
                        for (int i = 0; i < 16; ++i) {
                            double d3 = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 32.0D;
                            double d4 = entity.getY() + (double)(entity.getRandom().nextInt(32) - 16);
                            double d5 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 32.0D;
                            if (entity.randomTeleport(d3, d4, d5, true)) {
                                level.playSound(null, d0, d1, d2, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                                break;
                            }
                        }
                    }
                    return itemStack;
                }
            });

    public static final RegistryObject<Item> SWEETBERRYJUICE = ITEMS.register("sweet_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.SWEETBERRYJUICE)));

    public static final RegistryObject<Item> BLUEBERRYJUICE = ITEMS.register("blueberry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.BLUEBERRYJUICE)));

    public static final RegistryObject<Item> GOLDENBERRYJUICE = ITEMS.register("golden_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.GOLDENBERRYJUICE)));

    public static final RegistryObject<Item> LAVABERRYJUICE = ITEMS.register("lava_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.LAVABERRYJUICE)));

    public static final RegistryObject<Item> GLOWSTONEBERRYJUICE = ITEMS.register("glowstone_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.GLOWSTONEBERRYJUICE)));

    public static final RegistryObject<Item> MINERSBERRYJUICE = ITEMS.register("miners_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.MINERSBERRYJUICE)));

    public static final RegistryObject<Item> SHULKERBERRYJUICE = ITEMS.register("shulker_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.SHULKERBERRYJUICE)));

    public static final RegistryObject<Item> CHORUSBERRYJUICE = ITEMS.register("chorus_berry_juice",
            () -> new JuiceItem(new Item.Properties().stacksTo(16).food(ModFoodProperties.CHORUSBERRYJUICE)) {
                @Override
                public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
                    ItemStack itemStack = super.finishUsingItem(stack, level, entity);
                    if (!level.isClientSide) {
                        // Logika teleportacji (teleportowanie gracza)
                        double d0 = entity.getX();
                        double d1 = entity.getY();
                        double d2 = entity.getZ();
                        for (int i = 0; i < 16; ++i) {
                            double d3 = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 64.0D;
                            double d4 = entity.getY() + (double)(entity.getRandom().nextInt(64) - 32);
                            double d5 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 64.0D;
                            if (entity.randomTeleport(d3, d4, d5, true)) {
                                level.playSound(null, d0, d1, d2, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                                break;
                            }
                        }
                    }
                    return itemStack;
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
