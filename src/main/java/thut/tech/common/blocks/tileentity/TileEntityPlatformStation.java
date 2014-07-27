package thut.tech.common.blocks.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thut.api.ThutBlocks;
import thut.api.maths.Vector3;
import thut.tech.common.entity.EntityPlatform;
import thut.util.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Vexatos
 */
public class TileEntityPlatformStation extends TileEntity {

  public int power = 0;
  //public int prevPower = 1;
  public EntityPlatform platform;

  boolean listNull = false;
  List list = new ArrayList<Entity>();
  Vector3 here;

  public Vector3 root = new Vector3();
  //	public IElectricityStorage source;
  public TileEntityPlatformStation rootNode;
  public Vector<TileEntityPlatformStation> connected = new Vector<TileEntityPlatformStation>();
  //ForgeDirection sourceSide;
  public double energy;

  public long time = 0;
  public int metaData = 0;
  public Block blockID = Blocks.air;

  boolean loaded = false;

  public boolean called = false;
  public int station = 0;
  //public int calledYValue = -1;
  public int calledStation = 0;
  public int platformID = -1;
  public int side = 2;

  //int tries = 0;

  //public boolean toClear = false;

  public boolean first = true;
  public boolean read = false;
  public boolean redstone = true;
  //public boolean powered = false;

  @Override
  public void updateEntity() {
    if(first) {
      blockID = worldObj.getBlock(xCoord, yCoord, zCoord);
      here = new Vector3(this);
      //			GridTileLoadEvent evt = new GridTileLoadEvent(this, worldObj, getLocation());
      //			MinecraftForge.EVENT_BUS.post(evt);

      if(EntityPlatform.platforms.containsKey(platformID)) {
        platform = EntityPlatform.platforms.get(platformID);
      }

      first = false;
    }

    if(platform != null && blockID == ThutBlocks.lift && getBlockMetadata() == 2) {
      /*if(calledStation > 0) {
        LogHelper.info("Access Called station: " + String.valueOf(calledStation));
      }*/
      int calledStationOld = calledStation;
      calledStation = platform.destinationStation;
      if(calledStation != calledStationOld) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
      }
    }

