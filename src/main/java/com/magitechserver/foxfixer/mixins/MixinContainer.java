package com.magitechserver.foxfixer.mixins;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Created by Frani on 15/11/2017.
 */
@Mixin(Container.class)
public abstract class MixinContainer {

    @Shadow public List inventorySlots;

    @Overwrite
    public Slot getSlot(int p_75139_1_) {
        try {
            return (Slot) this.inventorySlots.get(p_75139_1_);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Class: " + this.getClass());
            return null;
        }
    }

}
