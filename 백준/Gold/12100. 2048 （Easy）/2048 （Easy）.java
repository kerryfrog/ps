import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.zip.InflaterInputStream;

public class Main {
	
	static int N, answer= 0;
	static int [][] board;
	
	public static int[][] left() {
		int [][] nextBoard = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			// 한줄씩 합침
			int beforeBlock =0;
			int nextBoardIdx= 0;
			for (int j = 0; j < N; j++) {
				if(board[i][j] != 0) {
					if(board[i][j] != beforeBlock) {
						nextBoard[i][nextBoardIdx++] = board[i][j];
						beforeBlock = board[i][j];
					}else {
						nextBoard[i][nextBoardIdx-1] += nextBoard[i][nextBoardIdx-1]; 
						beforeBlock =0;
					}
				}
			}

		}
		return nextBoard;
		
	}

	public static  int[][] right() {
		int [][] nextBoard = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			// 한줄씩 합침
			int beforeBlock =0;
			int nextBoardIdx=  N-1;
			for (int j = N-1; j >= 0; j--) {
				if(board[i][j] != 0) {
					if(board[i][j] != beforeBlock) {
						nextBoard[i][nextBoardIdx--] = board[i][j];
						beforeBlock = board[i][j];
					}else {
						nextBoard[i][nextBoardIdx+1] += nextBoard[i][nextBoardIdx+1]; 
						beforeBlock = 0;
					}
				}
			}

		}
		return nextBoard;
		
	}
	public static  int[][]up() {
		int [][] nextBoard = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			// 한 열씩 합침
			int beforeBlock =0;
			int nextBoardIdx= 0;
			for (int j = 0; j < N; j++) {
				if(board[j][i] != 0) {
					if(board[j][i] != beforeBlock) {
						nextBoard[nextBoardIdx++][i] = board[j][i];
						beforeBlock = board[j][i];
					}else {
						nextBoard[nextBoardIdx-1][i] += nextBoard[nextBoardIdx-1][i]; 
						beforeBlock =0;
					}
				}
			}
		}
		return nextBoard;
	}
	
	public static  int[][] down() {
		int [][] nextBoard = new int [N][N];
		
		for (int i = 0; i < N; i++) {
			// 한 열씩 합침
			int beforeBlock =0;
			int nextBoardIdx= N-1;
			for (int j = N-1; j >=0 ; j--) {
				if(board[j][i] != 0) {
					if(board[j][i] != beforeBlock) {
						nextBoard[nextBoardIdx--][i] = board[j][i];
						beforeBlock = board[j][i];
					}else {
						nextBoard[nextBoardIdx+1][i] += nextBoard[nextBoardIdx+1][i]; 
						beforeBlock =0;
					}
				}
			}
		}
		return nextBoard;
		
	}
	
	public static void print() {
		for (int[] is : board) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}
	
	public static int findMax() {
		int max =0;
		for (int[] is : board) {
			for (int i : is) {
				max = Math.max(max, i);
			}
		}
		return max;
	}
	
	public static  int[][] mapMove(int a) {
		if(a==0) return up();
		else if(a==1) return down();
		else if(a==2) return left();
		else if(a==3) return right();
		return null;
	}
	
	public static void dfs(int depth) {
		if(depth == 5) {
			answer = Math.max(answer, findMax());
			return;
		}
		
		for(int i=0; i<4; i++) {
			int [][] beforeBoard = board;
			board = mapMove(i);
			dfs(depth+1);
			board = beforeBoard;
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N =Integer.parseInt(br.readLine());
		board = new int [N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j]= Integer.parseInt(st.nextToken());
			}
		}
//		board =left();
//		print();
//		board = up();
//		print();
//		board= up();
//		print();
//		
		dfs(0);
		
		System.out.println(answer);
	}
}