package com.bobvarioa.kubejsbotanypots.recipes;

import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SoilRecipeJS extends RecipeJS {
    private Ingredient input = null;
    private DisplayState display = null;
    private List<String> categories = new ArrayList<>();
    private int ticks = -1;
    private float growthModifier = -1;

    @Override
    public void create(RecipeArguments args) {
        // .create(input, display, [categories], ticks, growthModifier?)
        input = parseItemInput(args.get(0));
        display = DisplayStateUtil.deserializeDisplay(MapJS.json(args.get(1)));
        categories = new ArrayList<>();
        for (var ele : ListJS.json(args.get(2))) {
            categories.add(ele.getAsString());
        }
        ticks = args.getInt(3, -1);
        growthModifier = args.getInt(4, -1);
    }

    @Override
    public void deserialize() {
        input = parseItemInput(json.get("input"));
        display = DisplayStateUtil.deserializeDisplay(json.get("display"));
        categories = new ArrayList<>();
        for (var ele : ListJS.json(json.get("categories"))) {
            categories.add(ele.getAsString());
        }
        if (json.get("ticks") != null) {
            ticks = json.get("ticks").getAsInt();
        }
        if (json.get("growthModifier") != null) {
            growthModifier = json.get("growthModifier").getAsFloat();
        }
    }



    @Override
    public void serialize() {
        json.add("input", input.toJson());
        json.add("display", DisplayStateUtil.serializeDisplay(display));
        var cats = new JsonArray();
        for (String cat : categories) {
            cats.add(cat);
        }
        json.add("categories", cats);
        if (ticks != -1) {
            json.addProperty("ticks", ticks);
        }
        if (growthModifier != -1) {
            json.addProperty("growthModifier", growthModifier);
        }
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(input);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (input != null) {
            if (match.contains(input)) {
                input = transformer.transform(this, match, input, with);
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
