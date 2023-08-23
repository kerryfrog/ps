import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static ArrayList<Integer>[] adj;
    static boolean[] isVisited;
    static int answer = 0;
    static int answserDepth = 0;

    private static void bfs(int now) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(now, 0));
        while (!queue.isEmpty()) {
            boolean isLeaf = true;
            Point p = queue.poll();
            for (Integer a : adj[p.x]) {
                if (!isVisited[a]) {
                    isVisited[a] = true;
                    queue.add(new Point(a, p.y+1));
                    isLeaf = false;
                }
            }
            if (isLeaf) {
                if (p.y > answserDepth) {
                    answer = p.x;
                    answserDepth = p.y;
                } else if (p.y == answserDepth && p.x > answer) {
                    answer = p.x;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        for (int tc = 1; tc <= 10; tc++) {
            isVisited = new boolean[101];
            adj = new ArrayList[101];
            answer = 0;
            answserDepth = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 101; i++) {
                adj[i] = new ArrayList<>();
            }
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a].add(b);
            }
            bfs(start);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

}