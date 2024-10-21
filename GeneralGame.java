/**
 * handles the setup, player turns, game flow,
 *  and win/draw conditions for different team-based games,
 *  with abstract methods for game-specific logic.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class GeneralGame {
    
	protected String gameName;
	protected int playerNumber;
	protected int teamNumber;
	protected int playerPerTeam;
	protected int minBoardSize;
	protected String team1Name;
	protected String team2Name;
	protected String team3Name;
	protected String team4Name;
	// store team name
	protected List<String> teamList = new ArrayList<String>();
	// store the list that store player name
    protected List<List> teamPlayerList = new ArrayList<List>();
    protected List<Player> team1PlayerList = new ArrayList<Player>();
    protected List<Player> team2PlayerList = new ArrayList<Player>();
    protected List<Player> team3PlayerList = new ArrayList<Player>();
    protected List<Player> team4PlayerList = new ArrayList<Player>();    
    protected GeneralBoard board;
    protected int latestMove;
	protected int latestBlock;
    protected String latestPieceType;
    
    
    // get player's name and their chosen piece type
	public void getPlayerNamePieceType() {
		initTeam();
		initPlayer();
		int j = 0;
		for (String eachteam : teamList) {
			// OAC has fixed piece type
			if (gameName.equals("OAC")) {
				System.out.println("🔹 Please enter the name of the player for " + eachteam + ":");
				String name = new Scanner(System.in).nextLine();
		        Player player = new Player(name, playerNumber, eachteam);
		        teamPlayerList.get(j).add(player);
		        j++;
			} else {		    // designed for other games
				for (int i=1; i<playerPerTeam+1; i++) {
					// get user name
					System.out.println("🔹 Please enter the name of the player for " + eachteam + ":");
			        String name = new Scanner(System.in).nextLine();
			        Player player = new Player(name, playerNumber, eachteam);
			        teamPlayerList.get(j).add(player);
			        // get piece type
			        while (true) {
			        	System.out.println("Choose a piece type for " + name + " (X or O):");
			        	String pieceType = new Scanner(System.in).nextLine();
				        if (checkPieceType(pieceType)) {
				        	player.setplayerPieceType(pieceType);
				        	break;
				        }
			        }
				}
		        j++;
			}
		}
	};
	
	
	// init team. add team name into team name list
	private void initTeam() {
		if (teamNumber==2) {
				teamList.add(team1Name);
				teamList.add(team2Name);
		} else if (teamNumber==3) {
				teamList.add(team1Name);
				teamList.add(team2Name);
				teamList.add(team3Name);
		} else if (teamNumber==4) {
				teamList.add(team1Name);
				teamList.add(team2Name);
				teamList.add(team3Name);
				teamList.add(team4Name);
		} else {
			System.out.println("Wrong Game Index Setting, team number should be 2/3/4");
		}
	}
	
	// init player. 
	// add teamPlayerNameList into teamPlayerList according how many players are in a team
	private void initPlayer() {
		if (teamNumber==1) {
			teamPlayerList.add(team1PlayerList);
		} else if (teamNumber==2) {
			teamPlayerList.add(team1PlayerList);
			teamPlayerList.add(team2PlayerList);
		} else if (teamNumber==3) {
			teamPlayerList.add(team1PlayerList);
			teamPlayerList.add(team2PlayerList);
			teamPlayerList.add(team3PlayerList);
		} else if (teamNumber==4) {
			teamPlayerList.add(team1PlayerList);
			teamPlayerList.add(team2PlayerList);
			teamPlayerList.add(team3PlayerList);
			teamPlayerList.add(team4PlayerList);
		} else {
			System.out.println("Wrong Game Index Setting, player per team number should be 1/2/3/4");
		}
	}

	// abstract method
	// each game has different piece type. Implement by child classes.
	protected abstract boolean checkPieceType(String pieceType);


	// Preparation done! start game
	public void startGame() {
	    latestMove = 0;
		latestBlock = 0;
	    latestPieceType = "";
        gameSummary();
        System.out.println(" ===============================");
		getBoardSize();
        printBoard();
	    System.out.println("Enter index of cell to place piece.\n");
		
	    for (List playerList: teamPlayerList) {
	   		updateplayerGameCount(playerList);
		}
        playGame(teamPlayerList);     
    }
	
	// abstract method. each game has different board size Implement by child classes.
	protected abstract void getBoardSize();

	public void printBoard() {
		board.printBoard();
    }
	
	private void updateplayerGameCount(List<Player> playerList) {
		for (Player player : playerList) {
            player.updateGameCount();
        }
	}
	
	// each team play game one by one
	private void playGame(List<List> teamPlayerList) {
		while (true) {
			for (List playerList : teamPlayerList) {
				teamTurn(playerList);
			}
		}
	}
	
	// each player in a team play by turn
	private void teamTurn(List<Player> playerList) {
		for (Player player : playerList) {
			playerTurn(player);
		}
	}

	// details when a player play
	private void playerTurn(Player player) {
		
		// In OAC, each player can play 2 types of piece.
		if (gameName.equals("OAC")) {
			while (true) {
				System.out.println();
				System.out.println(player.getTeamName() + "  " + player.getPlayerName() + "'s turn:");
				String inputCellIndex = new Scanner(System.in).nextLine();
				// check user input 
				if (checkInteger(inputCellIndex)) {
					if (board.checkIntegerAviable(Integer.parseInt(inputCellIndex))) {
						String inputPieceType;
						// each player can play 2 types of piece.
						while (true) {
							System.out.println("Please choose piece type you want to move to " + inputCellIndex + ":");
							inputPieceType = new Scanner(System.in).nextLine();
							if (checkPieceType(inputPieceType) == true) {
								board.setCellValue(Integer.parseInt(inputCellIndex), inputPieceType);
								break;
							} else {
								System.out.println("Only \"O\" and \"X\" is permitted");
							}
						}
						printBoard();
						// mark latest move piece type and cell index for later process
						latestPieceType = inputPieceType;
						latestMove = Integer.parseInt(inputCellIndex);
						break;
					}
				} 
				else {
					System.out.println("Please input an integer:");
				}
			}
			
			if (latestPieceType.equals("O") || latestPieceType.equals("X")) {
				if (latestMove != 0 && board.win(latestMove)) {
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					System.out.println("Team orders win the game!");
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					// player in Orders team win. update his win count 
					for (Player pl : team1PlayerList) {
						pl.updateWinCount();
					}
					// ask whether again
					gameOver();
				}
			}
			
			// when board is full, means chaos team win
			if (board.fullBoard()) {
				System.out.println("- - - - - - - - - - - - - - - - - - -");
				System.out.println("Team chaos win the game!");
				System.out.println("- - - - - - - - - - - - - - - - - - -");
				// player in chaos team win. update his win count 
				for (Player pl : team2PlayerList) {
					pl.updateWinCount();
				}
				// ask whether again
				gameOver();
			}
			
		} 
		else {
			
			// Designed for other games but OAC 
			while (true) {
				System.out.println();
				System.out.println(player.getTeamName() + "  " + player.getPlayerName() + "'s turn:");
				String inputCellIndex = new Scanner(System.in).nextLine();
				// check whether user input is valid 
				if (gameName.equals("STTT")) {
					String[] parts = inputCellIndex.split(" ");
					char block = parts[0].charAt(0); 
					String[] coords = parts[1].split(","); 
					int row = Integer.parseInt(coords[0]); 
					int col = Integer.parseInt(coords[1]);

					int basePosition = getBasePosition(block);
					inputCellIndex = "" + (basePosition + row * 9 + col + 1);  // 更新坐标

					if (checkInteger(inputCellIndex)) {
						int cellIndex = Integer.parseInt(inputCellIndex);
						if (board.checkIntegerAviable(cellIndex)) {

							board.setCellValue(cellIndex, player.getplayerPieceType());
							printBoard();
							
							latestMove = cellIndex;
							latestBlock = getBlockFromCell(cellIndex); 
							break;
						}
					} else {
						System.out.println("Please input a valid position.");
					}
				}
			}
			// check win after a move is made
			if (gameName.equals("STTT")) {
				if (latestMove != 0 && board.win(latestMove)) {
					player.updateWinCount();
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					System.out.println(player.getTeamName() + " win the game!");
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					
					// 结束游戏
					gameOver();
			}
			else {
				if (latestMove != 0 && board.win(latestMove)) {
					// update last player (winner)'s win count 
					player.updateWinCount();
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					System.out.println(player.getTeamName() + " win the game!");
					System.out.println("- - - - - - - - - - - - - - - - - - -");
					// check whether again
					gameOver();
			}
			
			}
			
			// if board is full, then it's a draw
			if (board.fullBoard()) {
				System.out.println();
				System.out.println("- - - - - - - - - - - - - - - - - - -");
				System.out.println("           It's a draw!           ");
				System.out.println("- - - - - - - - - - - - - - - - - - -");
				// everyone has draw count +1
			    for (List playerList: teamPlayerList) {
			    	updateplayerDrawCount(playerList);
				}
				// check whether again
				gameOver();
			}
		}
	}
	}

	public int getBasePosition(char block) {
        switch (block) {
            case 'A': return 0;   // 小棋盘A: 1-9
            case 'B': return 3;   // 小棋盘B: 10-18
            case 'C': return 6;   // 小棋盘C: 19-27
            case 'D': return 27;  // 小棋盘D: 28-36
            case 'E': return 30;  // 小棋盘E: 37-45
            case 'F': return 33;  // 小棋盘F: 46-54
            case 'G': return 54;  // 小棋盘G: 55-63
            case 'H': return 57;  // 小棋盘H: 64-72
            case 'I': return 60;  // 小棋盘I: 73-81
            default: throw new IllegalArgumentException("Invalid block letter");
        }
    }

	private int getBlockFromCell(int cellIndex) {
		int row = (cellIndex - 1) / 9;
		int col = (cellIndex - 1) % 9;
		return (row / 3) * 3 + (col / 3); // 确定大棋盘中是哪个小棋盘
	}

	// check whether user input is an integer
	protected boolean checkInteger(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// update player's draw count
	private void updateplayerDrawCount(List<Player> playerList) {
		for (Player player : playerList) {
            player.updateDrawCount();
        }
	}
	
	// check whether play again
	private void gameOver() {
        // Ask if they want to play again
        if (again()) {
            // Start the game again
            startGame();
        } else {
            // Stop game and return to game library page
            System.out.println("💡 Game Over! Returning to Game Menu...");
            gameSummary();
            GameMenu.start();
        }
    }
	
	// check want to play again
	private boolean again() {
        while (true) {
            System.out.println("🔄 Do you want to play again? (y/n)");
            String again = new Scanner(System.in).nextLine();
            if (again.equals("y")) {
                return true;
            } else if (again.equals("n")) {
                return false;
            } else {
                System.out.println("⚠️ Invalid input. Please enter 'y' or 'n':");
            }
        }
    }
	
	// print game player summary
	private void gameSummary() {
		System.out.println();
		System.out.println();
		// for every team
		for (int i=0; i<teamNumber; i++) {
			System.out.println("  ================= " + teamList.get(i) + " =================  ");
			List<Player> list = new ArrayList<Player>();
			list = teamPlayerList.get(i);
			// for every player
			for (Player player : list) {
				// print detailed info and record
				System.out.println(player);
			}
		}
		System.out.println();
		System.out.println();
	}


}