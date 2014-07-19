package thut.world.common.items;

import thut.core.common.ThutCore;
import thut.core.common.items.ItemThut;
import thut.reference.ThutWorldReference;

/**
 * @author Vexatos
 */
public class ItemThutWorld extends ItemThut {
  public ItemThutWorld() {
    super(ThutWorldReference.MOD_ID);
    this.setCreativeTab(ThutCore.tabThut);
  }
}
