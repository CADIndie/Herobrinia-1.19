package me.sirlennox.herobrinia;


import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.blocks.HerobrineBlock;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrine;
import me.sirlennox.herobrinia.items.HandOfHerobrine;
import me.sirlennox.herobrinia.items.HerobrineApple;
import me.sirlennox.herobrinia.items.LightningStick;
import me.sirlennox.herobrinia.items.MagicFlintAndSteel;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.registry.Registry;

import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "herobrinia";
    public static final String MOD_NAME = "Herobrinia";
    public static final Random rndm = new Random();
    public static long herobrineAttackDelay = 20000L;
    public static AttackRegistry attackRegistry;
    
    public static final EntityType<EntityHerobrine> HEROBRINE_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("herobrinia", "herobrine"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, EntityHerobrine::new).dimensions(EntityDimensions.fixed(0.75f, 2f)).build()
    );


    public static final ItemGroup HEROBRINIA_GROUP = FabricItemGroupBuilder.create(
            new Identifier("herobrinia", "herobrinia"))
            .icon(() -> new ItemStack(Blocks.NETHERITE_BLOCK))
            .build();

    public static final Block HEROBRINE_BLOCK = new HerobrineBlock();

    //Items
    public static final Item HEROBRINE_APPLE = new HerobrineApple();
    public static final Item HAND_OF_HEROBRINE = new HandOfHerobrine();
    public static final Item MAGIC_FLINT_AND_STEEL = new MagicFlintAndSteel();
    public static final Item LIGHTNING_STICK = new LightningStick();


    public static Identifier createIdentifier(String name) {
        return new Identifier(MOD_ID, name);
    }

    // public static final RegistryKey<Biome> HEROBRINIA_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("herobrinia", "herobrinia"));
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        register();


    }

    public void register() {
        net.fabricmc.fabric.api.event.player.UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {

            return ActionResult.PASS;
        });

        //Init AttackRegistry
        attackRegistry = new AttackRegistry();
        attackRegistry.init();


        HEROBRINIA_GROUP.setName("§cHerobrinia");

        //Register Entities
        FabricDefaultAttributeRegistry.register(HEROBRINE_ENTITY_TYPE, EntityHerobrine.createMobAttributes());

        //Register items
        register(createIdentifier("herobrine_apple"), HEROBRINE_APPLE);
        register(createIdentifier("hand_of_herobrine"), HAND_OF_HEROBRINE);
        register(createIdentifier("magic_flint_and_steel"), MAGIC_FLINT_AND_STEEL);
        register(createIdentifier("lightning_stick"), LIGHTNING_STICK);
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