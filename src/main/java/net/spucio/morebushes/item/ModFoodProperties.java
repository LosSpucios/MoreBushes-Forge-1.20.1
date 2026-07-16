package net.spucio.morebushes.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties BLUEBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60),1.0F).build();
    public static final FoodProperties GOLDENBERRIES = new FoodProperties.Builder().nutrition(5).saturationMod(0.25F).effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 3), 1.0F).alwaysEat().build();
    public static final FoodProperties LAVABERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 60),1.0F).build();
    public static final FoodProperties GLOWSTONEBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.GLOWING, 60),1.0F).effect(new MobEffectInstance(MobEffects.HEAL),1.0F).build();
    public static final FoodProperties MINERSBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 60),1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 60),1.0F).build();
    public static final FoodProperties SHULKERBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.LEVITATION, 60, 2),1.0F).build();
    public static final FoodProperties CHORUSBERRIES = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties SWEETBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties BLUEBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.WATER_BREATHING, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties GOLDENBERRYJUICE = new FoodProperties.Builder().nutrition(4).saturationMod(0.25F).effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 1), 1.0F).effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 12000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 4800, 3), 1.0F).alwaysEat().build();
    public static final FoodProperties LAVABERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties GLOWSTONEBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.GLOWING, 480), 1.0F).effect(new MobEffectInstance(MobEffects.REGENERATION, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties MINERSBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 480), 1.0F).effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties SHULKERBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.LEVITATION, 480), 1.0F).alwaysEat().build();
    public static final FoodProperties CHORUSBERRYJUICE = new FoodProperties.Builder().nutrition(8).saturationMod(0.1F).alwaysEat().build();
}
