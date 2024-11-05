import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, L;

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }

        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int before = map[i][0];
            int beforeLen = 1;
            int type = 0;
            boolean isAvailable = true;
            for (int j = 1; j < N; j++) {
                if (type == 0) {
                    if (map[i][j] == before) {
                        beforeLen++;
                    } else if (map[i][j] - before == 1 && beforeLen >= L) {
                        before = map[i][j];
                        beforeLen = 1;
                    } else if (map[i][j] - before > 1 || (map[i][j] - before == 1 && beforeLen < L)) {
                        isAvailable = false;
                        break;
                    } else {
                        type = 1;
                        beforeLen = 0;
                    }
                }
                if (type == 1) {
                    if (map[i][j] == before - 1) {
                        beforeLen += 1;
                    } else {
                        type = 0;
                        isAvailable = false;
                        beforeLen = 0;
                        before = map[i][j];
                        break;
                    }
                    if (beforeLen >= L) {
                        type = 0;
                        before = map[i][j];
                        beforeLen = 0;
                    }
                }
            }
            if (type == 1 && beforeLen < L) {
                continue;
            }
            if (isAvailable) {
//                System.out.println("answer++ i=" + i);
                answer++;
            }
        }

        for (int i = 0; i < N; i++) {
            int before = map[0][i];
            int beforeLen = 1;
            int type = 0;
            boolean isAvailable = true;
            for (int j = 1; j < N; j++) {
                if (type == 0) {
                    if (map[j][i] == before) {
                        beforeLen++;
                    } else if (map[j][i] - before == 1 && beforeLen >= L) {
                        before = map[j][i];
                        beforeLen = 1;
                    } else if (map[j][i] - before > 1 || (map[j][i] - before == 1 && beforeLen < L)) {
                        isAvailable = false;
                        break;
                    } else {
                        type = 1;
                        beforeLen = 0;
                    }
                }
                if (type == 1) {
                    if (map[j][i] == before - 1) {
                        beforeLen += 1;
                    } else {
                        type = 0;
                        isAvailable = false;
                        beforeLen = 0;
                        before = map[j][i];
                        break;
                    }
                    if (beforeLen >= L) {
                        type = 0;
                        before = map[j][i];
                        beforeLen = 0;
                    }
                }
            }
            if (type == 1 && beforeLen < L) {
                continue;
            }
            if (isAvailable) {
//                System.out.println(" chapter 2 answer++ i=" + i);
                answer++;
            }
        }

        System.out.println(answer);
    }

}