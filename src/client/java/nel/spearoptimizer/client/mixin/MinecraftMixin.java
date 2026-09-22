package nel.spearoptimizer.client.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow private int missTime;

	@Inject(method = "startAttack", at = @At("HEAD"))
	private void onStartAttack(CallbackInfoReturnable<Boolean> cir) {
		this.missTime = 0;
	}

	@Inject(method = "continueAttack", at = @At("HEAD"))
	private void onContinueAttack(boolean bl, CallbackInfo ci) {
		this.missTime = 0;
	}
}