package me.sirlennox.herobrinia.attack;

import me.sirlennox.herobrinia.Registry;
import me.sirlennox.herobrinia.attack.attacks.*;
import me.sirlennox.herobrinia.attack.attacks.Fall;

public class AttackRegistry extends Registry<Attack> {
    @Override
    public void init() {
        register(new Hit());
        register(new SpamHit());
        register(new Boost());
        register(new Lava());
        //register(new RandomTP());
        register(new SpamHit());
        register(new Teleport());
        register(new Web());
	    register(new Cut());
	    register(new FallingBlocks());
        register(new Fall());
        register(new TNT());
        register(new Lightning());
        register(new EveryEffect());
        register(new ObsidianTrap());
        register(new MLG());
        register(new FallDamage());
        register(new TeleportIntoMe());
        register(new SpawnMonsters());
    //    register(new EffectClear());
        register(new Stun());
        register(new Scare());
        register(new Fireball());
        register(new Freeze());
        register(new Burn());
        register(new DragonFireball());
        register(new Witherskull());
        //register(new Smash());
     //   register(new WaterTrap());
    }

}
