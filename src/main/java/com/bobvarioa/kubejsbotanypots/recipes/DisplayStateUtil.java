package com.bobvarioa.kubejsbotanypots.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darkhax.botanypots.data.displaystate.AgingDisplayState;
import net.darkhax.botanypots.data.displaystate.DisplayState;
import net.darkhax.botanypots.data.displaystate.SimpleDisplayState;
import net.darkhax.botanypots.data.displaystate.TransitionalDisplayState;

public class DisplayStateUtil {
    private DisplayStateUtil() {

    }
    public static DisplayState deserializeDisplay(JsonElement json) {
        if (json.isJsonObject()) {
            return DisplayState.SERIALIZER.fromJSON(json);
        } else if (json.isJsonPrimitive()) {
            var str = json.getAsString();

            return null;
        }
        return null;
    }

    public static JsonElement serializeDisplay(DisplayState block) {
        if (block instanceof SimpleDisplayState displayState) {
            var json = new JsonObject();
            var disp =  SimpleDisplayState.SERIALIZER.toJSON(displayState).getAsJsonObject();
            for (var entry : disp.get("state").getAsJsonObject().entrySet()) {
                json.add(entry.getKey(), entry.getValue());
            }

            var scale = disp.get("scale");
            if (scale != null) {
                json.add("scale", scale);
            }

            var rot = disp.get("rotation");
            if (rot != null) {
                json.add("rotation", rot);
            }

            var offset = disp.get("offset");
            if (offset != null) {
                json.add("offset", offset);
            }

            var renderFluid = disp.get("renderFluid");
            if (renderFluid != null) {
                json.addProperty("renderFluid", renderFluid.getAsBoolean());
            }

            return json;
        } else if (block instanceof AgingDisplayState displayState) {
            var json = new JsonObject();
            var disp =  AgingDisplayState.SERIALIZER.toJSON(displayState).getAsJsonObject();
            for (var entry : disp.get("block").getAsJsonObject().entrySet()) {
                json.add(entry.getKey(), entry.getValue());
            }
            json.addProperty("type", "botanypots:aging");
            return json;
        } else if (block instanceof TransitionalDisplayState displayState) {
            var json = new JsonObject();
            json.addProperty("type", "botanypots:transitional");
            var arr = new JsonArray();
            for (var ele : displayState.phases) {
                arr.add(DisplayStateUtil.serializeDisplay(ele));
            }
            json.add("phases", arr);
            return json;
        }
        return null;
    }
}
