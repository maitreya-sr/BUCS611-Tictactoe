/**
 * enum stores and manages available games, making it easier to add or remove games from the game library.
 */

public enum GameList {
	TicTacToe("1", "Tic Tac Toe"), 
	OrderAndChaos("2", "Order And Chaos"),
    SuperTicTacToe("3", "Super Tic Tac Toe");
    private final String gameNum;
    private final String gameName;
	
    GameList(String number, String name) {
        gameNum = number;
        gameName = name;
    }
    
    public String getGameName() {
        return gameName;
    }
    
	public String getGameNum() {
        return gameNum;
    }
    
    //override toString method in Enum class
    public String toString() {
        return "Enter " + gameNum + " to play " + gameName;
    }
}
