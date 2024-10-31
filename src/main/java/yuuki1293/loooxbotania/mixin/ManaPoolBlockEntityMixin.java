package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;

@Mixin(ManaPoolBlockEntity.class)
public class ManaPoolBlockEntityMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void onClassInit(CallbackInfo ci) {
    }
}
