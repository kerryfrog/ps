import java.io.*;
import java.util.*;

public class Main {

  static int[] dx = {-1, 0, 1 ,0};
  static int[] dy = {0, 1, 0, -1};

  static char[][] board;
  static int H,W;

  static int answer = -1;

  public static class Node implements Comparable<Node> {
    int x, y;
    int dist;
    Node(int x , int y , int dist){
      this.x = x;
      this.y =y;
      this.dist = dist;
    }

    @Override
    public int compareTo(Node n){
      return this.dist - n.dist;
    }

  }

  public static int find(int x, int y){
    boolean [][]visited = new boolean[H][W];
    PriorityQueue<Node> pq = new PriorityQueue<>();

    for(int i=0; i<4; i++){
      int nx = x + dx[i];
      int ny = y + dy[i];

      if( nx>=0 && nx <H && ny>=0 && ny <W && board[nx][ny] == 'L'){
        pq.add(new Node(nx, ny, 1));
      }
    }
    visited[x][y] = true;
    while (!pq.isEmpty()) {
      Node n = pq.poll();

      if(visited[n.x][n.y])
        continue;
      if (n.dist > answer)
        answer = n.dist;
      visited[n.x][n.y]  =true;
      for(int i=0; i<4; i++){
        int nx = n.x + dx[i];
        int ny = n.y + dy[i];

        if( nx>=0 && nx <H && ny>=0 && ny <W && board[nx][ny] == 'L'){
          pq.add(new Node(nx, ny, n.dist+1));
        }
      }
    }
    return 0;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());

    board = new char[H][W];


    for(int i= 0; i<H; i++){
      String now = br.readLine();
      for (int j=0; j<W; j++){
        board[i][j] = now.charAt(j);
      }
    }

    for(int i= 0; i<H; i++){
      for (int j=0; j<W; j++){
        if(board[i][j] == 'L'){
          find(i, j);
        }
      }
    }
    System.out.println(answer);
  }
}