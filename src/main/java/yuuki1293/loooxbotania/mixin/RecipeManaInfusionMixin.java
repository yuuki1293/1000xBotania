package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.common.crafting.RecipeManaInfusion;

@Mixin(RecipeManaInfusion.class)
public class RecipeManaInfusionMixin {
    @ModifyArg(method = "<init>(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/crafting/Ingredient;ILjava/lang/String;Lvazkii/botania/api/recipe/StateIngredient;)V", at = @At(value = "INVOKE", target = "Lcom/google/common/base/Preconditions;checkArgument(ZLjava/lang/Object;)V", ordinal = 1), index = 0, remap = false)
    private boolean injected(boolean z) {
        return true;
    }
}
