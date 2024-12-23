package yuuki1293.loooxbotania.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.block_entity.mana.ManaPumpBlockEntity;
import vazkii.botania.common.entity.ManaPoolMinecartEntity;
import yuuki1293.loooxbotania.Config;

@Mixin(ManaPoolMinecartEntity.class)
public abstract class ManaPoolMinecartEntityMixin {
    @Shadow(remap = false)
    public abstract int getMana();

    @ModifyVariable(method = "tick", at = @At("STORE"), ordinal = 0)
    private double tick(double d) {
        return 1F - (double) this.getMana() / (double) Config.maxMana * 0.1;
    }

    @ModifyArg(method = "moveAlongTrack", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"), index = 0)
    private int moveAlongTrack(int x, @Local(name = "cartMana") int cartMana) {
        return Config.maxMana - cartMana;
    }

    @Redirect(method = "moveAlongTrack", at = @At(value = "FIELD", target = "Lvazkii/botania/common/block/block_entity/mana/ManaPumpBlockEntity;comparator:I", opcode = Opcodes.PUTFIELD), remap = false)
    private void moveAlongTrack(ManaPumpBlockEntity pump, int value) {
        pump.comparator = (int) ((double) getMana() / (double) Config.maxMana * 15);
    }

    @ModifyArg(method = "getComparatorLevel", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/block_entity/mana/ManaPoolBlockEntity;calculateComparatorLevel(II)I"), index = 1, remap = false)
    private int getComparatorLevel(int x) {
        return Config.maxMana;
    }
}
