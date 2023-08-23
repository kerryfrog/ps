import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] isVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Edge> edgeList = new ArrayList<>();

    static int[] parents;

    private static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "Edge from " + from + " to " + to + " weight " + weight;
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap == bp) return false;
        parents[ap] = bp;
        return true;
    }

    // count 는 섬의 번호
    private static void DFS(int x, int y, int count) {
        board[x][y] = count;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (newX >= 0 && newY >= 0 && newX < N && newY < M && board[newX][newY] == 1 && !isVisited[newX][newY]) {
                isVisited[newX][newY] = true;
                board[newX][newY] = count;
                DFS(newX, newY, count);
            }
        }
    }

    public static void findBridge() {
        // 가로 탐색
        for (int i = 0; i < N; i++) {
            int start = 0, end = 0, length = 0;
            for (int j = 0; j < M; j++, length++) {
                if (board[i][j] != 0 && start == 0) {
                    // 초기 start
                    start = board[i][j];
                    length = 0;
                } else if (board[i][j] == start) {
                    // 섬이 가로로 커서 start 옮겨야 할 때
                    start = board[i][j];
                    length = 0;
                } else if (board[i][j] != 0 && start != 0) {
                    // 다음 섬 발견
                    if (length > 2) {
                        Edge e = new Edge(start, board[i][j], length - 1);
                        edgeList.add(e);
                    }
                    start = board[i][j];
                    length = 0;
                }
            }
        }

        // 세로 탐색
        for (int i = 0; i < M; i++) {
            int start = 0, end = 0, length = 0;
            for (int j = 0; j < N; j++, length++) {
                if (board[j][i] != 0 && start == 0) {
                    // 초기 start
                    start = board[j][i];
                    length = 0;
                } else if (board[j][i] == start) {
                    // 섬이 세로로 커서 start 옮겨야 할 때
                    start = board[j][i];
                    length = 0;
                } else if (board[j][i] != 0 && start != 0) {
                    // 다음 섬 발견
                    if (length > 2) {
                        Edge e = new Edge(start, board[j][i], length - 1);
                        edgeList.add(e);
                    }
                    start = board[j][i];
                    length = 0;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        isVisited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1. 보드에 섬 번호 세팅
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !isVisited[i][j]) {
                    DFS(i, j, num++);
                }

            }
        }
        int islandCount = num - 1;

//        for (int[] a :
//                board) {
//            System.out.println(Arrays.toString(a));
//        }
        // 간선리스트 만들기
        findBridge();

        // mst 만들기
//        for (Edge e : edgeList
//        ) {
//            System.out.println(e.toString());
//        }
        Collections.sort(edgeList);

        parents = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parents[i] = i;
        }

        int result = 0;
        int count = 0;
        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
//                System.out.println(edge.toString());
                result += edge.weight;
                if (++count == islandCount - 1) break;
            }
        }
        if (result == 0 || count != islandCount - 1) System.out.println(-1);
        else System.out.println(result);
    }
}