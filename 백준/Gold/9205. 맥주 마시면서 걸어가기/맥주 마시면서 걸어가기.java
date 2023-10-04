import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] cordi = new int[N + 2][2];
            int[][] dp = new int[N + 2][N + 2];


            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                cordi[i][0] = Integer.parseInt(st.nextToken());
                cordi[i][1] = Integer.parseInt(st.nextToken());
            }


            for (int i = 0; i < N + 2; i++) {
                for (int j = i; j < N + 2; j++) {
                    if(Math.abs(cordi[i][0] - cordi[j][0]) + Math.abs(cordi[i][1] - cordi[j][1]) <= 1000){
                        // 가능
                        dp[j][i] = 1;
                        dp[i][j] = 1;
                    } else{
                        //불가
                        dp[j][i] = 0;
                        dp[i][j] = 0;
                    }
                }
            }

            for (int k = 1; k < N + 1; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        if(dp[i][j] == 1) continue;
                        else if(dp[i][k] + dp[k][j] == 2) dp[i][j] = 1;
                    }
                }
            }

            if(dp[0][N + 1] == 1){
                sb.append("happy");
            }else{
                sb.append("sad");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}