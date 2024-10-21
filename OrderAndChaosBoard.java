/**
 * specially designed for OrderAndChaos's board.
 * OAC rules for win is implemented here.
 */

public class OrderAndChaosBoard extends GeneralBoard {
    private int size;
	
	public OrderAndChaosBoard(int size) {
        this.size = size;
        rowSize = size;
        colSize = size;
        board = new String[size][size];
    }

    
	// @Override
    // protected boolean win(int latestMove) {
	// 	int latestMoveRow = super.getCellRow(latestMove);
	// 	int latestMoveColumn = super.getCellCol(latestMove);
	// 	String latestMoveValue = super.getCellValue(latestMove);
		
	// 	//check row
	// 	int numOa = 0;
	// 	int numOb = 0;
	// 	// check 'O' from this cell's upper cells 
	// 	for (int i = latestMoveColumn-1; i > -1; i--) {
	// 		if(board[latestMoveRow][i] == null) {break;}	//check null
	// 		if(!board[latestMoveRow][i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOa++;
	//         }
	// 	}
	// 	//check 'O' from this cell's lower cells
	// 	for (int i = latestMoveColumn+1; i < size; i++) {
	// 		if(board[latestMoveRow][i] == null) {break;}
	// 		if(!board[latestMoveRow][i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOb++;
	//         }
	// 	}
	// 	// if 5 continues 'O' in a row
	// 	if (numOa + numOb == 4) {
	// 		return true;
	// 	}
        
    //     //check column
	// 	numOa = 0;
	// 	numOb = 0;
	// 	// check 'O' from this cell's left cells
	// 	for (int i = latestMoveRow-1; i > -1; i--) {
	// 		if(board[i][latestMoveColumn] == null) {break;}
	// 		if(!board[i][latestMoveColumn].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOa++;
	//         }
	// 	}
	// 	// check 'O' from this cell's right cells
	// 	for (int i = latestMoveRow+1; i < size; i++) {
	// 		if(board[i][latestMoveColumn] == null) {break;}
	// 		if(!board[i][latestMoveColumn].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOb++;
	//         }
	// 	}
	// 	// if 5 continues 'O' in a column
	// 	if (numOa + numOb == 4) {
	// 		return true;
	// 	}
        
    //     //check diagonal \
	// 	numOa = 0;
	// 	numOb = 0;
	// 	// check 'O' from this cell's left-above cells
	// 	for (int i = 1; i < Math.min(latestMoveRow, latestMoveColumn)+1; i++) {
	// 		if(board[latestMoveRow-i][latestMoveColumn-i] == null) {break;}
	// 		if(!board[latestMoveRow-i][latestMoveColumn-i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOa++;
	//         }
	// 	}
	// 	// check 'O' from this cell's right-below cells
	// 	for (int i = 1; i < Math.min(size-latestMoveRow-1, size-latestMoveColumn-1)+1; i++) {
	// 		if(board[latestMoveRow+i][latestMoveColumn+i] == null) {break;}
	// 		if(!board[latestMoveRow+i][latestMoveColumn+i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOb++;
	//         }
	// 	}
	// 	// if 5 continues 'O' in diagonal
	// 	if (numOa + numOb == 4) {
	// 		return true;
	// 	}
            
    //     //check anti-diagonal /
	// 	numOa = 0;
	// 	numOb = 0;
	// 	// check 'O' from this cell's left-below cells
	// 	for (int i = 1; i < Math.min(size-latestMoveRow-1, latestMoveColumn)+1; i++) {
	// 		if(board[latestMoveRow+i][latestMoveColumn-i] == null) {break;}
	// 		if(!board[latestMoveRow+i][latestMoveColumn-i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOa++;
	//         }
	// 	}
	// 	// check 'O' from this cell's right-above cells
	// 	for (int i = 1; i < Math.min(latestMoveRow, size-latestMoveColumn-1)+1; i++) {
	// 		if(board[latestMoveRow-i][latestMoveColumn+i] == null) {break;}
	// 		if(!board[latestMoveRow-i][latestMoveColumn+i].equals(latestMoveValue)) {
	// 			break;
	//         } else {
	//         	numOb++;
	//         }
	// 	}
	// 	// if 5 continues 'O' in anti-diagonal
	// 	if (numOa + numOb == 4) {
	// 		return true;
	// 	}
		
