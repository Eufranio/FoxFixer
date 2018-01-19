package com.magitechserver.foxfixer.mixins.thaum;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;

/**
 * Created by Frani on 19/01/2018.
 */
@Pseudo
@Mixin(value = PacketScannedToServer.class, remap = false)
public abstract class MixinPacketScannedToServer {

    private String name;
    private String uuid;

    @Inject(method = "<init>(Lthaumcraft/api/research/ScanResult;Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/String;)V", at = @At("RETURN"))
    private void onConstruct(ScanResult result, EntityPlayer player, String prefix, CallbackInfo ci) {
        this.name = player.getCommandSenderName();
        this.uuid = result.entity.getUniqueID().toString();
    }

    @Inject(method = "toBytes", at = @At("RETURN"))
    private void onToBytes(ByteBuf buf, CallbackInfo ci) {
        ByteBufUtils.writeUTF8String(buf, this.name);
        ByteBufUtils.writeUTF8String(buf, this.uuid);
    }
}
