package thut.world.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thut.api.ThutBlocks;
import thut.api.explosion.ExplosionCustom;
import thut.util.LogHelper;
import thut.world.common.WorldCore;
import thut.world.common.corehandlers.ConfigHandler;

public class BlockMisc extends Block {
  public int light = 0;
  public boolean dusts = false;
  Integer[][] data;

  public BlockMisc() {
    super(Material.rock);

    if(ConfigHandler.debugPrints) {
      this.setCreativeTab(WorldCore.tabThut);
    }
    this.setResistance(0);
    this.setHardness(0);
    ThutBlocks.misc = this;
    setTickRandomly(true);
  }

  /**
   * Updates the blocks bounds based on its current state. Args: world, x, y, z
   */
  public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    if(meta == 2) {
      this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    } else {
      this.setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
    }
  }

  public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
    ItemStack item = player.getHeldItem();

    if(!worldObj.isRemote && item != null && item.getItem() instanceof ItemDye) {
      int meta1 = (15 - item.getItemDamage()) + 1;
      LogHelper.debug("boom");
      ExplosionCustom boom = new ExplosionCustom(worldObj, null, x + 0.5, y + 0.5, z + 0.5, meta1 * meta1 * 250);
      boom.doExplosionA();
      return true;
    }

    return false;

  }

  @SideOnly(Side.CLIENT)

  /**
   * When this method is called, your block should register all the icons it needs with the given IconRegister. This
   * is the only chance you get to register icons.
   */
  public void registerBlockIcons(IIconRegister par1IconRegister) {
    this.blockIcon = par1IconRegister.registerIcon(WorldCore.TEXTURE_PATH + "misc");
  }

  /**
   * Called throughout the code as a replacement for block instanceof BlockContainer
   * Moving this to the Block base class allows for mods that wish to extend vinella
   * blocks, and also want to have a tile entity on that block, may.
   *
   * Return true from this function to specify this block has a tile entity.
   * @param metadata Metadata of the current block
   * @return True if block has a tile entity, false otherwise
   */
  public boolean hasTileEntity(int metadata) {
    return metadata > 0;
  }

  /**
   * Called throughout the code as a replacement for ITileEntityProvider.createNewTileEntity
   * Return the same thing you would from that function.
   * This will fall back to ITileEntityProvider.createNewTileEntity(World) if this block is a ITileEntityProvider
   * @param metadata The Metadata of the current block
   * @return A instance of a class extending TileEntity
   */
  public TileEntity createTileEntity(World world, int metadata) {
    return null;
  }

}
