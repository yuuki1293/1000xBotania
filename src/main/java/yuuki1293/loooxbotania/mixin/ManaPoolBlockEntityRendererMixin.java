package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.botania.client.render.block_entity.ManaPoolBlockEntityRenderer;
import yuuki1293.loooxbotania.Config;

@Mixin(ManaPoolBlockEntityRenderer.class)
public class ManaPoolBlockEntityRendererMixin {
    @ModifyVariable(method = "render(Lvazkii/botania/common/block/block_entity/mana/ManaPoolBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "STORE"), ordinal = 8, remap = false)
    private int injected(int x){
        if (x == -1)
            return Config.maxMana;
        return x;
    }
}
