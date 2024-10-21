/**
 * manages board operations like printing, validating moves, 
 * handling cell values, and checking for a full board, 
 * with an abstract method for game-specific win conditions.
 */

public abstract class GeneralBoard {
    protected int rowSize;
    protected int colSize;
    protected String[][] board;
    protected int placedPieceNum;
    
    // print most updated game board with cell index in it
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
                if (board[i][j] != null) {// && !board[i][j].equals("")) {
                	vline += "  ";
                	vline += board[i][j];
                	vline += " ";
                } else {
                    int cellnumber = j+ rowSize * i + 1;
                    vline += String.format("%3d", cellnumber)  + " ";
                }
            }
            vline += "|";
            System.out.println(hLine);
            System.out.println(vline);
        }
		
        System.out.println(hLine);
	}

	// check whether the user input is available in the current situation 
	public boolean checkIntegerAviable(int inputCellIndex) {
		//toString(board);
		if (inputCellIndex < 1 || inputCellIndex > (rowSize * colSize)) {
			System.out.println("Invalid! Please choose a valid cell:");
			return false;
		} else if (getCellValue(inputCellIndex) != null) {
			System.out.println("This cell is occupied with '"+ getCellValue(inputCellIndex) +"'. Please choose an empty cell:");
			return false;
		}
		return true;
	}

    // get value in the cell with input cell index
    public String getCellValue(int num) {
        int row = (num-1) / rowSize;
        int col = (num-1) % colSize;
        return board[row][col];
    }
    
    // get value in the cell with row-col location input
    public String getCellValue(int rowNum, int colNum) {
        return board[rowNum][colNum];
    }
    
    // set value in the cell with input cell index
    public void setCellValue(int num, String pieceType) {
        int row = (num-1) / rowSize;
        int col = (num-1) % colSize;
        setCellValue(row, col, pieceType);
		//toString(board);
		//System.out.println("1 setting [" + row + "][" + col + "] to '" + pieceType+ "'");
    }
    
    // set value in the cell with row-col location input
    public void setCellValue(int rowNum, int colNum, String pieceType) {
    	//System.out.println("2 setting [" + rowNum + "][" + colNum + "] to '" + pieceType+ "'");
        this.board[rowNum][colNum] = pieceType;
        placedPieceNum++;
    }

    // check whether the board is full and end the game
	public boolean fullBoard() {
		if (placedPieceNum == rowSize * colSize) {
			return true;
		} else {
			return false;
		}
	}
	
	// get row number according to input cell index
    public int getCellRow(int num) {
        int row = (num-1) / rowSize;
        return row;
    }
	
	// get column number according to input cell index
    public int getCellCol(int num) {
        int col = (num-1) % colSize;
        return col;
    }
	
	
	// customised toString format
    public void toString(String[][] board) {
    	String cellValue;
    	for (int i = 0; i < rowSize; i++) {
        	cellValue = "[ ";
            for (int j = 0; j < colSize; j++) {
            	cellValue += board[i][j] + " ";
            }
            cellValue += " ]";
        	System.out.println(cellValue);
    	}
    }

    // abstract method, leave to GeneralBoard's child class to implement
    // each game has different rules to determine winner
	protected abstract boolean win(int latestMove);

}
