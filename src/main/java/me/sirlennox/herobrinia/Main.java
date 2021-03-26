package me.sirlennox.herobrinia;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import me.sirlennox.herobrinia.attack.Attack;
import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.blocks.HerobrineBlock;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrine;
import me.sirlennox.herobrinia.items.*;
import me.sirlennox.herobrinia.items.herobrineequip.*;
import me.sirlennox.herobrinia.items.materials.HerobriniaArmorMaterial;
import me.sirlennox.herobrinia.items.materials.HerobriniaToolMaterial;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
            Registry.ENTITY_TYPE,
            new Identifier("herobrinia", "herobrine"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, EntityHerobrine::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
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
    public static final Item LIGHTNING_STICK = new LightningStick();
    public static final Item HEROBRINE_SWORD = new HerobrineSword();
    public static final Item HEROBRINE_BOW = new HerobrineBow();
    public static final Item HEROBRINE_ARROW = new HerobrineArrow();
    public static final Item HEROBRINE_PICKAXE = new HerobrinePickaxe();
    public static final Item HEROBRINE_AXE = new HerobrineAxe();
    public static final Item HEROBRINE_SHOVEL = new HerobrineShovel();
    public static final Item HEROBRINE_SHEARS = new HerobrineShears();
    public static final Item HEROBRINE_HOE = new HerobrineHoe();
    public static final Item HEROBRINE_INGOT = new HerobrineIngot();

    //Armor
    public static final Item HEROBRINE_HELMET = new HerobrineHelmet();
    public static final Item HEROBRINE_CHESTPLATE = new HerobrineChestplate();
    public static final Item HEROBRINE_LEGGINGS = new HerobrineLeggings();
    public static final Item HEROBRINE_BOOTS = new HerobrineBoots();

    //Blocks
    public static final Block HEROBRINE_BLOCK = new HerobrineBlock();
    public static AttackRegistry attackRegistry;

    public static Identifier createIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    // public static final RegistryKey<Biome> HEROBRINIA_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("herobrinia", "herobrinia"));
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        register();


    }

    public static final File DIR = new File("Herobrinia");
    public static final File CONFIG = new File(DIR, "config.json");

    public void register() {
        //Init AttackRegistry
        System.out.println("Loading attacks...");
        Main.attackRegistry = new AttackRegistry();
        Main.attackRegistry.init();

        System.out.println("Attacks found: ");
        Main.attackRegistry.REGISTERED.forEach(r -> System.out.println("- " + r.name));

        if(!DIR.exists()) {
            System.out.println("Directory 'Herobrinia' not found, creating...");
            DIR.mkdir();
        }
        if(!CONFIG.exists()) {
            System.out.println("Config 'Herobrinia/config.json' not found, creating...");
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
                    System.err.println("Failed parsing config! Try to fix it or delete it!");
                }else {
                    JsonObject obj = (JsonObject) je;
                    JsonArray array = obj.getAsJsonArray("disabledAttacks");
                    array.forEach(d -> {
                        Attack a = Main.attackRegistry.getByName(d.getAsString());
                        if (a == null) {
                            System.out.println("Attack '" + d.getAsString() + "' not found.");
                            return;
                        }
                        Main.attackRegistry.unregister(a);
                        System.out.println("Disabled attack '" + a.name + "'");
                    });
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        //Register Entities
        FabricDefaultAttributeRegistry.register(HEROBRINE_ENTITY_TYPE, EntityHerobrine.createMobAttributes());


        //Register items
        register(createIdentifier("herobrine_apple"), HEROBRINE_APPLE);
        register(createIdentifier("hand_of_herobrine"), HAND_OF_HEROBRINE);
        register(createIdentifier("hand_of_notch"), HAND_OF_NOTCH);
        register(createIdentifier("multi_hand_of_herobrine_and_notch"), MULTI_HAND_OF_HEROBRINE_AND_NOTCH);
        register(createIdentifier("magic_flint_and_steel"), MAGIC_FLINT_AND_STEEL);
        register(createIdentifier("lightning_stick"), LIGHTNING_STICK);
        register(createIdentifier("herobrine_sword"), HEROBRINE_SWORD);
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
        register(createIdentifier("herobrine_rose"), HEROBRINE_ROSE);

        //Register blocks
        register(createIdentifier("herobrine_block"), HEROBRINE_BLOCK);


    }





    public static void register(Identifier identifier, Block b) {
        Registry.register(Registry.BLOCK, identifier, b);
        Registry.register(Registry.ITEM, identifier, new BlockItem(b, new Item.Settings().group(HEROBRINIA_GROUP)) {
            @Override
            public Text getName() {
                return b.getName();
            }

            @Override
            public Text getName(ItemStack stack) {
                return this.getName();
            }
        });
    }

    public static void register(Identifier identifier, Item i) {
        Registry.register(Registry.ITEM, identifier, i);
    }



    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}