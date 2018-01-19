package com.magitechserver.foxfixer.mixins;

import am2.api.math.AMVector3;
import am2.blocks.IllusionBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Created by Frani on 01/12/2017.
 */
@Pseudo
@Mixin(IllusionBlock.class)
public abstract class MixinIllusionBlock {

    @Redirect(method = "getMimicLocation", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/IBlockAccess;getBlock(III)Lnet/minecraft/block/Block;"))
    public Block redirectGetBlock(IBlockAccess instance, int x, int y, int z) {
        try {
            return instance.getBlock(x, y, z);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Blocks.air;
        }
    }

}
