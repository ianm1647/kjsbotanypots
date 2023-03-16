package com.bobvarioa.kubejsbotanypots;

import com.bobvarioa.kubejsbotanypots.recipes.CropRecipeJS;
import com.bobvarioa.kubejsbotanypots.recipes.FertilizerRecipeJS;
import com.bobvarioa.kubejsbotanypots.recipes.SoilRecipeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.RegisterRecipeTypesEvent;
import net.darkhax.botanypots.BotanyPotHelper;
import net.minecraft.resources.ResourceLocation;

public class KubeJSBotanyPotsPlugin extends KubeJSPlugin {
    @Override
    public void registerRecipeTypes(RegisterRecipeTypesEvent event) {
        event.register(new ResourceLocation("botanypots:soil"), SoilRecipeJS::new);
        event.register(new ResourceLocation("botanypots:crop"), CropRecipeJS::new);
        event.register(new ResourceLocation("botanypots:fertilizer"), FertilizerRecipeJS::new);
    }
}
