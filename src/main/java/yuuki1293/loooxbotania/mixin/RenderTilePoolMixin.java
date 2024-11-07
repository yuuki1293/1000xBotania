package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.botania.client.render.tile.RenderTilePool;
import yuuki1293.loooxbotania.Config;

@Mixin(RenderTilePool.class)
public abstract class RenderTilePoolMixin {
    @ModifyVariable(method = "render(Lvazkii/botania/common/block/tile/mana/TilePool;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "STORE"), name = "cap", remap = false)
    private int modifyCap(int cap) {
        if (cap == -1)
            return Config.maxMana;
        return cap;
    }
}
