import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] products = new int[N + 1][2];
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            products[i][0] = w;
            products[i][1] = v;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j] = -1;
            }
        }

        int answer = 0;

        if (products[1][0] <= K) {
            dp[1][products[1][0]] = products[1][1];
            answer = products[1][1];
        }
//        System.out.println(Arrays.toString(dp[1]));
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (j >= products[i][0] && dp[i - 1][j - products[i][0]] != -1) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - products[i][0]] + products[i][1]);
                    answer = Math.max(dp[i][j], answer);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
//            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(answer);
    }
}