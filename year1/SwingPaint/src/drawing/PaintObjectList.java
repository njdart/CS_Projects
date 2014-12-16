package drawing;

import java.util.ArrayList;

public class PaintObjectList {
	
	public enum ObjectTypes {
		CIRCLE, SQUARE, TRIANGLE//, OCTAGON, PENTAGON, SMILYFACE
	}
	
	private int latestUuid = 0;
	
	private ArrayList<PaintObject> objs = new ArrayList<PaintObject>();
	
	public void add(PaintObject obj){
		obj.setUuid(latestUuid++);
		objs.add(obj);
	}
	
	public PaintObject getObjectWithUuid(int uuid){
		for(PaintObject o : objs)
			if(o.getUuid() == uuid)
				return o;
		return null;
	}
	
	public ArrayList<PaintObject> asList(){
		return objs;
	}
	
	public void remove(int uuid){
		for(PaintObject obj : objs)
			if(obj.uuid == obj.getUuid())
				objs.remove(obj);
	}

	public void remove(PaintObject obj) {
		objs.remove(obj);
	}
}
