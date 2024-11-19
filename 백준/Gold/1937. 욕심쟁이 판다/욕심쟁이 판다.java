import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static int[][] board;
  static int[][] dp;

  static int [] dx = {-1, 0, 1, 0};
  static int [] dy = {0, 1, 0, -1};


  public static int dfs(int depth, int x, int y) {

    if (dp[x][y] > 0){
      return dp[x][y];
    }

    int tmp =0;
    for (int i = 0; i <4; i++) {
      int nextX = x + dx[i];
      int nextY = y + dy[i];
      if(nextX>=0 && nextX < N && nextY >= 0 && nextY <N && board[x][y] < board[nextX][nextY]){
        tmp = Math.max(tmp, dfs(depth + 1, nextX, nextY));
      }
    }
    dp[x][y] = tmp + 1;
    return dp[x][y];
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());
    board = new int[N][N];
    dp = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        dfs(1, i, j);
      }
    }
    int answer =0;
    for(int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {

        answer = Math.max(dp[i][j], answer);
      }
    }

    System.out.println(answer);
  }
}