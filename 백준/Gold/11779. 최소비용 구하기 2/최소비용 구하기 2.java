import java.awt.Point;
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String []args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int n, m;
    int start, end ;
    n = Integer.parseInt(br.readLine());
    m = Integer.parseInt(br.readLine());
    int[][] graph = new int[n + 1][n + 1];
    boolean [] visited = new boolean [n+1];
    int [] dp = new int [n+1];
    String [] path = new String[n+1];
    int [] cnt = new int[n+1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        if (i==j)
          graph[i][j] = 0;
        else
          graph[i][j] = 1000000001;
      }
    }

    for(int i=0; i<m ; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      if (a==b)
        continue;
      int cost = Integer.parseInt(st.nextToken());
      graph[a][b] = Math.min(cost, graph[a][b] );
    }
    st = new StringTokenizer(br.readLine());

    start = Integer.parseInt(st.nextToken());
    end = Integer.parseInt(st.nextToken());

    for(int i= 0; i<= n ; i++){
      path[i] = start + " " + i;
      dp[i] = 1000000001;
      cnt[i] = 2;
    }
    cnt[start] = 1;
    path[start] = start +"";
//    다익스트라 시작
    for(int i=1; i<=n; i++){
      dp[i] = graph[start][i];
    }
    visited[start] = true;

    for(int i = 1; i<n; i++) {
      int minVal = Integer.MAX_VALUE;
      int minIdx = 0;
      for (int j = 1; j <= n; j++) {
        if (!visited[j] && minVal > dp[j]) {
          minVal = dp[j];
          minIdx = j;
        }
      }
      visited[minIdx] = true;
      for (int j = 1; j <= n; j++) {
        if(j==minIdx)
          continue;
        if (dp[j] > dp[minIdx] + graph[minIdx][j] ) {
          dp[j] = dp[minIdx] + graph[minIdx][j];
          path[j] = path[minIdx]  + " " + j;
          cnt[j] = cnt[minIdx] + 1;
        }
      }
    }
    System.out.println(dp[end]);
    System.out.println(cnt[end]);
    System.out.println(path[end]);
  }
}