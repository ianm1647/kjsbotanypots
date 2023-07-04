package com.bobvarioa.kubejsbotanypots;

import dev.latvian.mods.kubejs.script.ScriptType;
import net.darkhax.botanypots.events.CropDropEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(KubeJSBotanyPots.MODID)
public class KubeJSBotanyPots {
    public static final String MODID = "kubejsbotanypots";

    public KubeJSBotanyPots() {
        MinecraftForge.EVENT_BUS.addListener(this::cropDrop);
    }

    public void cropDrop(CropDropEvent event) {
        KubeJSBotanyPotsPlugin.CROP_GROW.post(ScriptType.SERVER, new KubeJSBotanyPotsPlugin.CropGrowEvent(event));
    }
}
