package drawing;

import java.awt.Color;
import java.awt.Graphics;

public abstract class PaintObject {
	
	protected int x;
	protected int y;
	protected int size;
	protected int uuid;
	protected Color color;
	protected int grabThreshold = 10;
						
	public PaintObject(int x, int y, int size, Color color){
		this.x = x;
		this.y = y;
		this.size = size;
		this.color = color;
	}
	
	/*==OVERRIDE==*/
	
	public abstract void draw(Graphics g);
	public abstract void remove(Graphics g);
	public abstract String getType();
	
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
	
	final protected void setUuid(int id){
		this.uuid = id;
	}
	
	final protected int getUuid(){
		return uuid;
	}
	
	public double getDistanceFrom(int x, int y){
		double dist = Math.sqrt(Math.pow((this.x - x + size/2), 2) * Math.pow((this.y - y + size/2), 2));	//Pythagoras
		System.out.println("Distance from (" + this.x + "," + this.y + ") to (" + x + "," + y + ") is " + dist);
		return dist;
	}
	
	public String toString() {
		return getType() + " " + getUuid() + " { Pos: (" + x + "," + y + "), size " + size +
			   " color: [" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "] }";
	}
	
	public int getThreshold(){
		return grabThreshold;
	}
	
}
