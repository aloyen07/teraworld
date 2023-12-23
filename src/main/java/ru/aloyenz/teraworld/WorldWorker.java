package ru.aloyenz.teraworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.Sys;

public class WorldWorker {

    private final IBlockState air = Blocks.AIR.getDefaultState();
    private final TeraWorldConfiguration config = TeraWorldConfiguration.getInstance();

    public void processChunk(World world, int xc, int zc) {
//        if (!(config.getChunkRate() == 0 && config.getChunkRateModification() == 0)) {
//            processRemoveChunk(world, xc, zc);
//        }
    }

    public boolean shouldGenerateChunk(int xc, int zc, World world) {
        if (!(config.getChunkRate() == 0 && config.getChunkRateModification() == 0)) {
            if (config.isOneChunkChallenge()) {
                return (int) Math.floor((double) world.getSpawnPoint().getX() /16) == xc &&
                       (int) Math.floor((double) world.getSpawnPoint().getZ() /16) == zc;
            } else {
                return (Math.abs(xc) % ((config.getChunkRate() + 1) +
                                (int) Math.floor(Math.abs(xc) * config.getChunkRateModification())) == 0 &&
                        Math.abs(zc) % ((config.getChunkRate() + 1) +
                                (int) Math.floor(Math.abs(zc) * config.getChunkRateModification())) == 0);
            }
        } else {
            return true;
        }
    }

//    private void processRemoveChunk(World world, int xc, int zc) {
//        if (!(xc%(config.getChunkRate() + xc*config.getChunkRateModification()) == 0 &&
//              zc%(config.getChunkRate() + zc*config.getChunkRateModification()) == 0)) {
//            removeChunk(world, xc, zc);
//        }
//    }
//
//    private void removeChunk(World world, int xc, int zc) {
//        for (int x = 0; x <= 15; x++) {
//            for (int z = 0; z <= 15; z++) {
//                for (int y = 0; y <= 256; y++) {
//                    world.setBlockState(new BlockPos(x+(xc*16), y, z+(zc*16)), air);
//                }
//            }
//        }
//    }
}
