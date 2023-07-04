package com.bobvarioa.kubejsbotanypots.recipes;

import dev.latvian.mods.kubejs.item.InputItem;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.ItemComponents;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;

public interface FertilizerRecipeJS {
    RecipeKey<InputItem> ITEM = ItemComponents.INPUT.key("ingredient");
    RecipeKey<Integer> MIN = NumberComponent.INT.key("min_growth");
    RecipeKey<Integer> MAX = NumberComponent.INT.key("max_growth");

    RecipeSchema SCHEMA = new RecipeSchema(ITEM, MIN, MAX);
}