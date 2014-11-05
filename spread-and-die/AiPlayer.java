import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class AiPlayer extends Player{

	int[] runTo = new int[2];
	Field f;
	int[] disease,
		  playerPosition;
	protected Random r = new Random();

	public Field.Movements move(Field f){
		this.f = f;
		playerPosition = f.getCurrentPlayerPosition();
		disease = f.getDiseaseStartingPosition();
		int outOfBoundsCells = countOutOfBoundsMovements();

		//if the players in a corner, stay there, you're probebly going to die!
		if(outOfBoundsCells > 1)
			return Field.Movements.STAY;
		else{
			//Check nearby cells for diseased or infected cells
			List<Field.Movements> canMove = new ArrayList<Field.Movements>();
			if(f.getCell(playerPosition[0]+1,playerPosition[1]) == Field.Disease.EMPTY)
				canMove.add(Field.Movements.DOWN);
			if(f.getCell(playerPosition[0]-1,playerPosition[1]) == Field.Disease.EMPTY)
				canMove.add(Field.Movements.UP);
			if(f.getCell(playerPosition[0],playerPosition[1]+1) == Field.Disease.EMPTY)
				canMove.add(Field.Movements.RIGHT);
			if(f.getCell(playerPosition[0],playerPosition[1]-1) == Field.Disease.EMPTY)
				canMove.add(Field.Movements.LEFT);

			if(canMove.size() + outOfBoundsCells == 4) return getToNearestCorner();
			if(canMove.size() > 0)
				return canMove.get(r.nextInt(canMove.size()));
			else
				return Field.Movements.STAY;
		}

	}

	public byte cure(){ return (byte)0x00; }

	public HashMap<Integer[], Character> manipulateRegions(Field f){
		return new HashMap<Integer[], Character>(){{ }};
	}

		//X is height, Y is width
	private Field.Movements getToNearestCorner(){

		/* BOTTOM */
		if(playerPosition[0] >= f.height()/2){
			/* RIGHT */
			if(playerPosition[1] >= f.width()/2)
				return new Field.Movements[] {Field.Movements.DOWN, Field.Movements.RIGHT}[r.nextInt(2)];
			/* LEFT */
			else
				return new Field.Movements[] {Field.Movements.DOWN, Field.Movements.LEFT}[r.nextInt(2)];
		/* TOP */
		} else {
			/* RIGHT */
			if(playerPosition[1] >= f.width()/2)
				return new Field.Movements[] {Field.Movements.UP, Field.Movements.RIGHT}[r.nextInt(2)];
			/* LEFT */
			else
				return new Field.Movements[] {Field.Movements.UP, Field.Movements.LEFT}[r.nextInt(2)];
		}
	}

	private int countOutOfBoundsMovements(){
		int outOfBoundsCells = 0;
		for(int[] c : new int[][] { {1,0}, {-1,0}, {0,1}, {0,-1} })
			if(f.getCell(playerPosition[0] + c[0],playerPosition[1] + c[1])==Field.Disease.OUTOFBOUNDS)
				++outOfBoundsCells;
		return outOfBoundsCells;

	}

}
