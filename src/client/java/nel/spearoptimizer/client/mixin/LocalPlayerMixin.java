package nel.spearoptimizer.client.mixin;

import nel.spearoptimizer.client.SpearState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void handleSpearMechanics(CallbackInfo ci) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        Minecraft mc = Minecraft.getInstance();

        if (player.isUsingItem()) {
            ItemStack stack = player.getUseItem();
            if (stack.getItem().toString().contains("spear")) {
                int useTicks = player.getTicksUsingItem();
                if (useTicks >= 10 && mc.options.keyAttack.isDown()) {
                    SpearState.releaseTimestamp = System.currentTimeMillis();
                    SpearState.awaitingLunge = true;
                    if (mc.gameMode != null) {
                        mc.gameMode.releaseUsingItem(player);
                    }
                }
            }
        }
    }
}