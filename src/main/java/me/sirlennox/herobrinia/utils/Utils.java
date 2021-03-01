package me.sirlennox.herobrinia.utils;

import me.sirlennox.herobrinia.Main;
import me.sirlennox.herobrinia.entities.herobrine.EntityHerobrine;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Utils {

    public static boolean setBlockAtPos(World world, double x, double y, double z, Block block) {
        boolean b = world.canSetBlock(new BlockPos(x, y, z));
        if(b) try { world.setBlockState(new BlockPos(x, y, z), block.getDefaultState()); } catch (Exception e) {}
        return b;
    }

    public static boolean setBlockAtPos(World world, Vec3d pos, Block block) {
        return setBlockAtPos(world, pos.x, pos.y, pos.z, block);
    }

    public static void setBlocks(World world, double x1, double x2, double y1, double y2, double z1, double z2, Block block) {
        boolean xForward = x1 < x2;
        boolean yForward = y1 < y2;
        boolean zForward = z1 < z2;
        double x = xForward ? x1 : x2;
        double xTo = !xForward ? x1 : x2;
        double y = yForward ? y1 : y2;
        double yTo = !yForward ? y1 : y2;
        double z = zForward ? z1 : z2;
        double zTo = !zForward ? z1 : z2;
        for(double xx = x; xx < xTo; xx++) {
            for(double zz = z; zz < zTo; zz++) {
                for(double yy = y; yy < yTo; yy++) {
                    setBlockAtPos(world, xx, yy, zz, block);
                }
            }
        }
        /*while(x <= xTo) {
            while(z <= zTo) {
                while (y <= yTo) {

                    setBlockAtPos(world, x, y, z, block);
                    System.out.println(x + " " + y + " " + z);
                    if (yForward) {
                        y++;
                    } else {
                        y--;
                    }
                }
                if(zForward) {
                    z++;
                }else {
                    z--;
                }
            }

            if(xForward) {
                x++;
            }else {
                x--;
            }
        }*/

    }

    public static void setBlocks(World world, Vec3d pos1, Vec3d pos2, Block block) {
        setBlocks(world, pos1.x, pos2.x, pos1.y, pos2.y, pos1.z, pos2.z, block);
    }

    public static void spawnLightning(World world, Vec3d pos) {
        LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightning.setPos(pos.x, pos.y, pos.z);
        world.spawnEntity(lightning);
    }

    public static void randomAttack(LivingEntity e, LivingEntity herobrine) {
        if(e == null) return;
        Main.attackRegistry.REGISTERED.get(Main.rndm.nextInt(Main.attackRegistry.REGISTERED.size())).attack(e, herobrine);
    }

}
