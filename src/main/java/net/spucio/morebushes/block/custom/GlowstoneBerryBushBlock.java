package net.spucio.morebushes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.spucio.morebushes.item.ModItems;

public class GlowstoneBerryBushBlock extends SweetBerryBushBlock {
    public GlowstoneBerryBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(ModItems.GLOWSTONEBERRIES.get());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int i = pState.getValue(AGE);
        boolean flag = i == 3;
        if (!flag && pPlayer.getItemInHand(pHand).is(Items.BONE_MEAL)) {
            return InteractionResult.PASS;
        } else if (i > 1) {
            int j = 1 + pLevel.random.nextInt(2);
            popResource(pLevel, pPos, new ItemStack(ModItems.GLOWSTONEBERRIES.get(), j + (flag ? 1 : 0)));
            pLevel.playSound((Player)null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);
            BlockState blockstate = pState.setValue(AGE, Integer.valueOf(1));
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        } else {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        }
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        super.entityInside(pState, pLevel, pPos, pEntity);

        if (!pLevel.isClientSide && pEntity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) pEntity;

            // 25% szansy na tick (1 na 4)
            if (pLevel.random.nextInt(4) == 0) {
                // Nakłada efekt Glowing na 100 ticków (5 sekund)
                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
            }
        }
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(Blocks.NETHERRACK) || pState.is(Blocks.WARPED_NYLIUM) || pState.is(Blocks.CRIMSON_NYLIUM);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState soil = worldIn.getBlockState(pos.below());
        return soil.is(Blocks.NETHERRACK) || soil.is(Blocks.WARPED_NYLIUM) || soil.is(Blocks.CRIMSON_NYLIUM);
    }
}