package thut.api.entity;

import net.minecraft.entity.Entity;
import thut.api.maths.Matrix3;
import thut.api.maths.Vector3;

import java.util.concurrent.ConcurrentHashMap;


public interface IMultiBox 
{
	
	public abstract void setBoxes();
	public abstract void setOffsets();

	public abstract ConcurrentHashMap<String, Matrix3> getBoxes();
	public abstract void addBox(String name, Matrix3 box);
	
	public abstract ConcurrentHashMap<String, Vector3> getOffsets();
	public abstract void addOffset(String name, Vector3 offset);
	
	public abstract void applyEntityCollision(Entity e);
	
	public abstract Matrix3 bounds(Vector3 target);
	
	abstract void checkCollision();
	
	
}
