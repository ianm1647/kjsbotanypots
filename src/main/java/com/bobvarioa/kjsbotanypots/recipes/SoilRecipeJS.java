package com.bobvarioa.kjsbotanypots.recipes;

import com.bobvarioa.kjsbotanypots.components.BotanyComponents;
import dev.latvian.mods.kubejs.recipe.RecipeKey;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.minecraft.world.item.crafting.Ingredient;

public interface SoilRecipeJS {
    RecipeKey<Ingredient> INPUT = IngredientComponent.INGREDIENT.key("input", ComponentRole.INPUT);
    RecipeKey<Display> DISPLAY = BotanyComponents.DISPLAY.key("display", ComponentRole.OTHER);
    RecipeKey<Float> GROWTH_MODIFIER = NumberComponent.FLOAT.key("growth_modifier", ComponentRole.OTHER).optional(1f);
    RecipeKey<Float> YIELD_MODIFIER = NumberComponent.FLOAT.key("yield_modifier", ComponentRole.OTHER).optional(1f);

    RecipeSchema SCHEMA = new RecipeSchema(INPUT, DISPLAY, GROWTH_MODIFIER, YIELD_MODIFIER);
}