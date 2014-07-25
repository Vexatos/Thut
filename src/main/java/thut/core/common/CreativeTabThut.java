package thut.core.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import thut.api.ThutBlocks;
import thut.reference.ThutConcreteReference;

import java.util.List;

public class CreativeTabThut extends CreativeTabs {

  public static CreativeTabThut tabThut = new CreativeTabThut();

  public CreativeTabThut() {
    super("tabConcrete");
  }

  @SideOnly(Side.CLIENT)
  public String getTabLabel() {
    return ThutConcreteReference.MOD_NAME;
  }

  @SideOnly(Side.CLIENT)
  public String getTranslatedTabLabel() {
    return this.getTabLabel();
  }

  @Override
  public Item getTabIconItem() {

    if(ThutBlocks.solidLavas[0] == null) {
      return Item.getItemFromBlock(Blocks.stone);
    }

    if(ThutBlocks.rebar != null) {
      return Item.getItemFromBlock(ThutBlocks.rebar);
    }

    return Item.getItemFromBlock(ThutBlocks.solidLavas[0]);
  }

  @Override
  public void displayAllReleventItems(List p_78018_1_) {
    super.displayAllReleventItems(p_78018_1_);
  }
}
