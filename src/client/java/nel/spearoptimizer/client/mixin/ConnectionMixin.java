package nel.spearoptimizer.client.mixin;

import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ConnectionMixin {

    @Shadow private Channel channel;

    @Inject(method = "send(Lnet/minecraft/network/protocol/Packet;)V", at = @At("TAIL"))
    private void onPacketSend(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof ServerboundUseItemPacket
                || packet instanceof ServerboundPlayerActionPacket
                || packet instanceof ServerboundInteractPacket
                || packet instanceof ServerboundSwingPacket
                || packet instanceof ServerboundSetCarriedItemPacket) {
            if (this.channel != null && this.channel.isOpen()) {
                this.channel.flush();
            }
        }
    }
}