package com.magitechserver.foxfixer.mixins;

import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.resources.SkinManager;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Frani on 08/10/2017.
 */

@Mixin(SkinManager.class)
public class MixinSkinManager {

    @Final
    @Shadow
    private LoadingCache field_152798_f;

    @Overwrite
    public Map func_152788_a(GameProfile profile) {
        try {
            return (Map) this.field_152798_f.getUnchecked(profile);
        } catch (Exception e) {
            LogManager.getLogger().warn("Received a null skin, reloading skin map...");
            return Collections.emptyMap();
        }
    }

}
