package ru.aloyenz.teraworld;

import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldEvents {

    private final WorldWorker worldWorker;

    public WorldEvents(WorldWorker worldWorker) {
        this.worldWorker = worldWorker;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void chunkGenerateEvent(ChunkGeneratorEvent.ReplaceBiomeBlocks event) {
        worldWorker.processChunk(event.getWorld(), event.getX(), event.getZ());
        event.getWorld().getChunkFromChunkCoords(event.getX(), event.getZ()).setTerrainPopulated(true);
    }

}
