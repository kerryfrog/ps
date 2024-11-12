import java.io.*;
import java.util.*;
import java.awt.Point;
public class Main {

  static int N,E, a, b;
  static int[] distance;
  static ArrayList<Point>[] graph;
  public static void find(int target){
    boolean[] visited = new boolean[N + 1];
    distance = new int[N + 1];

    for(int i=0; i<=N; i++){
      distance[i] = 8000000;
    }
    distance[target] = 0;
    for(int i=0; i< graph[target].size(); i++){
      Point now = graph[target].get(i);
      distance[now.x] = now.y;
    }
    visited[target] = true;

    for(int j=0; j < N-1; j++){
      int minValue = 8000000;
      int minIdx =-1;
      for(int i=1; i<=N ; i++) {
        if (!visited[i] && distance[i] < minValue) {
          minIdx = i;
          minValue = distance[i];
        }
      }
      if (minIdx == -1){
        break;
      }
      for(int i=0; i< graph[minIdx].size(); i++){
        Point now = graph[minIdx].get(i);
        distance[now.x] = Math.min(distance[now.x], distance[minIdx] + now.y);
      }
      visited[minIdx] = true;
    }

  }


  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    E = Integer.parseInt(st.nextToken());

    graph = new ArrayList[N+1];

    for(int i=0; i<=N ; i++){
      graph[i] = new ArrayList<Point>();
    }

    for(int i=0; i<E; i++){
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      graph[start].add(new Point(end, cost));
      graph[end].add(new Point(start, cost));
    }
    st = new StringTokenizer(br.readLine());
    a = Integer.parseInt(st.nextToken());
    b = Integer.parseInt(st.nextToken());

    find(1);
    int start2a = distance[a];
    int start2b = distance[b];

    find(a);
    int a2b = distance[b];
    int a2end = distance[N];

    find(b);
    int b2a = distance[a];
    int b2end = distance[N];

    int answer1 = start2a + a2b + b2end;
    int answer2 = start2b + b2a + a2end;

    int ans = Math.min(answer1, answer2);
    if (ans >= 8000000) {
      System.out.println(-1);
    } else {
      System.out.println(ans);
    }
  }
}