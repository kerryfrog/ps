import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cctv;
    static int zeroCount =0;
    static int [][] cctvPos;
    static int[][][] board;
    static boolean[][][] isVisitied;


    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    //   언세이프가 가장 큰거 골라야함
    static int unsafe = 0;


    private static int getRotate(int target) {
        switch (target) {
            case 1:
                return 4;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 4;
            case 5:
                return 1;
        }
        return 1;
    }

    // 상 우 하 좌
    private static int[] getDirections(int target, int rotation) {
//        System.out.println("getDirections " + target + " " + rotation);
        if (target == 1) {
            int[] dir = new int[1];
            switch (rotation) {
                case 0:
                    dir[0] = 0;
                    break;
                case 1:
                    dir[0] = 1;
                    break;
                case 2:
                    dir[0] = 2;
                    break;
                case 3:
                    dir[0] = 3;
                    break;
            }
            return dir;
        } else if (target == 2) {
            int[] dir = new int[2];
            switch (rotation) {
                case 0:
                    dir[0] = 1;
                    dir[1] = 3;
                    break;
                case 1:
                    dir[0] = 0;
                    dir[1] = 2;
                    break;
            }
            return dir;
        } else if (target == 3) {
            // 그림 예시에서 시계방향
            int[] dir = new int[2];
            switch (rotation) {
                case 0:
                    dir[0] = 0;
                    dir[1] = 1;
                    break;
                case 1:
                    dir[0] = 1;
                    dir[1] = 2;
                    break;
                case 2:
                    dir[0] = 2;
                    dir[1] = 3;
                    break;
                case 3:
                    dir[0] = 3;
                    dir[1] = 0;
                    break;
            }
            return dir;
        } else if (target == 4) {
            int[] dir = new int[3];
            switch (rotation) {
                case 0:
                    dir[0] = 3;
                    dir[1] = 0;
                    dir[2] = 1;
                    break;
                case 1:
                    dir[0] = 0;
                    dir[1] = 1;
                    dir[2] = 2;
                    break;
                case 2:
                    dir[0] = 1;
                    dir[1] = 2;
                    dir[2] = 3;
                    break;
                case 3:
                    dir[0] = 2;
                    dir[1] = 3;
                    dir[2] = 0;
                    break;
            }
            return dir;
        } else if (target == 5) {
            int[] dir = {0, 1, 2, 3};
            return dir;
        }
        int[] dir = {-1};
        return dir;
    }

    private static void dfs(int depth, int nowUnsafe) {
        if(depth == cctv){
            if(nowUnsafe > unsafe) unsafe = nowUnsafe;
            return;
        }
        int [] now = cctvPos[depth];
        int r = getRotate(board[now[0]][now[1]][0]);

        for (int i = 0; i < r; i++) {
            if (!isVisitied[now[0]][now[1]][i]) {
                isVisitied[now[0]][now[1]][i] = true;
                board[now[0]][now[1]][1] = i;

                // 돌리고 그 위치에서 unsafe 확인
                int newUnsafe = findUnSafe(depth);
//                System.out.println("now depth = "+ depth+ " unsafe =" + newUnsafe);
//                if(newUnsafe > unsafe) return;

                dfs(depth+1, newUnsafe);
                isVisitied[now[0]][now[1]][i] = false;
            }
        }
    }

    private static int findUnSafe(int nowCctv) {
        boolean[][] visited = new boolean[N][M];
        int unsafe = 0;
        for (int i = 0; i <= nowCctv ; i++) {
            int[] now = cctvPos[i];
            int[] directions = getDirections(board[now[0]][now[1]][0], board[now[0]][now[1]][1]);
            for (int k = 0; k <directions.length; k++) {
                int newX = now[0];
                int newY = now[1];
                for (int l = 0; l < 8; l++) {
                    newX = newX + dx[directions[k]];
                    newY = newY + dy[directions[k]];
                    if (newX >= 0 && newY >= 0 && newX < N && newY < M && board[newX][newY][0] != 6) {
                        if(!visited[newX][newY] && board[newX][newY][0] == 0) unsafe++;
                        visited[newX][newY] = true;
                    }
                    else break;
                }
            }
        }
        return unsafe;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M][2];
        isVisitied = new boolean[N][M][4];
        cctvPos = new int[8][2];
        cctv = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
                if(board[i][j][0] == 0) {
                    zeroCount ++;
                    continue;
                }
                if (!(board[i][j][0] == 6)){
                    cctvPos[cctv][0] = i;
                    cctvPos[cctv][1] = j;
                    cctv++;
                }
            }
        }
        dfs(0, 0);
//        System.out.println(unsafe);
        System.out.println(zeroCount - unsafe);
    }
}