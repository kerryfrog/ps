import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] size = new int[N];
        int[] cost = new int[N];
        int totalCost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            size[i] = Integer.parseInt(st.nextToken());

        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        int[][] dp = new int [N+1][totalCost+1];
        for (int i = cost[0]; i < totalCost+1; i++) {
            dp[0][i] = size[0];
        }

        for (int i = 1; i <N; i++) {
            for (int j =0; j < totalCost+1; j++) {
                if(j >= cost[i]){
                    dp[i][j] = Math.max(dp[i-1][j-cost[i]] + size[i],  dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j <totalCost+1; j++) {
//                System.out.printf("%s ", dp[i][j]);
//            }
//            System.out.println("");
//        }

        for (int i = 0; i < totalCost+1; i++) {
            if (dp[N - 1][i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}