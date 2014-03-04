package drawing;

import java.awt.Color;

public abstract class PaintObject {
	
	protected int x;
	protected int y;
	protected int size;
	protected int uuid;
	protected Color color;
						
	public PaintObject(int x, int y, int size, Color color){
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	/*==OVERRIDE==*/
	
	public abstract void draw();
	
	public abstract String toString();
	
	/*==Getters and setters==*/
	
	public int[] getPosition(){
		return new int[] {x, y};
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public double getDistanceFrom(int x, int y){
		return Math.sqrt(Math.sqrt(x - this.x) + Math.sqrt(y - this.y));
	}
	
	final protected void setUuid(int id){
		this.uuid = id;
	}
	
	final protected int getUuid(){
		return uuid;
	}
}
