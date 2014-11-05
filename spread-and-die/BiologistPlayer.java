import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class BiologistPlayer extends AiPlayer{

	@Override
	public byte cure(){ 	//return a random byte representing a cure
		byte[] b = new byte[1];
		r.nextBytes(b);
		return b[0];
	}
}