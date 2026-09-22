package nel.spearoptimizer.client.mixin;

import nel.spearoptimizer.client.SpearState;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @Inject(method = "releaseUsingItem", at = @At("HEAD"))
    private void onRelease(Player player, CallbackInfo ci) {
        if (player != null && player.isUsingItem()) {
            ItemStack stack = player.getUseItem();
            if (stack.getItem().toString().contains("spear")) {
                SpearState.releaseTimestamp = System.currentTimeMillis();
                SpearState.awaitingLunge = true;
            }
        }
    }
}