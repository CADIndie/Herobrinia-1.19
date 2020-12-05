package me.sirlennox.herobrinia;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Utils {

    public static boolean setBlockAtPos(World world, double x, double y, double z, Block block) {
        try { world.setBlockState(new BlockPos(x, y, z), block.getDefaultState()); } catch (Exception e) {}
        return world.canSetBlock(new BlockPos(x, y, z));
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
        while(x <= xTo) {
            while(z <= zTo) {
                while (y <= yTo) {

                    setBlockAtPos(world, x, y, z, block);
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
        }

    }

}
