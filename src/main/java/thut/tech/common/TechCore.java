package thut.tech.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import thut.api.network.PacketPipeline;
import thut.core.common.CreativeTabThut;
import thut.reference.ThutTechReference;
import thut.tech.common.handlers.BlockHandler;
import thut.tech.common.handlers.ConfigHandler;
import thut.tech.common.handlers.ItemHandler;
import thut.tech.common.network.PacketThutTech;

@Mod(modid = ThutTechReference.MOD_ID, name = ThutTechReference.MOD_NAME, version = "1.0.0")
public class TechCore {
  @SidedProxy(clientSide = ThutTechReference.CLIENT_PROXY_CLASS, serverSide = ThutTechReference.COMMON_PROXY_CLASS)
  public static CommonProxy proxy;

  @Instance(ThutTechReference.MOD_ID)
  public static TechCore instance;

  public static CreativeTabThut tabThut = CreativeTabThut.tabThut;

  public static final String ID = ThutTechReference.MOD_ID.toLowerCase();

  private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_UK", "en_US", "de_DE" };

  @EventHandler
  public void preInit(FMLPreInitializationEvent e) {
    BlockHandler.registerBlocks();
    ItemHandler.registerItems();

    Configuration config = new Configuration(e.getSuggestedConfigurationFile());
    ConfigHandler.load(config);

  }

  @EventHandler
  public void load(FMLInitializationEvent evt) {
    proxy.initClient();
    PacketPipeline.packetPipeline.initalise();
    PacketPipeline.packetPipeline.registerPacket(PacketThutTech.class);
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent e) {

    PacketPipeline.packetPipeline.postInitialise();
  }

}
