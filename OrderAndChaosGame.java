/**
 * Methods here are designed for OAC.
 * Some basic unchanged property is provided in constructor. 
 */

public class OrderAndChaosGame extends GeneralGame {

	// property that unchanged
    public OrderAndChaosGame() {
    	gameName = "OAC";
    	playerNumber = 2;
    	teamNumber = 2;
    	playerPerTeam = 1;
    	minBoardSize = 6;
    	team1Name = "Team order";
    	team2Name = "Team chaos";
    }
	
    private String[] pieceTypeArray = {"O", "X"};
	
    
	@Override
	protected boolean checkPieceType(String pieceType) {
		if (pieceType.equals(pieceTypeArray[0]) || pieceType.equals(pieceTypeArray[1])) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	protected void getBoardSize() {
		board = new OrderAndChaosBoard(minBoardSize);
		
	}

}
