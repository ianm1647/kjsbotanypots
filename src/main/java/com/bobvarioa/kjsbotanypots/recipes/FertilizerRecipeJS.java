package com.bobvarioa.kjsbotanypots.recipes;

import com.bobvarioa.kjsbotanypots.components.BotanyComponents;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.recipe.component.ComponentRole;
import dev.latvian.mods.kubejs.recipe.component.IngredientComponent;
import dev.latvian.mods.kubejs.recipe.component.NumberComponent;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchema;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmount;
import net.minecraft.world.item.crafting.Ingredient;

public interface FertilizerRecipeJS {
    RecipeKey<Ingredient> ITEM = IngredientComponent.INGREDIENT.key("held_item", ComponentRole.INPUT);
    RecipeKey<GrowthAmount> GROWTH = BotanyComponents.GROWTH.key("growth", ComponentRole.OTHER);


    RecipeSchema SCHEMA = new RecipeSchema(ITEM, GROWTH);
}