	// 	// no winner yet
	// 	return false;
	// }

// }
// @Override
// protected boolean win(int latestMove) {
//     int latestMoveRow = super.getCellRow(latestMove);
//     int latestMoveColumn = super.getCellCol(latestMove);

//     // Check both 'X' and 'O' for Order victory
//     return checkDirection(latestMoveRow, latestMoveColumn);
// }

// private boolean checkDirection(int row, int col) {
//     // Check row
//     if (checkLine(row, col, 0, 1)) return true; // Horizontal
//     // Check column
//     if (checkLine(row, col, 1, 0)) return true; // Vertical
//     // Check diagonal \
//     if (checkLine(row, col, 1, 1)) return true; // Diagonal \
//     // Check anti-diagonal /
//     if (checkLine(row, col, 1, -1)) return true; // Anti-diagonal /
    
//     return false;
// }

// private boolean checkLine(int row, int col, int rowIncrement, int colIncrement) {
//     int count = 1;  // Include the latest move

//     // Check in one direction
//     count += countInDirection(row, col, rowIncrement, colIncrement);
//     // Check in the opposite direction
//     count += countInDirection(row, col, -rowIncrement, -colIncrement);

//     // Return true if there are 5 or more consecutive pieces
//     return count >= 5;
// }

// private int countInDirection(int row, int col, int rowIncrement, int colIncrement) {
//     int count = 0;
//     int currentRow = row + rowIncrement;
//     int currentCol = col + colIncrement;

//     while (currentRow >= 0 && currentRow < size && currentCol >= 0 && currentCol < size) {
//         if (board[currentRow][currentCol] == null) {
//             break;
//         }
//         count++;
//         currentRow += rowIncrement;
//         currentCol += colIncrement;
//         // Stop counting if we find more than 5 in a row
//         if (count == 5) {
//             return count;
//         }
//     }

//     return count;
// }
// }
@Override
protected boolean win(int latestMove) {
    int latestMoveRow = super.getCellRow(latestMove);
    int latestMoveColumn = super.getCellCol(latestMove);
    String latestMoveValue = super.getCellValue(latestMove);

    // Check directions: horizontal, vertical, diagonal (\), and anti-diagonal (/)
    return checkDirection(latestMoveRow, latestMoveColumn, latestMoveValue, 0, 1) || // Horizontal
           checkDirection(latestMoveRow, latestMoveColumn, latestMoveValue, 1, 0) || // Vertical
           checkDirection(latestMoveRow, latestMoveColumn, latestMoveValue, 1, 1) || // Diagonal \
           checkDirection(latestMoveRow, latestMoveColumn, latestMoveValue, 1, -1);  // Anti-diagonal /
}

private boolean checkDirection(int row, int col, String value, int rowDelta, int colDelta) {
    int count = 1; // Start with the latest move
    
    // Check one direction (forward)
    count += countInDirection(row, col, value, rowDelta, colDelta);
    
    // Check the opposite direction (backward)
    count += countInDirection(row, col, value, -rowDelta, -colDelta);
    
    // If there are 5 or more in a row, return true (win condition met)
    return count >= 5;
}

private int countInDirection(int row, int col, String value, int rowDelta, int colDelta) {
    int count = 0;
    
    while (true) {
        row += rowDelta;
        col += colDelta;
        
        // Check if out of bounds
        if (row < 0 || row >= size || col < 0 || col >= size) {
            break;
        }
        
        // Check if the current cell matches the value
        if (board[row][col] == null || !board[row][col].equals(value)) {
            break;
        }
        
        count++;
    }
    
    return count;
}
}
