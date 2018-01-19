package com.magitechserver.foxfixer.mixins;

import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.security.*;

/**
 * Created by Frani on 08/10/2017.
 */
@Mixin(targets = "com/mojang/authlib/properties/Property", remap = false)
public abstract class MixinProperty {

    @Shadow @Final private String value;

    @Shadow @Final private String signature;

    @Overwrite
    public boolean isSignatureValid(PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(value.getBytes());
            return signature.verify(Base64.decodeBase64(this.signature));
        } catch (Exception e) {}
        return false;
    }


}
