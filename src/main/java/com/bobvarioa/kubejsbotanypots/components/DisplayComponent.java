package com.bobvarioa.kubejsbotanypots.components;

import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.recipe.RecipeJS;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponent;
import dev.latvian.mods.kubejs.util.MapJS;
import net.darkhax.botanypots.data.displaystate.DisplayState;

public class DisplayComponent implements RecipeComponent<DisplayState> {

    @Override
    public String componentType() {
        return "display_state";
    }

    @Override
    public Class<?> componentClass() {
        return DisplayState.class;
    }

    @Override
    public JsonElement write(RecipeJS recipe, DisplayState value) {
        return DisplayState.SERIALIZER.toJSON(value).getAsJsonObject().get("state");
    }

    @Override
    public DisplayState read(RecipeJS recipe, Object from) {
        return DisplayState.SERIALIZER.fromJSON(MapJS.json(from));
    }

    public static DisplayComponent ANY = new DisplayComponent();
}
