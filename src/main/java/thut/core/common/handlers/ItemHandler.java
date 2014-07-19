package thut.core.common.handlers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import thut.core.common.items.ItemSpout;
import thut.core.common.items.ItemTank;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vexatos
 */
public class ItemHandler {

  private static List<Item> items = new ArrayList<Item>();

  public static void registerItems() {

    items.add(new ItemSpout());
    items.add(new ItemTank());

    for(Item item : items) {
      GameRegistry.registerItem(item, item.getUnlocalizedName());
    }
  }
}
