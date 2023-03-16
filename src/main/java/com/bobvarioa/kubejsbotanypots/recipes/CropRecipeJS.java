package com.bobvarioa.kubejsbotanypots.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.recipe.*;
import dev.latvian.mods.kubejs.util.ListJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CropRecipeJS extends RecipeJS {
    public Ingredient seed = null;
    public List<String> categories = null;
    public int growthTicks = 1;
    public float growthModifier = 1;
    public DisplayState display = null;
    public List<LootEntry> results = null;

    class LootEntry {
        float chance;
        ItemStack output;
        int minRolls;
        int maxRolls;

        public LootEntry(float chance, ItemStack output, int minRolls, int maxRolls) {
            this.chance = chance;
            this.output = output;
            this.minRolls = minRolls;
            this.maxRolls = maxRolls;
        }

        JsonObject seralize() {
            var j = new JsonObject();
            j.addProperty("chance", chance);
            j.add("output", itemToJson(output));
            j.addProperty("minRolls", minRolls);
            j.addProperty("maxRolls", maxRolls);
            return j;
        }

        boolean hasOutput(IngredientMatch match) {
            return match.contains(output);
        }
    }

    @Override
    public void create(RecipeArguments args) {
        // .create(seed, [categories], growthTicks, growthModifier, display, [results])
        seed = parseItemInput(args.get(0));
        categories = new ArrayList<>();
        for (var ele : ListJS.of(args.get(1))) {
            categories.add(ele.toString());
        }
        growthTicks = args.getInt(2, 1);
        growthModifier = args.getFloat(3, 0);
        display = DisplayStateUtil.deserializeDisplay(MapJS.json(args.get(4)));
        results = new ArrayList<>();
        for (var res : ListJS.of(args.get(5))) {
            results.add(deserializeLoot(MapJS.json(res)));
        }
    }

    @Override
    public void deserialize() {
        seed = parseItemInput(json.get("seed"));
        var jsonCats = json.getAsJsonArray("categories");
        categories = new ArrayList<>();
        for (var jcat : jsonCats) {
            categories.add(jcat.getAsString());
        }
        growthTicks = json.get("growthTicks").getAsInt();
        var mod = json.get("growthModifier");
        if (mod != null) {
            growthModifier = mod.getAsFloat();
        }
        display = DisplayStateUtil.deserializeDisplay(json.get("display")); // todo
        results = new ArrayList<>();
        var jsonRes = json.getAsJsonArray("drops");
        for (var jres : jsonRes) {
            results.add(deserializeLoot(jres.getAsJsonObject()));
        }
    }

    protected LootEntry deserializeLoot(JsonObject j) {
        var jMinRolls = 1;
        if (j.get("minRolls") != null) {
            jMinRolls = j.get("minRolls").getAsInt();
        }
        var jMaxRolls = 1;
        if (j.get("maxRolls") != null) {
            jMaxRolls = j.get("maxRolls").getAsInt();
        }
        return new LootEntry(
            j.get("chance").getAsFloat(),
            parseItemOutput(j.get("output")),
            jMinRolls,
            jMaxRolls
        );
    }

    @Override
    public void serialize() {
        json.add("seed", seed.toJson());
        var cats = new JsonArray();
        for (var cat : categories) {
            cats.add(cat);
        }
        json.add("categories", cats);
        json.addProperty("growthTicks", growthTicks);
        json.addProperty("growthModifier", growthModifier);
        json.add("display", DisplayStateUtil.serializeDisplay(display));
        var jresults = new JsonArray();
        for (var res : results) {
            jresults.add(res.seralize());
        }
        json.add("drops", jresults);
    }

    @Override
    public boolean hasInput(IngredientMatch match) {
        return match.contains(seed);
    }

    @Override
    public boolean replaceInput(IngredientMatch match, Ingredient with, ItemInputTransformer transformer) {
        if (seed != null) {
            if (match.contains(seed)) {
                seed = transformer.transform(this, match, seed, with);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasOutput(IngredientMatch match) {
        for (var res : results) {
            if (res.hasOutput(match)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean replaceOutput(IngredientMatch match, ItemStack with, ItemOutputTransformer transformer) {
        for (var res : results) {
            if (res.hasOutput(match)) {
                res.output = transformer.transform(this, match, res.output, with);
                return true;
            }
        }
        return false;
    }
}