    if(blockID == ThutBlocks.platformRail && time % 10 == 0) {
      if(rootNode == null) {
        checkPower();
      }
      if(rootNode == this && time % 100 == 0) {
        //				TileEntity te = here.offset(sourceSide).getTileEntity(worldObj);
        //				if(te!=source)
        //				{
        //					clearConnections();
        //				}
      }
      if(!loaded || listNull || time % 1000 == 0) {
        list = worldObj.getEntitiesWithinAABB(EntityPlatform.class, AxisAlignedBB.getBoundingBox(xCoord + 0.5 - 2, 0, zCoord + 0.5 - 2, xCoord + 0.5 + 2, 255, zCoord + 0.5 + 2));
        loaded = true;
      }
      boolean check = false;
      for(Object e2 : list) {
        Entity e = (Entity) e2;
        if(e != null) {
          //					((EntityLift)e).source = source;
          boolean flag = ((EntityPlatform) e).destinationStation != 0 && ((int) ((EntityPlatform) e).prevStationCoords) == yCoord;
          check = check || ((int) (e.posY) == yCoord && !flag);
        } else {
          listNull = true;
        }
      }
      setCalled(check);
    }
    time++;
  }

  public void checkPower() {
    //		TileEntity down = here.offset(DOWN).getTileEntity(worldObj);
    //		int id = here.offset(DOWN).getBlockId(worldObj);
    //		if(down==null)
    //		{
    //			boolean found = false;
    //			for(ForgeDirection side: VALID_DIRECTIONS)
    //			{
    //				if(side!=UP&&side!=DOWN)
    //				{
    //					TileEntity te = here.offset(side).getTileEntity(worldObj);
    //					if(te instanceof IElectricityStorage)
    //					{
    //						found = true;
    //						source = (IElectricityStorage)te;
    //						rootNode = this;
    //						sourceSide = side;
    //					}
    //				}
    //			}
    //			if(!found)
    //				clearConnections();
    //			else
    //			{
    //				TileEntity up = here.offset(UP).getTileEntity(worldObj);
    //				if(up!=null&&up instanceof TileEntityLiftAccess)
    //				{
    //					((TileEntityLiftAccess)up).clearConnections();
    //				}
    //			}
    //		}
    //		else if(down instanceof TileEntityLiftAccess)
    //		{
    //			source = ((TileEntityLiftAccess)down).source;
    //			rootNode = ((TileEntityLiftAccess)down).rootNode;
    //		}
  }

  public void clearConnections() {
    //		if(here!=null)
    //		{
    //			source = null;
    //			rootNode = null;
    //			TileEntity up = here.offset(UP).getTileEntity(worldObj);
    //			if(up!=null&&up instanceof TileEntityLiftAccess)
    //			{
    //				((TileEntityLiftAccess) up).clearConnections();
    //			}
    //		}
  }

  public double getEnergy() {
    //		if(source!=null)
    //			return source.getJoules();
    //		else
    return 0;
  }

  public void setEnergy(double energy) {
    //		if(source!=null)
    //		{
    //			source.setJoules(energy);
    //		}
  }

  public String connectionInfo() {
    //String ret = "";
    //		if(source!=null)
    //		{
    //			ret = "Energy stored: "+getEnergy();
    //		}
    //return ret;
    return "";
  }

  @Override
  public void onChunkUnload() {
    if(platform != null) {
      platform.removeStation(this.station);
    }
    clearConnections();
  }

  /**
   * invalidates a tile entity
   */
  @Override
  public void invalidate() {
    this.tileEntityInvalid = true;
    if(platform != null) {
      platform.removeStation(this.station);
    }
    clearConnections();
    //		GridTileUnloadEvent evt = new GridTileUnloadEvent(this, worldObj, getLocation());
    //		MinecraftForge.EVENT_BUS.post(evt);
  }

  /**
   * validates a tile entity
   */
  @Override
  public void validate() {
    this.tileEntityInvalid = false;
  }

  public boolean checkSides() {
    List check = worldObj.getEntitiesWithinAABB(EntityPlatform.class, AxisAlignedBB.getBoundingBox(xCoord + 0.5 - 1, yCoord, zCoord + 0.5 - 1, xCoord + 0.5 + 1, yCoord + 1, zCoord + 0.5 + 1));
    if(check != null && check.size() > 0) {
      platform = (EntityPlatform) check.get(0);
      platformID = platform.id;
    }
    return !(check == null || check.isEmpty());
  }

  public synchronized void setStation(int station) {
    if(platform != null && station <= 64 && station > 0) {
      if(this.station > 0) {
        platform.removeStation(this.station);
      }
      platform.setStation(this, station);
      this.station = station;
      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
  }

  public void setPlatform(EntityPlatform platform) {
    this.platformID = platform.id;
    this.platform = platform;
  }

  @Override
  public void writeToNBT(NBTTagCompound par1) {
    super.writeToNBT(par1);
    par1.setInteger("meta", metaData);
    par1.setString("block id", blockID.getLocalizedName());
    par1.setInteger("side", side);
    par1.setInteger("station", station);
    root.writeToNBT(par1, "root");
    if(platform != null) {
      platformID = platform.id;
    }
    par1.setInteger("platform", platformID);
  }

  @Override
  public void readFromNBT(NBTTagCompound par1) {
    super.readFromNBT(par1);
    metaData = par1.getInteger("meta");
    if(Block.getBlockFromName(par1.getString("block id")) != null) {
      blockID = Block.getBlockFromName(par1.getString("block id"));
    }
    side = par1.getInteger("side");
    station = par1.getInteger("station");
    platformID = par1.getInteger("platform");
    root = Vector3.readFromNBT(par1, "root");
    if(platformID != -1 && EntityPlatform.platforms.containsKey(platformID)) {
      platform = EntityPlatform.platforms.get(platformID);
    }
    /*System.out.println(metaData);
    System.out.println(blockID != null);
    System.out.println(side);
    System.out.println(station);
    System.out.println(platformID);
    System.out.println(platform != null);*/
  }

  public void doButtonClick(int side, float hitX, float hitY, float hitZ) {
    if(!worldObj.isRemote && platform != null) {
      if(side == this.side && !platform.called) {
        int button = getButtonFromClick(side, hitX, hitY, hitZ);
        buttonPress(button);
        calledStation = platform.destinationStation;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        LogHelper.debug(calledStation + " " + button + " " + platform);
      }
    }
  }

  public synchronized void callYValue(int yValue) {
    if(platform != null) {
      platform.callYValue(yValue);
    }
  }

  public synchronized void buttonPress(int button) {
    if(button != 0 && button <= 64 && platform != null && platform.stations[button - 1] != null) {
      if(button == station) {
        this.called = true;
      }
      platform.call(button);
    }
  }

  public void setCalled(boolean called) {
    if(called != this.called) {
      this.called = called;
      updateBlock();
      notifySurroundings();
    }
  }

  public void setSide(int side) {
    if(side != 0 && side != 1) {
      this.side = side;
      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
  }

  public int getButtonFromClick(int side, float hitX, float hitY, float hitZ) {
    int ret = 0;

    switch(side){
      case 0:{
        return 0;
      }
      case 1:{
        return 0;
      }
      case 2:{
        ret = 1 + (int) (((1 - hitX) * 4) % 4) + 4 * (int) (((1 - hitY) * 4) % 4);
        return ret;
      }
      case 3:{
        ret = 1 + (int) (((hitX) * 4) % 4) + 4 * (int) (((1 - hitY) * 4) % 4);
        return ret;
      }
      case 4:{
        ret = 1 + 4 * (int) (((1 - hitY) * 4) % 4) + (int) (((hitZ) * 4) % 4);
        return ret;
      }
      case 5:{
        ret = 1 + 4 * (int) (((1 - hitY) * 4) % 4) + (int) (((1 - hitZ) * 4) % 4);
        return ret;
      }
      default:{
        return 0;
      }

    }

  }

  /**
   * Overriden in a sign to provide the text.
   */
  @Override
  public Packet getDescriptionPacket() {
    NBTTagCompound nbttagcompound = new NBTTagCompound();
    this.writeToNBT(nbttagcompound);
    return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
  }

  @Override
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    NBTTagCompound nbttagcompound = pkt.func_148857_g();
    this.readFromNBT(nbttagcompound);
  }

  public Block thisBlock() {
    if(worldObj != null && blockType == null) {
      blockType = worldObj.getBlock(xCoord, yCoord, zCoord);
    }
    return blockType;
  }

  public int getBlockMetadata() {
    if(worldObj != null) {
      return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    } else {
      return 0;
    }
  }

  public ForgeDirection getFacing() {
    return ForgeDirection.getOrientation(side);
  }

  public Block getBlock(ForgeDirection side) {
    return worldObj.getBlock(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
  }

  public int getBlockMetadata(ForgeDirection side) {
    return worldObj.getBlockMetadata(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
  }

  public void updateBlock(ForgeDirection side) {
    worldObj.notifyBlocksOfNeighborChange(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ, getBlock(side));
  }

  public void notifySurroundings() {
    worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType(), 0);
  }

  public void updateBlock() {
    worldObj.scheduleBlockUpdate(xCoord, yCoord, zCoord, getBlockType(), 5);
  }

  public TileEntity getBlockTE(ForgeDirection side) {
    return worldObj.getTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
  }

  public void setBlock(ForgeDirection side, Block id, int meta) {
    worldObj.setBlock(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ, id, meta, 3);
  }

}
