/* "Spread and Die"
 *
 * A First year java assignment for
 * CS12130.
 *
 * Candidate: Nicholas Dart nid21
 * Supervisor: Reyer Zwiggelaar
 */

import java.util.HashMap;

public abstract class Player{

	public abstract Field.Movements move(Field f);

	public abstract byte cure();

	public abstract HashMap<Integer[], Character> manipulateRegions(Field f);

}
