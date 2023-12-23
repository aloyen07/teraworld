package ru.aloyenz.teraworld;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Sys;

import java.io.File;

@Mod(modid = TeraWorld.MODID, name = TeraWorld.NAME, version = TeraWorld.VERSION)
public class TeraWorld
{
    public static final String MODID = "teraworld";
    public static final String NAME = "Tera World";
    public static final String VERSION = "1.0";

    private static Logger logger;
    private static TeraWorld INSTANCE;

    public static TeraWorld getInstance() {
        return INSTANCE;
    }


    private final TeraWorldConfiguration configuration = new TeraWorldConfiguration();
    private final WorldWorker worldWorker = new WorldWorker();

    public WorldWorker getWorldWorker() {
        return worldWorker;
    }

    public TeraWorld() {
        INSTANCE = this;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        //MinecraftForge.EVENT_BUS.register(new WorldEvents(worldWorker));
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
