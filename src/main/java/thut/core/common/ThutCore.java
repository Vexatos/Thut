package thut.core.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import thut.api.explosion.ExplosionCustom.Cruncher;
import thut.api.network.PacketPipeline;
import thut.core.common.handlers.ConfigHandler;
import thut.core.common.handlers.ItemHandler;
import thut.reference.ThutCoreReference;

@Mod(modid = ThutCoreReference.MOD_ID, name = ThutCoreReference.MOD_NAME, version = ThutCoreReference.VERSION)

public class ThutCore {

  @SidedProxy(clientSide = ThutCoreReference.CLIENT_PROXY_CLASS, serverSide = ThutCoreReference.COMMON_PROXY_CLASS)
  public static CommonProxy proxy;

  @Instance(ThutCoreReference.MOD_ID)
  public static ThutCore instance;

  public static CreativeTabThut tabThut = CreativeTabThut.tabThut;

  public static Block[] blocks;
  public static Item[] items;

  public static Class test;

  public static BiomeGenBase volcano;
  public static BiomeGenBase chalk;

  // Configuration Handler that handles the config file
  public ConfigHandler config;

  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    config = new ConfigHandler(e.getSuggestedConfigurationFile());

    ItemHandler.registerItems();

    proxy.loadSounds();

    MinecraftForge.EVENT_BUS.register(this);

    Cruncher sort = new Cruncher();
  }

  @EventHandler
  public void load(FMLInitializationEvent evt) {
    proxy.initClient();
    proxy.registerEntities();
    proxy.registerTEs();

    PacketPipeline.packetPipeline.initalise();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent e) {
    PacketPipeline.packetPipeline.postInitialise();
  }
}
