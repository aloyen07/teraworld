package ru.aloyenz.teraworld;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class TeraWorldConfiguration {

    private static TeraWorldConfiguration INSTANCE;

    public static TeraWorldConfiguration getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Configuration is not loaded yet!");
        } else {
            return INSTANCE;
        }
    }

    private final Configuration config;
    private final boolean disableChunkRemoving;
    private final int chunkRate;
    private final double chunkRateModification;
    private final boolean deleteStructuresOverChunkGrid;
    private final boolean oneChunkChallenge;

    public TeraWorldConfiguration() {
        if (INSTANCE != null) {
            throw new IllegalStateException(
                    "Configuration is loaded previously! Use TeraWorldConfiguration#getInstance"
            );
        } else {
            this.config = new Configuration(
                    new File(
                            Minecraft.getMinecraft().mcDataDir.getPath() +
                                    File.separator + "config" + File.separator + "teraworld.cfg")
            );

            this.disableChunkRemoving = config.get("chunkGrid", "disableChunkRemoves", false,
                    "If you enable this, chunk grid is gone.").getBoolean();
            this.chunkRate = config.get("chunkGrid", "chunkRate", 6,
                    "Defines the size of the distance between chunks (in chunks).\n" +
                            "All chunks that are within this distance will be deleted.\n" +
                            "If it is equal to 0, the chunks are not deleted.\n"
                    ).getInt();
            this.chunkRateModification = config.get("chunkGrid", "chunkRateModification", 0.0D,
                            "Increases the distance between the previous chunk. Chunk will be generated using the \n" +
                            "formula \"chunkRate + chunk coordinates*chunkRateModify\"\n"
            ).getDouble();
            this.deleteStructuresOverChunkGrid = config.get("chunkGrid", "deleteStructuresBlocksOverChunkGrid",
                    true,
                    "[BETA] If true, the mod will remove blocks of structures that are outside the chunks"
            ).getBoolean();
            this.oneChunkChallenge = config.get("chunkGrid", "oneChunkChallenge", true,
                    "If enabled, mod generates only one chunk per-world."
            ).getBoolean();

            this.config.save();

            INSTANCE = this;
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public double getChunkRateModification() {
        return chunkRateModification;
    }

    public int getChunkRate() {
        return chunkRate;
    }

    public boolean isDisableChunkRemoving() {
        return disableChunkRemoving;
    }

    public boolean isDeleteStructuresOverChunkGrid() {
        return deleteStructuresOverChunkGrid;
    }

    public boolean isOneChunkChallenge() {
        return oneChunkChallenge;
    }
}
