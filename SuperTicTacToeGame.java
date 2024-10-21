import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuperTicTacToeGame extends GeneralGame {
    public SuperTicTacToeGame() {
		gameName = "STTT";
    	playerNumber = 2;
    	teamNumber = 2;
    	playerPerTeam = 1;
    	minBoardSize = 3;
    	team1Name = "Team 1";
    	team2Name = "Team 2";
    }
	
	private String[] pieceTypeArray = {"O", "X"};
	
	@Override
	protected boolean checkPieceType(String str) {
		//System.out.println( "pT[0]: " + pieceTypeArray[0] + "  pT[1]: " + pieceTypeArray[1]);
		if (str.equals(pieceTypeArray[0]) || str.equals(pieceTypeArray[1])) {
			for (List playerList : teamPlayerList) {
				List<Player> list = new ArrayList<>();
				list = playerList;
			for (Player eachplayer : list){
				if (eachplayer.getplayerPieceType().equals(str)) {
					System.out.println("'" + str + "'" + " has been chosen"
					 + " by other player, please choose other piece type");
					return false;
				} else {
					return true;
				}
			}}
		}
		System.out.println("Only '" + pieceTypeArray[0] + "' or '" + pieceTypeArray[1]+ "' is permitted");
		return false;
	}
	
	// size limited between 3 ~ 10
	@Override
    protected void getBoardSize() {
		// int size;
        // while (true) {
        //     System.out.println("Please enter board size (select an integer from 3 to 10): ");
        //     String inputSizeString = new Scanner(System.in).nextLine(); 
        //     if (checkInteger(inputSizeString)) {
        //     	int inputSize = Integer.parseInt(inputSizeString);
        //         if (inputSize >= minBoardSize && inputSize <= 10) {
        //         	size = inputSize;
        //             break;
        //         }
        //     }
        // }
        // System.out.println("Set board size to " + size + "*" + size);
        board = new SuperTicTacToeBoard(9);
	}
}
