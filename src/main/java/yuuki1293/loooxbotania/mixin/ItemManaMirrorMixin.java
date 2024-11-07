package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.ItemManaMirror;
import yuuki1293.loooxbotania.Config;

@Mixin(ItemManaMirror.ManaItem.class)
public class ItemManaMirrorMixin {
    @Inject(method = "getMaxMana", at = @At(value = "RETURN"), cancellable = true)
    private void getMaxMana(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(Config.maxMana);
    }
}
