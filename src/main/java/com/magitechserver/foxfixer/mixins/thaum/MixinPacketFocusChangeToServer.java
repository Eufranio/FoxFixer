package com.magitechserver.foxfixer.mixins.thaum;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;

/**
 * Created by Frani on 19/01/2018.
 */
@Pseudo
@Mixin(value = PacketFocusChangeToServer.class, remap = false)
public abstract class MixinPacketFocusChangeToServer {

    public String name;

    @Inject(method = "<init>(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V", at = @At("RETURN"))
    private void onConstruct(EntityPlayer p, String focus, CallbackInfo ci) {
        this.name = p.getCommandSenderName();
    }

    @Inject(method = "toBytes", at = @At("RETURN"))
    private void onToBytes(ByteBuf buffer, CallbackInfo ci) {
        ByteBufUtils.writeUTF8String(buffer, this.name);
    }

}
