package com.bobvarioa.kubejsbotanypots.recipes;

import com.bobvarioa.kubejsbotanypots.components.DisplayComponent;
import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.component.StringComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.data.displaystate.DisplayState;

public interface SoilRecipeJS {
    RecipeKey<InputItem> INPUT = ItemComponents.INPUT.key("input");
    RecipeKey<DisplayState> DISPLAY = DisplayComponent.ANY.key("display");
    RecipeKey<String[]> CATEGORIES = StringComponent.ANY.asArray().key("categories");
    RecipeKey<Integer> TICKS = NumberComponent.INT.key("ticks").optional(0);
    RecipeKey<Float> GROWTH_MODIFIER = NumberComponent.FLOAT.key("growthModifier").optional(1f);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, DISPLAY, CATEGORIES, TICKS, GROWTH_MODIFIER);
}