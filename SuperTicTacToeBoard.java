// public class SuperTicTacToeBoard extends GeneralBoard {
//     private int size;

//     public SuperTicTacToeBoard(int size) {
//         this.size = size;
//         rowSize = size;
//         colSize = size;
//         board = new String[size][size];
//     }

    
//     public int getSize() {
//         return size;
//     }

//     public void printBoard() {
//         System.out.println("================================");
// 		String hLine = "";
// 		for (int i = 0; i < colSize; i++) {
// 			hLine += "+----";
// 		}
// 		hLine += "+";
		
// 		for (int i = 0; i < rowSize; i++) {
//             String vline = "";
//             for (int j = 0; j < colSize; j++) {
//             	vline += "|";
//                 if (board[i][j] != null) {// && !board[i][j].equals("")) {
//                 	vline += "  ";
//                 	vline += board[i][j];
//                 	vline += " ";
//                 } else {
//                     int cellnumber = j+ rowSize * i + 1;
//                     vline += "    ";
//                 }
//             }
//             vline += "|";
//             System.out.println(hLine);
//             System.out.println(vline);
//         }
		
//         System.out.println(hLine);
// 	}

    
//     protected boolean win(int latestMove, int latestBlock) {
//         // latestMove: 表示当前小棋盘中的落子位置
//         // latestBlock: 表示当前大棋盘中小棋盘的位置
        
//         int latestMoveRow = super.getCellRow(latestMove);
//         int latestMoveColumn = super.getCellCol(latestMove);
//         String latestMoveValue = super.getCellValue(latestMove);
    
//         // 1. 首先判断当前小棋盘（latestBlock）是否有胜利者
//         if (checkSmallBoardWin(latestBlock, latestMoveRow, latestMoveColumn, latestMoveValue)) {
//             // 2. 如果当前小棋盘胜利，接下来判断大棋盘的胜利
//             if (checkBigBoardWin(latestBlock, latestMoveValue)) {
//                 return true; // 大棋盘也连成一线，游戏结束
//             }
//         }
    
//         return false; // 未发现胜利者
//     }
    
//     private boolean checkSmallBoardWin(int block, int row, int col, String value) {
//         // 这里假设每个小棋盘有独立的board，比如 boardBlocks[block] 表示第block个小棋盘
//         return checkLine(boardBlocks[block], row, col, value) ||
//                checkDiagonal(boardBlocks[block], value) ||
//                checkAntiDiagonal(boardBlocks[block], value);
//     }
    
//     private boolean checkBigBoardWin(int latestBlock, String value) {
//         // latestBlock 表示当前赢得的小棋盘的位置，例如从1到9
//         int row = latestBlock / 3; // 大棋盘中的行号
//         int col = latestBlock % 3; // 大棋盘中的列号
    
//         // 检查大棋盘的行、列和对角线是否有3个小棋盘连成一线
//         return checkLine(bigBoard, row, col, value) ||  // 检查大棋盘的行和列
//                (row == col && checkDiagonal(bigBoard, value)) ||  // 检查主对角线
//                (row + col == 2 && checkAntiDiagonal(bigBoard, value));  // 检查副对角线
//     }
    
//     // 复用已有的行和列检查方法
//     private boolean checkLine(String[][] board, int row, int col, String value) {
//         for (int i = 0; i < 3; i++) {
//             if (!board[row][i].equals(value)) break;
//             if (i == 2) return true; // 行成功
//         }
    
//         for (int i = 0; i < 3; i++) {
//             if (!board[i][col].equals(value)) break;
//             if (i == 2) return true; // 列成功
//         }
    
//         return false;
//     }
    
//     // 复用已有的对角线检查方法
//     private boolean checkDiagonal(String[][] board, String value) {
//         for (int i = 0; i < 3; i++) {
//             if (!board[i][i].equals(value)) {
//                 return false;
//             }
//         }
//         return true;
//     }
    
//     private boolean checkAntiDiagonal(String[][] board, String value) {
//         for (int i = 0; i < 3; i++) {
//             if (!board[i][2 - i].equals(value)) {
//                 return false;
//             }
//         }
//         return true;
//     }
    
// }

