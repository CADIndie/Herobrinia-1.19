package me.sirlennox.herobrinia;


import me.sirlennox.herobrinia.attack.AttackRegistry;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrine;
import me.sirlennox.herobrinia.items.HandOfHerobrine;
import me.sirlennox.herobrinia.items.HerobrineApple;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "herobrinia";
    public static final String MOD_NAME = "Herobrinia";
    public static final Random rndm = new Random();
    public static long herobrineAttackDelay = 30000L;
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
    public static final Item HEROBRINE_APPLE = new HerobrineApple();
    public static final Item HAND_OF_HEROBRINE = new HandOfHerobrine();


    // public static final RegistryKey<Biome> HEROBRINIA_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("herobrinia", "herobrinia"));
    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        register();


    }

    public void register() {
        attackRegistry = new AttackRegistry();
        attackRegistry.init();
        HEROBRINIA_GROUP.setName("§cHerobrinia");
        FabricDefaultAttributeRegistry.register(HEROBRINE_ENTITY_TYPE, EntityHerobrine.createMobAttributes());
        Registry.register(Registry.ITEM, new Identifier("herobrinia", "herobrine_apple"), new HerobrineApple());
        Registry.register(Registry.ITEM, new Identifier("herobrinia", "hand_of_herobrine"), new HandOfHerobrine());

    }



    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}