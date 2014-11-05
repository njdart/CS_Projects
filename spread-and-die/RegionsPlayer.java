import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RegionsPlayer extends AiPlayer{

	Random r = new Random();

	//http://stackoverflow.com/questions/6886712/c-sharp-to-java-dictionaries (4/12/13)
	public HashMap<Integer[],Character> manipulateRegions(Field f){
		int[] pos = f.getCurrentPlayerPosition();
		final Integer[] p = new Integer[]{(Integer)pos[0],(Integer)pos[1]};
		List<Character> l = new ArrayList<Character>();
		for(char region : Field.regionCharacters)
			l.add(region);

		for(int i = 0; i < l.size();i++)
			if(l.get(i) == f.getPlayersRegionCell())
				l.remove(i);
		HashMap m = new HashMap<Integer[], Character>();

		for(int[] c : new int[][] { {1,0}, {-1,0}, {0,1}, {0,-1} }){
			m.put(new Integer[]{pos[0]+c[0],pos[1]+c[1]}, l.get(r.nextInt(l.size())));
		}

		return m;
	}
}