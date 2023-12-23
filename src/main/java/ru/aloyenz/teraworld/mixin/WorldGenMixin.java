package ru.aloyenz.teraworld.mixin;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.aloyenz.teraworld.TeraWorld;
import ru.aloyenz.teraworld.TeraWorldConfiguration;
import ru.aloyenz.teraworld.WorldWorker;

@Mixin(value = ChunkGeneratorOverworld.class) // Тут нужно вписать ВСЕ N-ное и неопределённое количество классов
public abstract class WorldGenMixin {

    @Shadow @Final private World world;

    @Shadow public abstract void setBlocksInChunk(int x, int z, ChunkPrimer primer);

    @Unique
    private WorldWorker teraWorld$worldWorker;
    @Unique
    private TeraWorldConfiguration teraWorld$configuration;
    @Unique
    private boolean teraWorld$variablesIsChecked;

    @Unique
    private void teraWorld$checkVariables() {
        if (teraWorld$configuration == null) {
            teraWorld$configuration = TeraWorldConfiguration.getInstance();
        }
        if (teraWorld$worldWorker== null) {
            teraWorld$worldWorker = TeraWorld.getInstance().getWorldWorker();
        }

        teraWorld$variablesIsChecked = true;
    }

    @Inject(method = "generateChunk", at = @At(value = "HEAD"), cancellable = true)
    public void generateChunk(int x, int z, CallbackInfoReturnable<Chunk> cir) {
        if (!teraWorld$variablesIsChecked) {
            teraWorld$checkVariables();
        }
        if (!teraWorld$worldWorker.shouldGenerateChunk(x, z, world) && !teraWorld$configuration.isDisableChunkRemoving()) {
            Chunk out = new Chunk(world, new ChunkPrimer(), x, z);
            teraWorld$removeSides(x, z);
            cir.setReturnValue(out);
        }
    }

    @Unique
    private void teraWorld$removeChunk(int x, int z) {
        if (!teraWorld$worldWorker.shouldGenerateChunk(x, z, world)
                && !teraWorld$configuration.isDisableChunkRemoving()) {
            this.setBlocksInChunk(x, z, new ChunkPrimer());
        }
    }

    @Unique
    private void teraWorld$removeSides(int x, int z) {
        teraWorld$removeChunk(x+1, z);
        teraWorld$removeChunk(x, z+1);
        teraWorld$removeChunk(x-1, z);
        teraWorld$removeChunk(x, z-1);
        teraWorld$removeChunk(x+1, z+1);
        teraWorld$removeChunk(x+1, z-1);
        teraWorld$removeChunk(x-1, z+1);
        teraWorld$removeChunk(x-1, z-1);
    }

    @Inject(method = "populate", at = @At(value = "HEAD"), cancellable = true)
    public void removePopulations(int x, int z, CallbackInfo ci) {
        if (!teraWorld$variablesIsChecked) {
            teraWorld$checkVariables();
        }
        if (!teraWorld$worldWorker.shouldGenerateChunk(x, z, world)
                && !teraWorld$configuration.isDisableChunkRemoving()
                && teraWorld$configuration.isDeleteStructuresOverChunkGrid()) {

            this.setBlocksInChunk(x, z, new ChunkPrimer());
            ci.cancel();
        } else {
            teraWorld$removeSides(x, z);
        }
    }
}