public class SuperTicTacToeBoard extends GeneralBoard {
    private int size;
    private String[][][] boardBlocks; // 存储每个小棋盘的状态（3x3的网格）
    private String[][] bigBoard; // 存储大棋盘状态，表示每个小棋盘是否胜利（3x3的网格）

    public SuperTicTacToeBoard(int size) {
        this.size = size;
        rowSize = size;
        colSize = size;
        board = new String[size][size];
        boardBlocks = new String[9][3][3]; 
        for (int block = 0; block < 9; block++) { 
            for (int row = 0; row < 3; row++) {   
                for (int col = 0; col < 3; col++) { 
                    boardBlocks[block][row][col] = "-1"; 
                }
            }
        }
        bigBoard = new String[3][3];
    }

    public int getSize() {
        return size;
    }

    public void printBoard() {
        System.out.println("================================");
        String hLine = "";
        for (int i = 0; i < colSize; i++) {
            hLine += "+----";
        }
        hLine += "+";

        for (int i = 0; i < rowSize; i++) {
            String vline = "";
            for (int j = 0; j < colSize; j++) {
                vline += "|";
                if (board[i][j] != null) {
                    vline += "  ";
                    vline += board[i][j];
                    vline += " ";
                } else {
                    int cellnumber = j + rowSize * i + 1;
                    vline += "    ";
                }
            }
            vline += "|";
            System.out.println(hLine);
            System.out.println(vline);
        }

        System.out.println(hLine);
    }

    public void setCellValue(int num, String pieceType) {
        int row = (num-1) / rowSize;
        int col = (num-1) % colSize;
        setCellValue(row, col, pieceType);
    }

    public void setCellValue(int rowNum, int colNum, String pieceType) {
        int blockRow = rowNum / 3; 
        int blockCol = colNum / 3; 
        int blockIndex = blockRow * 3 + blockCol; 

        int relativeRow = rowNum % 3; 
        int relativeCol = colNum % 3;

        this.boardBlocks[blockIndex][relativeRow][relativeCol] = pieceType;
        this.board[rowNum][colNum] = pieceType;

        placedPieceNum++;
    }

    protected boolean win(int latestMove) {
        
        int ro = (latestMove - 1) / 9; // 大棋盘的行（0-8）
        int co = (latestMove - 1) % 9; // 大棋盘的列（0-8）
        
        int blockRow = ro / 3; // 小棋盘所在的行（0-2）
        int blockCol = co / 3; // 小棋盘所在的列（0-2）

        int latestBlock = blockRow * 3 + blockCol;

        int latestMoveRow = super.getCellRow(latestMove);
        int latestMoveColumn = super.getCellCol(latestMove);
        String latestMoveValue = super.getCellValue(latestMove);

        if (checkSmallBoardWin(latestBlock, latestMoveRow, latestMoveColumn, latestMoveValue)) {
            int row = latestBlock / 3;
            int col = latestBlock % 3;
            bigBoard[row][col] = latestMoveValue;

            if (checkBigBoardWin(row, col, latestMoveValue)) {
                return true; 
            }
        }

        return false;
    }

    private boolean checkSmallBoardWin(int block, int row, int col, String value) {
        int blockRow = block / 3; 
        int blockCol = block % 3; 

        int blockStartRow = blockRow * 3; 
        int blockStartCol = blockCol * 3; 

        row = row - blockStartRow;
        col = col - blockStartCol;
        
        return checkLine(boardBlocks[block], row, col, value) ||
               checkDiagonal(boardBlocks[block], value) ||
               checkAntiDiagonal(boardBlocks[block], value);
    }

    private boolean checkBigBoardWin(int row, int col, String value) {
        return checkLine(bigBoard, row, col, value) ||  
               (row == col && checkDiagonal(bigBoard, value)) ||  
               (row + col == 2 && checkAntiDiagonal(bigBoard, value));  
    }

    private boolean checkLine(String[][] board, int row, int col, String value) {
        
        for (int i = 0; i < 3; i++) {
            if (board[row][i] != value) break;
            if (i == 2) return true; // 行成功
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][col] != value) break;
            if (i == 2) return true; // 列成功
        }

        return false;
    }

    private boolean checkDiagonal(String[][] board, String value) {
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != value) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(String[][] board, String value) {
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] != value) {
                return false;
            }
        }
        return true;
    }
}
