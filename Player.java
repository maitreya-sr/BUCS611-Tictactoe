/**
 * manages player information, including name, team, piece type,
 *  and game statistics such as wins, draws, and total games played.
 */


public class Player {
	
	private String playerName;
	private int playerNum;
	private String teamName;
	private String playerPieceType;
	private int gameCount;
	private int winCount;
	private int drawCount;
	
	public Player(String name, int num, String tName) {
		playerName = name;
		playerNum = num;
		teamName = tName;
		playerPieceType = "";
		gameCount = 0;
		winCount = 0;
		drawCount = 0;
	}
	
	
	public String getPlayerName() {
		return playerName;
	}
	
	
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	
	public String getTeamName() {
		return teamName;
	}
	
	
	public String getplayerPieceType() {
		return playerPieceType;
	}
	
	public void setplayerPieceType(String type) {
		playerPieceType = type;
	}
	
	
	
	public int getGameCount() {
		return gameCount;
	}
	
	public void updateGameCount() {
		this.gameCount++;
	}
	
	
	
	public int getWinCount() {
		return winCount;
	}
	
	public void updateWinCount() {
		this.winCount++;
	}
	
	
	
	public int getDrawCount() {
		return drawCount;
	}
	
	public void updateDrawCount() {
		this.drawCount++;
	}
	
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n+-----------------------------------------------------------+\n");
		sb.append(String.format("| %-10s | %-12s | %-8s | %-4s | %-4s | %-4s |\n", "Player", "Piece Type", "Total", "Win", "Loss", "Draw"));
		sb.append("+-----------------------------------------------------------+\n");
		sb.append(String.format("| %-10s | %-12s | %-8d | %-4d | %-4d | %-4d |\n", playerName, playerPieceType, gameCount, winCount, gameCount - winCount, drawCount));
		sb.append("+-----------------------------------------------------------+\n");
		return sb.toString();
	}
	

}
