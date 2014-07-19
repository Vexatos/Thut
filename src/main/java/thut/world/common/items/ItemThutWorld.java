package thut.world.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import thut.core.common.ThutCore;

/**
 * TODO Switch to this nice-looking system
 * Totally not stolen from EE3
 * @author Pahimar
 */
public class ItemThutWorld extends Item {
  public ItemThutWorld() {
    super();
    this.setCreativeTab(ThutCore.tabThut);
  }

  /*@Override
  public String getUnlocalizedName()
  {
    return String.format("item.%s%s", ThutWorldReference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  @Override
  public String getUnlocalizedName(ItemStack itemStack)
  {
    return String.format("item.%s%s", ThutWorldReference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }*/

  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister) {
    itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
  }

  protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }
}
