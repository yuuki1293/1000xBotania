package yuuki1293.loooxbotania.mixin;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.client.render.ColorHandler;
import vazkii.botania.xplat.IXplatAbstractions;
import yuuki1293.loooxbotania.Config;

@Mixin(ColorHandler.class)
public class ColorHandlerMixin {
    @Contract(pure = true)
    @ModifyArg(method = "submitItems", at = @At(value = "INVOKE", target = "Lvazkii/botania/client/render/ColorHandler$ItemHandlerConsumer;register(Lnet/minecraft/client/color/item/ItemColor;[Lnet/minecraft/world/level/ItemLike;)V", ordinal = 4), index = 0, remap = false)
    private static @NotNull ItemColor register(ItemColor color) {
        return (s, t) -> {
            if (t == 1) {
                var manaItem = IXplatAbstractions.INSTANCE.findManaItem(s);
                return Mth.hsvToRgb(0.528F, (float) manaItem.getMana() / (float) Config.maxMana, 1F);
            }
            return -1;
        };
    }
}
