package net.spucio.morebushes.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.spucio.morebushes.item.ModItems;

public class GoldenBerryBushBlock extends SweetBerryBushBlock implements BonemealableBlock {
    public GoldenBerryBushBlock(Properties properties) {
        super(properties);
    }

    // Blokada mączki kostnej: gra "nie widzi" tutaj celu do nawożenia
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return false;
    }

    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return false;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        // Pusta metoda, aby wyeliminować jakiekolwiek domyślne efekty mączki
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pRandom.nextFloat() < 0.5F) {
            super.randomTick(pState, pLevel, pPos, pRandom);
        }
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(ModItems.GOLDENBERRIES.get());
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        int age = pState.getValue(AGE);
        ItemStack heldItem = pPlayer.getItemInHand(pHand);

        // 1. ZBIERANIE: Tylko przy age 3
        if (age == 3) {
            int j = 1 + pLevel.random.nextInt(2);
            popResource(pLevel, pPos, new ItemStack(ModItems.GOLDENBERRIES.get(), j));
            pLevel.playSound(null, pPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + pLevel.random.nextFloat() * 0.4F);

            BlockState blockstate = pState.setValue(AGE, 1);
            pLevel.setBlock(pPos, blockstate, 2);
            pLevel.gameEvent(GameEvent.BLOCK_CHANGE, pPos, GameEvent.Context.of(pPlayer, blockstate));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        // 2. NAWOŻENIE ZŁOTEM
        if (heldItem.is(Items.GOLD_INGOT) && age < 3) {
            if (!pLevel.isClientSide) {
                // Szansa na wzrost (np. 30%)
                if (pLevel.random.nextFloat() < 0.4F) {
                    // Udany wzrost
                    pLevel.playSound(null, pPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                    ((ServerLevel) pLevel).sendParticles(net.minecraft.core.particles.ParticleTypes.WAX_ON,
                            pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, 10, 0.3D, 0.3D, 0.3D, 0.05D);

                    BlockState blockstate = pState.setValue(AGE, age + 1);
                    pLevel.setBlock(pPos, blockstate, 2);
                } else {
                    // "Pudło" - brak wzrostu, ale dźwięk i particle nadal mogą być dla efektu
                    pLevel.playSound(null, pPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 0.5F, 1.0F);
                    ((ServerLevel) pLevel).sendParticles(net.minecraft.core.particles.ParticleTypes.WAX_ON,
                            pPos.getX() + 0.5D, pPos.getY() + 0.5D, pPos.getZ() + 0.5D, 10, 0.3D, 0.3D, 0.3D, 0.05D);
                }

                // Sztabka znika niezależnie od tego, czy wzrost się udał (jak nawóz)
                if (!pPlayer.isCreative()) heldItem.shrink(1);
            }
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }

        return InteractionResult.FAIL;
    }
}