package drawing;

import java.util.ArrayList;

public class PaintObjectList {
	
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
}
