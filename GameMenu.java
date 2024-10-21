/*
 * presents available games, allows the user to select a game, and starts the chosen game.
 */
import java.util.Scanner;

public class GameMenu {

    // start game
    public static void start() {

        System.out.println("Hello there!");
        printGameList();

        // remind users to input
        System.out.println("=============================================");
        System.out.println("Enter the number of the game you want to play:");
        System.out.println("(or press Ctrl+C to exit the program)");
        System.out.println("=============================================");

        // users choose games
        while (true) {
            String selectNumber = new Scanner(System.in).nextLine();
            GeneralGame createGame = createGame(selectNumber);
            if (createGame == null) {
                System.out.println("Invalid input. Please enter a valid game number!");
            } else {
                createGame.getPlayerNamePieceType();
                createGame.startGame();
            }
        }
    }

    // print game list
    private static void printGameList() {
        System.out.println("Available Games:");
        GameList[] gameListValue = GameList.values();
        for (GameList eachGameListValue : gameListValue) {
            System.out.println("→ [" + eachGameListValue.getGameNum() + "] " + eachGameListValue);
        }
    }

    // create a game
    public static GeneralGame createGame(String number) {
        if (number.equals(GameList.TicTacToe.getGameNum())) {
            return new TicTacToeGame();
        } else if (number.equals(GameList.OrderAndChaos.getGameNum())) {
            return new OrderAndChaosGame();
        } else if (number.equals(GameList.SuperTicTacToe.getGameNum())){
            return new SuperTicTacToeGame();
        } else {
            return null;
        }
    }
}
