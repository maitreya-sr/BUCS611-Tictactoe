/**
 * A class that specially designed for TicTacToe board.
 * TTT rules for win is implemented here.
 * 
 */

public class TicTacToeBoard extends GeneralBoard {
    private int size;

    public TicTacToeBoard(int size) {
        this.size = size;
        rowSize = size;
        colSize = size;
        board = new String[size][size];
    }

    
    public int getSize() {
        return size;
    }

    
// 	@Override
// 	protected boolean win(int latestMove) {
// 		int latestMoveRow = super.getCellRow(latestMove);
// 		int latestMoveColumn = super.getCellCol(latestMove);
// 		String latestMoveValue = super.getCellValue(latestMove);
		
// 		//check row
//         for(int i = 0; i < size; i++){
//             if(board[latestMoveRow][i] != latestMoveValue )
//                 break;
//         	// all cells in this row is in same mark
//             if(i == size-1){
//             	//System.out.println("Detect Victory! On row " + latestMoveRow);
//                 return true; 
//             }
//         }
        
//         //check column
//         for(int i = 0; i < size; i++){
//             if(board[i][latestMoveColumn] != latestMoveValue )
//                 break;
//         	// all cells in this column is in same mark
//             if(i == size-1){
//             	//System.out.println("Detect Victory! On Column " + latestMoveColumn);
//                 return true; 
//             }
//         }
        
//         //check diagonal \
//         if(latestMoveRow == latestMoveColumn){
//             for(int i = 0; i < size; i++){
//             	// check whether in the line of diagonal
//                 if(board[i][i] != latestMoveValue)
//                     break;
//             	// all cells in this diagonal is in same mark
//                 if(i == size-1){
//                    //System.out.println("Detect Victory! On diagonal");
//                    return true;
//                 }
//             }
//         }
            
//         //check anti-diagonal /
//         if(latestMoveRow + latestMoveColumn == size - 1){
//             for(int i = 0; i < size; i++){
//             	// check whether in the line of anti-diagonal
//                 if(board[i][(size-1)-i] != latestMoveValue)
//                     break;
//             	// all cells in this anti-diagonal is in same mark
//                 if(i == size-1){
//                     //System.out.println("Detect Victory! On anti-diagonal");
//                 	return true;
//                 }
//             }
//         }
//         //no winner yet
// 		return false;
// 	}
	
	
// }

@Override
protected boolean win(int latestMove) {
    int latestMoveRow = super.getCellRow(latestMove);
    int latestMoveColumn = super.getCellCol(latestMove);
    String latestMoveValue = super.getCellValue(latestMove);
    
    // Check row and column for a win
    if (checkLine(latestMoveRow, latestMoveColumn, latestMoveValue)) {
        return true;
    }
    
    // Check main diagonal for a win
    if (latestMoveRow == latestMoveColumn && checkDiagonal(latestMoveValue)) {
        return true;
    }
    
    // Check anti-diagonal for a win
    if (latestMoveRow + latestMoveColumn == size - 1 && checkAntiDiagonal(latestMoveValue)) {
        return true;
    }
    
    // No winner yet
    return false;
}

private boolean checkLine(int row, int col, String value) {
    // Check the specified row for a win
    for (int i = 0; i < size; i++) {
        if (board[row][i] != value) {
            break; // Exit if a mismatch is found
        }
        if (i == size - 1) {
            return true; // All cells in this row match
        }
    }
    
    // Check the specified column for a win
    for (int i = 0; i < size; i++) {
        if (board[i][col] != value) {
            break; // Exit if a mismatch is found
        }
        if (i == size - 1) {
            return true; // All cells in this column match
        }
    }
    
    return false; // No win found in row or column
}

private boolean checkDiagonal(String value) {
    // Check the main diagonal for a win
    for (int i = 0; i < size; i++) {
        if (board[i][i] != value) {
            return false; // Exit if a mismatch is found
        }
    }
    return true; // All cells in the main diagonal match
}

private boolean checkAntiDiagonal(String value) {
    // Check the anti-diagonal for a win
    for (int i = 0; i < size; i++) {
        if (board[i][(size - 1) - i] != value) {
            return false; // Exit if a mismatch is found
        }
    }
    return true; // All cells in the anti-diagonal match
}
}