package com.magitechserver.foxfixer.mixins.thaum;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.network.playerdata.PacketAspectCombinationToServer;

/**
 * Created by Frani on 19/01/2018.
 */
@Pseudo
@Mixin(value = PacketAspectCombinationToServer.class, remap = false)
public abstract class MixinPacketAspectCombinationToServer {

    private String playerName;

    @Inject(method = "<init>(Lnet/minecraft/entity/player/EntityPlayer;IIILthaumcraft/api/aspects/Aspect;Lthaumcraft/api/aspects/Aspect;ZZZ)V", at = @At("RETURN"))
    private void onConstruct(EntityPlayer player, int x, int y, int z, Aspect aspect1, Aspect aspect2, boolean ab1, boolean ab2, boolean ret, CallbackInfo ci) {
        this.playerName = player.getCommandSenderName();
    }

    @Inject(method = "toBytes", at = @At("RETURN"))
    private void onToBytes(ByteBuf buf, CallbackInfo ci) {
        ByteBufUtils.writeUTF8String(buf, this.playerName);
    }

}
