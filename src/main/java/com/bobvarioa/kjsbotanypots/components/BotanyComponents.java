package com.bobvarioa.kjsbotanypots.components;

import dev.latvian.mods.kubejs.recipe.component.SimpleRecipeComponent;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.darkhax.botanypots.common.api.data.display.types.DisplayType;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmount;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmountType;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProvider;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProviderType;

public class BotanyComponents {
    public static DisplayComponent DISPLAY = new DisplayComponent();
    public static DropProviderComponent DROP = new DropProviderComponent();
    public static GrowthAmountComponent GROWTH = new GrowthAmountComponent();

    public static class DisplayComponent extends SimpleRecipeComponent<Display> {
        public DisplayComponent() {
            super("block_display", DisplayType.DISPLAY_STATE_CODEC, TypeInfo.of(Display.class));
        }

    }

    public static class DropProviderComponent extends SimpleRecipeComponent<ItemDropProvider> {
        public DropProviderComponent() {
            super("drop", ItemDropProviderType.DROP_PROVIDER_CODEC, TypeInfo.of(ItemDropProvider.class));
        }
    }


    public static class GrowthAmountComponent extends SimpleRecipeComponent<GrowthAmount> {
        public GrowthAmountComponent() {
            super("growth", GrowthAmountType.GROWTH_AMOUNT_CODEC, TypeInfo.of(GrowthAmount.class));
        }
    }
}
