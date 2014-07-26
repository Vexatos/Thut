package thut.tech.common.blocks.tileentity;

import cpw.mods.fml.common.Optional;
import li.cil.oc.api.network.Arguments;
import li.cil.oc.api.network.Callback;
import li.cil.oc.api.network.Context;
import thut.reference.ThutTechReference;
import thut.util.LogHelper;

/**
 * @author Vexatos
 */
@SuppressWarnings("unused")
@Optional.InterfaceList({
    @Optional.Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = ThutTechReference.MOD_OPENCOMPUTERS)
})
public class TileEntityLiftControl extends TileEntityLiftAccess implements li.cil.oc.api.network.SimpleComponent {

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Override
  public String getComponentName() {
    return "elevator";
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Callback(doc = "'call(floor:number):boolean'\n"
      + "  Tries to call the elevator to a certain floor. Returns 'true' on success, 'false' and an error message otherwise.")
  public Object[] call(Context context, Arguments args) {
    if(args.count() >= 1 && args.checkInteger(0) >= 1 && args.checkInteger(0) <= 16) {
      int lFloor = args.checkInteger(0);
      if(!worldObj.isRemote && lift != null) {
        if(!lift.called && doesFloorExist(lFloor)) {
          buttonPress(lFloor);
          calledFloor = lift.destinationFloor;
          worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
          LogHelper.debug(calledFloor + " " + lFloor + " " + lift);
        } else if(!doesFloorExist(lFloor)) {
          return new Object[] { false, "floor does not exist." };
        } else {
          return new Object[] { false, "elevator is currently moving." };
        }
      }
    } else {
      args.checkInteger(0);
    }
    return new Object[] { true };
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Callback(doc = "'isReady():boolean'\n"
      + "  Returns 'true' if the elevator can be called, 'false' if it can not (because it is already moving).")
  public Object[] isReady(Context context, Arguments args) {
    return new Object[] { !lift.called };
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Callback(doc = "'getLocalFloor():number'\n"
      + "  Returns the number of the floor the currently accessed Elevator Control is set to.")
  public Object[] getLocalFloor(Context context, Arguments args) {
    return new Object[] { this.floor };
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Callback(doc = "'getElevatorFloor():number'\n"
      + "  Returns the number of the floor the elevator is currently at. If the elevator is moving, the function returns the destination floor as its second argument.")
  public Object[] getElevatorFloor(Context context, Arguments args) {
    if(lift.destinationFloor >= 1) {
      return new Object[] { lift.currentFloor, lift.destinationFloor };
    }
    return new Object[] { lift.currentFloor };
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  private boolean doesFloorExist(int lFloor) {
    if(lift.floorArray[(lFloor - 1)] != null) {
      for(int j = 0; j < 4; j++) {
        if(lift.floorArray[lFloor - 1][j] != null && lift.floorArray[lFloor - 1][j].length == 3) {
          int x = lift.floorArray[lFloor - 1][j][0];
          int y = lift.floorArray[lFloor - 1][j][1];
          int z = lift.floorArray[lFloor - 1][j][2];
          if(worldObj.getTileEntity(x, y, z) != null && worldObj.getTileEntity(x, y, z) instanceof TileEntityLiftAccess) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Optional.Method(modid = ThutTechReference.MOD_OPENCOMPUTERS)
  @Callback(doc = "'doesFloorExist(floor:number):boolean'\n"
      + "  Returns 'true' if the floor exists and thus can be called; the function returns 'false' if the floor does not exist.")
  public Object[] doesFloorExist(Context context, Arguments args) {
    if(args.count() >= 1 && args.checkInteger(0) >= 1 && args.checkInteger(0) <= 16) {
      return new Object[] { doesFloorExist(args.checkInteger(0)) };
    } else {
      args.checkInteger(0);
    }
    return new Object[] { false, "floor is not a number between 1 and 16" };
  }
}
