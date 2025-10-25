package com.bobvarioa.kjsbotanypots;

import com.bobvarioa.kjsbotanypots.components.BotanyComponents;
import com.bobvarioa.kjsbotanypots.recipes.CropRecipeJS;
import com.bobvarioa.kjsbotanypots.recipes.FertilizerRecipeJS;
import com.bobvarioa.kjsbotanypots.recipes.SoilRecipeJS;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.component.RecipeComponentTypeRegistry;
import dev.latvian.mods.kubejs.recipe.schema.RecipeSchemaRegistry;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import net.darkhax.botanypots.common.api.data.display.types.Display;
import net.darkhax.botanypots.common.api.data.growthamount.GrowthAmount;
import net.darkhax.botanypots.common.api.data.itemdrops.ItemDropProvider;
import net.darkhax.botanypots.common.impl.data.display.types.*;
import net.darkhax.botanypots.common.impl.data.growthamount.ConstantGrowthAmount;
import net.darkhax.botanypots.common.impl.data.growthamount.PercentageGrowthAmount;
import net.darkhax.botanypots.common.impl.data.growthamount.RangedGrowthAmount;
import net.darkhax.botanypots.common.impl.data.itemdrops.BlockDrops;
import net.darkhax.botanypots.common.impl.data.itemdrops.EntityDrops;
import net.darkhax.botanypots.common.impl.data.itemdrops.LootTableDrops;
import net.darkhax.botanypots.common.impl.data.itemdrops.SimpleDropProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KJSBotanyPotsPlugin implements KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RecipeSchemaRegistry event) {
        event.register(ResourceLocation.fromNamespaceAndPath("botanypots","soil"), SoilRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("botanypots","crop"), CropRecipeJS.SCHEMA);
        event.register(ResourceLocation.fromNamespaceAndPath("botanypots","fertilizer"), FertilizerRecipeJS.SCHEMA);
    }

    @Override
    public void registerRecipeComponents(RecipeComponentTypeRegistry registry) {
        registry.register(BotanyComponents.DISPLAY);
        registry.register(BotanyComponents.GROWTH);
        registry.register(BotanyComponents.DROP);
    }

    @Override
    public void registerBindings(BindingRegistry bindings) {
        bindings.add("DisplayState", DisplayStateBinding.class);
        bindings.add("DisplayStateOptions", BasicOptions.class);
        bindings.add("DropItem", DropItemBinding.class);
        bindings.add("GrowthAmount", GrowthAmountBinding.class);
    }

    public static class DisplayStateBinding {
        public static Display basic(BlockState state, BasicOptions options) {
            return new SimpleDisplayState(state, options);
        }

        public static Display basic(BlockState state) {
            return new SimpleDisplayState(state, BasicOptions.ofDefault());
        }

        public static Display aging(Block block, BasicOptions options) {
            return new AgingDisplayState(block, options);
        }

        public static Display aging(Block block) {
            return new AgingDisplayState(block, BasicOptions.ofDefault());
        }

        public static Display entity(CompoundTag displayEntity, boolean shouldTick, int spinSpeed, Vector3f scale, Vector3f offset) {
            return new EntityDisplayState(displayEntity, shouldTick, spinSpeed, scale, Optional.of(offset));
        }

        public static Display entity(CompoundTag displayEntity, boolean shouldTick, int spinSpeed, Vector3f scale) {
            return new EntityDisplayState(displayEntity, shouldTick, spinSpeed, scale, Optional.empty());
        }

        public static Display entity(CompoundTag displayEntity, boolean shouldTick, int spinSpeed) {
            return new EntityDisplayState(displayEntity, shouldTick, spinSpeed, new Vector3f(1,1,1), Optional.empty());
        }

        public static Display entity(CompoundTag displayEntity, boolean shouldTick) {
            return new EntityDisplayState(displayEntity, shouldTick, 0, new Vector3f(1,1,1), Optional.empty());
        }

        public static Display entity(CompoundTag displayEntity) {
            return new EntityDisplayState(displayEntity, true, 0, new Vector3f(1,1,1), Optional.empty());
        }

        public static Display entity(EntityType<?> entityType) {
            var tag = new CompoundTag();
            tag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());
            return new EntityDisplayState(tag, true, 0, new Vector3f(1,1,1), Optional.empty());
        }

        public static Display transitional(List<Display> phases) {
            return new TransitionalDisplayState(phases);
        }
    }

    public static class DropItemBinding {
        public static ItemDropProvider item(ItemStack stack, float chance) {
            var arr = new ArrayList<SimpleDropProvider.SimpleDrop>();
            arr.add(new SimpleDropProvider.SimpleDrop(stack, chance));
            return new SimpleDropProvider(arr);
        }

        public static ItemDropProvider blockDrops(BlockState state) {
            return new BlockDrops(state.getBlock(), state);
        }

        public static ItemDropProvider entity(EntityType<?> entityType) {
            var tag = new CompoundTag();
            tag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(entityType).toString());
            return new EntityDrops(tag, Optional.empty());
        }

        public static ItemDropProvider entity(CompoundTag tag) {
            return new EntityDrops(tag, Optional.empty());
        }

        public static ItemDropProvider entity(CompoundTag tag, Holder<DamageType> type) {
            return new EntityDrops(tag, Optional.of(type));
        }

        public static ItemDropProvider lootTable(ResourceLocation location) {
            return new LootTableDrops(location);
        }
    }

    public static class GrowthAmountBinding {
        public static GrowthAmount constant(int growth) {
            return new ConstantGrowthAmount(growth);
        }
        public static GrowthAmount range(int minGrowth, int maxGrowth) {
            return new RangedGrowthAmount(minGrowth, maxGrowth);
        }

        public static GrowthAmount percent(float percentage) {
            return new PercentageGrowthAmount(percentage);
        }
    }
}
