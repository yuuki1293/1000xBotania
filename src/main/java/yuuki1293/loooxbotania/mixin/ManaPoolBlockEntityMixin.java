package yuuki1293.loooxbotania.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

import static yuuki1293.loooxbotania.LoooxBotania.NEW_MAX_MANA;

@Mixin(ManaPoolBlockEntity.class)
public abstract class ManaPoolBlockEntityMixin extends BlockEntity {
    @Shadow(remap = false)
    private int manaCap;
    @Shadow(remap = false)
    @Final
    private static int MAX_MANA_DILLUTED;

    @Shadow(remap = false)
    public abstract int getMaxMana();

    public ManaPoolBlockEntityMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Redirect(method = "initManaCapAndNetwork", at = @At(value = "FIELD", target = "Lvazkii/botania/common/block/block_entity/mana/ManaPoolBlockEntity;manaCap:I", opcode = Opcodes.PUTFIELD), remap = false)
    private void initManaCapAndNetwork(ManaPoolBlockEntity manaPoolBlockEntity, int x) {
        if (this.getMaxMana() == -1) {
            this.manaCap = ((ManaPoolBlock) getBlockState().getBlock()).variant == ManaPoolBlock.Variant.DILUTED ? MAX_MANA_DILLUTED : NEW_MAX_MANA;
        }
    }

    @Inject(method = "getCurrentMana", at = @At(value = "RETURN"), cancellable = true, remap = false)
    private void getCurrentMana(CallbackInfoReturnable<Integer> cir) {
        if (getBlockState().getBlock() instanceof ManaPoolBlock pool) {
            if (pool.variant == ManaPoolBlock.Variant.CREATIVE) {
                cir.setReturnValue(NEW_MAX_MANA);
            }
        }
    }
}
