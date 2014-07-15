package thut.concrete.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thut.concrete.common.blocks.tileentity.crafting.ContainerLimekiln;
import thut.concrete.common.blocks.tileentity.crafting.ContainerMixer;
import thut.concrete.common.blocks.tileentity.crafting.TileEntityKiln;
import thut.concrete.common.blocks.tileentity.crafting.TileEntityMixer;

public class CommonProxy implements IGuiHandler {

  public void initClient() {
  }

  public void loadConfiguration() {
  }

  public EntityPlayer getPlayer(String playerName) {
    if(playerName != null) {
      return getWorld().getPlayerEntityByName(playerName);
    } else {
      return null;
    }
  }

  public EntityPlayer getPlayer() {
    return null;
  }

  public boolean isOnClientSide() {
    return false;
  }

  public World getWorld() {
    return FMLCommonHandler.instance().getMinecraftServerInstance().worldServers[0];
  }

  public void registerEntities() {

    try {
      Class<?> registry = Class.forName("powercrystals.minefactoryreloaded.MFRRegistry");
      if(registry != null) {
        //			FarmingRegistry.registerSafariNetBlacklist(EntityLift.class);
        //			FarmingRegistry.registerSafariNetBlacklist(EntityTurret.class);
        //			FarmingRegistry.registerSafariNetBlacklist(EntityBeam.class);
      }
    } catch(ClassNotFoundException e) {
      // TODO Auto-generated catch block
      //	System.out.println("[ThutConcrete] MFR not found, lift not added to the non-existant safari net blacklist.");
      //	e.printStackTrace();
    }
  }

  @Override
  public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
    TileEntity te = world.getTileEntity(x, y, z);
    if(te == null) {
      return null;
    }
    if(te instanceof TileEntityKiln) {
      TileEntityKiln tileEntity = (TileEntityKiln) te;
      return new ContainerLimekiln(player.inventory, tileEntity);
    }
    if(te instanceof TileEntityMixer) {
      TileEntityMixer tileEntity = (TileEntityMixer) te;
      return new ContainerMixer(player.inventory, tileEntity);
    }
    return null;
  }

  @Override
  public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
    return null;
  }

  public void loadSounds() {
  }

}
