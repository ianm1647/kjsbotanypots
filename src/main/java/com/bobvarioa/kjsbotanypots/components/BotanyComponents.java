package com.bobvarioa.kjsbotanypots.components;

import dev.latvian.mods.kubejs.recipe.component.RecipeComponentType;
import dev.latvian.mods.kubejs.recipe.component.SimpleRecipeComponent;
import dev.latvian.mods.rhino.type.TypeInfo;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.darkhax.botanypots.common.api.data.display.types.DisplayType;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmount;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmountType;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProvider;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProviderType;
import net.minecraft.resources.ResourceLocation;

public class BotanyComponents {
    public static RecipeComponentType<Display> DISPLAY = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("botanypots", "block_display"), DisplayComponent::new);
    public static RecipeComponentType<ItemDropProvider> DROP = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("botanypots", "drop"), DropProviderComponent::new);
    public static RecipeComponentType<GrowthAmount> GROWTH = RecipeComponentType.unit(ResourceLocation.fromNamespaceAndPath("botanypots", "growth"), GrowthAmountComponent::new);

    public static class DisplayComponent extends SimpleRecipeComponent<Display> {
        public DisplayComponent(RecipeComponentType<?> type) {
            super(type, DisplayType.DISPLAY_STATE_CODEC, TypeInfo.of(Display.class));
        }

    }

    public static class DropProviderComponent extends SimpleRecipeComponent<ItemDropProvider> {
        public DropProviderComponent(RecipeComponentType<?> type) {
            super(type, ItemDropProviderType.DROP_PROVIDER_CODEC, TypeInfo.of(ItemDropProvider.class));
        }
    }


    public static class GrowthAmountComponent extends SimpleRecipeComponent<GrowthAmount> {
        public GrowthAmountComponent(RecipeComponentType<?> type) {
            super(type, GrowthAmountType.GROWTH_AMOUNT_CODEC, TypeInfo.of(GrowthAmount.class));
        }
    }
}
