package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.item.ManaMirrorItem;
import yuuki1293.loooxbotania.Config;

@Mixin(ManaMirrorItem.ManaItemImpl.class)
public class ManaMirrorItemManaItemImplMixin {
    @ModifyArg(method = "getMaxMana", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/helper/ItemNBTHelper;getInt(Lnet/minecraft/world/item/ItemStack;Ljava/lang/String;I)I"), index = 2, remap = false)
    private int getMaxMana(int x) {
        return Config.maxMana;
    }
}
