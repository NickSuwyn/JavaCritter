package suppliedCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("GameBoard")
	private List<Tile> gameBoard;
	
	public List<Tile> getGameBoard() {
		return gameBoard;
	}
	
	public void setGameBoard(List<Tile> gameBoard) {
		this.gameBoard = gameBoard;
	}
	
}
