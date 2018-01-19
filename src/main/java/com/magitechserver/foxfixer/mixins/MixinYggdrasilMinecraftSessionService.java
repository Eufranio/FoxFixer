package com.magitechserver.foxfixer.mixins;

import com.google.gson.Gson;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Created by Frani on 08/10/2017.
 */
@Mixin(targets = "com/mojang/authlib/yggdrasil/YggdrasilMinecraftSessionService", remap = false)
public abstract class MixinYggdrasilMinecraftSessionService {

    @Redirect(method = "getTextures", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V"))
    private void redirectThrow(Logger logger, String error, Throwable throwable) {
        logger.error("Could not decode textures payload");
    }

}
