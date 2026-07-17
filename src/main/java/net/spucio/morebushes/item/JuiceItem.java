package net.spucio.morebushes.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class JuiceItem extends Item {
    public JuiceItem(Properties properties) {
        super(properties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        // W 1.20.1 super wywołanie powinno wyglądać tak:
        ItemStack result = super.finishUsingItem(stack, level, entityLiving);

        // reszta Twojej logiki...
        if (entityLiving instanceof Player player && player.isCreative()) {
            return result;
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        } else {
            if (entityLiving instanceof Player player) {
                ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                if (!player.getInventory().add(bottle)) {
                    player.drop(bottle, false);
                }
            }
            return result;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // W 1.20.1 używamy getFoodProperties() bez argumentów
        FoodProperties food = stack.getItem().getFoodProperties();

        if (food != null) {
            // W 1.20.1 efekty to lista Pair<Supplier<MobEffectInstance>, Float>
            for (com.mojang.datafixers.util.Pair<net.minecraft.world.effect.MobEffectInstance, Float> effectPair : food.getEffects()) {
                MobEffectInstance effect = effectPair.getFirst();

                // Pobieranie nazwy efektu w 1.20.1
                String effectName = effect.getEffect().getDescriptionId();

                int totalSeconds = effect.getDuration() / 20;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                String formattedTime = minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);

                tooltip.add(Component.translatable(effectName)
                        .append(" (" + formattedTime + ")")
                        .withStyle(ChatFormatting.BLUE));
            }
        }

        // Pobieranie klucza w 1.20.1
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(this);
        if (id != null && id.getPath().contains("chorus_berry_juice")) {
            tooltip.add(Component.literal("Chorus Effect III").withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(stack, level, tooltip, flag);
    }
}