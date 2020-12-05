package me.sirlennox.herobrinia;

import me.sirlennox.herobrinia.attacks.*;
import me.sirlennox.herobrinia.attacks.Void;

import java.util.ArrayList;

public class AttackManager {

    public static ArrayList<Attack> attacks = new ArrayList<>();

    public static void init() {
        registerAttack(new Hit());
        registerAttack(new SpamHit());
        registerAttack(new Boost());
        registerAttack(new Lava());
        registerAttack(new RandomTP());
        registerAttack(new SpamHit());
        registerAttack(new Teleport());
        registerAttack(new Web());
	registerAttack(new Cut());
	registerAttack(new FallingBlocks());
        registerAttack(new Void());
    }

    public static void registerAttack(Attack attack) {
        attacks.add(attack);
    }

}
