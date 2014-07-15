package thut.world.common.worldgen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static thut.api.ThutBlocks.*;

public class WorldGen extends WorldGenerator {

  Block[] replacable = { stone, dirt, sand, sandstone };
  List<Integer[]> locations = new ArrayList<Integer[]>();
  Block mineableID;
  int mineableMeta = 0;
  int number;

  WorldGen(Block id, int size) {
    this.mineableID = id;
    this.number = size;
  }

  WorldGen(Block id, int meta, int size) {
    this.mineableID = id;
    this.number = size;
    this.mineableMeta = meta;
  }

  public boolean generateSheet(World worldObj, Random r, int x0, int y0, int z0, int depth, int width) {
    //System.out.println("Generating Sheet");
    while(locations.size() < number) {
      int x = (int) (r.nextGaussian() * width), y = (int) (r.nextGaussian() * depth), z = (int) (r.nextGaussian() * width);
      Integer[] loc = { x, y, z };
      boolean add = true;
      for(Integer[] testloc : locations) {
        if(testloc[0].equals(loc[0]) && testloc[1].equals(loc[1]) && testloc[2].equals(loc[2])) {
          add = false;
        }
      }
      if(add) {
        locations.add(loc);
      }
    }
    //System.out.println("Spawning blocks");
    int n = 0;
    for(Integer[] loc : locations) {
      int x = loc[0] + x0, y = loc[1] + y0, z = loc[2] + z0;
      boolean set = false;
      for(Block i : replacable) {
        if((worldObj.getBlock(x, y, z) == i))//block != null &&
        {
          set = true;
        }
      }
      if(set) {
        n++;
        worldObj.setBlock(x, y, z, mineableID, mineableMeta, 2);
      }
    }
    //System.out.println("Generated "+n+" blocks");

    return true;
  }

  @Override
  public boolean generate(World world, Random random, int i, int j, int k) {
    return false;
  }

}
