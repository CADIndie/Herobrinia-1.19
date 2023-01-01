package me.sirlennox.herobrinia;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.blocks.HerobrineBlock;
import me.sirlennox.herobrinia.blocks.HerobriniaBlock;
import me.sirlennox.herobrinia.blocks.NetherHerobrineNuggetOre;
import me.sirlennox.herobrinia.effects.InvulnerableEffect;
import me.sirlennox.herobrinia.effects.RandomTPEffect;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrine;
import me.sirlennox.herobrinia.entities.herobrinepiglin.EntityHerobrinePiglin;
import me.sirlennox.herobrinia.items.*;
import me.sirlennox.herobrinia.items.herobrineequip.*;
import me.sirlennox.herobrinia.items.materials.HerobriniaArmorMaterial;
import me.sirlennox.herobrinia.items.materials.HerobriniaToolMaterial;
import me.sirlennox.herobrinia.mixins.BrewingRecipeRegistryMixin;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.*;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main implements ModInitializer {

    public static final EntityType<EntityHerobrine> HEROBRINE_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("herobrinia", "herobrine"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, EntityHerobrine::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );

    public static final EntityType<EntityHerobrinePiglin> HEROBRINE_PIGLIN_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("herobrinia", "herobrine_piglin"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, EntityHerobrinePiglin::new).fireImmune().trackRangeBlocks(10).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "herobrinia";
    public static final String MOD_NAME = "Herobrinia";
    public static final Random rndm = new Random();
    public static long herobrineAttackDelay = 10000L;


    public static final ItemGroup HEROBRINIA_GROUP = BetterFabricItemGroupBuilder.create(
            new Identifier("herobrinia", "herobrinia"), "§cHerobrinia")
            .icon(() -> new ItemStack(Blocks.NETHERITE_BLOCK))
            .build();


    //Materials
    public static final ToolMaterial HEROBRINIA_TOOL_MATERIAL = new HerobriniaToolMaterial();
    public static final ArmorMaterial HEROBRINIA_ARMOR_MATERIAL = new HerobriniaArmorMaterial();

    //Items
    public static final Item HEROBRINE_APPLE = new HerobrineApple();
    public static final Item HAND_OF_HEROBRINE = new HandOfHerobrine();
    public static final Item HAND_OF_NOTCH = new HandOfNotch();
    public static final Item MULTI_HAND_OF_HEROBRINE_AND_NOTCH = new MultiHandOfHerobrineAndNotch();
    public static final Item HEROBRINE_ROSE = new HerobrineRose();
    public static final Item MAGIC_FLINT_AND_STEEL = new MagicFlintAndSteel();
    public static final Item LIGHTNING_STICK = new LightningStick(new Item.Settings());
    public static final Item HEROBRINE_SWORD = new HerobrineSword();
    public static final Item HEROBRINE_BOW = new HerobrineBow();
    public static final Item HEROBRINE_ARROW = new HerobrineArrow();
    public static final Item HEROBRINE_PICKAXE = new HerobrinePickaxe();
    public static final Item HEROBRINE_AXE = new HerobrineAxe();
    public static final Item HEROBRINE_SHOVEL = new HerobrineShovel();
    public static final Item HEROBRINE_SHEARS = new HerobrineShears();
    public static final Item HEROBRINE_HOE = new HerobrineHoe();
    public static final Item HEROBRINE_INGOT = new HerobrineIngot();
    public static final Item HEROBRINE_NUGGET = new HerobrineNugget();
    public static final Item HEROBRINE_TOTEM = new HerobrineTotem();


    //Armor
    public static final Item HEROBRINE_HELMET = new HerobrineHelmet();
    public static final Item HEROBRINE_CHESTPLATE = new HerobrineChestplate();
    public static final Item HEROBRINE_LEGGINGS = new HerobrineLeggings();
    public static final Item HEROBRINE_BOOTS = new HerobrineBoots();

    //Blocks
    public static final HerobriniaBlock HEROBRINE_BLOCK = new HerobrineBlock();
    public static final HerobriniaBlock NEHTER_HEROBRINE_NUGGET_ORE = new NetherHerobrineNuggetOre();

    //Ores
    private static final ConfiguredFeature<?, ?> ORE_HEROBRINE_NUGGET_NETHER_SMALL = Feature.ORE.generate(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, NEHTER_HEROBRINE_NUGGET_ORE.getDefaultState(), 2)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(8, 16, 128))).spreadHorizontally();
    private static final ConfiguredFeature<?, ?> ORE_HEROBRINE_NUGGET_NETHER_LARGE = Feature.ORE.generate(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, NEHTER_HEROBRINE_NUGGET_ORE.getDefaultState(), 3)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(16, 8))).spreadHorizontally();


    //Effects
    public static final StatusEffect INVULNERABLE_EFFECT = new InvulnerableEffect();
    public static final StatusEffect RANDOMTP_EFFECT = new RandomTPEffect();

    public static AttackRegistry attackRegistry;

    public static Identifier createIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    public static Main instance;


    // public static final RegistryKey<Biome> HEROBRINIA_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("herobrinia", "herobrinia"));
    @Override
    public void onInitialize() {
        instance = this;
        log(Level.INFO, "Initializing");
        register();


    }

    public static final File DIR = new File("Herobrinia");
    public static final File CONFIG = new File(DIR, "config.json");

    public void register() {
        //Init AttackRegistry
        log(Level.INFO, "Loading attacks...");
        Main.attackRegistry = new AttackRegistry();
        Main.attackRegistry.init();

        log(Level.INFO,"Attacks found: ");
        Main.attackRegistry.REGISTERED.forEach(r -> log(Level.INFO, "- " + r.name));

        if(!DIR.exists()) {
            log(Level.INFO,"Directory 'Herobrinia' not found, creating...");
            DIR.mkdir();
        }
        if(!CONFIG.exists()) {
            log(Level.INFO,"Config 'Herobrinia/config.json' not found, creating...");
            try {
                CONFIG.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter fw = new FileWriter(CONFIG);
                JsonObject d = new JsonObject();
                d.add("disabledAttacks", new JsonArray());
                fw.write(d.toString());
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                JsonElement je = new JsonParser().parse(new JsonReader(new FileReader(CONFIG)));
                if(je instanceof JsonNull) {
                    log(Level.ERROR, "Failed parsing config! Try to fix it or delete it!");
                }else {
                    JsonObject obj = (JsonObject) je;
                    JsonArray array = obj.getAsJsonArray("disabledAttacks");
                    array.forEach(d -> {
                        Attack a = Main.attackRegistry.getByName(d.getAsString());
                        if (a == null) {
                            log(Level.ERROR, "Could not disable Attack '" + d.getAsString() + "' (not found)");
                            return;
                        }
                        Main.attackRegistry.unregister(a);
                        log(Level.INFO,"Disabled attack '" + a.name + "'");
                    });
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        //Register Entities
        log(Level.INFO, "Registering Entities...");
        FabricDefaultAttributeRegistry.register(HEROBRINE_ENTITY_TYPE, EntityHerobrine.createMobAttributes());
        FabricDefaultAttributeRegistry.register(HEROBRINE_PIGLIN_ENTITY_TYPE, EntityHerobrinePiglin.createMobAttributes());


        log(Level.INFO, "Registering Items...");

        //Register items
        register(createIdentifier("herobrine_apple"), HEROBRINE_APPLE);
        register(createIdentifier("hand_of_herobrine"), HAND_OF_HEROBRINE);
        register(createIdentifier("hand_of_notch"), HAND_OF_NOTCH);
        register(createIdentifier("multi_hand_of_herobrine_and_notch"), MULTI_HAND_OF_HEROBRINE_AND_NOTCH);
        register(createIdentifier("magic_flint_and_steel"), MAGIC_FLINT_AND_STEEL);
        register(createIdentifier("lightning_stick"), LIGHTNING_STICK);
        register(createIdentifier("herobrine_sword"), HEROBRINE_SWORD);
        register(createIdentifier("herobrine_totem"), HEROBRINE_TOTEM);
        register(createIdentifier("herobrine_pickaxe"), HEROBRINE_PICKAXE);
        register(createIdentifier("herobrine_axe"), HEROBRINE_AXE);
        register(createIdentifier("herobrine_shovel"), HEROBRINE_SHOVEL);
        register(createIdentifier("herobrine_hoe"), HEROBRINE_HOE);
        register(createIdentifier("herobrine_shears"), HEROBRINE_SHEARS);
        register(createIdentifier("herobrine_arrow"), HEROBRINE_ARROW);
        register(createIdentifier("herobrine_helmet"), HEROBRINE_HELMET);
        register(createIdentifier("herobrine_chestplate"), HEROBRINE_CHESTPLATE);
        register(createIdentifier("herobrine_leggings"), HEROBRINE_LEGGINGS);
        register(createIdentifier("herobrine_boots"), HEROBRINE_BOOTS);
        register(createIdentifier("herobrine_bow"), HEROBRINE_BOW);
        register(createIdentifier("herobrine_ingot"), HEROBRINE_INGOT);
        register(createIdentifier("herobrine_nugget"), HEROBRINE_NUGGET);
        register(createIdentifier("herobrine_rose"), HEROBRINE_ROSE);
        register(createIdentifier("herobrine_spawn_egg"), new SpawnEggItem(HEROBRINE_ENTITY_TYPE, 10051392, 0xFFFFFF, new Item.Settings()) {
            @Override
            public Text getName() {
                return Text.literal("Herobrine Spawn Egg");
            }

            @Override
            public Text getName(ItemStack stack) {
                return this.getName();
            }
        });
        register(createIdentifier("herobrine_piglin_spawn_egg"), new SpawnEggItem(HEROBRINE_PIGLIN_ENTITY_TYPE, 0xA52A2A, 0xFFFFFF, new Item.Settings()) {
            @Override
            public Text getName() {
                return Text.literal("Herobrine Piglin Spawn Egg");
            }

            @Override
            public Text getName(ItemStack stack) {
                return this.getName();
            }
        });
        log(Level.INFO, "Registering Blocks...");
        //Register blocks
        register(createIdentifier("herobrine_block"), HEROBRINE_BLOCK);
        register(createIdentifier("nether_herobrine_nugget_ore"), NEHTER_HEROBRINE_NUGGET_ORE);

        log(Level.INFO, "Initializing Ores...");
        Registry.register(Registries.FEATURE,  createIdentifier("ore_herobrine_nugget_nether_small"), ORE_HEROBRINE_NUGGET_NETHER_SMALL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,  createIdentifier("ore_herobrine_nugget_nether_large"), ORE_HEROBRINE_NUGGET_NETHER_LARGE);

        RegistryKey<ConfiguredFeature<?, ?>> oreHerobrineNuggetNetherSmall = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,
                createIdentifier("ore_herobrine_nugget_nether_small"));
        RegistryKey<ConfiguredFeature<?, ?>> oreHerobrineNuggetNetherLarge = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,
                createIdentifier("ore_herobrine_nugget_nether_large"));


        //Register Effects
        log(Level.INFO, "Initializing Effects...");

        register(createIdentifier("invulnerable"), INVULNERABLE_EFFECT);
        register(createIdentifier("randomtp"), RANDOMTP_EFFECT);

        //Register Potions
        log(Level.INFO, "Initializing Potions...");

        Potion invulnerablePotion = registerPotion(createIdentifier("invulnerable"), INVULNERABLE_EFFECT, 30 * 20, 0);
    //   registerPotion(createIdentifier("invulnerable_long"), INVULNERABLE_EFFECT, 60 * 20, 0);
        Potion randomTpPotion = registerPotion(createIdentifier("randomtp"), RANDOMTP_EFFECT, 1, 0);

        BrewingRecipeRegistryMixin.registerPotionRecipe(Potions.AWKWARD, Items.CHORUS_FRUIT, randomTpPotion);
        BrewingRecipeRegistryMixin.registerPotionRecipe(Potions.AWKWARD, HEROBRINE_TOTEM, invulnerablePotion);
        //Register Biome Modifications
        log(Level.INFO, "Initializing Biome Modifications...");

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, oreHerobrineNuggetNetherSmall);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, oreHerobrineNuggetNetherLarge);
        BiomeModifications.addSpawn(BiomeSelectors.foundInTheNether(), SpawnGroup.CREATURE, HEROBRINE_PIGLIN_ENTITY_TYPE, 5, 1, 2);
    }
