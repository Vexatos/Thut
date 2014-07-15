package thut.world.common.corehandlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RecipeHandler {

  // Empty fields for holding items
  public static Item[] items = ItemHandler.items;
  public static List<Item> itemList = ItemHandler.itemList;

  public static ItemStack[] brushes = ItemHandler.brushes;

  private static final String[] dyeNames = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow",
      "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };

  public RecipeHandler(ConfigHandler config) {
    registerOres();
  }

  public void registerRecipes() {
    registerSpecialRecipes();
    registerShapedRecipes();
    registerShapeless();
  }

  public void registerSpecialRecipes() {
    boolean dust = false;
  }

  public void registerShapedRecipes() {

  }

  public void registerOres() {

  }

  public void registerShapeless() {

  }

}
