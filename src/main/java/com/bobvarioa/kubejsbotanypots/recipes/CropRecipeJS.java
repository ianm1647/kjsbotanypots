package com.bobvarioa.kubejsbotanypots.recipes;

import com.bobvarioa.kubejsbotanypots.components.DisplayComponent;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.data.displaystate.DisplayState;

import java.util.Map;


public interface CropRecipeJS {
    RecipeKey<InputItem> SEED = ItemComponents.INPUT.key("seed");
    RecipeKey<String[]> CATEGORIES = StringComponent.ANY.asArray().key("categories");
    RecipeKey<Long> GROWTH_TICKS = TimeComponent.TICKS.key("growthTicks");
    RecipeKey<Float> GROWTH_MODIFIER = NumberComponent.FLOAT.key("growthModifier").optional(1f);
    RecipeKey<DisplayState> DISPLAY = DisplayComponent.ANY.key("display");

    class LootEntry {
        float chance;
        OutputItem output;
        int minRolls;
        int maxRolls;

        public LootEntry(float chance, OutputItem output, int minRolls, int maxRolls) {
            this.chance = chance;
            this.output = output;
            this.minRolls = minRolls;
            this.maxRolls = maxRolls;
        }
    }

    RecipeComponentBuilder LOOT_ENTRY = new RecipeComponentBuilder(4)
            .add(ItemComponents.OUTPUT.key("output"))
            .add(NumberComponent.FLOAT.key("chance"))
            .add(NumberComponent.INT.key("minRolls"))
            .add(NumberComponent.INT.key("maxRolls"));

    RecipeKey<Map<String, Object>[]> DROPS = LOOT_ENTRY.asArray().key("drops");

    RecipeSchema SCHEMA = new RecipeSchema(SEED, CATEGORIES, DISPLAY, DROPS, GROWTH_TICKS, GROWTH_MODIFIER);
}
