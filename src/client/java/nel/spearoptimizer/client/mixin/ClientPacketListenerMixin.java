package nel.spearoptimizer.client.mixin;

import nel.spearoptimizer.client.SpearState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Inject(method = "handleSetEntityMotion", at = @At("HEAD"), cancellable = true)
    private void onMotion(ClientboundSetEntityMotionPacket packet, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        if (packet.getId() == client.player.getId() && SpearState.awaitingLunge) {
            SpearState.awaitingLunge = false;

            Vec3 target = packet.getMovement();
            Vec3 current = client.player.getDeltaMovement();

            Vec3 blended = new Vec3(
                    current.x * 0.25 + target.x * 0.75,
                    target.y,
                    current.z * 0.25 + target.z * 0.75
            );

            client.player.setDeltaMovement(blended);
            ci.cancel();
        }
    }
}