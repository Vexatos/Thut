package thut.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.ForgeDirection;
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

  /**
   * Returns the direction the entity is facing
   * @param e The entity
   * @return The ForgeDirection the entity is facing
   */
  public static ForgeDirection getFacingfromEntity(EntityLivingBase e) {
    ForgeDirection side = ForgeDirection.NORTH;
    double angle = e.rotationYaw % 360;
    double angle2 = Math.abs(angle);

    if(angle2 > 315 || angle2 <= 45) {
      return ForgeDirection.SOUTH;
    }
    if((angle > 45 && angle <= 135) || (angle < -225 && angle >= -315)) {
      return ForgeDirection.WEST;
    }
    if(angle2 > 135 && angle2 <= 225) {
      return ForgeDirection.NORTH;
    }
    if((angle > 225 && angle <= 315) || (angle < -45 && angle >= -135)) {
      return ForgeDirection.EAST;
    }
    return side;
  }
}
