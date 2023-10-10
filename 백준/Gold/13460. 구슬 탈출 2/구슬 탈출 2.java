import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static char[][] board;
//	 N : 세로 , M : 가로 
	static int N, M, answer =11;
	static Point red, blue;

	public static void changePoint(char[][] target ,char color, int x, int y) {
		if (color == 'R') {
			target[red.x][red.y] = '.';
			target[x][y] = 'R';
			red = new Point(x, y);
		} else {
			target[blue.x][blue.y] = '.';
			target[x][y] = 'B';
			blue = new Point(x, y);
		}

	}
	// 0:불가능  1: 이동 완료 2: 빨간공 나와서 종료 3: 이동 안함 
	public static int right(char [][] target) {
		// 먼저 움직일 공 선택

		int moveCnt = 0;
		boolean in = false;
		Point first = red;
		char color1 = 'R';
		Point second = blue;
		char color2 = 'B';
		
		if (blue.x == red.x && blue.y > red.y) {
			first = blue;
			color1 = 'B';
			second = red;
			color2 = 'R';
		}

		for (int i = first.y + 1; i <M ; i++) {
			moveCnt++;
			if (target[first.x][i] == '#' || target[first.x][i] == color2) {
				moveCnt--;
				changePoint(target, color1, first.x, i -1);
				break;
			}
			if (target[first.x][i] == 'O') {
				if (color1 == 'B')
					return 0;
				else {
					in = true;
					target[first.x][first.y] = '.';
					break;
				}
					
			}
		}
		for (int i = second.y + 1; i < M; i++) {
			moveCnt++;
			if (target[second.x][i] == '#' || target[second.x][i] == color1) {
				moveCnt--;
				changePoint(target, color2, second.x, i -1);
				break;
			}
			if (target[second.x][i] == 'O') {
				if (color2 == 'B')
					return 0;
				else
					return 2;
			}
		}
		
		if(moveCnt <=0) return 3;
		if(in) return 2;
		return 1;
	}

	// 0:불가능 1: 이동 완료 2: 빨간공 나와서 종료
	public static int left(char [][] target) {
		// 먼저 움직일 공 선택
		int moveCnt =0;
		boolean in = false;
		Point first = red;
		char color1 = 'R';
		Point second = blue;
		char color2 = 'B';

		if (blue.x == red.x && blue.y < red.y) {
			first = blue;
			color1 = 'B';
			second = red;
			color2 = 'R';
		}

		for (int i = first.y - 1; i >= 0; i--) {
			moveCnt++;
			if (target[first.x][i] == '#' || target[first.x][i] == color2) {
				moveCnt--;
				changePoint(target, color1, first.x, i + 1);
				break;
			}
			if (board[first.x][i] == 'O') {
				if (color1 == 'B')
					return 0;
				else {
					in = true;
					target[first.x][first.y] = '.';
					break;
				}
			}
		}
		for (int i = second.y - 1; i >= 0; i--) {
			moveCnt++;
			if (target[second.x][i] == '#' || target[second.x][i] == color1) {
				moveCnt--;
				changePoint(target,color2, second.x, i + 1);
				break;
			}
			if (target[second.x][i] == 'O') {
				if (color2 == 'B')
					return 0;
				else
					return 2;
			}
		}
		if(moveCnt <=0) return 3;
		if(in) return 2;
		return 1;
	}

	// 0:불가능 1: 이동 완료 2: 빨간공 나와서 종료
	public static int down(char [][] target) {
		// 먼저 움직일 공 선택
		int moveCnt =0;
		boolean in = false;
		Point first = red;
		char color1 = 'R';
		Point second = blue;
		char color2 = 'B';

		if (blue.y == red.y && blue.x > red.x) {
			first = blue;
			color1 = 'B';
			second = red;
			color2 = 'R';
		}

		for (int i = first.x + 1; i <N; i++) {
			moveCnt++;
			if (target[i][first.y] == '#' || target[i][first.y] == color2) {
				moveCnt--;
				changePoint(target,color1, i-1, first.y);
				break;
			}
			if (target[i][first.y] == 'O') {
				if (color1 == 'B')
					return 0;
				else {
					in = true;
					target[first.x][first.y] = '.';
					break;
				}
			}
		}
		for (int i = second.x +1; i <N ; i++) {
			moveCnt++;
			if (target[i][second.y] == '#' || target[i][second.y] == color1) {
				moveCnt--;
				changePoint(target,color2, i-1, second.y);
				break;
			}
			if (target[i][second.y] == 'O') {
				if (color2 == 'B')
					return 0;
				else
					return 2;
			}
		}
		if(moveCnt <=0) return 3;
		if(in) return 2;
		return 1;
	}
	
	
	public static int up(char [][] target) {
		// 먼저 움직일 공 선택
		int moveCnt = 0;
		boolean in = false;
		Point first = red;
		char color1 = 'R';
		Point second = blue;
		char color2 = 'B';

		if (blue.y == red.y && blue.x < red.x) {
			first = blue;
			color1 = 'B';
			second = red;
			color2 = 'R';
		}

		for (int i = first.x -1; i >=0; i--) {
			moveCnt++;
			if (target[i][first.y] == '#' || target[i][first.y] == color2) {
				moveCnt--;
				changePoint(target, color1, i+1, first.y);
				break;
			}
			if (target[i][first.y] == 'O') {
				if (color1 == 'B')
					return 0;
				else {
					in = true;
					target[first.x][first.y] = '.';
					break;
				}
			}
		}
		for (int i = second.x -1; i >=0 ; i--) {
			moveCnt++;
			if (target[i][second.y] == '#' || target[i][second.y] == color1) {
				moveCnt--;
				changePoint(target, color2, i+1, second.y);
				break;
			}
			if (target[i][second.y] == 'O') {
				if (color2 == 'B')
					return 0;
				else
					return 2;
			}
		}
		if(moveCnt <= 0) return 3;
		if(in) return 2;
		return 1;
	}
	public static void print(char [][] a) {
		for (char[] cs : a) {
			System.out.println(Arrays.toString(cs));
		}
		System.out.println();
	}
	
	public static void copyBoard(char[][] newBoard , char[][] before) {
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < M; j++) {
				newBoard[i][j]= before[i][j];
				if(newBoard[i][j] == 'B') blue = new Point(i,j);
				if(newBoard[i][j] == 'R') red = new Point(i,j);
			}
		}
	}
	
	public static int move(int direction,  char[][] target) {
		// 0 1 2 3 상하좌우
		int result = 0; 
		if(direction ==0) result = up(target);
		else if(direction == 1) result= down(target);
		else if(direction ==2) result = left(target);
		else if (direction ==3) result =right(target);
		return result;
	}
	
	public static boolean checkBeforeMove(int before, int next) {
		if(before == 0 && next == 1) return true;
		else if (before ==1 && next == 0) return true;
		else if(before==3 && next ==2) return true;
		else if (before == 2 && next ==3) return true;
		else return false;
	}
	
	public static void DFS(int depth, int beforeMove) {
		if(depth == 10) {
			return;
		}
		if(depth >= answer) return;
//		System.out.println(depth+  " before move "+ beforeMove);
//		print(board);
		char [][] target = new char[N][M];
		char [][] before = board;
		for (int i = 0; i <4; i++) {
			if(checkBeforeMove(beforeMove, i)) continue;
			copyBoard(target, before);
			int result = move(i,target);
//			System.out.println("depth ="+ depth +" i="+i +" result :" +  result );
			if(result == 2 ) {
				answer = Math.min(answer, depth+1);
				return;
			} else if (result == 0 || result == 3) continue;
			board = target;
			DFS(depth+1, i);
			board = before;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {

				board[i][j] = line.charAt(j);
				if (board[i][j] == 'R')
					red = new Point(i, j);
				if (board[i][j] == 'B')
					blue = new Point(i, j);
			}
		}
		DFS(0, -1);
		if(answer == 11) answer=-1;
		System.out.println(answer);
	}
}