package thut.world.common.worldgen;

import net.minecraft.world.biome.BiomeGenBase;
import thut.api.ThutBlocks;

public class BiomeGenChalk extends BiomeGenBase
{

	public BiomeGenChalk(int par1) {
		super(par1);
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
        this.fillerBlock = ThutBlocks.worldGen;
        this.topBlock = ThutBlocks.grass;
        this.biomeName = "chalk";
	}

	
}
