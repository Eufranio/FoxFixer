package com.magitechserver.foxfixer;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.CoreModManager;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.Map;

/**
 * Created by Frani on 08/10/2017.
 */

@IFMLLoadingPlugin.Name("FoxFixer")
@IFMLLoadingPlugin.MCVersion("1.7.10")
public class MagiCore implements IFMLLoadingPlugin {

    public MagiCore() {
        MixinBootstrap.init();
        try{
            Mixins.addConfiguration("mixins/mixin-forge.json");
            File exNihilo = new File(new File("mods"), "Ex-Nihilo-1.38-53.jar");
            if (exNihilo.exists()) {
                ((LaunchClassLoader) this.getClass().getClassLoader()).addURL(exNihilo.toURI().toURL());
                CoreModManager.getReparseableCoremods().add(exNihilo.getName());
                Mixins.addConfiguration("mixins/mixin-exnihilo.json");
            }

            File tf = new File(new File("mods"), "ThermalFoundation-[1.7.10]1.2.6-118.jar");
            if (tf.exists()) {
                ((LaunchClassLoader) this.getClass().getClassLoader()).addURL(tf.toURI().toURL());
                CoreModManager.getReparseableCoremods().add(tf.getName());
                Mixins.addConfiguration("mixins/mixin-thermalfoundation.json");
            }

            File am2 = new File(new File("mods"), "1.7.10_AM2-1.4.0.009.jar");
            if (am2.exists()) {
                ((LaunchClassLoader) this.getClass().getClassLoader()).addURL(am2.toURI().toURL());
                CoreModManager.getReparseableCoremods().add(am2.getName());
                Mixins.addConfiguration("mixins/mixin-am2.json");
            }

            File thaum = new File(new File("mods"), "Thaumcraft-1.7.10-4.2.3.5.jar");
            if (am2.exists()) {
                ((LaunchClassLoader) this.getClass().getClassLoader()).addURL(am2.toURI().toURL());
                CoreModManager.getReparseableCoremods().add(am2.getName());
                Mixins.addConfiguration("mixins/mixin-thaum.json");
            }
        }catch (Exception e) {
            LogManager.getLogger().warn(" ");
            LogManager.getLogger().warn("[FoxFixer] Error loading mod: ");
            LogManager.getLogger().warn(" ");
            e.printStackTrace();
        }

    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return FoxFixer.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass()
    {
        return null;
    }

}
