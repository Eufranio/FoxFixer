package com.magitechserver.foxfixer;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;


/**
 * Created by HeyZeer0 on 08/03/2017.
 * Copyright © HeyZeer0 - 2016
 */
public class FoxFixer extends DummyModContainer {

    public FoxFixer()
    {
        super(new ModMetadata());
        ModMetadata metadata = getMetadata();
        metadata.authorList.add("Eufranio");
        metadata.name = "FoxFixer";
        metadata.modId = "foxfixer";
        metadata.version = "1.0";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

}