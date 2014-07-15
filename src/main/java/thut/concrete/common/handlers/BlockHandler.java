package thut.concrete.common.handlers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import thut.concrete.common.blocks.fluids.*;
import thut.concrete.common.blocks.technical.BlockKiln;
import thut.concrete.common.blocks.technical.BlockMixer;
import thut.concrete.common.blocks.technical.BlockRebar;
import thut.concrete.common.blocks.tileentity.crafting.TileEntityKiln;
import thut.concrete.common.blocks.tileentity.crafting.TileEntityMixer;
import thut.core.common.blocks.BlockFluid;

import java.util.ArrayList;
import java.util.List;

public class BlockHandler {
  private static List<Block> blockList = new ArrayList<Block>();

  public static void registerBlocks() {
    blockList.add(new BlockLiquidConcrete());
    blockList.add(new BlockConcrete());
    blockList.add(new BlockLiquidREConcrete());
    blockList.add(new BlockREConcrete());
    blockList.add(new BlockAsphalt());
    blockList.add(new BlockLiquidAsphalt());

    blockList.add(new BlockKiln());
    blockList.add(new BlockMixer());
    GameRegistry.registerTileEntity(TileEntityKiln.class, "multikilncore");
    GameRegistry.registerTileEntity(TileEntityMixer.class, "mixerte");

    blockList.add(new BlockRebar());

    for(Block b : blockList) {
      GameRegistry.registerBlock(b, b.getUnlocalizedName().substring(5));
    }
    initFluids();
  }

  public static void initFluids() {
    for(Block b : blockList) {
      if(b instanceof BlockFluid) {
        ((BlockFluid) b).setData();
      }
    }
  }
}
