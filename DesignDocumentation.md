# An explanation of classes
GameMenu.java: presents available games, allows the user to select a game, and starts the chosen game.
GameList.java: enum stores and manages available games, making it easier to add or remove games from the game library.
GeneralBoard.java: manages board operations like printing, validating moves, handling cell values, and checking for a full board, with an abstract method for game-specific win conditions.
GeneralGame.java: handles the setup, player turns, game flow, and win/draw conditions for different team-based games, with abstract methods for game-specific logic.
OrderAndChaosBoard.java: specially designed for OrderAndChaos's board. OAC rules for win is implemented here.
OrderAndChaosGame.java: Methods here are designed for OAC. Some basic unchanged property is provided in constructor.
Player.java: manages player information, including name, team, piece type, and game statistics such as wins, draws, and total games played.
TicTacToeBoard.java: A class that specially designed for TicTacToe board. TTT rules for win is implemented here.
TicTacToeGame.java: Methods here are designed for TTT. Some basic unchanged property is provided in constructor.
SuperTicTacToeBoard.java: A class that specially designed for SuperTicTacToe board. STTT rules for win is implemented here.
SuperTicTacToeGame.java: Methods here are designed for STTT. Some basic unchanged property is provided in constructor.

# Flexibility and Extensibility
The design of this project leverages object-oriented principles to ensure that new games and variations can be added without significant changes to the existing codebase. This is achieved through:

Abstract Base Classes (GeneralGame, GeneralBoard): These serve as the backbone for game and board operations. They abstract common functionality and allow concrete subclasses (such as TicTacToeGame, OrderAndChaosGame) to implement game-specific logic. This design promotes code reuse while allowing flexibility in game rules.

Separation of Concerns: Each game’s specific logic (such as win conditions and board setup) is encapsulated in its respective board and game classes. This isolation makes the system more maintainable and scalable.

Enum-Based Game Management (GameList): The enum structure allows for easy registration of new games. The game menu can dynamically present available games without hardcoding specific game logic into the GameMenu class.

# Modifications for New Turn-Based Variants
To allow for new turn-based variants (e.g., Connect 4), the following steps would be necessary:

Create a New Board Class: For any new game that uses a grid-based board, a new class (e.g., ConnectFourBoard) should extend GeneralBoard. The specific logic for win conditions (e.g., checking for four-in-a-row for Connect 4) would be implemented in this class.

Create a New Game Class: A new game class (e.g., ConnectFourGame) should extend GeneralGame and implement game-specific methods. This would include any unique setup, turn-taking, or draw/win conditions.

Update GameList: The new game should be added to the GameList enum to allow it to be selected and started through the GameMenu.


