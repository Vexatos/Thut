package thut.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import thut.tech.common.items.ItemLinker;

/**
 * @author Vexatos
 */
public class ThutUtils {
  /**
   * Checks whether the Item is a wrench-like item
   * @param item The item to check
   * @param includeStick Whether a Stick also counts as a wrench
   * @param includeLinker Whetehr the ItemLinker also counts as a wrench
   * @return 'true' if the item is a wrench-like item
   */
  public static boolean isWrench(Item item, boolean includeStick, boolean includeLinker) {
    return item.getUnlocalizedName().toLowerCase().contains("wrench")
        || item.getUnlocalizedName().toLowerCase().contains("screwdriver")
        || (includeStick && item.getUnlocalizedName().equals(Items.stick.getUnlocalizedName())
        || (includeLinker && item instanceof ItemLinker));
  }
}
