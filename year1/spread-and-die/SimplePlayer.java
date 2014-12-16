import java.util.Random;
import java.util.HashMap;

public class SimplePlayer extends Player{

	private Random r = new Random();

	public Field.Movements move(Field f){
		switch(r.nextInt(4)){
			case 0:  return Field.Movements.LEFT;
			case 1:  return Field.Movements.RIGHT;
			case 2:  return Field.Movements.UP;
			case 3:  return Field.Movements.DOWN;
			default: return Field.Movements.STAY;
		}
	}

	public byte cure(){ return (byte)0x00; }

	public HashMap<Integer[], Character> manipulateRegions(Field f){
		return new HashMap<Integer[], Character>(){{ }};
	}
}