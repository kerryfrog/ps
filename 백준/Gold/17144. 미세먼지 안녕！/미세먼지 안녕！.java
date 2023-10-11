import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T ;
	static int [][] board;
	static int [] dx = {-1, 0, 1, 0};
	static int [] dy = {0, 1, 0 , -1};
	
	static int [] upperDx = {0, -1, 0, 1};
	static int [] upperDy = {1, 0, -1 , 0};
	
	static int [] lowerDx = {0, 1, 0, -1};
	static int [] lowerDy = {1, 0, -1 , 0};
	
	static Point machine;
	
	public static void run() {
		int [][] nextBoard = new int [R][C];
		
		// 
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(board[i][j] > 0 ) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int newX = i + dx[k];
						int newY = j + dy[k];
						if(newX>=0 && newY >= 0 && newX < R && newY <C && board[newX][newY] != -1) {
							cnt += board[i][j] /5;
							nextBoard[newX][newY] += board[i][j] /5;
						}
					}
					nextBoard[i][j] +=  board[i][j] - cnt ;
				}
			}
		}
		
		nextBoard[machine.x][machine.y]= -1;
		nextBoard[machine.x-1][machine.y]= -1;
		board = nextBoard;
		
//		System.out.println("확산 후");
//		print(board);
		// 2. 공기 청정
		Point now = new Point(machine.x -1, machine.y );
		int dir = 0;
		int beforeDust =0;
		while(!(now.x == machine.x-2 && now.y == machine.y)) {
			int newX = now.x + upperDx[dir];
			int newY = now.y + upperDy[dir];
			
			if(newX >= 0 && newY >= machine.y && newX <= machine.x-1 && newY <C) {
				int tmp = board[newX][newY];
				board[newX][newY] = beforeDust;
				beforeDust = tmp;
				now = new Point(newX, newY);
//				print(board);
			}else {
				dir++;
			}
		}
		// 공기청정 하부 
		now = new Point(machine.x, machine.y );
		dir = 0;
		beforeDust =0;
		while(!(now.x == machine.x+1 && now.y == machine.y)) {
			int newX = now.x + lowerDx[dir];
			int newY = now.y + lowerDy[dir];
			
			if(newX >= machine.x && newY >= machine.y && newX < R && newY <C) {
				int tmp = board[newX][newY];
				board[newX][newY] = beforeDust;
				beforeDust = tmp;
				now = new Point(newX, newY);
			}else {
				dir++;
			}
		}
//		print(board);
	}
	
	public static void print(int [][] b) {
		for (int[] is : b) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int [R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == -1) {
					machine = new Point(i,j);
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			run();
		}
		
		int answer =0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += board[i][j]; 
			}
		}
		System.out.println(answer+2);
		
	}

}