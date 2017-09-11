package codeGoesHere;

import java.util.List;

import suppliedCode.Critter;
import suppliedCode.Move;
import suppliedCode.Tile;

public class CritterImpl implements Critter {
	
	int counter = 0;

	@Override
	public Move chooseMove(List<Tile> tiles) {

		if (counter == 0) {
			counter++;
			return Move.CLOCKWISE;
		} else if (counter == 1) {
			counter++;
			return Move.COUNTERCLOCKWISE;
		} else if (counter == 2) {
			counter++;
			return Move.MOVE;
		} else {
			counter = 0;
			return Move.STAY;
		}
	}
	
	

}
