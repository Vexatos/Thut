package thut.core.common.items;

import net.minecraft.item.Item;
import thut.core.common.ThutCore;
import thut.reference.ThutCoreReference;

/**
 * Totally not stolen from EE3
 * @author Pahimar
 */
public class ItemThut extends Item {

  public String MOD_ID = ThutCoreReference.MOD_ID;

  public ItemThut(String id) {
    super();
    MOD_ID = id;
    this.setCreativeTab(ThutCore.tabThut);
  }

  /*@Override
  public String getUnlocalizedName() {
    return String.format("item.%s:%s", MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  @Override
  public String getUnlocalizedName(ItemStack itemStack) {
    return String.format("item.%s:%s", MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void registerIcons(IIconRegister iconRegister) {
    itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
  }

  protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
    return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
  }*/
}
