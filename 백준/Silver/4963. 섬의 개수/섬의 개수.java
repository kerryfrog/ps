import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    private static void BFS(int x, int y) {
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] tmp = {x, y};
        queue.add(tmp);
        isVisited[x][y] = true;
        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            for (int i = 0; i < 8; i++) {
                int newX = now[0] + dx[i];
                int newY = now[1] + dy[i];
                if (newX >= 0 && newY >= 0 && newX < h && newY < w && board[newX][newY] == 1 && !isVisited[newX][newY]) {
                    Integer[] next = {newX, newY};
                    queue.add(next);
                    isVisited[newX][newY] = true;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;

            board = new int[h][w];
            isVisited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == 1 && !isVisited[i][j]) {
//                        System.out.println(i+" "+j);
                        BFS(i, j);
                        answer++;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}