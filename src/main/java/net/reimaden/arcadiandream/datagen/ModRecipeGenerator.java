/*
 * Copyright (c) 2022-2023 Maxmani and contributors.
 * Licensed under the EUPL-1.2 or later.
 */

package net.reimaden.arcadiandream.datagen;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.reimaden.arcadiandream.ArcadianDream;
import net.reimaden.arcadiandream.block.ModBlocks;
import net.reimaden.arcadiandream.datagen.builders.RitualCraftingRecipeJsonBuilder;
import net.reimaden.arcadiandream.datagen.providers.ModRecipeProvider;
import net.reimaden.arcadiandream.item.ModItems;
import net.reimaden.arcadiandream.util.ModTags;

import java.util.function.Consumer;

public class ModRecipeGenerator extends ModRecipeProvider {

    private static final ImmutableList<ItemConvertible> DRAGON_GEM_ORES =
            ImmutableList.of(ModItems.DRAGON_GEM_ORE, ModItems.DEEPSLATE_DRAGON_GEM_ORE, ModItems.END_STONE_DRAGON_GEM_ORE);
    public static final ImmutableList<ItemConvertible> MAKAITE_ORES =
            ImmutableList.of(ModItems.MAKAITE_ORE, ModItems.RAW_MAKAITE);

    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        // Shapeless Crafting recipes
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.POWER_ITEM, RecipeCategory.MISC, ModItems.BIG_POWER_ITEM);
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.POINT_ITEM, RecipeCategory.MISC, ModItems.MAX_POINT_ITEM);
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.STAR_ITEM, RecipeCategory.MISC, ModItems.FAITH_ITEM);
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.RAW_MAKAITE, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_MAKAITE_BLOCK);
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.MAKAITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModItems.MAKAITE_BLOCK);
        makeReversibleCompacting(exporter, RecipeCategory.MISC, ModItems.DRAGON_GEM, RecipeCategory.BUILDING_BLOCKS, ModItems.DRAGON_GEM_BLOCK);

        makeShapeless(exporter, ModItems.BOMB_ITEM, ModItems.EXTEND_ITEM, null, 3);

        // Shaped Crafting recipes
        makeTools(exporter, ModItems.MAKAITE_AXE, ModItems.MAKAITE_HOE, ModItems.MAKAITE_PICKAXE,
                ModItems.MAKAITE_SHOVEL, ModItems.MAKAITE_SWORD, ModItems.MAKAITE_INGOT);
        makeArmor(exporter, ModItems.MAKAITE_BOOTS, ModItems.MAKAITE_CHESTPLATE,
                ModItems.MAKAITE_HELMET, ModItems.MAKAITE_LEGGINGS, ModItems.MAKAITE_INGOT);

        makePatterns(exporter, ModItems.SPREAD_PATTERN_TEMPLATE, ModItems.SPREAD_PATTERN);
        makePatterns(exporter, ModItems.RAY_PATTERN_TEMPLATE, ModItems.RAY_PATTERN);
        makePatterns(exporter, ModItems.RING_PATTERN_TEMPLATE, ModItems.RING_PATTERN);
        makePatterns(exporter, ModItems.CONE_PATTERN_TEMPLATE, ModItems.CONE_PATTERN);
        makePatterns(exporter, ModItems.DOUBLE_PATTERN_TEMPLATE, ModItems.DOUBLE_PATTERN);
        makePatterns(exporter, ModItems.TRIPLE_PATTERN_TEMPLATE, ModItems.TRIPLE_PATTERN);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ORDINARY_HAT)
                .input('#', Items.BLACK_WOOL)
                .input('S', Items.STRING)
                .input('W', Items.WHITE_WOOL)
                .input('E', Items.ENDER_PEARL)
                .pattern(" # ")
                .pattern("SWS")
                .pattern("#E#")
                .criterion(RecipeProvider.hasItem(Items.ENDER_PEARL),
                        RecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.ONBASHIRA_PILLAR)
                .input('#', ItemTags.LOGS)
                .pattern("#")
                .pattern("#")
                .criterion("has_log", RecipeProvider.conditionsFromTag(ItemTags.LOGS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.RITUAL_SHRINE)
                .input('#', Blocks.AMETHYST_BLOCK)
                .input('D', ModItems.DRAGON_GEM)
                .pattern(" D ")
                .pattern("D#D")
                .pattern("###")
                .criterion(RecipeProvider.hasItem(Blocks.AMETHYST_BLOCK),
                        RecipeProvider.conditionsFromItem(Blocks.AMETHYST_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.DANMAKU_CRAFTING_TABLE)
                .input('#', ModTags.Items.BULLET_CORES)
                .input('C', Blocks.CRAFTING_TABLE)
                .pattern("###")
                .pattern("#C#")
                .pattern("###")
                .criterion("has_item", RecipeProvider.conditionsFromTag(ModTags.Items.BULLET_CORES))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CIRCLE_BULLET_CORE)
                .input('#', ModItems.STAR_ITEM)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ").criterion(RecipeProvider.hasItem(ModItems.STAR_ITEM),
                        RecipeProvider.conditionsFromItem(ModItems.STAR_ITEM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUBBLE_BULLET_CORE)
                .input('#', ModItems.STAR_ITEM)
                .pattern("###")
                .pattern("###")
                .pattern("###").criterion(RecipeProvider.hasItem(ModItems.STAR_ITEM),
                        RecipeProvider.conditionsFromItem(ModItems.STAR_ITEM))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.AMULET_BULLET_CORE)
                .input('#', ModItems.STAR_ITEM)
                .input('U', ModTags.Items.UNDEAD_PARTS)
                .pattern("UU")
                .pattern("##")
                .pattern("##").criterion(RecipeProvider.hasItem(ModItems.STAR_ITEM),
                        RecipeProvider.conditionsFromItem(ModItems.STAR_ITEM))
                .offerTo(exporter);

        // Smelting recipes
        makeSmelting(exporter, DRAGON_GEM_ORES, RecipeCategory.MISC, ModItems.DRAGON_GEM, 1.2f, 200,
                RecipeProvider.getItemPath(ModItems.DRAGON_GEM));
        makeSmelting(exporter, MAKAITE_ORES, RecipeCategory.MISC, ModItems.MAKAITE_INGOT, 0.8f, 200,
                RecipeProvider.getItemPath(ModItems.MAKAITE_INGOT));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModItems.DEATH_SCYTHE), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 200)
                .criterion(RecipeProvider.hasItem(ModItems.DEATH_SCYTHE),
                        RecipeProvider.conditionsFromItem(ModItems.DEATH_SCYTHE))
                .offerTo(exporter, new Identifier(ArcadianDream.MOD_ID, Items.IRON_NUGGET + "_from_smelting"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(ModItems.DEATH_SCYTHE), RecipeCategory.MISC, Items.IRON_NUGGET, 0.1f, 100)
                .criterion(RecipeProvider.hasItem(ModItems.DEATH_SCYTHE),
                        RecipeProvider.conditionsFromItem(ModItems.DEATH_SCYTHE))
                .offerTo(exporter, new Identifier(ArcadianDream.MOD_ID, Items.IRON_NUGGET + "_from_blasting"));

        // Blasting recipes
        makeBlasting(exporter, DRAGON_GEM_ORES, RecipeCategory.MISC, ModItems.DRAGON_GEM, 1.2f, 100,
                RecipeProvider.getItemPath(ModItems.DRAGON_GEM));
        makeBlasting(exporter, MAKAITE_ORES, RecipeCategory.MISC, ModItems.MAKAITE_INGOT, 0.8f, 100,
                RecipeProvider.getItemPath(ModItems.MAKAITE_INGOT));

        // Ritual Crafting recipes
        RitualCraftingRecipeJsonBuilder.create(ModItems.MAKAITE_INFUSED_NETHERITE_INGOT)
                .input(ModItems.MAKAITE_INGOT)
                .input(ConventionalItemTags.NETHERITE_INGOTS)
                .offerTo(exporter, ritualCraftingId(ModItems.MAKAITE_INFUSED_NETHERITE_INGOT));

        RitualCraftingRecipeJsonBuilder.create(ModItems.NUE_TRIDENT)
                .input(Items.TRIDENT)
                .input(ModItems.MAKAITE_INFUSED_NETHERITE_INGOT)
                .input(ConventionalItemTags.DIAMONDS)
                .input(Items.SPIDER_EYE)
                .input(Items.FIRE_CHARGE)
                .input(Items.SPIDER_EYE)
                .input(ConventionalItemTags.DIAMONDS)
                .input(ModItems.MAKAITE_INFUSED_NETHERITE_INGOT)
                .input(Items.PRISMARINE_CRYSTALS, 2)
                .offerTo(exporter, ritualCraftingId(ModItems.NUE_TRIDENT));

        RitualCraftingRecipeJsonBuilder.create(ModItems.MOCHI_MALLET)
                .input(Items.RABBIT_HIDE)
                .input(Items.STICK, 2)
                .input(Ingredient.fromTag(ItemTags.PLANKS), 3)
                .moonPhase(MoonPhases.FULL_MOON.getId())
                .offerTo(exporter, ritualCraftingId(ModItems.MOCHI_MALLET));

        RitualCraftingRecipeJsonBuilder.create(ModItems.WALL_PASSING_CHISEL)
                .input(Items.ENDER_PEARL)
                .input(ConventionalItemTags.GOLD_INGOTS)
                .input(Items.STICK)
                .input(ConventionalItemTags.GOLD_INGOTS)
                .offerTo(exporter, ritualCraftingId(ModItems.WALL_PASSING_CHISEL));

        RitualCraftingRecipeJsonBuilder.create(ModItems.IBUKI_GOURD)
                .input(Items.CHAIN)
                .input(ItemTags.TERRACOTTA)
                .input(Items.BUCKET)
                .input(Items.PAPER, 2)
                .offerTo(exporter, ritualCraftingId(ModItems.IBUKI_GOURD));

        RitualCraftingRecipeJsonBuilder.create(ModItems.HISOU_SWORD)
                .input(Items.BLAZE_ROD)
                .input(ModItems.DRAGON_GEM, 2)
                .offerTo(exporter, ritualCraftingId(ModItems.HISOU_SWORD));

        RitualCraftingRecipeJsonBuilder.create(ModItems.DEATH_SCYTHE)
                .input(Items.STICK, 3)
                .input(Ingredient.fromTag(ConventionalItemTags.IRON_INGOTS), 5)
                .input(Ingredient.fromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS), 8)
                .dimension(Dimensions.THE_NETHER.getId())
                .offerTo(exporter, ritualCraftingId(ModItems.DEATH_SCYTHE));

        RitualCraftingRecipeJsonBuilder.create(ModItems.HOURAI_ELIXIR)
                .input(Items.NETHER_STAR)
                .input(Items.ENCHANTED_GOLDEN_APPLE)
                .input(Items.NETHER_STAR)
                .input(Items.ENCHANTED_GOLDEN_APPLE)
                .input(Items.NETHER_STAR)
                .input(Items.ENCHANTED_GOLDEN_APPLE)
                .input(Items.NETHER_STAR)
                .input(Items.ENCHANTED_GOLDEN_APPLE)
                .dimension(Dimensions.THE_END.getId())
                .offerTo(exporter, ritualCraftingId(ModItems.HOURAI_ELIXIR));

        RitualCraftingRecipeJsonBuilder.create(ModItems.MIRACLE_MALLET)
                .input(Items.NETHER_STAR)
                .input(Blocks.GOLD_BLOCK)
                .input(Blocks.EMERALD_BLOCK)
                .input(Blocks.REDSTONE_BLOCK)
                .input(Items.BLAZE_ROD)
                .input(Blocks.REDSTONE_BLOCK)
                .input(Blocks.EMERALD_BLOCK)
                .input(Blocks.GOLD_BLOCK)
                .input(Blocks.GOLD_BLOCK)
                .input(Blocks.GOLD_BLOCK)
                .offerTo(exporter, ritualCraftingId(ModItems.MIRACLE_MALLET));
    }
}
