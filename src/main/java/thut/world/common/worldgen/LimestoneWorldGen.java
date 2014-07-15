package thut.world.common.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import thut.api.ThutBlocks;
import thut.world.common.corehandlers.ConfigHandler;

import java.util.Random;

public class LimestoneWorldGen implements IWorldGenerator {

  @Override
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    if(world.provider.isSurfaceWorld() && ConfigHandler.limestone) {
      if(random.nextInt(10) == 1) {
        int x = chunkX * 16 + random.nextInt(16);
        int y = chunkZ * 16 + random.nextInt(16);
        int z = 20 + random.nextInt(80);
        //TODO
        (new WorldGen(ThutBlocks.worldGen, 2, 64)).generateSheet(world, random, x, z, y, 1, 3);
      }
    }

  }
}
