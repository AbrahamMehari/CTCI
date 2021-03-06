package neetcode.arraysNHashing;

import java.util.HashSet;

/**
 * Determine if 9x9 Sodoku board is valid. Only the filled cells need to be validated according to the following
 * rules
 * 
 * 1. Each row must contain the digits 1 - 9
 * 2. Each column must contain 1 - 9 without repetition
 * 3. Each of the nine 3x3 sub-boxes of the grid must contain the 1-9 without repetition.
 * Input: board = 
 * 
	[['5','3','.'||,'.','7','.'||,'.','.','.']
	,['6','.','.'||,'1','9','5'||,'.','.','.']
	,['.','9','8'||,'.','.','.'||,'.','6','.']
	==========================================
	,['8','.','.'||,'.','6','.'||,'.','.','3']
	,['4','.','.'||,'8','.','3'||,'.','.','1']
	,['7','.','.'||,'.','2','.'||,'.','.','6']
	==========================================
	,['.','6','.'||,'.','.','.'||,'2','8','.']
	,['.','.','.'||,'4','1','9'||,'.','.','5']
	,['.','.','.'||,'.','8','.'||,'.','7','9']]
	
	Output: true
 * 
 * @author Er. Abraham Bisrat https://github.com/abrahammehari
 *
 */
public class ValidSodoku {
	public static void p(Object line) { System.out.println(line); }
	
	public static void main(String[] args) {
		char[][] board = 
				{{'5','3','.','.','7','.','.','.','.'}
				,{'6','.','.','1','9','5','.','.','.'}
				,{'.','9','8','.','.','.','.','6','.'}
				,{'8','.','.','.','6','.','.','.','3'}
				,{'4','.','.','8','.','3','.','.','1'}
				,{'7','.','.','.','2','.','.','.','6'}
				,{'.','6','.','.','.','.','2','8','.'}
				,{'.','.','.','4','1','9','.','.','5'}
				,{'.','.','.','.','8','.','.','7','9'}};
		char[][] board2 = 
				{{'.','.','.','.','5','.','.','1','.'}
				,{'.','4','.','3','.','.','.','.','.'}
				,{'.','.','.','.','.','3','.','.','1'}
				,{'8','.','.','.','.','.','.','2','.'}
				,{'.','.','2','.','7','.','.','.','.'}
				,{'.','1','5','.','.','.','.','.','.'}
				,{'.','.','.','.','.','2','.','.','.'}
				,{'.','2','.','9','.','.','.','.','.'}
				,{'.','.','4','.','.','.','.','.','.'}};
		p(isValidSudoku(board));
		p(isValidSudoku(board2));
		p(isValidSudokuImp(board));
		p(isValidSudokuImp(board2));
	}
	private static boolean isValidSudoku(char[][] board) {
		if(board.length < 9 || board == null) return false;
		for(int i = 0; i < board.length; i++) {
			char[] col = new char[board[i].length];
			for(int j = 0; j < board[0].length; j++) {
				col[j] = board[j][i];
				if(i % 3 == 0 && j % 3 == 0) {
					if(!checkSubCube(i, j, board)) return false;
				}
			}
			if(!validOneToNine(board[i])) return false;	// row
			if(!validOneToNine(col)) return false;			// column
		}
		return true;
	}
	private static boolean checkSubCube(int i, int j, char[][] board) {
		char[] cube = new char[board[0].length];
		int counter = 0;
		for(int x = i; x < i + 3; x++)
			for(int y = j; y < j + 3; y++)
				cube[counter++] = board[x][y];
		
		if(!validOneToNine(cube)) return false;
		return true;
	}
	private static boolean validOneToNine(char[] rowInput) {
		int[] row = cToInt(rowInput);
		boolean[] allowed = new boolean[row.length];
		for(int each : row) {
			if(each == 0) continue;
			if(allowed[each - 1]) return false;
			allowed[each - 1] = true;
		}
//		p("true;");
		return true;
	}
	private static int[] cToInt(char[] x) { 
		int[] result = new int[x.length]; 
		for(int i = 0; i < x.length; i++)
			if(Character.getNumericValue(x[i]) >= 0)
				result[i] = Character.getNumericValue(x[i]);
		return result;
	}
	/** Improved approach - easier to understand;
	 * 	But space suffers.
	 * 
	 *  Essentially we are throwing every iteration of result into a hash-set and 
	 *  checking if what we are to throw next has already been included in the previous
	 *  pile. if so return false;
	 **/
	public static boolean isValidSudokuImp(char[][] board) {
        HashSet<String> visited = new HashSet<>();
        
        for(int i=0; i < 9; i++){
            for(int j=0; j< 9; j++){
                if(board[i][j] != '.'){
	                //Check whether HashSet contains duplicate elements in row and column 
	                if(visited.contains("row" + i + board[i][j]) || visited.contains("col" + j + board[i][j]) ){
	                    return false;
	                }
	                visited.add("row" + i + board[i][j]);
	                visited.add("col" + j + board[i][j]);
	                
	                //Check whether Box contains duplicate elements in it
	                if(visited.contains("box"+ (i/3) + (j/3) + board[i][j])){
	                    return false;
	                }
	                visited.add("box"+ (i/3) + (j/3) + board[i][j]);
                }
            }
        }
        p(visited);
        return true;
    }
}



