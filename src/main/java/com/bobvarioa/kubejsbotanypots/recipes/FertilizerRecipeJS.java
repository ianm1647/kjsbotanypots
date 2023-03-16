package com.bobvarioa.kubejsbotanypots.recipes;

import dev.latvian.mods.kubejs.recipe.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class FertilizerRecipeJS extends RecipeJS {
    private Ingredient fertilizerItem = null;
    private int minGrowth = 0;
    private int maxGrowth = 0;

    @Override
    public void create(RecipeArguments args) {
        // .create(item, min_growth, max_growth)
        fertilizerItem = parseItemInput(args.get(0));
        minGrowth = args.getInt(1, 0);
        maxGrowth = args.getInt(2, 0);
    }

    @Override
    public void deserialize() {
        fertilizerItem = parseItemInput(json.get("ingredient"));
        if (json.get("min_growth") != null) {
            minGrowth = json.get("min_growth").getAsInt();
        }
        if (json.get("max_growth") != null) {
            maxGrowth = json.get("max_growth").getAsInt();
        }
    }

    @Override
    public void serialize() {
        json.add("ingredient", fertilizerItem.toJson());
        json.addProperty("min_growth", minGrowth);
        json.addProperty("max_growth", maxGrowth);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(fertilizerItem);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (fertilizerItem != null) {
            if (match.contains(fertilizerItem)) {
                fertilizerItem = transformer.transform(this, match, fertilizerItem, with);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        return false;
    }
}
