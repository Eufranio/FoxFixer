package com.magitechserver.foxfixer.mixins;

import cofh.thermalfoundation.block.BlockOre;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.*;

/**
 * Created by Frani on 02/11/2017.
 */

@Pseudo
@Mixin(value = BlockOre.class, remap = false)
public abstract class MixinBlockOre {

    @Shadow(remap = false) @Final public static IIcon[] TEXTURES;

    @Shadow(remap = false) @Final public static int[] LIGHT;

    @Overwrite
    public IIcon func_149691_a(int a, int b) {
        if (b > 6) return TEXTURES[1];
        return TEXTURES[b];
    }

    @Overwrite
    public int getLightValue(IBlockAccess var1, int var2, int var3, int var4) {
        int l = var1.getBlockMetadata(var2, var3, var4);
        if (l < 0 || l > 7) {
            return LIGHT[0];
        }
        return LIGHT[var1.getBlockMetadata(var2, var3, var4)];
    }

}
