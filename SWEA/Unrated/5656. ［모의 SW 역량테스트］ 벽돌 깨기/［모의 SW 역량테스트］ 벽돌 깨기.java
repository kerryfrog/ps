import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int T, N, W, H;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static int [][] bomb(int a, int[][] board) {
        int x = 0;
        for (int i = 0; i < H; i++) {
            if (board[i][a] != 0) {
                x = i;
                break;
            }
        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, a));
        isVisited = new boolean[H][W];
        isVisited[x][a] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int power = board[now.x][now.y];
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < power; j++) {
                    int newX = now.x + dx[i] * j;
                    int newY = now.y + dy[i] * j;
                    if (newX >= 0 && newY >= 0 && newY < W && newX < H && !isVisited[newX][newY]) {
                        if (board[newX][newY] != 0 && board[newX][newY] != 1)
                            q.add(new Point(newX, newY));
                        isVisited[newX][newY] = true;
                    }
                }
            }
        }

        int[][] board2 = new int[H][W];

        for (int i = 0; i < W; i++) {
            int h = H - 1;
            for (int j = H - 1; j >= 0; j--) {
                if (!isVisited[j][i]) {
                    board2[h][i] = board[j][i];
                    h--;
                }
            }
        }
        return board2;
    }

    static int answer = Integer.MAX_VALUE;


    public static int count(int[][] board) {
        int cnt =0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(board[i][j] != 0) cnt++;
            }
        }
        return cnt;
    }

    public static void dfs(int depth, int[][] board) {
        if (depth == N) {
            answer = Math.min(answer, count(board));
            return;
        }
        for (int i = 0; i < W; i++) {
            int[][] nextBoard = bomb(i, board);
            dfs(depth+1,nextBoard);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            answer = Integer.MAX_VALUE;
            board = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, board);
            sb.append("#").append(tc).append(" ").append(answer).append("\n");

        }
        System.out.println(sb);
    }
}