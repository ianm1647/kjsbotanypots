package com.bobvarioa.kjsbotanypots.recipes;

import com.bobvarioa.kjsbotanypots.components.BotanyComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.*;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProvider;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;


public interface CropRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.INGREDIENT.key("input", ComponentRole.INPUT);
    RecipeKey<Ingredient> SOIL = IngredientComponent.INGREDIENT.key("soil", ComponentRole.INPUT);
    RecipeKey<List<Display>> DISPLAY = BotanyComponents.DISPLAY.asListOrSelf().key("display", ComponentRole.OTHER);
    RecipeKey<List<ItemDropProvider>> DROPS = BotanyComponents.DROP.asList().key("drops", ComponentRole.OUTPUT);
    RecipeKey<Long> GROWTH_TIME = NumberComponent.LONG.key("grow_time", ComponentRole.OTHER);
    RecipeKey<Float> YIELD = NumberComponent.FLOAT.key("yield", ComponentRole.OTHER).optional(0.0f);


    RecipeSchema SCHEMA = new RecipeSchema(INPUT, SOIL, DISPLAY, DROPS, GROWTH_TIME, YIELD);
}
