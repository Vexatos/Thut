package thut.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ThutItems extends Items {
	
	//WorldGen Items
	public static ItemStack dust;
	public static ItemStack trass;
	
	//Concrete Items
	public static ItemStack[] brushes = new ItemStack[17];
	public static ItemStack cement;
	public static ItemStack lime;
	public static ItemStack carbonate;
	
	public static ItemStack eightLiquidConcrete;
	
	public static ItemStack boneMeal;// = new ItemStack(Item.dyePowder,1,15);
	
	public static ItemStack sand;// = new ItemStack(Block.sand);
	public static ItemStack gravel;// = new ItemStack(Block.gravel);
	public static ItemStack cobble;// = new ItemStack(Block.cobblestone);
	public static ItemStack water;// = new ItemStack(Item.bucketWater);
	
	public static ItemStack concreteBucket;// = new ItemStack(ItemBucketConcrete.instance);
	public static ItemStack trassOre;// = new ItemStack(Blocks.worldGen,1,1);
	public static ItemStack limestoneOre;// = new ItemStack(Blocks.worldGen,1,2);
	public static ItemStack chalkOre;// = new ItemStack(Blocks.worldGen,1,0);
	public static ItemStack twoRebar;// = new ItemStack(rebar,2,0);
	
	public static ItemStack solidLava0;// = new ItemStack(getSolidLava(0),1,0);
	public static ItemStack solidLava1;// = new ItemStack(getSolidLava(1),1,0);
	public static ItemStack solidLava2;// = new ItemStack(getSolidLava(2),1,0);
	
	private static List<Item> itemList = new ArrayList<Item>();
	public static List<ItemStack> cookable = new ArrayList<ItemStack>();
	
	
	
	public static List<Item> getItems()
	{
		if(itemList.size()==0)
			initItems();
		
		return itemList;
	}
	
	private static void initItems()
	{
		for(int i = 0; i<Short.MAX_VALUE; i++)
		{
			Item item = Item.getItemById(i);
			if(item!=null)
				itemList.add(item);
		}
	}
}
