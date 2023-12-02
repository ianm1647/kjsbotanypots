package com.bobvarioa.kubejsbotanypots.recipes;

import com.bobvarioa.kubejsbotanypots.components.DisplayComponent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.item.OutputItem;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public interface CropRecipeJS {
    RecipeKey<InputItem> SEED = ItemComponents.INPUT.key("seed");
    RecipeKey<String[]> CATEGORIES = StringComponent.ANY.asArray().key("categories");
    RecipeKey<Long> GROWTH_TICKS = TimeComponent.TICKS.key("growthTicks");
    RecipeKey<Float> GROWTH_MODIFIER = NumberComponent.FLOAT.key("growthModifier").optional(1f);
    RecipeKey<DisplayState[]> DISPLAY = DisplayComponent.ANY.asArrayOrSelf().key("display");


    RecipeComponent<OutputItem> ID_CHANCE = new RecipeComponentWithParent<OutputItem>() {

        @Override
        public RecipeComponent<OutputItem> parentComponent() {
            return ItemComponents.OUTPUT;
        }

        @Override
        public @Nullable JsonElement write(RecipeJS recipe, OutputItem value) {
            var json = RecipeComponentWithParent.super.write(recipe, value).getAsJsonObject();
            // ngl even after looking at the json many times idk why this is right, i just know it is
            json.add("output", json.get("item"));
            return json;
        }

        @Override
        public OutputItem read(RecipeJS recipe, Object from) {
            if (from instanceof JsonObject json) {
                json.add("item", json.get("output").getAsJsonObject().get("item"));
                return RecipeComponentWithParent.super.read(recipe, json);
            }

            return RecipeComponentWithParent.super.read(recipe, from);
        }
    };

    RecipeKey<OutputItem[]> DROPS = ID_CHANCE.asArray().key("drops");

    RecipeSchema SCHEMA = new RecipeSchema(SEED, CATEGORIES, DISPLAY, DROPS, GROWTH_TICKS, GROWTH_MODIFIER);
}
