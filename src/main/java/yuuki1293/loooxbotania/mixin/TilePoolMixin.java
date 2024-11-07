package yuuki1293.loooxbotania.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
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
import vazkii.botania.common.block.mana.BlockPool;
import vazkii.botania.common.block.tile.mana.TilePool;
import yuuki1293.loooxbotania.Config;

@Mixin(TilePool.class)
public abstract class TilePoolMixin extends BlockEntity {
    @Shadow(remap = false)
    public int manaCap;
    @Shadow(remap = false)
    @Final
    private static int MAX_MANA_DILLUTED;

    public TilePoolMixin(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Redirect(method = "initManaCapAndNetwork", at = @At(value = "FIELD", target = "Lvazkii/botania/common/block/tile/mana/TilePool;manaCap:I", opcode = Opcodes.PUTFIELD), remap = false)
    private void initManaCapAndNetwork(TilePool tilePool, int x) {
        if (this.manaCap == -1) {
            this.manaCap = ((BlockPool) getBlockState().getBlock()).variant == BlockPool.Variant.DILUTED ? MAX_MANA_DILLUTED : Config.maxMana;
        }
    }

    @Inject(method = "getCurrentMana", at = @At(value = "RETURN"), cancellable = true, remap = false)
    private void getCurrentMana(CallbackInfoReturnable<Integer> cir) {
        if (getBlockState().getBlock() instanceof BlockPool pool) {
            if (pool.variant == BlockPool.Variant.CREATIVE) {
                cir.setReturnValue(Config.maxMana);
            }
        }
    }
}
