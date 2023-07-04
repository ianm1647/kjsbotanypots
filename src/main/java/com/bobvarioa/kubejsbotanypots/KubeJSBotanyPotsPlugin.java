package com.bobvarioa.kubejsbotanypots;

import com.bobvarioa.kubejsbotanypots.recipes.CropRecipeJS;
import com.bobvarioa.kubejsbotanypots.recipes.FertilizerRecipeJS;
import com.bobvarioa.kubejsbotanypots.recipes.SoilRecipeJS;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;
import net.darkhax.botanypots.data.recipes.crop.Crop;
import net.darkhax.botanypots.events.CropDropEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Random;

public class KubeJSBotanyPotsPlugin extends KubeJSPlugin {
    public static EventGroup GROUP = EventGroup.of("BotanyPotsEvents");
    public static EventHandler CROP_GROW = GROUP.server("onCropGrow", () -> CropGrowEvent.class);

    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        event.register(new ResourceLocation("botanypots:soil"), SoilRecipeJS.SCHEMA);
        event.register(new ResourceLocation("botanypots:crop"), CropRecipeJS.SCHEMA);
        event.register(new ResourceLocation("botanypots:fertilizer"), FertilizerRecipeJS.SCHEMA);
    }

    @Override
    public void registerEvents() {
        GROUP.register();
    }

    public static class CropGrowEvent extends EventJS {
        private CropDropEvent event;

        public CropGrowEvent(CropDropEvent event) {
            this.event = event;
        }

        public Random getRandom() {
            return event.getRandom();
        }

        public Crop getCrop() {
            return event.getCrop();
        }

        public List<ItemStack> getOriginalDrops() {
            return event.getOriginalDrops();
        }

        public List<ItemStack> getDrops() {
            return event.getDrops();
        }
    }
}
