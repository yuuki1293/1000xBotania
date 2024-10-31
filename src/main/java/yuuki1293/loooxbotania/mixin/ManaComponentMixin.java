package yuuki1293.loooxbotania.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.client.patchouli.component.ManaComponent;

import static yuuki1293.loooxbotania.LoooxBotania.NEW_MAX_MANA;

@Mixin(ManaComponent.class)
public class ManaComponentMixin {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lvazkii/botania/client/gui/HUDHandler;renderManaBar(Lnet/minecraft/client/gui/GuiGraphics;IIIFII)V"), index = 6)
    private int injected(int maxMana){
        return NEW_MAX_MANA;
    }
}