/*
    public void registerConfiguredFeature(String id, ConfiguredFeature<?, ?> configuredFeature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }*/




    public static void register(Identifier id, StatusEffect entry) {
         Registry.register(Registries.STATUS_EFFECT, id, entry);
    }

    public static Potion registerPotion(Identifier id, StatusEffect entry, int duration, int amplifier) {
        return registerPotion(id, new Potion(/*id.getPath(),*/ new StatusEffectInstance(entry, duration, amplifier)));
    }

    public static Potion registerPotion(Identifier id, StatusEffectInstance... effects) {
        return registerPotion(id, new Potion(/*id.getPath(),*/ effects));
    }

    public static Potion registerPotion(Identifier id, StatusEffectInstance effect) {
        return registerPotion(id, new StatusEffectInstance[] {effect});
    }

    public static Potion registerPotion(Identifier id, StatusEffect effect) {
        return registerPotion(id, effect, 30 * 20, 0);
    }

    public static Potion registerPotion(Identifier id, Potion potion) {
        return Registry.register(Registries.POTION, id, potion);
    }

    public static void register(Identifier identifier, HerobriniaBlock b) {
        Registry.register(Registries.BLOCK, identifier, b);
        Registry.register(Registries.ITEM, identifier, new BlockItem(b, new Item.Settings()) {
            @Override
            public Text getName() {
                return b.getItemName();
            }

            @Override
            public Text getName(ItemStack stack) {
                return this.getName();
            }

            @Override
            public boolean isFireproof() {
                return b.isFireproof();
            }
        });
    }

    public static void register(Identifier identifier, Item i) {
        Registry.register(Registries.ITEM, identifier, i);
    }



    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}