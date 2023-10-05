import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] distance;
    static boolean[][] isVisited;

    public static void main(String[] args) throws IOException {
        class Node implements Comparable<Node>{
            public int x, y;
            public int cost;

            Node(int x, int y, int cost) {
                this.x = x;
                this.y = y;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node node) {
                return this.cost - node.cost;
            }

        }

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; ; i++) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            board = new int[N][N];
            distance = new int[N][N];
            isVisited = new boolean[N][N];
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                    distance[j][k] = 3000;
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0, board[0][0]));

            while (!pq.isEmpty()) {
                Node a = pq.poll();

                for (int j = 0; j < 4; j++) {
                    int newX = a.x + dx[j];
                    int newY = a.y + dy[j];

                    if ( newX >= 0 && newY >= 0 && newX < N && newY < N && !isVisited[newX][newY]) {
                        if(distance[newX][newY] > a.cost +board[newX][newY]){
                            isVisited[newX][newY] = true;
                            distance[newX][newY] = Math.min(distance[newX][newY], a.cost + board[newX][newY]);
                            pq.add(new Node(newX, newY, distance[newX][newY]));
                        }
                    }
                }
            }
            sb.append("Problem ").append(i).append(": ").append(distance[N-1][N-1]).append("\n");

        }
        System.out.println(sb);
    }
}