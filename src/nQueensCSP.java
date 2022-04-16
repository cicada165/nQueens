import java.util.Scanner;

public class nQueensCSP {
	
	//Method to print the solution
	static void printBoard(int[][] arr) {
		int size = arr.length;
		
		for(int i = 0; i <= size * 2; i++) {
			if(i % 2 == 0) {
				for(int c = 0; c < size; c++) {
					if(c == size - 1) {
						System.out.print("-----" + "\n");
					}else {
						System.out.print("----");
						}
				}
			}
			
			if(i % 2 == 1) {
				System.out.print("|");
				for(int j = 0; j < size; j++) {
					if(j == size - 1) {
						System.out.print(" " + arr[(i - 1) / 2][j] + " |" + "\n");
					}else {
						System.out.print(" " + arr[(i - 1) / 2][j] + " |");
					}
				}
			}
		}
	}
	
	//Constraint
	static boolean placeable(int[][] arr, int ci, int cj) {
		int size = arr.length;
		int t = cj;
		
		//Check left 
		for(int j = cj - 1; j >= 0; j--) {
			if(arr[ci][j] != 0) {
				return false;
			}
		}
		
		//Check upper left
		for(int i = ci; i >= 0; i--) {
			
			if(i - 1 >= 0 && t - 1 >= 0) {
				if(arr[i - 1][t - 1] != 0) {
					return false;
				}
			}
			t = t - 1;
		}
		
		t = cj;
		
		//Check lower left
		for(int i = ci; i <= size; i++) {
			
			if(i + 1 < size && t - 1 >= 0) {
				if(arr[i + 1][t - 1] != 0) {
					return false;
					}
			}
					
			t = t - 1;			
		}
		
		return true;
	}
	
	static boolean BackTracking(int[][] arr, int col) {
		
		int size = arr.length;
		
		if(col >= size) {
			return true;
		}
		
		for(int i = 0; i < size; i++) {
			if(placeable(arr,i,col) == true) {
				arr[i][col] = 1;
			
			if(BackTracking(arr, col + 1) == true) {
				return true;
			}
			
			arr[i][col] = 0;
			
			}
		}
		
		return false;
	}
	
	
	
	//Driver method
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter an integer n:");
		int n = scan.nextInt();
		
		int[][] board = new int[n][n];
		
		if(BackTracking(board,0) == false){
			System.out.print("No Solution");
		}else{
			printBoard(board);
		}
		scan.close();
	}
}
